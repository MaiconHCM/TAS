package br.edu.materdei.tas.flex.aspect;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.flex.entity.ExcecaoProdutoEntity;
import br.edu.materdei.tas.flex.entity.PromocaoProdutoEntity;
import br.edu.materdei.tas.flex.entity.RelatoProdutoEntity;
import br.edu.materdei.tas.flex.entity.VendaFlexEntity;
import br.edu.materdei.tas.flex.entity.VendedorEntity;
import br.edu.materdei.tas.flex.service.ExcecaoProdutoService;
import br.edu.materdei.tas.flex.service.PromocaoProdutoService;
import br.edu.materdei.tas.flex.service.RelatoProdutoService;
import br.edu.materdei.tas.flex.service.VendaFlexService;
import br.edu.materdei.tas.flex.service.VendedorService;
import br.edu.materdei.tas.venda.entity.ClienteEntity;
import br.edu.materdei.tas.venda.entity.ItemPedidoEntity;
import br.edu.materdei.tas.venda.entity.VendaEntity;
import br.edu.materdei.tas.venda.service.VendaService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class VendaFlexAspect {

   @Autowired
   private VendaFlexService service;

   //VENDA DO PRODUTO
   @Autowired
   private VendaService vendaService;

   //Vendedor
   @Autowired
   private VendedorService VendedorService;

   //RELATO PRODUTO
   @Autowired
   private RelatoProdutoService RelatoProdutoService;

   //PROMOÇÃO PRODUTO
   @Autowired
   private PromocaoProdutoService PromocaoProdutoService;

   //EXCEÇÃO PRODUTO
   @Autowired
   private ExcecaoProdutoService ExcecaoProdutoService;

   @Around("execution( * br.edu.materdei.tas.venda.service.VendaService.save(..))")
   public void salvarVenda(ProceedingJoinPoint joinPoint) {
      try {

         Object[] args = joinPoint.getArgs();

         //Define a venda de origem
         VendaEntity venda = (VendaEntity) args[0];

         //Cria entidade de venda flex
         VendaFlexEntity vendaFlex = new VendaFlexEntity();

         //Obtem cliente
         ClienteEntity cliente = venda.getPedido().getCliente();

         //Obtem vendedor
         VendedorEntity vendedor = null;
         for (VendedorEntity i : VendedorService.findAll()) {
            //Verifica se data do flex desse vendedor já iniciou
            if (i.getDtinicioflex() != null) {
               if (i.getDtinicioflex().before(new Date())) {
                  for (ClienteEntity c : i.getClientes()) {
                     if (c.getId() == cliente.getId()) {
                        vendedor = i;
                        break;
                     }
                  }
               }
            }
         }
         boolean temLimite = true;
         //Faz For na lista de produtos no relato
         double vlrflex = 0;
         //Listagem de relatos
         List<RelatoProdutoEntity> arrayRelatoProdutos = new ArrayList<RelatoProdutoEntity>();
         for (ItemPedidoEntity item : venda.getPedido().getItens()) {
            RelatoProdutoEntity relatoProduto = null;
            ExcecaoProdutoEntity excecao = null;
            PromocaoProdutoEntity promocao = null;

            // Caso a venda já existe, o id é diferente de 0
            if (venda.getId() != null) {
               // Aqui busca se esse produto já foi vendido nessa venda anteriormente.
               relatoProduto = RelatoProdutoService.findByVendaidAndProduto_id(venda.getId(), item.getProduto().getId());
            }

            //Caso o produto nunca tenha sido vendido
            if (relatoProduto == null) {

               // Caso o produto não foi vendido nessa venda, cria do zero
               relatoProduto = new RelatoProdutoEntity();

               //Busca se existe promoção para esse produto nessa época
               promocao = PromocaoProdutoService.findByProductInSale(item.getProduto().getId());
               //Busca se exceção nessa época para esse produto
               excecao = ExcecaoProdutoService.findByProductInException(item.getProduto().getId());

               //Define promoção
               relatoProduto.setPromocao(promocao);
               //Define exceção
               relatoProduto.setExcecao(excecao);
               // Define o produto
               relatoProduto.setProduto(item.getProduto());
               //Define o valor dessa época (durante a venda)
               relatoProduto.setVlrunit(item.getProduto().getPreco());

            } else {
               // Caso esse produto já foi vendido nessa venda, carrega os atributos
               excecao = relatoProduto.getExcecao();
               promocao = relatoProduto.getPromocao();
            }

            //Calcula o valor desse flex
            if (promocao != null) {
               vlrflex += (item.getVlrunit() - promocao.getValor()) * item.getQtdade();
            } else if (excecao == null) {
               vlrflex += (item.getVlrunit() - item.getProduto().getPreco()) * item.getQtdade();
            }

            // Adiciona esse relato no array;
            arrayRelatoProdutos.add(relatoProduto);
         }

         vendaFlex.setVenda(venda);
         vendaFlex.setVlrflex(vlrflex);
         vendaFlex.setRelatoProdutos(arrayRelatoProdutos);
         if (vendedor != null) {
            vendaFlex.setVendedor(vendedor);
            double novoFlex = vendedor.getSaldoflex() + vlrflex;

            if (novoFlex < -500) {
               temLimite = false;
            } else {
               vendedor.setSaldoflex(novoFlex);
            }
         }

         if (temLimite) {
            joinPoint.proceed();

            for (RelatoProdutoEntity item : arrayRelatoProdutos) {
               //Salva o relato para utilizar como auditoria
               RelatoProdutoService.save(item);
            }
            this.service.save(vendaFlex);

            if (vendedor != null) {
               this.VendedorService.save(vendedor);
            }
         }

      } catch (Throwable ex) {
         Logger.getLogger(VendaFlexAspect.class.getName()).log(Level.SEVERE, null, ex);
      }

   }

   @Around("execution( * br.edu.materdei.tas.venda.service.VendaService.delete(..))")
   public void estornarVenda(ProceedingJoinPoint joinPoint) {
      Object[] args = joinPoint.getArgs();
      Integer id = (Integer) args[0];

      VendaEntity venda = null;

      VendaFlexEntity vendaflex = null;
      try {

         venda = this.vendaService.findById(id);

         vendaflex = this.service.findByVendaId(venda.getId());

         if (vendaflex != null) {
            List<RelatoProdutoEntity> arrayRelatoProdutos = RelatoProdutoService.findAllByVendaFlexid(vendaflex.getId());

            arrayRelatoProdutos.forEach(item -> {
               try {
                  RelatoProdutoService.delete(item.getId());
               } catch (ResourceNotFoundException ex) {
                  Logger.getLogger(VendaFlexAspect.class.getName()).log(Level.SEVERE, null, ex);
               }
            });
            this.service.delete(vendaflex.getId());
         }
      } catch (ResourceNotFoundException ex) {
         Logger.getLogger(VendaFlexAspect.class.getName()).log(Level.SEVERE, null, ex);
      }
      //procede com exclusão
      try {
         joinPoint.proceed();
      } catch (Throwable ex) {
         Logger.getLogger(VendaFlexAspect.class.getName()).log(Level.SEVERE, null, ex);
      }

      if (vendaflex != null) {
         VendedorEntity vendedor;
         try {
            vendedor = this.VendedorService.findById(vendaflex.getVendedor().getId());

            double estorno = vendedor.getSaldoflex() - vendaflex.getVlrflex();
            vendedor.setSaldoflex(estorno);
            this.VendedorService.save(vendedor);
         } catch (ResourceNotFoundException ex) {
            Logger.getLogger(VendaFlexAspect.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }
}

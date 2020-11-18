package br.edu.materdei.tas.flex.aspect;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.flex.entity.VendaFlexEntity;
import br.edu.materdei.tas.flex.entity.VendedorEntity;
import br.edu.materdei.tas.flex.service.ExcecaoProdutoService;
import br.edu.materdei.tas.flex.service.PromocaoProdutoService;
import br.edu.materdei.tas.flex.service.RelatoProdutoService;
import br.edu.materdei.tas.flex.service.VendaFlexService;
import br.edu.materdei.tas.flex.service.VendedorService;
import br.edu.materdei.tas.venda.entity.ClienteEntity;
import br.edu.materdei.tas.venda.entity.VendaEntity;
import br.edu.materdei.tas.venda.service.VendaService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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

   @AfterReturning(pointcut = "execution( * br.edu.materdei.tas.venda.service.VendaService.save(..))")
   public void salvarVenda(JoinPoint joinPoint) {
      Object[] args = joinPoint.getArgs();
      VendaEntity venda = (VendaEntity) args[0];
      
      //Cria entidade de venda flex
      VendaFlexEntity vendaFlex = new VendaFlexEntity();
      
      //Set no venda de origem
      vendaFlex.setVenda(venda);
      
      //Obtem cliente
      ClienteEntity cliente=venda.getPedido().getCliente();
      
      //Obtem vendedor
      VendedorService.findAll().forEach(vendedor -> {
         if(vendedor.getClientes().contains(cliente)){
            vendaFlex.setVendedor(vendedor);
         }
      });
      
      double vlrflex=0;
      venda.getPedido().getItens().forEach(item -> {
         int id=item.getId();
         //Alimenta o estoque com os dados dos itens da nota
         /*
         vendaFlex.setProduto(item.getProduto());
         vendaFlex.setQtdade(item.getQtdade() * -1);
         vendaFlex.setHistorico("Movto de entrada originado pela venda " + venda.getCodigo());
         */
         //Grava a movimentação de estoque
         
      });
      this.service.save(vendaFlex);
   }

   @Around("execution( * br.edu.materdei.tas.venda.service.VendaService.delete(..))")
   public void estornarVenda(ProceedingJoinPoint joinPoint) {
      Object[] args = joinPoint.getArgs();
      Integer id = (Integer) args[0];

      VendaEntity venda = null;

      try {
         venda = this.vendaService.findById(id);
      } catch (ResourceNotFoundException ex) {
         Logger.getLogger(VendaFlexAspect.class.getName()).log(Level.SEVERE, null, ex);
      }

      //procede com exclusão
      try {
         joinPoint.proceed();
      } catch (Throwable ex) {
         Logger.getLogger(VendaFlexAspect.class.getName()).log(Level.SEVERE, null, ex);
      }
      if (venda != null) {
         String codigo = venda.getCodigo();
         venda.getPedido().getItens().forEach(item -> {
            VendaFlexEntity vendaFlex = new VendaFlexEntity();

            //Alimenta o estoque com os dados dos itens da nota
            vendaFlex.setProduto(item.getProduto());
            vendaFlex.setQtdade(item.getQtdade());
            vendaFlex.setHistorico("Movto de saída originado pelo extorno da venda " + codigo);

            //Grava a movimentação de estoque
            this.service.save(vendaFlex);
         });
      }

   }
}

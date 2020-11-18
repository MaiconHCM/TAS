package br.edu.materdei.tas.estoque.aspect;

import br.edu.materdei.tas.compra.entity.CompraEntity;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.estoque.entity.EstoqueEntity;
import br.edu.materdei.tas.estoque.service.EstoqueService;
import br.edu.materdei.tas.venda.entity.VendaEntity;
import br.edu.materdei.tas.venda.service.VendaService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EstoqueAspect {

   @Autowired
   private EstoqueService service;

   @Autowired
   private EntityManager entityManager;

   @Autowired
   private VendaService vendaService;

   @AfterReturning(pointcut = "execution( * br.edu.materdei.tas.compra.service.CompraService.save(..))")
   public void salvarCompra(JoinPoint joinPoint) {
      Object[] args = joinPoint.getArgs();
      CompraEntity compra = (CompraEntity) args[0];

      compra.getItens().forEach(item -> {
         EstoqueEntity estoque = new EstoqueEntity();

         //Alimenta o estoque com os dados dos itens da nota
         estoque.setProduto(item.getProduto());
         estoque.setQtdade(item.getQtdade());
         estoque.setHistorico("Movto de entrada originado pela compra " + compra.getCodigo());

         //Grava a movimentação de estoque
         this.service.save(estoque);
      });
   }

   @AfterReturning(pointcut = "execution( * br.edu.materdei.tas.venda.service.VendaService.save(..))")
   public void salvarVenda(JoinPoint joinPoint) {
      Object[] args = joinPoint.getArgs();
      VendaEntity venda = (VendaEntity) args[0];

      venda.getPedido().getItens().forEach(item -> {
         EstoqueEntity estoque = new EstoqueEntity();

         //Alimenta o estoque com os dados dos itens da nota
         estoque.setProduto(item.getProduto());
         estoque.setQtdade(item.getQtdade() * -1);
         estoque.setHistorico("Movto de entrada originado pela venda " + venda.getCodigo());

         //Grava a movimentação de estoque
         this.service.save(estoque);
      });
   }

   @AfterReturning(pointcut = "execution( * br.edu.materdei.tas.compra.service.CompraService.delete(..))")
   public void excluirCompra(JoinPoint joinPoint) {
      Object[] args = joinPoint.getArgs();
      Integer id = (Integer) args[0];

      CompraEntity compra = this.entityManager.getReference(CompraEntity.class, id);

      compra.getItens().forEach(item -> {
         EstoqueEntity estoque = new EstoqueEntity();

         //Alimenta o estoque com os dados dos itens da nota
         estoque.setProduto(item.getProduto());
         estoque.setQtdade(item.getQtdade() * -1);
         estoque.setHistorico("Movto de saída originado pelo extorno da compra " + compra.getCodigo());

         //Grava a movimentação de estoque
         this.service.save(estoque);
      });

   }

   @Around("execution( * br.edu.materdei.tas.venda.service.VendaService.delete(..))")
   public void estornarVenda(ProceedingJoinPoint joinPoint) {
      Object[] args = joinPoint.getArgs();
      Integer id = (Integer) args[0];

      VendaEntity venda = null;

      try {
         venda = this.vendaService.findById(id);
      } catch (ResourceNotFoundException ex) {
         Logger.getLogger(EstoqueAspect.class.getName()).log(Level.SEVERE, null, ex);
      }

      //procede com exclusão
      try {
         joinPoint.proceed();
      } catch (Throwable ex) {
         Logger.getLogger(EstoqueAspect.class.getName()).log(Level.SEVERE, null, ex);
      }
      if (venda != null) {
         String codigo = venda.getCodigo();
         venda.getPedido().getItens().forEach(item -> {
            EstoqueEntity estoque = new EstoqueEntity();

            //Alimenta o estoque com os dados dos itens da nota
            estoque.setProduto(item.getProduto());
            estoque.setQtdade(item.getQtdade());
            estoque.setHistorico("Movto de saída originado pelo extorno da venda " + codigo);

            //Grava a movimentação de estoque
            this.service.save(estoque);
         });
      }

   }
}

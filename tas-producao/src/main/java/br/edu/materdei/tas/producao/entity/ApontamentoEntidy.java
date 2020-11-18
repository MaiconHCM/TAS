/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.materdei.tas.producao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Note
 */
@Entity
@Table(name = "apotamento")
public class ApontamentoEntidy {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   //muitos apontamentos para uma ordem de produção
   @ManyToOne
   @JoinColumn(nullable = false)
   private OrdemProducaoEntidy ordemProducaoEntidy;

   @Temporal(TemporalType.DATE)
   @Column(nullable = false)
   private Date dtapontamento;

   // mesma situação anterior não sei o tamanho da empresa, não consigo definir length
   @Column(nullable = false)
   private Integer qtdade;

   /**
    * @return the id
    */
   public Integer getId() {
      return id;
   }

   /**
    * @param id the id to set
    */
   public void setId(Integer id) {
      this.id = id;
   }

   /**
    * @return the ordemProducaoEntidy
    */
   public OrdemProducaoEntidy getOrdemProducaoEntidy() {
      return ordemProducaoEntidy;
   }

   /**
    * @param ordemProducaoEntidy the ordemProducaoEntidy to set
    */
   public void setOrdemProducaoEntidy(OrdemProducaoEntidy ordemProducaoEntidy) {
      this.ordemProducaoEntidy = ordemProducaoEntidy;
   }

   /**
    * @return the dtapontamento
    */
   public Date getDtapontamento() {
      return dtapontamento;
   }

   /**
    * @param dtapontamento the dtapontamento to set
    */
   public void setDtapontamento(Date dtapontamento) {
      this.dtapontamento = dtapontamento;
   }

   /**
    * @return the qtdade
    */
   public Integer getQtdade() {
      return qtdade;
   }

   /**
    * @param qtdade the qtdade to set
    */
   public void setQtdade(Integer qtdade) {
      this.qtdade = qtdade;
   }

}

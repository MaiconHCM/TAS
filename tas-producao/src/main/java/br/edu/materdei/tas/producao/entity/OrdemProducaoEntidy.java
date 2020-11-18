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
@Table(name = "ordemproducao")
public class OrdemProducaoEntidy {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   //Data abertura tem que ser obrigatorio porque tudo tem um inicio
   @Temporal(TemporalType.DATE)
   @Column(nullable = false)
   private Date dtabertura;
   
   //Não sei o tamanho da empresa, por isso não coloco tamanho maximo.
   @Column(nullable = false)
   private Integer qtdade;
   
   //muitas ordem de produção para um produtoAcabado
   @ManyToOne @JoinColumn(nullable = false)
   private ProdutoAcabadoEntidy produto;
   
   //Esse coloquei null porque tem coisas que demoram dias para terminar de ser processado.
   @Temporal(TemporalType.DATE)
   @Column(nullable = true)
   private Date dtfinalizado;

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
    * @return the dtabertura
    */
   public Date getDtabertura() {
      return dtabertura;
   }

   /**
    * @param dtabertura the dtabertura to set
    */
   public void setDtabertura(Date dtabertura) {
      this.dtabertura = dtabertura;
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

   /**
    * @return the produto
    */
   public ProdutoAcabadoEntidy getProduto() {
      return produto;
   }

   /**
    * @param produto the produto to set
    */
   public void setProduto(ProdutoAcabadoEntidy produto) {
      this.produto = produto;
   }

   /**
    * @return the dtfinalizado
    */
   public Date getDtfinalizado() {
      return dtfinalizado;
   }

   /**
    * @param dtfinalizado the dtfinalizado to set
    */
   public void setDtfinalizado(Date dtfinalizado) {
      this.dtfinalizado = dtfinalizado;
   }
   
}

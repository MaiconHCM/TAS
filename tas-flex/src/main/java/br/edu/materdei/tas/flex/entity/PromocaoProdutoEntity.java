/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.materdei.tas.flex.entity;

import br.edu.materdei.tas.core.entity.ProdutoEntity;
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

@Entity
@Table(name = "promocaoproduto")
public class PromocaoProdutoEntity {

   //identificador
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   //Valor do produto na promoção
   @Column(nullable = false)
   private Double valor;

   //O produto que está em promoção
   @ManyToOne
   @JoinColumn(nullable = false)
   private ProdutoEntity produto;

   //Data de inicio que ele entra em promoção
   @Temporal(TemporalType.DATE)
   @Column(nullable = false)
   private Date dtinicio;

   //data de fim, sendo obrigatorio (pois em teoria a promoção é temporaria)
   @Temporal(TemporalType.DATE)
   @Column(nullable = false)
   private Date dtfim;

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
    * @return the valor
    */
   public Double getValor() {
      return valor;
   }

   /**
    * @param valor the valor to set
    */
   public void setValor(Double valor) {
      this.valor = valor;
   }

   /**
    * @return the produto
    */
   public ProdutoEntity getProduto() {
      return produto;
   }

   /**
    * @param produto the produto to set
    */
   public void setProduto(ProdutoEntity produto) {
      this.produto = produto;
   }

   /**
    * @return the dtinicio
    */
   public Date getDtinicio() {
      return dtinicio;
   }

   /**
    * @param dtinicio the dtinicio to set
    */
   public void setDtinicio(Date dtinicio) {
      this.dtinicio = dtinicio;
   }

   /**
    * @return the dtfim
    */
   public Date getDtfim() {
      return dtfim;
   }

   /**
    * @param dtfim the dtfim to set
    */
   public void setDtfim(Date dtfim) {
      this.dtfim = dtfim;
   }
}

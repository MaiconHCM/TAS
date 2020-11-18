/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.materdei.tas.flex.entity;

import br.edu.materdei.tas.core.entity.ProdutoEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "relatoproduto")
public class RelatoProdutoEntity {

   //identificador
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   //Produto no qual está sendo feito o relato
   @ManyToOne
   @JoinColumn(nullable = false)
   private ProdutoEntity produto;

   //valor unitário do produto nesse periodo
   @Column(nullable = false)
   private Double vlrunit;

   //Produto em excecão? caso sim o valor do flex deve ser ignorado
   @ManyToOne
   @JoinColumn(nullable = true)
   private ExcecaoProdutoEntity excecao;

   //Produto em prmocao? caso sim o valor do flex deve ser calculado a partir dele
   @ManyToOne
   @JoinColumn(nullable = true)
   private PromocaoProdutoEntity promocao;

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
    * @return the vlrunit
    */
   public Double getVlrunit() {
      return vlrunit;
   }

   /**
    * @param vlrunit the vlrunit to set
    */
   public void setVlrunit(Double vlrunit) {
      this.vlrunit = vlrunit;
   }

   /**
    * @return the excecao
    */
   public ExcecaoProdutoEntity getExcecao() {
      return excecao;
   }

   /**
    * @param excecao the excecao to set
    */
   public void setExcecao(ExcecaoProdutoEntity excecao) {
      this.excecao = excecao;
   }

   /**
    * @return the promocao
    */
   public PromocaoProdutoEntity getPromocao() {
      return promocao;
   }

   /**
    * @param promocao the promocao to set
    */
   public void setPromocao(PromocaoProdutoEntity promocao) {
      this.promocao = promocao;
   }

}

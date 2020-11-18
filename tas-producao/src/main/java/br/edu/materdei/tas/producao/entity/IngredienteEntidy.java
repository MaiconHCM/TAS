/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.materdei.tas.producao.entity;

import br.edu.materdei.tas.core.entity.ProdutoEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Note
 */
@Entity
@Table(name = "ingrediente")
public class IngredienteEntidy {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   //Várias instancia de Ingrediente acabados pode ter um unico produto
   @ManyToOne
   @JoinColumn(nullable = false)
   private ProdutoEntity produto;

   //Não acho necessidade de colocar um width aqui. Não tem uma noção clara do tamanho da empresa e quanto ela gasta em produtos.
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

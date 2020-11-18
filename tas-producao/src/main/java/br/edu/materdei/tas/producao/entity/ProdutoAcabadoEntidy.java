/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.materdei.tas.producao.entity;

import br.edu.materdei.tas.core.entity.ProdutoEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Note
 */
@Entity
@Table(name = "produtoacabado")
public class ProdutoAcabadoEntidy {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   //Null false porque é ncessario um produto
   @ManyToOne @JoinColumn(nullable = false)
   private ProdutoEntity produto;
   
   //Um ProdutoAcabado vai ter vários Ingredientes, no mímimo 1 ingrediente é necessario
   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(nullable = false)
   private List<IngredienteEntidy> ingredientes;

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
    * @return the ingredientes
    */
   public List<IngredienteEntidy> getIngredientes() {
      return ingredientes;
   }

   /**
    * @param ingredientes the ingredientes to set
    */
   public void setIngredientes(List<IngredienteEntidy> ingredientes) {
      this.ingredientes = ingredientes;
   }
   
   
}

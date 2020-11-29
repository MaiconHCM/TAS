package br.edu.materdei.tas.flex.entity;

import br.edu.materdei.tas.venda.entity.VendaEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vendaflex")
public class VendaFlexEntity {

   //identificador
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   //Valor do flex gerado
   @Column(nullable = false)
   private Double vlrflex;

   //Venda de origem desse flex
   @OneToOne
   @JoinColumn(nullable = false)
   private VendaEntity venda;

   //Vendedor que recebeu o flex
   @ManyToOne
   @JoinColumn(nullable = true)
   private VendedorEntity vendedor;

   //relato do produto que foram vendidos
   @OneToMany
   @JoinColumn(nullable = true)
   private List<RelatoProdutoEntity> relatoprodutos;

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
    * @return the vlrflex
    */
   public Double getVlrflex() {
      return vlrflex;
   }

   /**
    * @param vlrflex the vlrflex to set
    */
   public void setVlrflex(Double vlrflex) {
      this.vlrflex = vlrflex;
   }

   /**
    * @return the venda
    */
   public VendaEntity getVenda() {
      return venda;
   }

   /**
    * @param venda the venda to set
    */
   public void setVenda(VendaEntity venda) {
      this.venda = venda;
   }

   /**
    * @return the vendedor
    */
   public VendedorEntity getVendedor() {
      return vendedor;
   }

   /**
    * @param vendedor the vendedor to set
    */
   public void setVendedor(VendedorEntity vendedor) {
      this.vendedor = vendedor;
   }

   /**
    * @return the relatoprodutos
    */
   public List<RelatoProdutoEntity> getrelatoprodutos() {
      return relatoprodutos;
   }

   /**
    * @param relatoprodutos the relatoprodutos to set
    */
   public void setRelatoProdutos(List<RelatoProdutoEntity> relatoprodutos) {
      this.relatoprodutos = relatoprodutos;
   }
}

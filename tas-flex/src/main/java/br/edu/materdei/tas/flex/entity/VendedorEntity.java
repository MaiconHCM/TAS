package br.edu.materdei.tas.flex.entity;

import br.edu.materdei.tas.core.entity.Pessoa;
import br.edu.materdei.tas.venda.entity.ClienteEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "vendedor")
public class VendedorEntity extends Pessoa {
   
   //identificador
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   //Define o cpf do vendedor
   @Column(length = 11, nullable = false)
   private String cpf;

   //Saldo flex (ou verba de desconto) atual do vendedor
   @Column(nullable = false)
   private Double saldoflex;

   //Lista de clientes desse vendedor
   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private List<ClienteEntity> clientes;

   //data de inicio, para começo do flex
   @Temporal(TemporalType.DATE)
   @Column(nullable = false)
   private Date dtinicioflex;

   /**
    * @return the cpf
    */
   public String getCpf() {
      return cpf;
   }

   /**
    * @param cpf the cpf to set
    */
   public void setCpf(String cpf) {
      this.cpf = cpf;
   }

   /**
    * @return the saldoflex
    */
   public Double getSaldoflex() {
      return saldoflex;
   }

   /**
    * @param saldoflex the saldoflex to set
    */
   public void setSaldoflex(Double saldoflex) {
      this.saldoflex = saldoflex;
   }

   /**
    * @return the clientes
    */
   public List<ClienteEntity> getClientes() {
      return clientes;
   }

   /**
    * @param clientes the clientes to set
    */
   public void setClientes(List<ClienteEntity> clientes) {
      this.clientes = clientes;
   }

   /**
    * @return the dtinicioflex
    */
   public Date getDtinicioflex() {
      return dtinicioflex;
   }

   /**
    * @param dtinicioflex the dtinicioflex to set
    */
   public void setDtinicioflex(Date dtinicioflex) {
      this.dtinicioflex = dtinicioflex;
   }

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
}

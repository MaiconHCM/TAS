import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ClienteEntity } from '../_services/cliente.service';

@Injectable({
  providedIn: 'root'
})
export class VendedorService {

  constructor(private http: HttpClient) { }

  /**
   * Fornece uma lista com TODOS os registros
   */
  public listar() {
    return this.http.get(environment.urlSRV + '/api/vendedores');
  }

  /**
   * Verifica se existe um ID no vendedor passado por parametro.
   * Se existir, significa que o vendedor deverá ser atualizado,
   * caso contrário, deverá ser incluído.
   * 
   * @param vendedor 
   */
  public salvar(vendedor: VendedorEntity) {
    if (vendedor.id) {
      return this.alterar(vendedor);
    } else {
      return this.adicionar(vendedor);
    }
  }

  /**
   * Exclui a cidade com o mesmo ID passado por parâmetro
   * 
   * @param id 
   */
  public excluir(id: number) {
    return this.http.delete(environment.urlSRV + '/api/vendedores/' + id);
  }

  /**
   * Adiciona um novo registro
   * 
   * @param vendedor 
   */
  private adicionar(vendedor: VendedorEntity) {
    return this.http.post(environment.urlSRV + '/api/vendedores', vendedor);
  }

  /**
   * Altera o registro com o ID passado por parametro na URI
   * @param vendedor 
   */
  private alterar(vendedor: VendedorEntity) {
    return this.http.put(environment.urlSRV + '/api/vendedores/' + vendedor.id, vendedor);
  }
}
export class Pessoa {
  id: number;
  codigo: string;
  nome: string;
  email: string;
  endereco: string;
  ativo: string;

  constructor() {
    this.ativo = 'S';
  }
}
export class VendedorEntity extends Pessoa {
  cpf: string;
  saldoflex: number;
  dtinicioflex: Date;
  clientes: ClienteEntity[];
  constructor(){
    super();
    this.clientes=[];
    this.saldoflex=0;
  }
}

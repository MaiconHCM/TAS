import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProdutoEntity } from '../_services/produto.service';

@Injectable({
  providedIn: 'root'
})
export class PromocaoProdutoService {

  constructor(private http: HttpClient) { }

  /**
   * Fornece uma lista com TODOS os registros
   */
  public listar() {
    return this.http.get(environment.urlSRV +'/api/promocaoprodutos');
  }

  /**
   * Verifica se existe um ID no promocaoproduto passado por parametro.
   * Se existir, significa que o promocaoproduto deverá ser atualizado,
   * caso contrário, deverá ser incluído.
   * 
   * @param promocaoproduto 
   */
  public salvar(promocaoproduto: PromocaoProdutoEntity) {
    if (promocaoproduto.id) {
      return this.alterar(promocaoproduto);
    } else {
      return this.adicionar(promocaoproduto);
    }
  }

  /**
   * Exclui a cidade com o mesmo ID passado por parâmetro
   * 
   * @param id 
   */
  public excluir(id: number) {
    return this.http.delete(environment.urlSRV +'/api/promocaoprodutos/'+ id);
  }
  
  /**
   * Adiciona um novo registro
   * 
   * @param promocaoproduto 
   */
  private adicionar(promocaoproduto: PromocaoProdutoEntity) {
    return this.http.post(environment.urlSRV +'/api/promocaoprodutos', promocaoproduto);
  }

  /**
   * Altera o registro com o ID passado por parametro na URI
   * @param promocaoproduto 
   */
  private alterar(promocaoproduto: PromocaoProdutoEntity) {
    return this.http.put(environment.urlSRV +'/api/promocaoprodutos/'+ promocaoproduto.id, promocaoproduto);
  }
}

export class PromocaoProdutoEntity {
  id: number;
  produto: ProdutoEntity;
  dtinicio: Date;
  dtfim:Date;
}

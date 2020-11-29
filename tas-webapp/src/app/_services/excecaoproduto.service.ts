import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProdutoEntity } from '../_services/produto.service';

@Injectable({
  providedIn: 'root'
})
export class ExcecaoProdutoService {

  constructor(private http: HttpClient) { }

  /**
   * Fornece uma lista com TODOS os registros
   */
  public listar() {
    return this.http.get(environment.urlSRV +'/api/excecaoprodutos');
  }

  /**
   * Verifica se existe um ID no excecaoproduto passado por parametro.
   * Se existir, significa que o excecaoproduto deverá ser atualizado,
   * caso contrário, deverá ser incluído.
   * 
   * @param excecaoproduto 
   */
  public salvar(excecaoproduto: ExcecaoProdutoEntity) {
    if (excecaoproduto.id) {
      return this.alterar(excecaoproduto);
    } else {
      return this.adicionar(excecaoproduto);
    }
  }

  /**
   * Exclui a cidade com o mesmo ID passado por parâmetro
   * 
   * @param id 
   */
  public excluir(id: number) {
    return this.http.delete(environment.urlSRV +'/api/excecaoprodutos/'+ id);
  }
  
  /**
   * Adiciona um novo registro
   * 
   * @param excecaoproduto 
   */
  private adicionar(excecaoproduto: ExcecaoProdutoEntity) {
    return this.http.post(environment.urlSRV +'/api/excecaoprodutos', excecaoproduto);
  }

  /**
   * Altera o registro com o ID passado por parametro na URI
   * @param excecaoproduto 
   */
  private alterar(excecaoproduto: ExcecaoProdutoEntity) {
    return this.http.put(environment.urlSRV +'/api/excecaoprodutos/'+ excecaoproduto.id, excecaoproduto);
  }
}

export class ExcecaoProdutoEntity {
  id: number;
  produto: ProdutoEntity;
  dtinicio: Date;
  dtfim:Date;
}

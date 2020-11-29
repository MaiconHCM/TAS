import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { VendedorEntity } from './vendedor.service';
import { PedidoEntity } from './pedido.service';
import { ProdutoEntity } from './produto.service';
import { ExcecaoProdutoEntity } from './excecaoproduto.service';
import { PromocaoProdutoEntity } from './promocaoproduto.service';

@Injectable({
  providedIn: 'root'
})
export class VendaFlexService {

  constructor(private http: HttpClient) { }

  /**
   * Fornece uma lista com TODOS os registros
   */
  public listar() {
    return this.http.get(environment.urlSRV + '/api/vendasflex');
  }

  /**
   * Verifica se existe um ID no vendaflex passado por parametro.
   * Se existir, significa que o vendaflex deverá ser atualizado,
   * caso contrário, deverá ser incluído.
   * 
   * @param vendaflex 
   */
  public salvar(vendaflex: VendaFlexEntity) {
    if (vendaflex.id) {
      return this.alterar(vendaflex);
    } else {
      return this.adicionar(vendaflex);
    }
  }

  /**
   * Exclui a cidade com o mesmo ID passado por parâmetro
   * 
   * @param id 
   */
  public excluir(id: number) {
    return this.http.delete(environment.urlSRV + '/api/vendasflex/' + id);
  }

  /**
   * Adiciona um novo registro
   * 
   * @param vendaflex 
   */
  private adicionar(vendaflex: VendaFlexEntity) {
    return this.http.post(environment.urlSRV + '/api/vendasflex', vendaflex);
  }

  /**
   * Altera o registro com o ID passado por parametro na URI
   * @param vendaflex 
   */
  private alterar(vendaflex: VendaFlexEntity) {
    return this.http.put(environment.urlSRV + '/api/vendasflex/' + vendaflex.id, vendaflex);
  }
}
export class VendaEntity {
  id: number;
  codigo: string;
  dtvenda: Date;
  pedido: PedidoEntity;
}
export class RelatoProdutoEntity {
  id: number;
  produto: ProdutoEntity;
  vlrunit: number;
  excecao: ExcecaoProdutoEntity;
  promocao: PromocaoProdutoEntity;
}
export class VendaFlexEntity {
  id: number;
  vlrflex: number;
  venda: VendaEntity;
  vendedor: VendedorEntity;
  relatoProdutos: RelatoProdutoEntity[];
}

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClienteComponent } from './cliente/cliente.component';
import { ExcecaoprodutoComponent } from './excecaoproduto/excecaoproduto.component';
import { GrupoComponent } from './grupo/grupo.component';
import { PedidoComponent } from './pedido/pedido.component';
import { ProdutoComponent } from './produto/produto.component';
import { PromocaoprodutoComponent } from './promocaoproduto/promocaoproduto.component';
import { VendaflexComponent } from './vendaflex/vendaflex.component';
import { VendedorComponent } from './vendedor/vendedor.component';

const routes: Routes = [
  { path: 'grupos', component: GrupoComponent },
  { path: 'produtos', component: ProdutoComponent },
  { path: 'clientes', component: ClienteComponent },
  { path: 'pedidos', component: PedidoComponent },
  { path: 'vendedores', component: VendedorComponent },
  { path: 'excecaoprodutos', component: ExcecaoprodutoComponent },
  { path: 'promocaoprodutos', component: PromocaoprodutoComponent },
  { path: 'vendasflex', component: VendaflexComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

package br.edu.materdei.tas.flex.repository;

import br.edu.materdei.tas.flex.entity.RelatoProdutoEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatoProdutoRepository extends JpaRepository<RelatoProdutoEntity, Integer> {
   @Query(value = "SELECT * FROM relatoproduto WHERE relatoprodutos_id=?1 AND produto_id=?2", nativeQuery = true)
   RelatoProdutoEntity findOneByVendaidAndProduto_id(int venda, int produto);
   
   @Query(value = "SELECT * FROM relatoproduto WHERE relatoprodutos_id=?1", nativeQuery = true)
   List<RelatoProdutoEntity> findAllByVendaFlexid(int venda);
}
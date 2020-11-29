package br.edu.materdei.tas.flex.repository;

import br.edu.materdei.tas.flex.entity.PromocaoProdutoEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocaoProdutoRepository extends JpaRepository<PromocaoProdutoEntity, Integer> {
   @Query(value = "SELECT * FROM promocaoproduto WHERE (NOW() BETWEEN dtinicio AND dtfim) AND produto_id=?1", nativeQuery = true)
   PromocaoProdutoEntity findByProductInSale(int id);
}

package br.edu.materdei.tas.flex.repository;

import br.edu.materdei.tas.flex.entity.ExcecaoProdutoEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcecaoProdutoRepository extends JpaRepository<ExcecaoProdutoEntity, Integer> {
   @Query(value = "SELECT * FROM excecaoproduto WHERE (NOW() BETWEEN dtinicio AND dtfim) AND produto_id=?1", nativeQuery = true)
   ExcecaoProdutoEntity findByProductInException(int id);
   
   
}

package br.edu.materdei.tas.flex.repository;

import br.edu.materdei.tas.flex.entity.PromocaoProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocaoProdutoRepository extends JpaRepository<PromocaoProdutoEntity, Integer>{
    
}

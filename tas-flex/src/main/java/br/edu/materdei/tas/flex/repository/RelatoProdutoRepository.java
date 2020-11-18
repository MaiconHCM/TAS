package br.edu.materdei.tas.flex.repository;

import br.edu.materdei.tas.flex.entity.RelatoProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatoProdutoRepository extends JpaRepository<RelatoProdutoEntity, Integer>{
    
}

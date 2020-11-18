package br.edu.materdei.tas.flex.repository;

import br.edu.materdei.tas.flex.entity.EstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueEntity, Integer>{
    
}

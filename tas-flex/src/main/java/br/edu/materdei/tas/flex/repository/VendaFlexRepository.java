package br.edu.materdei.tas.flex.repository;

import br.edu.materdei.tas.flex.entity.VendaFlexEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaFlexRepository extends JpaRepository<VendaFlexEntity, Integer>{
    
}

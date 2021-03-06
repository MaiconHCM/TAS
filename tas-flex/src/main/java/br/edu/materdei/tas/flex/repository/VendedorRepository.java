package br.edu.materdei.tas.flex.repository;

import br.edu.materdei.tas.flex.entity.VendedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<VendedorEntity, Integer>{
    
}

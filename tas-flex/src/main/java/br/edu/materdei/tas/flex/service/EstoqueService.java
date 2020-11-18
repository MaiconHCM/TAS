package br.edu.materdei.tas.flex.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.flex.entity.EstoqueEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.materdei.tas.flex.repository.EstoqueRepository;

@Service
public class EstoqueService implements IBaseService<EstoqueEntity> {

    @Autowired
    private EstoqueRepository repository;
    
    @Override
    public List<EstoqueEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public EstoqueEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow( 
                ()-> new ResourceNotFoundException(id));
    }

    @Override
    public EstoqueEntity save(EstoqueEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
    
}

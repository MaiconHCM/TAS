package br.edu.materdei.tas.flex.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.flex.entity.VendedorEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.materdei.tas.flex.repository.VendedorRepository;

@Service
public class VendedorService implements IBaseService<VendedorEntity> {

    @Autowired
    private VendedorRepository repository;
    
    @Override
    public List<VendedorEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public VendedorEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow( 
                ()-> new ResourceNotFoundException(id));
    }

    @Override
    public VendedorEntity save(VendedorEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
    
}

package br.edu.materdei.tas.flex.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.flex.entity.ExcecaoProdutoEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.materdei.tas.flex.repository.ExcecaoProdutoRepository;

@Service
public class ExcecaoProdutoService implements IBaseService<ExcecaoProdutoEntity> {

    @Autowired
    private ExcecaoProdutoRepository repository;
    
    @Override
    public List<ExcecaoProdutoEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public ExcecaoProdutoEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow( 
                ()-> new ResourceNotFoundException(id));
    }

    @Override
    public ExcecaoProdutoEntity save(ExcecaoProdutoEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
    
}

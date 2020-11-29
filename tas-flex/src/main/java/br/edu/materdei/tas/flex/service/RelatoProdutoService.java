package br.edu.materdei.tas.flex.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.flex.entity.RelatoProdutoEntity;
import br.edu.materdei.tas.flex.repository.RelatoProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatoProdutoService implements IBaseService<RelatoProdutoEntity> {

    @Autowired
    private RelatoProdutoRepository repository;
    
    @Override
    public List<RelatoProdutoEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public RelatoProdutoEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow( 
                ()-> new ResourceNotFoundException(id));
    }

    @Override
    public RelatoProdutoEntity save(RelatoProdutoEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
    public RelatoProdutoEntity findByVendaidAndProduto_id(int vendaid,int produto_id) throws ResourceNotFoundException {
        return repository.findOneByVendaidAndProduto_id(vendaid,produto_id);
    }
    public List<RelatoProdutoEntity> findAllByVendaFlexid(int vendaid) throws ResourceNotFoundException {
        return repository.findAllByVendaFlexid(vendaid);
    }
    
}

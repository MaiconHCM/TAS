package br.edu.materdei.tas.flex.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.flex.entity.PromocaoProdutoEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.materdei.tas.flex.repository.PromocaoProdutoRepository;

@Service
public class PromocaoProdutoService implements IBaseService<PromocaoProdutoEntity> {

   @Autowired
   private PromocaoProdutoRepository repository;

   @Override
   public List<PromocaoProdutoEntity> findAll() {
      return repository.findAll();
   }

   @Override
   public PromocaoProdutoEntity findById(Integer id) throws ResourceNotFoundException {
      return repository.findById(id).orElseThrow(
              () -> new ResourceNotFoundException(id));
   }

   @Override
   public PromocaoProdutoEntity save(PromocaoProdutoEntity entity) {
      return repository.saveAndFlush(entity);
   }

   @Override
   public void delete(Integer id) throws ResourceNotFoundException {
      repository.deleteById(id);
   }

   public PromocaoProdutoEntity findByProductInSale(int id) throws ResourceNotFoundException {
      return repository.findByProductInSale(id);
   }

}

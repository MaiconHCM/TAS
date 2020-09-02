package br.edu.materdei.tas.core.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import br.edu.materdei.tas.core.entity.ProdutoEntity;
import br.edu.materdei.tas.core.repository.ProdutoRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProdutoService implements IBaseService<ProdutoEntity> {

   @Autowired
   private ProdutoRepository repository;

   @Override
   @Transactional
   public List<ProdutoEntity> FindAll() {
      return repository.findAll();
   }

   @Override
   @Transactional
   public ProdutoEntity findById(Integer Id) throws ResourceNotFoundException {
      return repository.findById(Id).orElseThrow(() -> new ResourceNotFoundException(Id));
   }

   @Override
   @Transactional
   public ProdutoEntity save(ProdutoEntity entidy) {
      return repository.saveAndFlush(entidy);
   }

   @Override
   public void delete(Integer Id) throws ResourceNotFoundException {
      repository.deleteById(Id);
   }

}

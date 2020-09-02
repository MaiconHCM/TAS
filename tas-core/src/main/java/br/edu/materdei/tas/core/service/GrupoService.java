package br.edu.materdei.tas.core.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import br.edu.materdei.tas.core.entity.GrupoEntity;
import br.edu.materdei.tas.core.repository.GrupoRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GrupoService implements IBaseService<GrupoEntity> {

   @Autowired
   private GrupoRepository repository;

   @Override
   @Transactional
   public List<GrupoEntity> FindAll() {
      return repository.findAll();
   }

   @Override
   @Transactional
   public GrupoEntity findById(Integer Id) throws ResourceNotFoundException {
      return repository.findById(Id).orElseThrow(() -> new ResourceNotFoundException(Id));
   }

   @Override
   @Transactional
   public GrupoEntity save(GrupoEntity entidy) {
      return repository.saveAndFlush(entidy);
   }

   @Override
   public void delete(Integer Id) throws ResourceNotFoundException {
      repository.deleteById(Id);
   }

}

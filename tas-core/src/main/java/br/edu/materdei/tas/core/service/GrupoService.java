package br.edu.materdei.tas.core.service;

import br.edu.materdei.tas.core.service.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import br.edu.materdei.tas.core.entity.GrupoEntity;
import br.edu.materdei.tas.core.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GrupoService implements IBaseService<GrupoEntity> {
   
   @Autowired
   private GrupoRepository repository;
   
   @Override
   public List<GrupoEntity> FindAll() {
      return repository.findAll();
   }

   @Override
   public GrupoEntity findById(Integer Id) throws ResourceNotFoundException {
      return null;
   }

   @Override
   public GrupoEntity save(GrupoEntity entidy) {
      return null;
   }

   @Override
   public void delete(Integer Id) throws ResourceNotFoundException {
   }


}

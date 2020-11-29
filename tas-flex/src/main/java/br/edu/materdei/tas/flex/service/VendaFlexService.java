package br.edu.materdei.tas.flex.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.flex.entity.VendaFlexEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.materdei.tas.flex.repository.VendaFlexRepository;

@Service
public class VendaFlexService implements IBaseService<VendaFlexEntity> {

   @Autowired
   private VendaFlexRepository repository;

   @Override
   public List<VendaFlexEntity> findAll() {
      return repository.findAll();
   }

   @Override
   public VendaFlexEntity findById(Integer id) throws ResourceNotFoundException {
      return repository.findById(id).orElseThrow(
              () -> new ResourceNotFoundException(id));
   }

   @Override
   public VendaFlexEntity save(VendaFlexEntity entity) {
      return repository.saveAndFlush(entity);
   }

   @Override
   public void delete(Integer id) throws ResourceNotFoundException {
      repository.deleteById(id);
   }
   public VendaFlexEntity findByVendaId(Integer id){
      return repository.findOneByVendaid(id);
   }

}

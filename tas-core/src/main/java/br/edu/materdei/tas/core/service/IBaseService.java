package br.edu.materdei.tas.core.service;

import br.edu.materdei.tas.core.service.exception.ResourceNotFoundException;
import java.util.List;

public interface IBaseService<T> {

   List<T> FindAll();

   T findById(Integer Id) throws ResourceNotFoundException;
   
   T save(T entidy);
   
   void delete(Integer Id) throws ResourceNotFoundException;
}

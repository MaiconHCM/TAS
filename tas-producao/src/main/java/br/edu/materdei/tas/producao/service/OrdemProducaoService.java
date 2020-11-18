package br.edu.materdei.tas.producao.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.producao.entity.OrdemProducaoEntidy;
import br.edu.materdei.tas.producao.repository.OrdemProducaoRepository;
import br.edu.materdei.tas.core.service.IBaseService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Note
 */
@Service
public class OrdemProducaoService implements IBaseService<OrdemProducaoEntidy> {

   @Autowired
   private OrdemProducaoRepository repository;

   @Override
   @Transactional
   public List<OrdemProducaoEntidy> findAll() {
      return repository.findAll();
   }

   @Override
   @Transactional
   public OrdemProducaoEntidy findById(Integer id) throws ResourceNotFoundException {
      return repository.findById(id).orElseThrow(
              () -> new ResourceNotFoundException(id));
   }

   @Override
   @Transactional
   public OrdemProducaoEntidy save(OrdemProducaoEntidy entity) {
      return repository.saveAndFlush(entity);
   }

   @Override
   @Transactional
   public void delete(Integer id) throws ResourceNotFoundException {
      repository.deleteById(id);
   }
}

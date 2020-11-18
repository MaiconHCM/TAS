package br.edu.materdei.tas.producao.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.producao.entity.ProdutoAcabadoEntidy;
import br.edu.materdei.tas.producao.repository.ProdutoAcabadoRepository;
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
public class ProdutoAcabadoService implements IBaseService<ProdutoAcabadoEntidy> {

   @Autowired
   private ProdutoAcabadoRepository repository;

   @Override
   @Transactional
   public List<ProdutoAcabadoEntidy> findAll() {
      return repository.findAll();
   }

   @Override
   @Transactional
   public ProdutoAcabadoEntidy findById(Integer id) throws ResourceNotFoundException {
      return repository.findById(id).orElseThrow(
              () -> new ResourceNotFoundException(id));
   }

   @Override
   @Transactional
   public ProdutoAcabadoEntidy save(ProdutoAcabadoEntidy entity) {
      return repository.saveAndFlush(entity);
   }

   @Override
   @Transactional
   public void delete(Integer id) throws ResourceNotFoundException {
      repository.deleteById(id);
   }
}

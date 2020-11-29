package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.flex.entity.ExcecaoProdutoEntity;
import br.edu.materdei.tas.flex.entity.PromocaoProdutoEntity;
import br.edu.materdei.tas.flex.service.ExcecaoProdutoService;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcecaoProdutoController {

   @Autowired
   private ExcecaoProdutoService service;

   @GetMapping("excecaoprodutos")
   public ResponseEntity<List<PromocaoProdutoEntity>> findAll() {
      try {
         List<ExcecaoProdutoEntity> produtos = service.findAll();

         return new ResponseEntity(produtos, HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @PostMapping("excecaoprodutos")
   public ResponseEntity create(@RequestBody ExcecaoProdutoEntity produto) {
      try {

         this.service.save(produto);
         return new ResponseEntity(produto, HttpStatus.CREATED);

      } catch (Exception e) {
         return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @GetMapping("excecaoprodutos/{id}")
   public ResponseEntity findById(@PathVariable("id") Integer id) {
      try {
         ExcecaoProdutoEntity produto = this.service.findById(id);
         return new ResponseEntity(produto, HttpStatus.OK);
      } catch (ResourceNotFoundException e) {
         return new ResponseEntity(
                 new CustomErrorResponse("Não existe um produto com esse código"),
                 HttpStatus.NOT_FOUND
         );
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @PutMapping("excecaoprodutos/{id}")
   public ResponseEntity update(@PathVariable("id") Integer id,
           @RequestBody ExcecaoProdutoEntity produto) {
      try {
         ExcecaoProdutoEntity found = this.service.findById(id);

         produto.setId(found.getId());

         this.service.save(produto);

         return new ResponseEntity(produto, HttpStatus.OK);
      } catch (ResourceNotFoundException e) {
         return new ResponseEntity(
                 new CustomErrorResponse("Não existe um produto com esse código"),
                 HttpStatus.NOT_FOUND
         );
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @DeleteMapping("excecaoprodutos/{id}")
   public ResponseEntity delete(@PathVariable("id") Integer id) {
      try {
         ExcecaoProdutoEntity found = this.service.findById(id);

         this.service.delete(found.getId());

         return new ResponseEntity(HttpStatus.NO_CONTENT);
      } catch (ResourceNotFoundException e) {
         return new ResponseEntity(
                 new CustomErrorResponse("Não existe um produto com esse código"),
                 HttpStatus.NOT_FOUND
         );
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

}

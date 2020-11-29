package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.flex.service.VendedorService;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import br.edu.materdei.tas.flex.entity.VendedorEntity;
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
public class VendedorController {

   @Autowired
   private VendedorService service;

   @GetMapping("vendedores")
   public ResponseEntity<List<VendedorEntity>> findAll() {
      try {
         List<VendedorEntity> vendedores = service.findAll();

         return new ResponseEntity(vendedores, HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @PostMapping("vendedores")
   public ResponseEntity create(@RequestBody VendedorEntity vendedor) {
      try {

         this.service.save(vendedor);
         return new ResponseEntity(vendedor, HttpStatus.CREATED);

      } catch (Exception e) {
         return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @GetMapping("vendedores/{id}")
   public ResponseEntity findById(@PathVariable("id") Integer id) {
      try {
         VendedorEntity vendedor = this.service.findById(id);
         return new ResponseEntity(vendedor, HttpStatus.OK);
      } catch (ResourceNotFoundException e) {
         return new ResponseEntity(
                 new CustomErrorResponse("Não existe um vendedor com esse código"),
                 HttpStatus.NOT_FOUND
         );
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @PutMapping("vendedores/{id}")
   public ResponseEntity update(@PathVariable("id") Integer id,
           @RequestBody VendedorEntity vendedor) {
      try {
         VendedorEntity found = this.service.findById(id);

         vendedor.setId(found.getId());

         this.service.save(vendedor);

         return new ResponseEntity(vendedor, HttpStatus.OK);
      } catch (ResourceNotFoundException e) {
         return new ResponseEntity(
                 new CustomErrorResponse("Não existe um vendedor com esse código"),
                 HttpStatus.NOT_FOUND
         );
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @DeleteMapping("vendedores/{id}")
   public ResponseEntity delete(@PathVariable("id") Integer id) {
      try {
         VendedorEntity found = this.service.findById(id);

         this.service.delete(found.getId());

         return new ResponseEntity(HttpStatus.NO_CONTENT);
      } catch (ResourceNotFoundException e) {
         return new ResponseEntity(
                 new CustomErrorResponse("Não existe um vendedor com esse código"),
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

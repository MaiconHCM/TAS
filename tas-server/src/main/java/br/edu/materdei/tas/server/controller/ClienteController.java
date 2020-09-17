package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import br.edu.materdei.tas.venda.entity.ClienteEntity;
import br.edu.materdei.tas.venda.service.ClienteService;
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
public class ClienteController {

   @Autowired
   private ClienteService service;

   @GetMapping("clientes")
   public ResponseEntity<List<ClienteEntity>> findAll() {
      try {
         List<ClienteEntity> clientes = service.findAll();

         return new ResponseEntity(clientes, HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @PostMapping("clientes")
   public ResponseEntity create(@RequestBody ClienteEntity cliente) {
      try {

         this.service.save(cliente);
         return new ResponseEntity(cliente, HttpStatus.CREATED);

      } catch (Exception e) {
         return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @GetMapping("clientes/{id}")
   public ResponseEntity findById(@PathVariable("id") Integer id) {
      try {
         ClienteEntity cliente = this.service.findById(id);
         return new ResponseEntity(cliente, HttpStatus.OK);
      } catch (ResourceNotFoundException e) {
         return new ResponseEntity(
                 new CustomErrorResponse("Não existe um cliente com esse código"),
                 HttpStatus.NOT_FOUND
         );
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @PutMapping("clientes/{id}")
   public ResponseEntity update(@PathVariable("id") Integer id,
           @RequestBody ClienteEntity cliente) {
      try {
         ClienteEntity found = this.service.findById(id);

         cliente.setId(found.getId());

         this.service.save(cliente);

         return new ResponseEntity(cliente, HttpStatus.OK);
      } catch (ResourceNotFoundException e) {
         return new ResponseEntity(
                 new CustomErrorResponse("Não existe um cliente com esse código"),
                 HttpStatus.NOT_FOUND
         );
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @DeleteMapping("clientes/{id}")
   public ResponseEntity delete(@PathVariable("id") Integer id) {
      try {
         ClienteEntity found = this.service.findById(id);

         this.service.delete(found.getId());

         return new ResponseEntity(HttpStatus.NO_CONTENT);
      } catch (ResourceNotFoundException e) {
         return new ResponseEntity(
                 new CustomErrorResponse("Não existe um cliente com esse código"),
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

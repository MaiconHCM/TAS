package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.flex.service.VendaFlexService;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import br.edu.materdei.tas.flex.entity.VendaFlexEntity;
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
public class VendaFlexController {

   @Autowired
   private VendaFlexService service;

   @GetMapping("vendasflex")
   public ResponseEntity<List<VendaFlexEntity>> findAll() {
      try {
         List<VendaFlexEntity> vendasflex = service.findAll();

         return new ResponseEntity(vendasflex, HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @PostMapping("vendasflex")
   public ResponseEntity create(@RequestBody VendaFlexEntity venda) {
      try {

         this.service.save(venda);
         return new ResponseEntity(venda, HttpStatus.CREATED);

      } catch (Exception e) {
         return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @GetMapping("vendasflex/{id}")
   public ResponseEntity findById(@PathVariable("id") Integer id) {
      try {
         VendaFlexEntity venda = this.service.findById(id);
         return new ResponseEntity(venda, HttpStatus.OK);
      } catch (ResourceNotFoundException e) {
         return new ResponseEntity(
                 new CustomErrorResponse("Não existe um venda com esse código"),
                 HttpStatus.NOT_FOUND
         );
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @PutMapping("vendasflex/{id}")
   public ResponseEntity update(@PathVariable("id") Integer id,
           @RequestBody VendaFlexEntity venda) {
      try {
         VendaFlexEntity found = this.service.findById(id);

         venda.setId(found.getId());

         this.service.save(venda);

         return new ResponseEntity(venda, HttpStatus.OK);
      } catch (ResourceNotFoundException e) {
         return new ResponseEntity(
                 new CustomErrorResponse("Não existe um venda com esse código"),
                 HttpStatus.NOT_FOUND
         );
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @DeleteMapping("vendasflex/{id}")
   public ResponseEntity delete(@PathVariable("id") Integer id) {
      try {
         VendaFlexEntity found = this.service.findById(id);

         this.service.delete(found.getId());

         return new ResponseEntity(HttpStatus.NO_CONTENT);
      } catch (ResourceNotFoundException e) {
         return new ResponseEntity(
                 new CustomErrorResponse("Não existe um venda com esse código"),
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

package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import br.edu.materdei.tas.venda.entity.VendaEntity;
import br.edu.materdei.tas.venda.service.VendaService;
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
public class VendaController {

   @Autowired
   private VendaService service;

   @GetMapping("vendas")
   public ResponseEntity<List<VendaEntity>> findAll() {
      try {
         List<VendaEntity> vendas = service.findAll();

         return new ResponseEntity(vendas, HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @PostMapping("vendas")
   public ResponseEntity create(@RequestBody VendaEntity venda) {
      try {

         this.service.save(venda);
         return new ResponseEntity(venda, HttpStatus.CREATED);

      } catch (Exception e) {
         return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @GetMapping("vendas/{id}")
   public ResponseEntity findById(@PathVariable("id") Integer id) {
      try {
         VendaEntity venda = this.service.findById(id);
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

   @PutMapping("vendas/{id}")
   public ResponseEntity update(@PathVariable("id") Integer id,
           @RequestBody VendaEntity venda) {
      try {
         VendaEntity found = this.service.findById(id);

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

   @DeleteMapping("vendas/{id}")
   public ResponseEntity delete(@PathVariable("id") Integer id) {
      try {
         VendaEntity found = this.service.findById(id);

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

package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.entity.GrupoEntity;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.GrupoService;
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
public class GrupoController {

   @Autowired
   private GrupoService service;

   @GetMapping("grupos")
   public ResponseEntity<List<GrupoEntity>> findAll() {
      try {
         List<GrupoEntity> grupos = service.findAll();

         return new ResponseEntity(grupos, HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @PostMapping("grupos")
   public ResponseEntity create(@RequestBody GrupoEntity grupo) {
      try {

         this.service.save(grupo);
         return new ResponseEntity(grupo, HttpStatus.CREATED);

      } catch (Exception e) {
         return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @GetMapping("grupos/{id}")
   public ResponseEntity findById(@PathVariable("id") Integer id) {
      try {
         GrupoEntity grupo = this.service.findById(id);
         return new ResponseEntity(grupo, HttpStatus.OK);
      } catch (ResourceNotFoundException e) {
         return new ResponseEntity(
                 new CustomErrorResponse("Não existe um grupo com esse código"),
                 HttpStatus.NOT_FOUND
         );
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @PutMapping("grupos/{id}")
   public ResponseEntity update(@PathVariable("id") Integer id,
           @RequestBody GrupoEntity grupo) {
      try {
         GrupoEntity found = this.service.findById(id);

         grupo.setId(found.getId());

         this.service.save(grupo);

         return new ResponseEntity(grupo, HttpStatus.OK);
      } catch (ResourceNotFoundException e) {
         return new ResponseEntity(
                 new CustomErrorResponse("Não existe um grupo com esse código"),
                 HttpStatus.NOT_FOUND
         );
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @DeleteMapping("grupos/{id}")
   public ResponseEntity delete(@PathVariable("id") Integer id) {
      try {
         GrupoEntity found = this.service.findById(id);

         this.service.delete(found.getId());

         return new ResponseEntity(HttpStatus.NO_CONTENT);
      } catch (ResourceNotFoundException e) {
         return new ResponseEntity(
                 new CustomErrorResponse("Não existe um grupo com esse código"),
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

package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import br.edu.materdei.tas.venda.entity.PedidoEntity;
import br.edu.materdei.tas.venda.service.PedidoService;
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
public class PedidoController {

   @Autowired
   private PedidoService service;

   @GetMapping("pedidos")
   public ResponseEntity<List<PedidoEntity>> findAll() {
      try {
         List<PedidoEntity> pedidos = service.findAll();

         return new ResponseEntity(pedidos, HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @PostMapping("pedidos")
   public ResponseEntity create(@RequestBody PedidoEntity pedido) {
      try {

         this.service.save(pedido);
         return new ResponseEntity(pedido, HttpStatus.CREATED);

      } catch (Exception e) {
         return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @GetMapping("pedidos/{id}")
   public ResponseEntity findById(@PathVariable("id") Integer id) {
      try {
         PedidoEntity pedido = this.service.findById(id);
         return new ResponseEntity(pedido, HttpStatus.OK);
      } catch (ResourceNotFoundException e) {
         return new ResponseEntity(
                 new CustomErrorResponse("Não existe um pedido com esse código"),
                 HttpStatus.NOT_FOUND
         );
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @PutMapping("pedidos/{id}")
   public ResponseEntity update(@PathVariable("id") Integer id,
           @RequestBody PedidoEntity pedido) {
      try {
         PedidoEntity found = this.service.findById(id);

         pedido.setId(found.getId());

         this.service.save(pedido);

         return new ResponseEntity(pedido, HttpStatus.OK);
      } catch (ResourceNotFoundException e) {
         return new ResponseEntity(
                 new CustomErrorResponse("Não existe um pedido com esse código"),
                 HttpStatus.NOT_FOUND
         );
      } catch (Exception e) {
         return new ResponseEntity(
                 new CustomErrorResponse(e.getMessage()),
                 HttpStatus.INTERNAL_SERVER_ERROR
         );
      }
   }

   @DeleteMapping("pedidos/{id}")
   public ResponseEntity delete(@PathVariable("id") Integer id) {
      try {
         PedidoEntity found = this.service.findById(id);

         this.service.delete(found.getId());

         return new ResponseEntity(HttpStatus.NO_CONTENT);
      } catch (ResourceNotFoundException e) {
         return new ResponseEntity(
                 new CustomErrorResponse("Não existe um pedido com esse código"),
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Note
 */
@SpringBootApplication
@EntityScan({
   "br.edu.materdei.tas.core.entity",
   "br.edu.materdei.tas.compra.entity",
   "br.edu.materdei.tas.venda.entity",
   "br.edu.materdei.tas.estoque.entity"
})
@EnableJpaRepositories({
   "br.edu.materdei.tas.core.repository",
   "br.edu.materdei.tas.compra.repository",
   "br.edu.materdei.tas.venda.repository",
   "br.edu.materdei.tas.estoque.repository",
})
@ComponentScan("br.edu.materdei.tas")
public class Application {
   public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
   }
}

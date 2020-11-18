package br.edu.materdei.tas.producao.repository;

import br.edu.materdei.tas.producao.entity.ProdutoAcabadoEntidy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Note
 */
@Repository
public interface ProdutoAcabadoRepository extends JpaRepository<ProdutoAcabadoEntidy, Integer> {
 
}
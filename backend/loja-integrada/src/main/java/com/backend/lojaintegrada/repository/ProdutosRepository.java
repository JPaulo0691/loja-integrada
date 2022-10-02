package com.backend.lojaintegrada.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.lojaintegrada.model.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Integer>{

}

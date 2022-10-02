package com.backend.lojaintegrada.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.lojaintegrada.model.Produto;
import com.backend.lojaintegrada.repository.ProdutosRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutosRepository produtosRepository;
	
	@Transactional
	public Produto cadastrarProdutos(Produto produto) {
		return produtosRepository.save(produto);
	}
	
	public List<Produto> getAllItens(){
		return produtosRepository.findAll();
	}
	
	public Optional<Produto> getById(Integer id){
		return produtosRepository.findById(id);
	}	
	
	public Produto atualizar(Integer id, Produto produto) {
		
		Optional<Produto> produtosOptional = produtosRepository.findById(id);
		
		var produtos = produtosOptional.get();
		
		produtos.setDescricao(produto.getDescricao());
		produtos.setPreco_unitario(produto.getPreco_unitario());
		
		return produtosRepository.save(produtos);
	}
	
	@Transactional
	public void deleteItem(Produto produto) {
		
		produtosRepository.delete(produto);
		
	}

}

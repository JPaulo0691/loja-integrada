package com.backend.lojaintegrada.service;

import java.util.Optional;

import com.backend.lojaintegrada.dtos.PedidoDTO;
import com.backend.lojaintegrada.enums.StatusPedido;
import com.backend.lojaintegrada.model.Pedido;


public interface PedidoService {
	
	Pedido cadastrarPedido(PedidoDTO pedidoDTO);
	
	Optional<Pedido> relatorioPedido(Integer id);
	
	void atualizarStatus(Integer id, StatusPedido status);

}

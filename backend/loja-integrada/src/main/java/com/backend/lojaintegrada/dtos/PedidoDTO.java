package com.backend.lojaintegrada.dtos;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.backend.lojaintegrada.customAnotations.NotEmptyList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
	
	@NotNull(message = "{codigo-cliente.obrigatorio}")
	private Integer cliente;
	@NotEmptyList(message = "{itens-pedido.obrigatorio}")
	private List<ItemPedidoDTO> itens;

}

package org.serratec.backand.finalWork.mapper;

import org.serratec.backand.finalWork.DTO.PedidoDto;
import org.serratec.backand.finalWork.models.PedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

	public PedidoEntity toEntity(PedidoDto dto) {
		PedidoEntity entity = new PedidoEntity();
		entity.setDataPedido(dto.getDataPedido());
		entity.setClienteId(dto.getClienteId());
		entity.setStatus(dto.getStatus());
		entity.setValorTotal(dto.getValorTotal());
		entity.setProdutosPedido(dto.getProdutosPedido());
		return entity;
	}
	public PedidoDto toDto(PedidoEntity cliente) {
		PedidoDto dto = new PedidoDto();
		dto.setDataPedido(cliente.getDataPedido());
		dto.setClienteId(cliente.getClienteId());
		dto.setStatus(cliente.getStatus());
		dto.setValorTotal(cliente.getValorTotal());
		dto.setProdutosPedido(cliente.getProdutosPedido());
		return dto;
	}

	
}

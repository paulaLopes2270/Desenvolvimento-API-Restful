package org.serratec.backand.finalWork.mapper;

import org.serratec.backand.finalWork.DTO.EnderecoDto;
import org.serratec.backand.finalWork.models.EnderecoEntity;
import org.springframework.stereotype.Component;
@Component
public class EnderecoMapper {

	public EnderecoEntity toEntity(EnderecoDto dto) {
		EnderecoEntity entity = new EnderecoEntity();
		entity.setCep(dto.getCep());
		entity.setEstado(dto.getEstado());
		entity.setBairro(dto.getBairro());
		entity.setRua(dto.getRua());
		entity.setNumero(dto.getNumero());
		entity.setComplemento(dto.getComplemento());
		return entity;
	}
	
	public EnderecoDto toDto(EnderecoEntity endereco) {
		EnderecoDto dto = new EnderecoDto();
		dto.setCep(dto.getCep());
		dto.setEstado(endereco.getEstado());
		dto.setBairro(endereco.getBairro());
		dto.setRua(endereco.getRua());
		dto.setNumero(endereco.getNumero());
		dto.setComplemento(endereco.getComplemento());
		return dto;
	}
	
	
	
	
}

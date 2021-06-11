package org.serratec.backend.projetoFinal.service;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.projetoFinal.dto.EnderecoDto;
import org.serratec.backend.projetoFinal.entity.ClienteEntity;
import org.serratec.backend.projetoFinal.entity.EnderecoEntity;
import org.serratec.backend.projetoFinal.mapper.EnderecoMapper;
import org.serratec.backend.projetoFinal.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;

@Service
public class EnderecoService {
	@Autowired
	EnderecoRepository repository;
	
	@Autowired
	EnderecoMapper mapper;
	
	@Autowired
	ClienteService clienteService;
	
	public List<EnderecoEntity> getAll(){
		return repository.findAll();
	}
	
	public List<EnderecoEntity> getById(Long id) throws NotFoundException {
		 Optional<List<EnderecoEntity>> endereco = repository.findByclienteIdId(id);
		
		if (endereco.isEmpty()) {
			throw new NotFoundException("Não achei");
		}
		
		return endereco.get();
		 
	}
	
	public EnderecoDto create(Long id,EnderecoDto endereco) throws NotFoundException {
		
		EnderecoEntity enderecoEntrega = new EnderecoEntity();
		EnderecoEntity enderecoCobranca = new EnderecoEntity();
		
		ClienteEntity cliente = clienteService.getById(id);
		
		
		enderecoEntrega.setCep(endereco.getCep());
		enderecoEntrega.setRua(endereco.getRua());
		enderecoEntrega.setBairro(endereco.getBairro());
		enderecoEntrega.setCidade(endereco.getCidade());
		enderecoEntrega.setNumero(endereco.getNumero());
		enderecoEntrega.setComplemento(endereco.getComplemento());
		enderecoEntrega.setEstado(endereco.getEstado());
		enderecoEntrega.setTipoEndereco("entrega");
		enderecoEntrega.setClienteId(cliente);
		repository.save(enderecoEntrega);
		
		enderecoCobranca.setCep(endereco.getCep());
		enderecoCobranca.setRua(endereco.getRua());
		enderecoCobranca.setBairro(endereco.getBairro());
		enderecoCobranca.setCidade(endereco.getCidade());
		enderecoCobranca.setNumero(endereco.getNumero());
		enderecoCobranca.setComplemento(endereco.getComplemento());
		enderecoCobranca.setEstado(endereco.getEstado());
		enderecoCobranca.setTipoEndereco("cobranca");
		enderecoCobranca.setClienteId(cliente);
		return  mapper.toDto(repository.save(enderecoCobranca));
		
	}
	
	public EnderecoDto update(Long id, EnderecoDto endereco) throws NotFoundException, BadHttpRequest {
		EnderecoEntity enderecoAtual = this.localizaEnderecoTipo(id,endereco.getTipoEndereco());
		
		if(endereco.getTipoEndereco()!=null) {
			if(endereco.getCep()!=null) {
				enderecoAtual.setCep(endereco.getCep());
			}
			
			if(endereco.getRua()!=null) {
				enderecoAtual.setRua(endereco.getRua());
			}
			
			if(endereco.getBairro()!=null) {
				enderecoAtual.setBairro(endereco.getBairro());
			}
			
			if(endereco.getCidade()!=null) {
				enderecoAtual.setCidade(endereco.getCidade());
			}
			
			if(endereco.getNumero()!=null) {
				enderecoAtual.setNumero(endereco.getNumero());
			}
			if(endereco.getComplemento()!=null) {
				enderecoAtual.setComplemento(endereco.getComplemento());
			}
			
			if(endereco.getEstado()!=null) {
				enderecoAtual.setEstado(endereco.getEstado());
			}
		}else {
			throw new BadHttpRequest();
		}
		
		return mapper.toDto(repository.save(enderecoAtual));
	}
	
	private EnderecoEntity localizaEnderecoTipo(Long id, String tipoEndereco) {
		// TODO Auto-generated method stub
		return repository.findByclienteIdIdAndTipoEndereco(id, tipoEndereco);
	}
	
	public void delete (Long id) {
		repository.deleteById(id);
	}

}

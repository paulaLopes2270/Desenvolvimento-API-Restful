package org.serratec.backend.projetoFinal.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.projetoFinal.dto.ClienteDto;
import org.serratec.backend.projetoFinal.entity.ClienteEntity;
import org.serratec.backend.projetoFinal.mapper.ClienteMapper;
import org.serratec.backend.projetoFinal.repository.ClienteRepository;
import org.serratec.backend.projetoFinal.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repository;
	
	@Autowired
	EnderecoService enderecoService;
	
	@Autowired
	ClienteMapper mapper;

	public List<ClienteEntity> getAll() throws NotFoundException {
		return repository.findAll();
	}

	public ClienteEntity getById(Long id) throws NotFoundException {
		Optional<ClienteEntity> cliente = repository.findById(id);
		if (cliente.isEmpty()) {
			throw new NotFoundException("Não achei");
		}
		return cliente.get();
	}

	public ClienteDto create(ClienteDto cliente) {
		ClienteEntity clienteNovo = mapper.toEntity(cliente);
		clienteNovo.setDataCadastro(LocalDate.now());
		
		return mapper.toDto(repository.save(clienteNovo));
	}

	public ClienteDto update(Long id, ClienteDto cliente) throws NotFoundException {
		ClienteEntity clienteEntity = this.getById(id);

		if (cliente.getNome() != null) {
			clienteEntity.setNome(cliente.getNome());
		}

		if (cliente.getEmail() != null) {
			clienteEntity.setEmail(cliente.getEmail());
		}

		if (cliente.getUsername() != null) {
			clienteEntity.setUsername(cliente.getUsername());
		}

		if (cliente.getSenha() != null) {
			clienteEntity.setSenha(cliente.getSenha());
		}

		if (cliente.getTelefone() != null) {
			clienteEntity.setTelefone(cliente.getTelefone());
		}

		if (cliente.getDataNascimento() != null) {
			clienteEntity.setDataNascimento(cliente.getDataNascimento());
		}

		
		clienteEntity.setDataAtualizacao(LocalDate.now());
		return mapper.toDto(repository.save(clienteEntity));
	}

	
	public void delete(Long id) throws NotFoundException {
		repository.deleteById(id);
	}



}


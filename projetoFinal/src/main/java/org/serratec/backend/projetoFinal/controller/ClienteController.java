package org.serratec.backend.projetoFinal.controller;

import java.util.List;

import org.serratec.backend.projetoFinal.dto.ClienteDto;
import org.serratec.backend.projetoFinal.entity.ClienteEntity;
import org.serratec.backend.projetoFinal.entity.EnderecoEntity;
import org.serratec.backend.projetoFinal.service.ClienteService;
import org.serratec.backend.projetoFinal.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	ClienteService service;
	
	@GetMapping
	public List<ClienteEntity> getAll() throws NotFoundException{
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public ClienteEntity getById(@PathVariable("id") Long id) throws NotFoundException {
		return service.getById(id);
	}
	
	@PostMapping
	public ClienteDto create(@RequestBody ClienteDto cliente) {
		return service.create(cliente);
	}
	
	@PutMapping("/{id}")
	public ClienteDto update(@PathVariable("id") Long id, @RequestBody ClienteDto cliente) throws NotFoundException {
		return service.update(id, cliente);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") Long id) throws NotFoundException {
		service.delete(id);
	}
	
	
}

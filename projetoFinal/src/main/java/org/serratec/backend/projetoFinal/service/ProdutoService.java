package org.serratec.backend.projetoFinal.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.projetoFinal.dto.ProdutoDto;
import org.serratec.backend.projetoFinal.entity.CategoriaEntity;
import org.serratec.backend.projetoFinal.entity.ProdutoEntity;
import org.serratec.backend.projetoFinal.mapper.ProdutoMapper;
import org.serratec.backend.projetoFinal.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repository;
	
	@Autowired
	ProdutoMapper mapper;
	
	@Autowired
	CategoriaService catgoService;

	public List<ProdutoEntity> getAll() throws NotFoundException {
		return repository.findAll();
	}

	public ProdutoEntity getByNome(String nome) throws NotFoundException {
		Optional<ProdutoEntity> produto = repository.findByNome(nome);
		if (produto.isEmpty()) {
			throw new NotFoundException("Não achei");
		}
		return produto.get();
	}

	//
	
	public ProdutoEntity getById(Long id) throws NotFoundException {
		Optional<ProdutoEntity> produto = repository.findById(id);
		if (produto.isEmpty()) {
			throw new NotFoundException("Não achei");
		}
		return produto.get();
	}
	
	//
	
	public ProdutoDto create(ProdutoDto produto) throws NotFoundException {
		CategoriaEntity catgo = catgoService.getByNome(produto.getCategoria());
		ProdutoEntity pdto = mapper.toEntity(produto);
		pdto.setCategoriaId(catgo);
		pdto.setDateCadastro(LocalDate.now());
		return mapper.toDto(repository.save(pdto));
	}

	public ProdutoDto update(String nome, ProdutoDto produto) throws NotFoundException, BadHttpRequest {
		ProdutoEntity produtoEntity = this.getByNome(nome);
		if (produto.getNome() != null) {
			produtoEntity.setNome(produto.getNome());
		}

		if (produto.getDescricao() != null) {
			produtoEntity.setDescricao(produto.getDescricao());
		}

		if (produto.getQtdEstoque() != null) {
			if (produto.getQtdEstoque() < 0) {
				throw new BadHttpRequest();
			} else {
				produtoEntity.setQtdEstoque(produto.getQtdEstoque());
			}

		}

		if (produto.getPreco() != null) {
			if (produto.getPreco() < 0) {
				throw new BadHttpRequest();
			} else {
				produtoEntity.setPreco(produto.getPreco());
			}

		}

		produtoEntity.setDateAtualizacao(LocalDate.now());

		return mapper.toDto(repository.save(produtoEntity));
	}

	public void delete(String nome) throws NotFoundException {
		ProdutoEntity pdto = getByNome(nome);
		Long idPdto = pdto.getId();
		
		repository.deleteById(idPdto);
	}

}

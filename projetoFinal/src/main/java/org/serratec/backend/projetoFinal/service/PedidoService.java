package org.serratec.backend.projetoFinal.service;

import java.util.List;

import org.serratec.backend.projetoFinal.dto.PedidoDto;
import org.serratec.backend.projetoFinal.entity.PedidoEntity;
import org.serratec.backend.projetoFinal.entity.PedidoProdutoEntity;
import org.serratec.backend.projetoFinal.entity.ProdutoEntity;
import org.serratec.backend.projetoFinal.mapper.PedidoMapper;
import org.serratec.backend.projetoFinal.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository repository;
	
	@Autowired
	PedidoMapper mapper;
	
	@Autowired
	PedidoProdutoService pedidoProdutoService;
	
	public List<PedidoEntity> getAll(){
		return  repository.findAll();
	}
	
	public PedidoEntity getById(Long id) {
		return repository.findById(id).get();
	}
	
	public PedidoDto create(PedidoDto pedido) throws NotFoundException {
		
		PedidoEntity pedidoEntity= repository.save(mapper.toEntity(pedido));
		pedidoEntity.setNumeroPedido(pedidoEntity.getId()*1.0+1000.0);
		
		Long idPedido = pedidoEntity.getId();//compartilhar
		
		List <PedidoProdutoEntity>listaPedidoProduto = pedido.getPedidoProdutos();
			
		for (PedidoProdutoEntity pedidoProduto : listaPedidoProduto) {
			
			    ProdutoEntity produto = pedidoProduto.getProduto();
				Long idProduto = produto.getId();
				Long qtdeProduto =pedidoProduto.getQtde();
				Double valorTotal = pedidoProdutoService.conteudoPedidoProduto(idPedido, idProduto, qtdeProduto);
				pedidoEntity.setValorTotal(pedidoEntity.getValorTotal()+valorTotal);
		
		}
		
		return mapper.toDto(repository.save(pedidoEntity));
		
		
	}
	
	//Atualizar pedido, excluindo um produto no carrinho
	public void excluiProdutoPedido(Long idPedido, Long idProduto) {
		
		Long qtde=(long) 0;
		Double abatimento;
		abatimento = pedidoProdutoService.verificaprodutoNoCarrinhoExclui(idPedido, idProduto,qtde);
		
		PedidoEntity pedidoEntity = repository.findById(idPedido).get();
		pedidoEntity.setValorTotal(pedidoEntity.getValorTotal()+abatimento);
		repository.save(pedidoEntity);
		
	}
	
	//Atualiza o pedido, incluindo um produto no carrinho
	public void incluiProdutoPedido (Long idPedido, Long idProduto,Long qtde) throws NotFoundException {
		
		Double acrescimo;
		acrescimo = pedidoProdutoService.verificaprodutoNoCarrinhoInclui(idPedido, idProduto, qtde);
		
		PedidoEntity pedidoEntity = repository.findById(idPedido).get();
		pedidoEntity.setValorTotal(pedidoEntity.getValorTotal()+acrescimo);
		repository.save(pedidoEntity);
		
		
	}
	
//	public boolean verificaStatusPedido() {
//		
//	}

}




 


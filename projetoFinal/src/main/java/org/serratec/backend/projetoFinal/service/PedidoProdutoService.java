package org.serratec.backend.projetoFinal.service;

import java.util.Optional;

import org.serratec.backend.projetoFinal.entity.PedidoEntity;
import org.serratec.backend.projetoFinal.entity.PedidoProdutoEntity;
import org.serratec.backend.projetoFinal.entity.ProdutoEntity;
import org.serratec.backend.projetoFinal.repository.PedidoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class PedidoProdutoService {

	@Autowired
	PedidoProdutoRepository repository;

	@Autowired
	ProdutoService produtoService;

	@Autowired
	PedidoService pedidoService;

	public PedidoProdutoEntity create(PedidoProdutoEntity pedidoProduto) {
		return repository.save(pedidoProduto);

	}

	public Double conteudoPedidoProduto(Long idPedido, Long idProduto, Long qtdeProduto) throws NotFoundException {
		// cria um objeto PedidoProdutoEntity
		PedidoProdutoEntity pedidoProduto = new PedidoProdutoEntity();

		// seta os atibutos do objeto
		ProdutoEntity produto = produtoService.getById(idProduto);
		pedidoProduto.setProduto(produto);

		PedidoEntity pedido = pedidoService.getById(idPedido);
		pedidoProduto.setPedido(pedido);

		pedidoProduto.setQtde(qtdeProduto);

		pedidoProduto.setPreco(produto.getPreco());

		// salva o objeto PedidoProdutoEntity no banco
		PedidoProdutoEntity pedidoProdutoSalvo = this.create(pedidoProduto);

		// recupera a qtde e o preço do produto salvo dentro no banco
		Long qtde = pedidoProdutoSalvo.getQtde();
		Double preco = pedidoProdutoSalvo.getPreco();

		// retorna o valor total do pedidoProduto
		return 1.0 * qtde * preco;

	}

	public Double verificaprodutoNoCarrinhoExclui(Long idPedido, Long idProduto, Long qtde) {
		PedidoProdutoEntity pedidoproduto = repository.findAllByPedido_idAndProduto_id(idPedido,idProduto);
		Double abatimento;
		if(pedidoproduto!=null) {
			 abatimento= this.excluiprodutoPedido(idPedido, idProduto);
		}else {
			abatimento=0.;
		}
		
		return abatimento;
	}
	
	public Double excluiprodutoPedido(Long idPedido, Long idProduto) {
		
		
		PedidoProdutoEntity pedidoproduto = repository.findAllByPedido_idAndProduto_id(idPedido,idProduto);

		Long idPedidoProduto = pedidoproduto.getId();
		Long qtde = pedidoproduto.getQtde();
		Double preco = pedidoproduto.getPreco();
		
		this.excluiProduto(idPedidoProduto);
		return (-1.0*qtde*preco);
		
		
	}

	
	public void excluiProduto(Long id) {

		repository.deleteById(id);

	}
	
	public Double verificaprodutoNoCarrinhoInclui(Long idPedido, Long idProduto, Long qtde) throws NotFoundException {
		Double abatimento;
		
		abatimento = this.verificaprodutoNoCarrinhoExclui(idPedido, idProduto,qtde);
		return (this.incluiProdutoPedido(idPedido, idProduto, qtde)+abatimento);
		
	}
	
	
	public Double incluiProdutoPedido(Long idPedido, Long idProduto, Long qtde ) throws NotFoundException {
		
		PedidoProdutoEntity pedidoProduto = new PedidoProdutoEntity();

		// seta os atibutos do objeto
		ProdutoEntity produto = produtoService.getById(idProduto);
		pedidoProduto.setProduto(produto);

		PedidoEntity pedido = pedidoService.getById(idPedido);
		pedidoProduto.setPedido(pedido);

		pedidoProduto.setQtde(qtde);
		pedidoProduto.setPreco(produto.getPreco());
		
		this.create(pedidoProduto);
		
		Double acrescimo = 1.0*qtde*produto.getPreco();
		return acrescimo;
		
		//return "produto incluído no pedido com sucesso!";
	}
	
	
	

}

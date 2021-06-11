package org.serratec.backend.projetoFinal.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PedidoProdutoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//relaciona o atributo pedido com PedidoEntity.produtos(list)
	@ManyToOne
	@JsonBackReference//16:02/16:14
	private ProdutoEntity produto;
	
	//relaciona o atributo pedido com produtoEntity.pedido(list)
	@ManyToOne
	@JsonIgnore //16:11
	private PedidoEntity pedido;
	
	private Long qtde;
	private Double preco;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ProdutoEntity getProduto() {
		return produto;
	}
	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}
	public PedidoEntity getPedido() {
		return pedido;
	}
	public void setPedido(PedidoEntity pedido) {
		this.pedido = pedido;
	}
	public Long getQtde() {
		return qtde;
	}
	public void setQtde(Long qtde) {
		this.qtde = qtde;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
	
	
	
	
	

}

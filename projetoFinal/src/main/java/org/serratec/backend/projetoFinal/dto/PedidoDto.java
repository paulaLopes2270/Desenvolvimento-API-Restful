package org.serratec.backend.projetoFinal.dto;

import java.time.LocalDate;
import java.util.List;

import org.serratec.backend.projetoFinal.entity.ClienteEntity;
import org.serratec.backend.projetoFinal.entity.PedidoProdutoEntity;

public class PedidoDto {
//	private List<ProdutoEntity> produtos;
	private Double valorTotal=0.;
	private LocalDate dataPedido= LocalDate.now();
	private LocalDate dataEntrega = LocalDate.now().plusDays(5);
	private String status="aberto";
	private ClienteEntity clienteId;
	private Double numeroPedido=1.;
	private List<PedidoProdutoEntity> pedidoProdutos;
	//compartirlhar
//	private Long idProduto;
//	private Long qtde;
	
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public LocalDate getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}
	public LocalDate getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ClienteEntity getClienteId() {
		return clienteId;
	}
	public void setClienteId(ClienteEntity clienteId) {
		this.clienteId = clienteId;
	}
	public Double getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(Double numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public List<PedidoProdutoEntity> getPedidoProdutos() {
		return pedidoProdutos;
	}
	public void setPedidoProdutos(List<PedidoProdutoEntity> pedidoProdutos) {
		this.pedidoProdutos = pedidoProdutos;
	}
	
	

}

package org.serratec.backend.projetoFinal.dto;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class ProdutoDto {
	
	@NotNull
	@Size(min = 5, max = 50)
	private String nome;
	
	@NotNull
	@Size(min = 5, max = 100)
	private String descricao;
	
	private Double preco;
	
	private Integer qtdEstoque;
		
	private LocalDate dateCadastro;
	
	private LocalDate dateAtualizacao;
	
	private String categoria;

	
	
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public LocalDate getDateAtualizacao() {
		return dateAtualizacao;
	}

	public void setDateAtualizacao(LocalDate dateAtualizacao) {
		this.dateAtualizacao = dateAtualizacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public LocalDate getDateCadastro() {
		return dateCadastro;
	}

	public void setDateCadastro(LocalDate dateCadastro) {
		this.dateCadastro = dateCadastro;
	}
}

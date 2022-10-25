package br.com.serratec.ecommerce.dto;

import java.util.Date;

	public class ProdutoResponseDTO {
		
	private Long id;
	
	private String nome;

	private String descricao;

	private Double qtd_estoque;

	private Date data_cadastro;

	private Double valor_unitario;
	
	private Long id_categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getQtd_estoque() {
		return qtd_estoque;
	}

	public void setQtd_estoque(Double qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Double getValor_unitario() {
		return valor_unitario;
	}

	public void setValor_unitario(Double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}

	public Long getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Long id_categoria) {
		this.id_categoria = id_categoria;
	}
	
	
	
}

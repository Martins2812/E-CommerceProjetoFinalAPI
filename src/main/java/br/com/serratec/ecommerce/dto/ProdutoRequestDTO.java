package br.com.serratec.ecommerce.dto;

import java.util.Date;
import java.util.List;
import br.com.serratec.ecommerce.model.Produto;

public class ProdutoRequestDTO {

	private String nome;

	private String descricao;

	private Double qtd_estoque;

	private Date data_cadastro;

	private Double valor_unitario;
	
	private List<Produto> produto;

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

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}	
}

package br.com.serratec.ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long id;

	@NotBlank
	@Size(max = 30)
	private String nome;

	@NotBlank
	@Size(max = 100)
	private String descricao;

	@NotBlank
	private Double qtd_estoque;

	@FutureOrPresent
	private Date data_cadastro;

	@NotBlank
	private Double valor_unitario;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	@OneToOne(mappedBy = "produto")
	private ItemPedido itempedido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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


}

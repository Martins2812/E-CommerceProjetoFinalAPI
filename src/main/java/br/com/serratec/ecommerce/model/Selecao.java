package br.com.serratec.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "selecao")
public class Selecao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, length = 30)
	private String nome;

	@Column(name = "descricao", nullable =  true, length = 100)
	private String descricao;
	
	@Column(name= "quantGols", nullable = false)
	private String quantGols;

	@Column(name = "imagem_selecao", nullable = false, columnDefinition = "TEXT")
	private String imagemSelecao;

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

	public String getImagemSelecao() {
		return imagemSelecao;
	}

	public void setImagemSelecao(String imagemSelecao) {
		this.imagemSelecao = imagemSelecao;
	}

	public String getQuantGols() {
		return quantGols;
	}

	public void setQuantGols(String quantGols) {
		this.quantGols = quantGols;
	}	
}

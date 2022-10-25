package br.com.serratec.ecommerce.dto;

public class SelecaoResponseDTO {
	
	private Long id;

	private String nome;

	private String descricao;

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
}


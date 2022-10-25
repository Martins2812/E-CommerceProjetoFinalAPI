package br.com.serratec.ecommerce.dto;

import org.springframework.web.multipart.MultipartFile;

public class SelecaoRequestDTO {

	private String nome;

	private String descricao;
	
	private String quantGols;

	private MultipartFile imagemSelecao;

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

	public String getQuantGols() {
		return quantGols;
	}

	public void setQuantGols(String quantGols) {
		this.quantGols = quantGols;
	}

	public MultipartFile getImagemSelecao() {
		return imagemSelecao;
	}

	public void setImagemSelecao(MultipartFile imagemSelecao) {
		this.imagemSelecao = imagemSelecao;
	}

	
}

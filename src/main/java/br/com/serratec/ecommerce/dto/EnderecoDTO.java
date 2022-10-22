package br.com.serratec.ecommerce.dto;

import br.com.serratec.ecommerce.model.Endereco;

public class EnderecoDTO {
	
	private Long id;
	
	private String cep;
	
	private String rua;
	
	private String bairro;
	
	private String cidade;
	
	private Number numero;
	
	private String complemento;
	
	private String uf;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Number getNumero() {
		return numero;
	}

	public void setNumero(Number numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public EnderecoDTO(Endereco endereco) {
		super();
		this.id = endereco.getId();
		this.cep = endereco.getCep();
		this.rua = endereco.getRua();
		this.bairro = endereco.getBairro();
		this.cidade = endereco.getCidade();
		this.numero = endereco.getNumero();
		this.complemento = endereco.getComplemento();
		this.uf = endereco.getUf();
	}
	
	


}

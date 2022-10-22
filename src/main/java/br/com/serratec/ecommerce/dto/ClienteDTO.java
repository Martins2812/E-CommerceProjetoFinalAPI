package br.com.serratec.ecommerce.dto;

import java.util.Date;

import br.com.serratec.ecommerce.model.Cliente;

public class ClienteDTO {
	
	private Long id;
	
	private String email;
	
	private String nomeCompleto;
	
	private String cpf;
	
	private String telefone;
	
	private Date data_nascimento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public ClienteDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.email = cliente.getEmail();
		this.nomeCompleto = cliente.getnomeCompleto();
		this.cpf = cliente.getCpf();
		this.telefone = cliente.getTelefone();
		this.data_nascimento = cliente.getData_nascimento();
	}
	
	

}

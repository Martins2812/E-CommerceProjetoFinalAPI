package br.com.serratec.ecommerce.dto;

import java.util.Date;

import br.com.serratec.ecommerce.model.Pedido;

public class PedidoDTO {
	
	private Long id;
	
	private Date data_pedido;
	
	private Date data_entrega;
	
	private Date data_envio;
	
	private Boolean status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData_pedido() {
		return data_pedido;
	}

	public void setData_pedido(Date data_pedido) {
		this.data_pedido = data_pedido;
	}

	public Date getData_entrega() {
		return data_entrega;
	}

	public void setData_entrega(Date data_entrega) {
		this.data_entrega = data_entrega;
	}

	public Date getData_envio() {
		return data_envio;
	}

	public void setData_envio(Date data_envio) {
		this.data_envio = data_envio;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public PedidoDTO(Pedido pedido) {
		super();
		this.id = pedido.getId();
		this.data_pedido = pedido.getData_pedido();
		this.data_entrega = pedido.getData_entrega();
		this.data_envio = pedido.getData_envio();
		this.status = pedido.getStatus();
	}

	
}

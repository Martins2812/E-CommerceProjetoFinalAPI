package br.com.serratec.ecommerce.dto;

import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.model.Produto;

public class ItemPedidoResponseDTO {

	private Long id;
	
	private Double quantidade;
	
	private Double preco_venda;
	
	private Pedido pedido;
	
	private Produto produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco_venda() {
		return preco_venda;
	}

	public void setPreco_venda(Double preco_venda) {
		this.preco_venda = preco_venda;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}


package br.com.serratec.ecommerce.dto;

import br.com.serratec.ecommerce.model.ItemPedido;

public class ItemPedidoDTO {
	
	private Long id;
	
	private Double quantidade;
	
	private Double preco_venda;
	
	private Double desconto;
	
	private Double valor_bruto;
	
	private Double valor_liquido;

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

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getValor_bruto() {
		return valor_bruto;
	}

	public void setValor_bruto(Double valor_bruto) {
		this.valor_bruto = valor_bruto;
	}

	public Double getValor_liquido() {
		return valor_liquido;
	}

	public void setValor_liquido(Double valor_liquido) {
		this.valor_liquido = valor_liquido;
	}

	public ItemPedidoDTO(ItemPedido itempedido) {
		super();
		this.id = itempedido.getId();
		this.quantidade = itempedido.getQuantidade();
		this.preco_venda = itempedido.getPreco_venda();
		this.desconto = itempedido.getDesconto();
		this.valor_bruto = itempedido.getValor_bruto();
		this.valor_liquido = itempedido.getValor_liquido();
	}
	
	

}

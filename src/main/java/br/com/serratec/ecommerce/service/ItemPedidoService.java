package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.ItemPedidoRequestDTO;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.model.ItemPedido;
import br.com.serratec.ecommerce.repository.ItemPedidoRepository;


@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository repositorio;
	
	private ModelMapper mapper = new ModelMapper();
	
	public List<ItemPedidoRequestDTO> obterTodosOsItemPedidos() {
		
		List<ItemPedido> lista = repositorio.findAll();
		
		var novaLista = new ArrayList<ItemPedidoRequestDTO>();
		
		for (ItemPedido itemPedido : lista) {
			novaLista.add(mapper.map(itemPedido, ItemPedidoRequestDTO.class));
		}
		return novaLista;
	}
	
	public Optional<ItemPedidoRequestDTO> obterItemPedidoPorId(Long id) {
		
		Optional<ItemPedido> optItemPedido = repositorio.findById(id);
		
		if (optItemPedido.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o item com id :" + id);
		}
		ItemPedidoRequestDTO dto = mapper.map(optItemPedido.get(), ItemPedidoRequestDTO.class);
		return Optional.of(dto);
	}
	
	public ItemPedidoRequestDTO cadastrar (ItemPedidoRequestDTO itemPedido) {
	
		valorBruto(itemPedido);
		valorLiquido(itemPedido);
		
		var contaModel = mapper.map(itemPedido, ItemPedido.class);
		
		contaModel.setId(null);
		contaModel = repositorio.save(contaModel);
		
		var response = mapper.map(contaModel, ItemPedidoRequestDTO.class);
		
		return response;
	}
	
	public ItemPedidoRequestDTO atualizar (Long id, ItemPedidoRequestDTO itemPedido) {
		
		obterItemPedidoPorId(id);
		
		var contaModel = mapper.map(itemPedido, ItemPedido.class);
		
		contaModel.setId(id);
		contaModel = repositorio.save(contaModel);

		return mapper.map(contaModel, ItemPedidoRequestDTO.class);
	}
	
	public void deletar(Long id) {
		obterItemPedidoPorId(id);
		repositorio.deleteById(id);
	}
	
	private double valorBruto(ItemPedidoRequestDTO itemPedido) {
		double bruto = itemPedido.getPreco_venda() * itemPedido.getQuantidade();
		return bruto;
	}
	
	private double valorLiquido(ItemPedidoRequestDTO itemPedido) {
		double liquido = valorBruto(itemPedido) - itemPedido.getDesconto();
		return liquido;
	}
}

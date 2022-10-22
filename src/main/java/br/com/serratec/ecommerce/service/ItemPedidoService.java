package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.ItemPedidoDTO;
import br.com.serratec.ecommerce.exception.ResourceBadRequestException;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.model.ItemPedido;
import br.com.serratec.ecommerce.repository.ItemPedidoRepository;


@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository repositorio;
	
	private ModelMapper mapper = new ModelMapper();
	
	public List<ItemPedidoDTO> obterTodosOsItemPedidos() {
		
		List<ItemPedido> lista = repositorio.findAll();
		
		var novaLista = new ArrayList<ItemPedidoDTO>();
		
		for (ItemPedido itemPedido : lista) {
			novaLista.add(mapper.map(itemPedido, ItemPedidoDTO.class));
		}
		return novaLista;
	}
	
	public Optional<ItemPedidoDTO> obterItemPedidoPorId(Long id) {
		
		Optional<ItemPedido> optItemPedido = repositorio.findById(id);
		
		if (optItemPedido.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o item com id :" + id);
		}
		ItemPedidoDTO dto = mapper.map(optItemPedido.get(), ItemPedidoDTO.class);
		return Optional.of(dto);
	}
	
	public ItemPedidoDTO cadastrar (ItemPedidoDTO itemPedido) {
		
		validarModelo(itemPedido);
		
		var contaModel = mapper.map(itemPedido, ItemPedido.class);
		
		contaModel.setId(null);
		contaModel = repositorio.save(contaModel);
		
		var response = mapper.map(contaModel, ItemPedidoDTO.class);
		
		return response;
	}
	
	public ItemPedidoDTO atualizar (Long id, ItemPedidoDTO itemPedido) {
		
		obterItemPedidoPorId(id);
		
		validarModelo(itemPedido);
		
		var contaModel = mapper.map(itemPedido, ItemPedido.class);
		
		contaModel.setId(id);
		contaModel = repositorio.save(contaModel);

		return mapper.map(contaModel, ItemPedidoDTO.class);
	}
	
	public void deletar(Long id) {
		obterItemPedidoPorId(id);
		repositorio.deleteById(id);
	}

	private void validarModelo(ItemPedidoDTO itemPedido) {
		
		if(itemPedido.getId() == null) {
			throw new ResourceBadRequestException("O item deve ter um id.");
		}
	}
}

package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.serratec.ecommerce.dto.ItemPedidoRequestDTO;
import br.com.serratec.ecommerce.dto.ItemPedidoResponseDTO;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.model.ItemPedido;
import br.com.serratec.ecommerce.repository.ItemPedidoRepository;


@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository repositorio;
	
	private ModelMapper mapper = new ModelMapper();
	
	public List<ItemPedidoResponseDTO> obterTodosOsItemPedidos() {
		
		List<ItemPedido> lista = repositorio.findAll();
		
		var novaLista = new ArrayList<ItemPedidoResponseDTO>();
		
		for (ItemPedido itemPedido : lista) {
			novaLista.add(mapper.map(itemPedido, ItemPedidoResponseDTO.class));
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
	
	public ItemPedidoResponseDTO cadastrar (ItemPedidoRequestDTO itemPedido) {
		
		var itemPedidoModel = mapper.map(itemPedido, ItemPedido.class);
		
		itemPedidoModel.setId(null);
		itemPedidoModel = repositorio.save(itemPedidoModel);
		
		var response = mapper.map(itemPedidoModel, ItemPedidoResponseDTO.class);
		
		return response;
	}
	
	public ItemPedidoRequestDTO atualizar (Long id, ItemPedidoRequestDTO itemPedido) {
		
		obterItemPedidoPorId(id);
		
		var itemPedidoModel = mapper.map(itemPedido, ItemPedido.class);
		
		itemPedidoModel.setId(id);
		itemPedidoModel = repositorio.save(itemPedidoModel);

		return mapper.map(itemPedidoModel, ItemPedidoRequestDTO.class);
	}
	
	public void deletar(Long id) {
		obterItemPedidoPorId(id);
		repositorio.deleteById(id);
	}
}

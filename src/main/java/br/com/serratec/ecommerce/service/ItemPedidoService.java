package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.ItemPedido;
import br.com.serratec.ecommerce.repository.ItemPedidoRepository;


@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository repositorio;
	
	public List<ItemPedido> obterTodosOsItemPedidos() {
		return repositorio.findAll();
	}
	
	public Optional<ItemPedido> obterItemPedidoPorId(Long id) {
		
		Optional<ItemPedido> optItemPedido = repositorio.findById(id);
		
		if (optItemPedido.isEmpty()) {
			
		}
		return optItemPedido;
	}
	
	public ItemPedido cadastrar (ItemPedido itemPedido) {
		
		itemPedido.setId(null);
		return repositorio.save(itemPedido);
	}
	
	public ItemPedido atualizar (Long id, ItemPedido itemPedido) {
		
		itemPedido.setId(id);
		return repositorio.save(itemPedido);
	}
	
	public void deletar(Long id) {
		repositorio.deleteById(id);
	}

}

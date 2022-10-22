package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.repository.PedidoRepository;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repositorio;
	
	public List<Pedido> obterTodosOsPedidos() {
		return repositorio.findAll();
	}
	
	public Optional<Pedido> obterPedidoPorId(Long id) {
		
		Optional<Pedido> optPedido = repositorio.findById(id);
		
		if (optPedido.isEmpty()) {
			
		}
		return optPedido;
	}
	
	public Pedido cadastrar (Pedido pedido) {
		
		pedido.setId(null);
		return repositorio.save(pedido);
	}
	
	public Pedido atualizar (Long id, Pedido pedido) {
		obterPedidoPorId(id);
		pedido.setId(id);
		return repositorio.save(pedido);
	}
	
	public void deletar(Long id) {
		repositorio.deleteById(id);
	}

}

package br.com.serratec.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.service.PedidoService;


@RestController
@RequestMapping("api/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService servico;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> obterTodosOsPedidos() {
		
		List<Pedido> lista = servico.obterTodosOsPedidos();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> obterPedidoPorId(@PathVariable Long id) {
		
		Optional<Pedido> optPedido = servico.obterPedidoPorId(id);
		return ResponseEntity.ok(optPedido.get());
	}
	
	@PostMapping
	public ResponseEntity<Pedido> cadastrar (@RequestBody Pedido pedido) {
		
		pedido = servico.cadastrar(pedido);
		return new ResponseEntity<>(pedido, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pedido> atualizar(@PathVariable Long id,@RequestBody Pedido pedido) {
		return ResponseEntity.ok(servico.atualizar(id, pedido));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
	}

}

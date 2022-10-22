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

import br.com.serratec.ecommerce.dto.PedidoDTO;
import br.com.serratec.ecommerce.service.PedidoService;


@RestController
@RequestMapping("api/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService servico;
	
	@GetMapping
	public ResponseEntity<List<PedidoDTO>> obterTodosOsPedidos() {
		
		List<PedidoDTO> lista = servico.obterTodosOsPedidos();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDTO> obterPedidoPorId(@PathVariable Long id) {
		
		Optional<PedidoDTO> optPedido = servico.obterPedidoPorId(id);
		return ResponseEntity.ok(optPedido.get());
	}
	
	@PostMapping
	public ResponseEntity<PedidoDTO> cadastrar (@RequestBody PedidoDTO pedido) {
		
		PedidoDTO pedidoDTO = servico.cadastrar(pedido);
		return new ResponseEntity<>(pedidoDTO, HttpStatus.CREATED);	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PedidoDTO> atualizar(@PathVariable Long id,@RequestBody PedidoDTO pedido) {
		return ResponseEntity.ok(servico.atualizar(id, pedido));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}

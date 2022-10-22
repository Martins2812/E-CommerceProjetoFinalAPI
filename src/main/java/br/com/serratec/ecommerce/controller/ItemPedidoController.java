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

import br.com.serratec.ecommerce.model.ItemPedido;
import br.com.serratec.ecommerce.service.ItemPedidoService;


@RestController
@RequestMapping("api/itemPedido")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoService servico;
	
	@GetMapping
	public ResponseEntity<List<ItemPedido>> obterTodosOsItemPedidos() {
		
		List<ItemPedido> lista = servico.obterTodosOsItemPedidos();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> obterItemPedidoPorId(@PathVariable Long id) {
		
		Optional<ItemPedido> optItemPedido = servico.obterItemPedidoPorId(id);
		return ResponseEntity.ok(optItemPedido.get());
	}
	
	@PostMapping
	public ResponseEntity<ItemPedido> cadastrar (@RequestBody ItemPedido itemPedido) {
		
		itemPedido = servico.cadastrar(itemPedido);
		return new ResponseEntity<>(itemPedido, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ItemPedido> atualizar(@PathVariable Long id,@RequestBody ItemPedido itemPedido) {
		return ResponseEntity.ok(servico.atualizar(id, itemPedido));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
	}

}

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

import br.com.serratec.ecommerce.model.Cliente;
import br.com.serratec.ecommerce.service.ClienteService;


@RestController
@RequestMapping("api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService servico;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> obterTodosOsClientes() {
		
		List<Cliente> lista = servico.obterTodosOsClientes();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> obterClientePorId(@PathVariable Long id) {
		
		Optional<Cliente> optCliente = servico.obterClientePorId(id);
		return ResponseEntity.ok(optCliente.get());
	}
	
	@PostMapping
	public ResponseEntity<Cliente> cadastrar (@RequestBody Cliente cliente) { 
		cliente = servico.cadastrar(cliente);
		
		return new ResponseEntity<>(cliente, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id,@RequestBody Cliente cliente) {
		return ResponseEntity.ok(servico.atualizar(id, cliente));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
	}

}

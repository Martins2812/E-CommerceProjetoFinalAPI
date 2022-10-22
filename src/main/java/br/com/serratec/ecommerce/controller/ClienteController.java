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

import br.com.serratec.ecommerce.dto.ClienteDTO;
import br.com.serratec.ecommerce.service.ClienteService;


@RestController
@RequestMapping("api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService servico;
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> obterTodosOsClientes() {
		
		List<ClienteDTO> lista = servico.obterTodosOsClientes();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> obterClientePorId(@PathVariable Long id) {
		
		Optional<ClienteDTO> optCliente = servico.obterClientePorId(id);
		return ResponseEntity.ok(optCliente.get());
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> cadastrar (@RequestBody ClienteDTO cliente) {
		
		ClienteDTO clienteDTO = servico.cadastrar(cliente);
		
		return new ResponseEntity<>(clienteDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id,@RequestBody ClienteDTO cliente) {
		return ResponseEntity.ok(servico.atualizar(id, cliente));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}

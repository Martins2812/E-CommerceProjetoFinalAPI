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

import br.com.serratec.ecommerce.dto.ClienteRequestDTO;
import br.com.serratec.ecommerce.dto.ClienteResponseDTO;
import br.com.serratec.ecommerce.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService servico;	
	
	@GetMapping
	@ApiOperation(value="Lista todos os cliente", notes="Listagem de Clientes")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todos os clientes"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<List<ClienteResponseDTO>> obterTodosOsClientes() {
		
		List<ClienteResponseDTO> lista = servico.obterTodosOsClientes();
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/{id}")
	@ApiOperation(value="Retorna um cliente", notes="Cliente")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna um cliente"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	
	public ResponseEntity<ClienteResponseDTO> obterClientePorId(@PathVariable Long id) {
		
		Optional<ClienteResponseDTO> optCliente = servico.obterClientePorId(id);
		return ResponseEntity.ok(optCliente.get());
	}
	
	
	@PostMapping
	@ApiOperation(value="Insere os dados de um Cliente", notes="Inserir Cliente")
	@ApiResponses(value= {
	@ApiResponse(code=201, message="Cliente adicionado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<ClienteRequestDTO> cadastrar (@RequestBody ClienteRequestDTO cliente) {

		ClienteRequestDTO clienteDTO = servico.cadastrar(cliente);
		
		return new ResponseEntity<>(clienteDTO, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{id}")
	@ApiOperation(value="Atualiza dados de um cliente", notes="Atualizar Cliente")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Cliente Atualizado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<ClienteRequestDTO> atualizar(@PathVariable Long id,@RequestBody ClienteRequestDTO cliente) {
		return ResponseEntity.ok(servico.atualizar(id, cliente));
	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Remove um cliente", notes="Remover Cliente")
	@ApiResponses(value= {
	@ApiResponse(code=204, message="Cliente Removido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

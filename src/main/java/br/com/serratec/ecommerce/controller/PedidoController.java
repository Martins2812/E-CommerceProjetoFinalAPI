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
import br.com.serratec.ecommerce.dto.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.PedidoResponseDTO;
import br.com.serratec.ecommerce.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("api/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService servico;
	
	@GetMapping
	@ApiOperation(value="Lista todos os Pedidos", notes="Listagem dos Pedidos")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todos os Pedidos"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<List<PedidoResponseDTO>> obterTodosOsPedidos() {
		
		List<PedidoResponseDTO> lista = servico.obterTodosOsPedidos();
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/{id}")
	@ApiOperation(value="Retorna ao Pedido", notes="Pedido")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna um Pedido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<PedidoResponseDTO> obterPedidoPorId(@PathVariable Long id) {
		
		Optional<PedidoResponseDTO> optPedido = servico.obterPedidoPorId(id);
		return ResponseEntity.ok(optPedido.get());
	}
	
	
	@PostMapping
	@ApiOperation(value="Insere os dados de um Pedido", notes="Inserir Pedido")
	@ApiResponses(value= {
	@ApiResponse(code=201, message="Pedido adicionado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<PedidoResponseDTO> cadastrar (@RequestBody PedidoRequestDTO pedido) {
		
		PedidoResponseDTO pedidoDTO = servico.cadastrar(pedido);
		return new ResponseEntity<>(pedidoDTO, HttpStatus.CREATED);	
	}
	
	
	@PutMapping("/{id}")
	@ApiOperation(value="Atualiza dados de um Pedido", notes="Atualizar Pedido")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Pedido Atualizado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<PedidoResponseDTO> atualizar(@PathVariable Long id,@RequestBody PedidoRequestDTO pedido) {
		return ResponseEntity.ok(servico.atualizar(id, pedido));
	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Remove um Pedido", notes="Remover Pedido")
	@ApiResponses(value= {
	@ApiResponse(code=204, message="Pedido Removido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

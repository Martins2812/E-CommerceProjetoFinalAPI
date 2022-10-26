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
import br.com.serratec.ecommerce.dto.ItemPedidoRequestDTO;
import br.com.serratec.ecommerce.dto.ItemPedidoResponseDTO;
import br.com.serratec.ecommerce.service.ItemPedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("api/itemPedido")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoService servico;
	
	@GetMapping
	@ApiOperation(value="Listar todos ItensPedido", notes="Listagem dos ItensPedido")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todas os ItensPedido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<List<ItemPedidoResponseDTO>> obterTodosOsItemPedidos() {
		
		List<ItemPedidoResponseDTO> lista = servico.obterTodosOsItemPedidos();
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/{id}")
	@ApiOperation(value="Retorna ao ItemPedido", notes="ItemPedido")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna um ItemPedido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<ItemPedidoRequestDTO> obterItemPedidoPorId(@PathVariable Long id) {
		
		Optional<ItemPedidoRequestDTO> optItemPedido = servico.obterItemPedidoPorId(id);
		return ResponseEntity.ok(optItemPedido.get());
	}
	
	
	@PostMapping
	@ApiOperation(value="Insere os dados de um ItemPedido", notes="Inserir ItemPedido")
	@ApiResponses(value= {
	@ApiResponse(code=201, message="ItemPedido adicionado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<ItemPedidoResponseDTO> cadastrar (@RequestBody ItemPedidoRequestDTO itemPedido) {
		
		ItemPedidoResponseDTO itemPedidoDTO = servico.cadastrar(itemPedido);
		return new ResponseEntity<>(itemPedidoDTO, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{id}")
	@ApiOperation(value="Atualiza dados de um ItemPedido", notes="Atualizar ItemPedido")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="ItemPedido Atualizado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<ItemPedidoRequestDTO> atualizar(@PathVariable Long id,@RequestBody ItemPedidoRequestDTO itemPedido) {
		return ResponseEntity.ok(servico.atualizar(id, itemPedido));
	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Remove um ItemPedido", notes="Remover itemPedido")
	@ApiResponses(value= {
	@ApiResponse(code=204, message="ItemPedido Removido"),
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

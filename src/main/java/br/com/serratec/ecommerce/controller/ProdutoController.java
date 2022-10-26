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
import br.com.serratec.ecommerce.dto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.dto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService servico;	
	
	@GetMapping
	@ApiOperation(value="Lista todos os Produtos", notes="Listagem dos Produtos")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todas os Produtos"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<List<ProdutoResponseDTO>> obterTodosOsProdutos() {
		
		List<ProdutoResponseDTO> lista = servico.obterTodosOsProdutos();
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/{id}")
	@ApiOperation(value="Retorna ao Produto", notes="Produto")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna um Produto"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<ProdutoResponseDTO> obterProdutoPorId(@PathVariable Long id) {
		
		Optional<ProdutoResponseDTO> optProduto = servico.obterProdutoPorId(id);
		return ResponseEntity.ok(optProduto.get());
	}
	
	
	@PostMapping
	@ApiOperation(value="Insere os dados de um Produto", notes="Inserir Produto")
	@ApiResponses(value= {
	@ApiResponse(code=201, message="Produto adicionado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<ProdutoResponseDTO> cadastrar (@RequestBody ProdutoRequestDTO produto) {
		
		ProdutoResponseDTO produtoDTO = servico.cadastrar(produto);
		return new ResponseEntity<>(produtoDTO, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{id}")
	@ApiOperation(value="Atualiza dados de um Produto", notes="Atualizar Produto")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Produto Atualizado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<ProdutoRequestDTO> atualizar(@PathVariable Long id,@RequestBody ProdutoRequestDTO produto) {
		return ResponseEntity.ok(servico.atualizar(id, produto));
	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Remove um Produto", notes="Remover Produto")
	@ApiResponses(value= {
	@ApiResponse(code=204, message="Produto Removido"),
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

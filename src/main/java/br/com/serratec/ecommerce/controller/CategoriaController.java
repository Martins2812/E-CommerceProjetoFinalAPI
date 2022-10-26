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
import br.com.serratec.ecommerce.model.Categoria;
import br.com.serratec.ecommerce.service.CategoriaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService servico;
	
	@GetMapping
	@ApiOperation(value="Lista todas as Categorias", notes="Listagem de Categorias")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todas as Categorias"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<List<Categoria>> obterTodasAsCategorias() {
		
		List<Categoria> lista = servico.obterTodasAsCategorias();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value="Retorna uma categoria", notes="Categoria")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna uma categoria"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<Categoria> obterCategoriaPorId(@PathVariable Long id) {
		
		Optional<Categoria> optCategoria = servico.obterCategoriaPorId(id);
		return ResponseEntity.ok(optCategoria.get());
	}
	
	@PostMapping
	@ApiOperation(value="Insere os dados de uma Categoria", notes="Inserir Categoria")
	@ApiResponses(value= {
	@ApiResponse(code=201, message="Categoria adicionada"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<Categoria> cadastrar (@RequestBody Categoria categoria) {
		
		categoria = servico.cadastrar(categoria);
		return new ResponseEntity<>(categoria, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value="Atualiza dados de uma categoria", notes="Atualizar Categoria")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Categoria Atualizada"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<Categoria> atualizar(@PathVariable Long id,@RequestBody Categoria categoria) {
		return ResponseEntity.ok(servico.atualizar(id, categoria));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Remove uma categoria", notes="Remover Categoria")
	@ApiResponses(value= {
	@ApiResponse(code=204, message="Categoria Removida"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<Object> deletar(@PathVariable Long id) {
		
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

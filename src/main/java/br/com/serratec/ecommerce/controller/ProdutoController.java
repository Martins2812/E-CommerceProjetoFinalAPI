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

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService servico;
	
	@GetMapping
	public ResponseEntity<List<ProdutoResponseDTO>> obterTodosOsProdutos() {
		
		List<ProdutoResponseDTO> lista = servico.obterTodosOsProdutos();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> obterProdutoPorId(@PathVariable Long id) {
		
		Optional<ProdutoResponseDTO> optProduto = servico.obterProdutoPorId(id);
		return ResponseEntity.ok(optProduto.get());
	}
	
	@PostMapping
	public ResponseEntity<ProdutoResponseDTO> cadastrar (@RequestBody ProdutoRequestDTO produto) {
		
		ProdutoResponseDTO produtoDTO = servico.cadastrar(produto);
		return new ResponseEntity<>(produtoDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoRequestDTO> atualizar(@PathVariable Long id,@RequestBody ProdutoRequestDTO produto) {
		return ResponseEntity.ok(servico.atualizar(id, produto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}

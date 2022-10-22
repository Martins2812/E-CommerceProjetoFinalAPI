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

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService servico;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> obterTodasAsCategorias() {
		List<Categoria> lista = servico.obterTodasAsCategorias();
		return ResponseEntity.ok(lista); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> obterCategoriaPorId(@PathVariable Long id) {
		Optional<Categoria> optCategoria = servico.obterCategoriaPorId(id);
		return ResponseEntity.ok(optCategoria.get());
	}
	
	@PostMapping
	public ResponseEntity<Categoria> cadastrar (@RequestBody Categoria categoria) {
		categoria = servico.cadastrar(categoria);
		return new ResponseEntity<>(categoria, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long id,@RequestBody Categoria categoria) {
		return ResponseEntity.ok(servico.atualizar(id, categoria));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
	}
}

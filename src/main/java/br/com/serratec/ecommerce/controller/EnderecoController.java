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

import br.com.serratec.ecommerce.dto.EnderecoDTO;
import br.com.serratec.ecommerce.service.EnderecoService;


@RestController
@RequestMapping("api/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService servico;
	
	@GetMapping
	public ResponseEntity<List<EnderecoDTO>> obterTodosOsEnderecos() {
		
		List<EnderecoDTO> lista = servico.obterTodosOsEnderecos();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EnderecoDTO> obterEnderecoPorId(@PathVariable Long id) {
		
		Optional<EnderecoDTO> optEndereco = servico.obterEnderecoPorId(id);
		return ResponseEntity.ok(optEndereco.get());
	}
	
	@PostMapping
	public ResponseEntity<EnderecoDTO> cadastrar (@RequestBody EnderecoDTO endereco) {
		
		EnderecoDTO enderecoDTO = servico.cadastrar(endereco);
		return new ResponseEntity<>(enderecoDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EnderecoDTO> atualizar(@PathVariable Long id,@RequestBody EnderecoDTO endereco) {
		return ResponseEntity.ok(servico.atualizar(id, endereco));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}

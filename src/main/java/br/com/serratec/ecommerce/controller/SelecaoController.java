package br.com.serratec.ecommerce.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.serratec.ecommerce.dto.SelecaoRequestDTO;
import br.com.serratec.ecommerce.dto.SelecaoResponseDTO;
import br.com.serratec.ecommerce.service.SelecaoService;

@RestController
@RequestMapping("api/selecao")

public class SelecaoController {

	@Autowired
	private SelecaoService servico;
	
	@GetMapping
	public ResponseEntity<List<SelecaoResponseDTO>> obterTodos() {
		List<SelecaoResponseDTO> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SelecaoResponseDTO> obterPorId(@PathVariable Long id) {
		Optional<SelecaoResponseDTO> optSelecao = servico.obterPorId(id);
		if(optSelecao.isPresent()){
			return ResponseEntity.ok(optSelecao.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<SelecaoResponseDTO> cadastrar(@ModelAttribute SelecaoRequestDTO selecao) {
		SelecaoResponseDTO selecaoDTO = servico.cadastrar(selecao);
		return new ResponseEntity<>(selecaoDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SelecaoResponseDTO> atualizar(@PathVariable Long id, @RequestBody SelecaoRequestDTO selecao) {
		return ResponseEntity.ok(servico.atualizar(id, selecao));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}


package br.com.serratec.ecommerce.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
import com.google.gson.Gson;
import br.com.serratec.ecommerce.dto.EnderecoRequestDTO;
import br.com.serratec.ecommerce.dto.EnderecoResponseDTO;
import br.com.serratec.ecommerce.model.Endereco;
import br.com.serratec.ecommerce.service.EnderecoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService servico;

	@GetMapping
	@ApiOperation(value="Lista todos os Enderecos", notes="Listagem de Enderecos")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todos os Enderecos"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<List<EnderecoResponseDTO>> obterTodosOsEnderecos() {

		List<EnderecoResponseDTO> lista = servico.obterTodosOsEnderecos();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/{id}")
	@ApiOperation(value="Retorna um endereco", notes="Endereco")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna um Endereco"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<EnderecoResponseDTO> obterEnderecoPorId(@PathVariable Long id) {

		Optional<EnderecoResponseDTO> optEndereco = servico.obterEnderecoPorId(id);
		return ResponseEntity.ok(optEndereco.get());
	}

	@PostMapping
	@ApiOperation(value="Insere os dados de um Endereco", notes="Inserir Endereco")
	@ApiResponses(value= {
	@ApiResponse(code=201, message="Endereco adicionado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<EnderecoResponseDTO> cadastrar(@RequestBody EnderecoRequestDTO endereco) 
			throws Exception {         
			URL url = new URL ("https://viacep.com.br/ws/"+ endereco.getCep()+"/json/");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8")); 
			
			String cep = "";
			StringBuilder jsonCep=new StringBuilder(); 
			
			while((cep=br.readLine())!=null) { 
				 jsonCep.append(cep);  
			}
			Endereco userAux = new Gson().fromJson(jsonCep.toString(), Endereco.class);
			endereco.setCep(userAux.getCep());
			endereco.setLogradouro(userAux.getLogradouro());
			endereco.setComplemento(userAux.getComplemento());
			endereco.setBairro(userAux.getBairro()); 
			endereco.setLocalidade(userAux.getLocalidade());
			endereco.setUf(userAux.getUf());  
			EnderecoResponseDTO enderecoDTO = servico.cadastrar(endereco);
			return new ResponseEntity<>(enderecoDTO, HttpStatus.CREATED);     
			}

	@PutMapping("/{id}")
	@ApiOperation(value="Atualiza dados de um endereco", notes="Atualizar Endereco")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Endereco Atualizado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<EnderecoRequestDTO> atualizar(@PathVariable Long id,
			@RequestBody EnderecoRequestDTO endereco) {
		return ResponseEntity.ok(servico.atualizar(id, endereco));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value="Remove um endereco", notes="Remover Endereco")
	@ApiResponses(value= {
	@ApiResponse(code=204, message="Endereco Removido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

	



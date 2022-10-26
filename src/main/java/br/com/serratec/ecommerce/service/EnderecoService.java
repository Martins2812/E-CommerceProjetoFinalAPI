package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.serratec.ecommerce.dto.EnderecoRequestDTO;
import br.com.serratec.ecommerce.dto.EnderecoResponseDTO;
import br.com.serratec.ecommerce.exception.ResourceBadRequestException;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.model.Endereco;
import br.com.serratec.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repositorio;
	
	private ModelMapper mapper = new ModelMapper();
	
	public List<EnderecoResponseDTO> obterTodosOsEnderecos() {
		
		List<Endereco> lista = repositorio.findAll();
		
		var novaLista = new ArrayList<EnderecoResponseDTO>();
		
		for (Endereco endereco : lista) {
			novaLista.add(mapper.map(endereco, EnderecoResponseDTO.class));
		}
		return novaLista;
	}
	
	public Optional<EnderecoResponseDTO> obterEnderecoPorId(Long id) {
		
		Optional<Endereco> optEndereco = repositorio.findById(id);
		
		if (optEndereco.isEmpty()) {
			throw new ResourceNotFoundException("Não é possivel encontrar o endereço com o id: " + id);
		}
		EnderecoResponseDTO dto = mapper.map(optEndereco.get(), EnderecoResponseDTO.class);
		return Optional.of(dto);
	}
	
	public EnderecoResponseDTO cadastrar (EnderecoRequestDTO endereco) {
		
		validarCep(endereco);
		
		var enderecoModel = mapper.map(endereco, Endereco.class);
		
		enderecoModel.setId(null);
		enderecoModel = repositorio.save(enderecoModel);
		
		var response = mapper.map(enderecoModel, EnderecoResponseDTO.class);
		
		return response;
	}
	
	public EnderecoRequestDTO atualizar (Long id, EnderecoRequestDTO endereco) {
		
		validarCep(endereco);
		
		var enderecoModel = mapper.map(endereco, Endereco.class);
		
		enderecoModel.setId(id);
		enderecoModel = repositorio.save(enderecoModel);

		return mapper.map(enderecoModel, EnderecoRequestDTO.class);
	}
	
	public void deletar(Long id) {
		obterEnderecoPorId(id);
		repositorio.deleteById(id);
	}
	
	private void validarCep(EnderecoRequestDTO endereco) {
		
		if(endereco.getCep() == null) {
			throw new ResourceBadRequestException("O endereço deve ter um CEP.");
		}else if (endereco.getCep().length() > 9){
			throw new ResourceBadRequestException("Digite um número correto de CEP ex: '00000000'");
		}
	}
}

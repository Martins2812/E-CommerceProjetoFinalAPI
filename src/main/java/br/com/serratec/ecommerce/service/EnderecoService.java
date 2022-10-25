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
		
		validarCEP(endereco);
		validarCidade(endereco);
		validarBairro(endereco);
		validarComplemento(endereco);
		validarRua(endereco);
		validarUF(endereco);
		
		var contaModel = mapper.map(endereco, Endereco.class);
		
		contaModel.setId(null);
		contaModel = repositorio.save(contaModel);
		
		var response = mapper.map(contaModel, EnderecoResponseDTO.class);
		
		return response;
	}
	
	public EnderecoRequestDTO atualizar (Long id, EnderecoRequestDTO endereco) {
		
		obterEnderecoPorId(id);
		
		validarCEP(endereco);
		validarCidade(endereco);
		validarBairro(endereco);
		validarComplemento(endereco);
		validarRua(endereco);
		validarUF(endereco);
		
		var contaModel = mapper.map(endereco, Endereco.class);
		
		contaModel.setId(id);
		contaModel = repositorio.save(contaModel);

		return mapper.map(contaModel, EnderecoRequestDTO.class);
	}
	
	public void deletar(Long id) {
		obterEnderecoPorId(id);
		repositorio.deleteById(id);
	}
	
	private void validarCEP(EnderecoRequestDTO endereco) {
		
		if(endereco.getCep() == null) {
			throw new ResourceBadRequestException("O endereço deve ter um CEP.");
		} if (endereco.getCep().length() > 8) {
			throw new ResourceBadRequestException("O CEP não pode conter mais que 8 caracteres");
		}
	}
	
	private void validarRua(EnderecoRequestDTO endereco) {
		
		if(endereco.getLogradouro() == null) {
			throw new ResourceBadRequestException("O endereço deve ter uma Rua.");
		} if (endereco.getLogradouro().length() > 50) {
			throw new ResourceBadRequestException("Excedido o número máximo de caracteres");
		}
	}

	private void validarBairro(EnderecoRequestDTO endereco) {
	
	if(endereco.getBairro() == null) {
		throw new ResourceBadRequestException("O endereço deve ter um Bairro.");
	} if (endereco.getLogradouro().length() > 50) {
		throw new ResourceBadRequestException("Excedido o número máximo de caracteres");
	}
}

	private void validarCidade(EnderecoRequestDTO endereco) {
	
	if(endereco.getLocalidade() == null) {
		throw new ResourceBadRequestException("O endereço deve ter uma Cidade.");
	} if (endereco.getLogradouro().length() > 30) {
		throw new ResourceBadRequestException("Excedido o número máximo de caracteres");
	}
}


	private void validarComplemento(EnderecoRequestDTO endereco) {
	
	if(endereco.getComplemento() == null) {
		throw new ResourceBadRequestException("O endereço deve ter um CEP.");
	} if (endereco.getLogradouro().length() > 100) {
		throw new ResourceBadRequestException("Excedido o número máximo de caracteres");
	}
}

	private void validarUF(EnderecoRequestDTO endereco) {
	
	if(endereco.getUf() == null) {
		throw new ResourceBadRequestException("O endereço deve ter um UF.");
	} if (endereco.getLogradouro().length() > 2) {
		throw new ResourceBadRequestException("Excedido o número máximo de caracteres");
	}
}


}

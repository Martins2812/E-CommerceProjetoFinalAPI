package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.EnderecoDTO;
import br.com.serratec.ecommerce.exception.ResourceBadRequestException;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.model.Endereco;
import br.com.serratec.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repositorio;
	
	private ModelMapper mapper = new ModelMapper();
	
	public List<EnderecoDTO> obterTodosOsEnderecos() {
		
		List<Endereco> lista = repositorio.findAll();
		
		var novaLista = new ArrayList<EnderecoDTO>();
		
		for (Endereco endereco : lista) {
			novaLista.add(mapper.map(endereco, EnderecoDTO.class));
		}
		return novaLista;
	}
	
	public Optional<EnderecoDTO> obterEnderecoPorId(Long id) {
		
		Optional<Endereco> optEndereco = repositorio.findById(id);
		
		if (optEndereco.isEmpty()) {
			throw new ResourceNotFoundException("Não é possivel encontrar o endereço com o id: " + id);
		}
		EnderecoDTO dto = mapper.map(optEndereco.get(), EnderecoDTO.class);
		return Optional.of(dto);
	}
	
	public EnderecoDTO cadastrar (EnderecoDTO endereco) {
		
		validarModelo(endereco);
		
		var contaModel = mapper.map(endereco, Endereco.class);
		
		contaModel.setId(null);
		contaModel = repositorio.save(contaModel);
		
		var response = mapper.map(contaModel, EnderecoDTO.class);
		
		return response;
	}
	
	public EnderecoDTO atualizar (Long id, EnderecoDTO endereco) {
		
		obterEnderecoPorId(id);
		
		validarModelo(endereco);
		
		var contaModel = mapper.map(endereco, Endereco.class);
		
		contaModel.setId(id);
		contaModel = repositorio.save(contaModel);

		return mapper.map(contaModel, EnderecoDTO.class);
	}
	
	public void deletar(Long id) {
		obterEnderecoPorId(id);
		repositorio.deleteById(id);
	}
	
	private void validarModelo(EnderecoDTO endereco) {
		
		if(endereco.getCep() == null) {
			throw new ResourceBadRequestException("O endereço deve ter um CEP.");
		}
	}

}

package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.ClienteRequestDTO;
import br.com.serratec.ecommerce.dto.ClienteResponseDTO;
import br.com.serratec.ecommerce.exception.ResourceBadRequestException;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.model.Cliente;
import br.com.serratec.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repositorio;
	
	private ModelMapper mapper = new ModelMapper();
	
	public List<ClienteRequestDTO> obterTodosOsClientes() {
		
		List<Cliente> lista = repositorio.findAll();
		
		var novaLista = new ArrayList<ClienteRequestDTO>();
		
		for (Cliente cliente : lista) {
			novaLista.add(mapper.map(cliente, ClienteRequestDTO.class));
		}
		return novaLista;
	}
	
	public Optional<ClienteResponseDTO> obterClientePorId(Long id) {
		
		Optional<Cliente> optCliente = repositorio.findById(id);
		
		if (optCliente.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o Cliente com id :" + id);
		}
		
		ClienteResponseDTO dto = mapper.map(optCliente.get(), ClienteResponseDTO.class);
		return Optional.of(dto);
	}
	
	public ClienteRequestDTO cadastrar (ClienteRequestDTO cliente) {
		validarCpf(cliente);
		validarNomeCompleto(cliente);
		validarEmail(cliente);
		var contaModel = mapper.map(cliente, Cliente.class);
		
		contaModel.setId(null);
		contaModel = repositorio.save(contaModel);
		
		var response = mapper.map(contaModel, ClienteRequestDTO.class);
		
		return response;
	}
	
	public ClienteRequestDTO atualizar (Long id, ClienteRequestDTO cliente) {
		validarCpf(cliente);
		obterClientePorId(id);
		validarNomeCompleto(cliente);
		validarEmail(cliente);
		var contaModel = mapper.map(cliente, Cliente.class);
		
		contaModel.setId(id);
		contaModel = repositorio.save(contaModel);

		return mapper.map(contaModel, ClienteRequestDTO.class);
	}
	
	public void deletar(Long id) {
		obterClientePorId(id);
		repositorio.deleteById(id);
	}

	private void validarNomeCompleto(ClienteRequestDTO cliente) {
		if(cliente.getNomeCompleto() == null) {
			throw new ResourceBadRequestException("O cliente deve ter um nome.");
		}else if (cliente.getNomeCompleto().length() > 60){	
			throw new ResourceBadRequestException("Digite um nome menor do que 60 caracteres");
		}
	}
	
	private void validarCpf(ClienteRequestDTO cliente) {
		if(cliente.getCpf() == null) {
			throw new ResourceBadRequestException("O CPF não pode ser nulo");
		}else if (cliente.getCpf().length() < 14 && cliente.getCpf().length() > 14){
			throw new ResourceBadRequestException("Digite um número correto de CPF ex'000.000.000-00'");
		}
	}
	
	private void validarEmail(ClienteRequestDTO cliente) {
		if(cliente.getEmail() == null) {
			throw new ResourceBadRequestException("O cliente deve ter um e-mail válido");
		}else if (cliente.getEmail().length() > 30){	
			throw new ResourceBadRequestException("Digite um nome menor do que 60 caracteres");
		}
	}
	
	
	
}

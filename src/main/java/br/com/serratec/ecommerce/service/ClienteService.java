package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.ClienteDTO;
import br.com.serratec.ecommerce.exception.ResourceBadRequestException;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.model.Cliente;
import br.com.serratec.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repositorio;
	
	private ModelMapper mapper = new ModelMapper();
	
	public List<ClienteDTO> obterTodosOsClientes() {
		
		List<Cliente> lista = repositorio.findAll();
		
		var novaLista = new ArrayList<ClienteDTO>();
		
		for (Cliente cliente : lista) {
			novaLista.add(mapper.map(cliente, ClienteDTO.class));
		}
		return novaLista;
	}
	
	public Optional<ClienteDTO> obterClientePorId(Long id) {
		
		Optional<Cliente> optCliente = repositorio.findById(id);
		
		if (optCliente.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o Cliente com id :" + id);
		}
		
		ClienteDTO dto = mapper.map(optCliente.get(), ClienteDTO.class);
		return Optional.of(dto);
	}
	
	public ClienteDTO cadastrar (ClienteDTO cliente) {
		
		validarModelo(cliente);
		
		var contaModel = mapper.map(cliente, Cliente.class);
		
		contaModel.setId(null);
		contaModel = repositorio.save(contaModel);
		
		var response = mapper.map(contaModel, ClienteDTO.class);
		
		return response;
	}
	
	public ClienteDTO atualizar (Long id, ClienteDTO cliente) {
		
		obterClientePorId(id);
		
		validarModelo(cliente);
		
		var contaModel = mapper.map(cliente, Cliente.class);
		
		contaModel.setId(id);
		contaModel = repositorio.save(contaModel);

		return mapper.map(contaModel, ClienteDTO.class);
	}
	
	public void deletar(Long id) {
		obterClientePorId(id);
		repositorio.deleteById(id);
	}

	private void validarModelo(ClienteDTO cliente) {
		
		if(cliente.getNomeCompleto() == null) {
			throw new ResourceBadRequestException("O cliente deve ter um nome.");
		}
	}
}

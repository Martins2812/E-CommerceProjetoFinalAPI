package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.Cliente;
import br.com.serratec.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repositorio;
	
	public List<Cliente> obterTodosOsClientes() {
		return repositorio.findAll();
	}
	
	public Optional<Cliente> obterClientePorId(Long id) {
		
		Optional<Cliente> optCliente = repositorio.findById(id);
		
		if (optCliente.isEmpty()) {
			
		}
		return optCliente;
	}
	
	public Cliente cadastrar (Cliente cliente) {
		
		cliente.setId(null);
		return repositorio.save(cliente);
	}
	
	public Cliente atualizar (Long id, Cliente cliente) {
		
		cliente.setId(id);
		return repositorio.save(cliente);
	}
	
	public void deletar(Long id) {
		repositorio.deleteById(id);
	}

}

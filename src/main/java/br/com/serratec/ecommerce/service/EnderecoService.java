package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.Endereco;
import br.com.serratec.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repositorio;
	
	public List<Endereco> obterTodosOsEnderecos() {
		return repositorio.findAll();
	}
	
	public Optional<Endereco> obterEnderecoPorId(Long id) {
		
		Optional<Endereco> optEndereco = repositorio.findById(id);
		
		if (optEndereco.isEmpty()) {
			
		}
		return optEndereco;
	}
	
	public Endereco cadastrar (Endereco endereco) {
		
		endereco.setId(null);
		return repositorio.save(endereco);
	}
	
	public Endereco atualizar (Long id, Endereco endereco) {
		
		endereco.setId(id);
		return repositorio.save(endereco);
	}
	
	public void deletar(Long id) {
		repositorio.deleteById(id);
	}

}

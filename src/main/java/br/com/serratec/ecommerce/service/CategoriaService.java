package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.Categoria;
import br.com.serratec.ecommerce.repository.CategoriaRepository;


@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repositorio;
	
	public List<Categoria> obterTodasAsCategorias() {
		return repositorio.findAll();
	}
	
	public Optional<Categoria> obterCategoriaPorId(Long id) {
		
		Optional<Categoria> optCategoria = repositorio.findById(id);
		
		if (optCategoria.isEmpty()) {
			
		}
		return optCategoria;
	}
	
	public Categoria cadastrar (Categoria categoria) {
		
		categoria.setId(null);
		return repositorio.save(categoria);
	}
	
	public Categoria atualizar (Long id, Categoria categoria) {
		
		categoria.setId(id);
		return repositorio.save(categoria);
	}
	
	public void deletar(Long id) {
		repositorio.deleteById(id);
	}

}

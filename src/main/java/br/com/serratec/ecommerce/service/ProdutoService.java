package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.model.Produto;
import br.com.serratec.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repositorio;
	
	private ModelMapper mapper = new ModelMapper(); 

	public List<ProdutoRequestDTO> obterTodosOsProdutos() {

		List<Produto> lista = repositorio.findAll();
		
		var novaLista = new ArrayList<ProdutoRequestDTO>();
		
		for (Produto produto : lista) {
			novaLista.add(mapper.map(produto, ProdutoRequestDTO.class));
		}
		return novaLista;
	}

	public Optional<ProdutoRequestDTO> obterProdutoPorId(Long id) {

		Optional<Produto> optProduto = repositorio.findById(id);

		if (optProduto.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o produto com id :" + id);
		}
		
		ProdutoRequestDTO dto = mapper.map(optProduto.get(), ProdutoRequestDTO.class);
		return Optional.of(dto);
	}

	public ProdutoRequestDTO cadastrar(ProdutoRequestDTO produto) {
		
		var contaModel = mapper.map(produto, Produto.class);
		
		contaModel.setId(null);
		contaModel = repositorio.save(contaModel);
		
		var response = mapper.map(contaModel, ProdutoRequestDTO.class);
		
		return response;
	}

	public ProdutoRequestDTO atualizar(Long id, ProdutoRequestDTO produto) {
		obterProdutoPorId(id);
		
		var contaModel = mapper.map(produto, Produto.class);
		
		contaModel.setId(id);
		contaModel = repositorio.save(contaModel);

		return mapper.map(contaModel, ProdutoRequestDTO.class);
	}

	public void deletar(Long id) {
		obterProdutoPorId(id);
		repositorio.deleteById(id);
	}
}

package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.ProdutoDTO;
import br.com.serratec.ecommerce.exception.ResourceBadRequestException;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.model.Produto;
import br.com.serratec.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repositorio;
	
	private ModelMapper mapper = new ModelMapper(); 

	public List<ProdutoDTO> obterTodosOsProdutos() {

		List<Produto> lista = repositorio.findAll();
		
		var novaLista = new ArrayList<ProdutoDTO>();
		
		for (Produto produto : lista) {
			novaLista.add(mapper.map(produto, ProdutoDTO.class));
		}
		return novaLista;
	}

	public Optional<ProdutoDTO> obterProdutoPorId(Long id) {

		Optional<Produto> optProduto = repositorio.findById(id);

		if (optProduto.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o produto com id :" + id);
		}
		
		ProdutoDTO dto = mapper.map(optProduto.get(), ProdutoDTO.class);
		return Optional.of(dto);
	}

	public ProdutoDTO cadastrar(ProdutoDTO produto) {

		validarModelo(produto);
		
		var contaModel = mapper.map(produto, Produto.class);
		
		contaModel.setId(null);
		contaModel = repositorio.save(contaModel);
		
		var response = mapper.map(contaModel, ProdutoDTO.class);
		
		return response;
	}

	public ProdutoDTO atualizar(Long id, ProdutoDTO produto) {
		obterProdutoPorId(id);
		
		validarModelo(produto);
		
		var contaModel = mapper.map(produto, Produto.class);
		
		contaModel.setId(id);
		contaModel = repositorio.save(contaModel);

		return mapper.map(contaModel, ProdutoDTO.class);
	}

	public void deletar(Long id) {
		obterProdutoPorId(id);
		repositorio.deleteById(id);
	}

	private void validarModelo(ProdutoDTO produto) {
		
		if(produto.getNome() == null) {
			throw new ResourceBadRequestException("O produto deve ter um nome.");
		}
	}
}

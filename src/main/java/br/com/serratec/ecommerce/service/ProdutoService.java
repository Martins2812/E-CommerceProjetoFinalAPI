package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.exception.ResourceBadRequestException;
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

		validarModelo(produto);
		validarDataDeCadastro(produto);
		validarDescricao(produto);
		validarQuantidadeNoEstoque(produto);
		validarValor(produto);
		
		var contaModel = mapper.map(produto, Produto.class);
		
		contaModel.setId(null);
		contaModel = repositorio.save(contaModel);
		
		var response = mapper.map(contaModel, ProdutoRequestDTO.class);
		
		return response;
	}

	public ProdutoRequestDTO atualizar(Long id, ProdutoRequestDTO produto) {
		obterProdutoPorId(id);
		
		validarModelo(produto);
		validarDataDeCadastro(produto);
		validarDescricao(produto);
		validarQuantidadeNoEstoque(produto);
		validarValor(produto);
		
		var contaModel = mapper.map(produto, Produto.class);
		
		contaModel.setId(id);
		contaModel = repositorio.save(contaModel);

		return mapper.map(contaModel, ProdutoRequestDTO.class);
	}

	public void deletar(Long id) {
		obterProdutoPorId(id);
		repositorio.deleteById(id);
	}

	private void validarModelo(ProdutoRequestDTO produto) {
		
		if(produto.getNome() == null) {
			throw new ResourceBadRequestException("O produto deve ter um nome.");
		}
	}
	
	private void validarDescricao(ProdutoRequestDTO produto) {
		
		if(produto.getDescricao() == null) {
			throw new ResourceBadRequestException("O produto deve ter uma descrição.");
		} if (produto.getDescricao().length() > 100) {
			throw new ResourceBadRequestException("Excedido o número máximo de caracteres");
		}
	}

	private void validarDataDeCadastro(ProdutoRequestDTO produto) {
	
	if(produto.getData_cadastro() == null) {
		throw new ResourceBadRequestException("O produto deve ter a data de cadastro.");
		} 
	}

	private void validarQuantidadeNoEstoque(ProdutoRequestDTO produto) {
	
	if(produto.getQtd_estoque() == null) {
		throw new ResourceBadRequestException("O produto deve ter a quantidade no estoque.");
		} 
	}

	private void validarValor(ProdutoRequestDTO produto) {
	
	if(produto.getValor_unitario() == null) {
		throw new ResourceBadRequestException("O produto deve ter um valor.");
		}
	}
}

package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.serratec.ecommerce.dto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.dto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.exception.ResourceBadRequestException;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.model.Produto;
import br.com.serratec.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repositorio;

	private ModelMapper mapper = new ModelMapper();

	public List<ProdutoResponseDTO> obterTodosOsProdutos() {

		List<Produto> lista = repositorio.findAll();

		var novaLista = new ArrayList<ProdutoResponseDTO>();

		for (Produto produto : lista) {
			novaLista.add(mapper.map(produto, ProdutoResponseDTO.class));
		}
		return novaLista;
	}

	public Optional<ProdutoResponseDTO> obterProdutoPorId(Long id) {

		Optional<Produto> optProduto = repositorio.findById(id);

		if (optProduto.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o produto com id :" + id);
		}

		ProdutoResponseDTO dto = mapper.map(optProduto.get(), ProdutoResponseDTO.class);
		return Optional.of(dto);
	}

	public ProdutoResponseDTO cadastrar(ProdutoRequestDTO produto) {
		validarDescricao(produto);
		validarNome(produto);
		validarValorUnitario(produto);
		var produtoModel = mapper.map(produto, Produto.class);

		produtoModel.setId(null);
		produtoModel = repositorio.save(produtoModel);

		var response = mapper.map(produtoModel, ProdutoResponseDTO.class);

		return response;
	}

	public ProdutoRequestDTO atualizar(Long id, ProdutoRequestDTO produto) {
		validarDescricao(produto);
		validarNome(produto);
		validarValorUnitario(produto);
		var produtoModel = mapper.map(produto, Produto.class);

		produtoModel.setId(id);
		produtoModel = repositorio.save(produtoModel);

		return mapper.map(produtoModel, ProdutoRequestDTO.class);
	}

	public void deletar(Long id) {
		obterProdutoPorId(id);
		repositorio.deleteById(id);
	}

	private void validarNome(ProdutoRequestDTO produto) {

		if (produto.getNome() == null) {
			throw new ResourceBadRequestException("O nome do produto não pode ser nulo.");
		} else if (produto.getNome().length() > 30) {
			throw new ResourceBadRequestException("Digite um nome com até 30 caracteres. ");
		}
	}
	
	private void validarDescricao(ProdutoRequestDTO produto) {

		if (produto.getDescricao().length() > 100) {
			throw new ResourceBadRequestException("O nome do produto não pode ultrapassar 100 caracteres.");
		} 
	}
	
	private void validarValorUnitario(ProdutoRequestDTO produto) {

		if (produto.getValor_unitario() == null) {
			throw new ResourceBadRequestException("O valor unitario não pode ser nulo");
		} 
	}
	
	
}

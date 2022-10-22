package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.Produto;
import br.com.serratec.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repositorio;

	public List<Produto> obterTodosOsProdutos() {
		return repositorio.findAll();
	}

	public Optional<Produto> obterProdutoPorId(Long id) {

		Optional<Produto> optProduto = repositorio.findById(id);

		if (optProduto.isEmpty()) {

		}
		return optProduto;
	}

	public Produto cadastrar(Produto produto) {

		produto.setId(null);
		return repositorio.save(produto);
	}

	public Produto atualizar(Long id, Produto produto) {
		obterProdutoPorId(id);
		produto.setId(id);
		return repositorio.save(produto);
	}

	public void deletar(Long id) {
		repositorio.deleteById(id);
	}

}

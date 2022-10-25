package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import br.com.serratec.ecommerce.dto.SelecaoRequestDTO;
import br.com.serratec.ecommerce.dto.SelecaoResponseDTO;
import br.com.serratec.ecommerce.exception.ResourceBadRequestException;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.model.Selecao;
import br.com.serratec.ecommerce.repository.SelecaoRepository;

@Service
public class SelecaoService {

	@Autowired
	private SelecaoRepository repositorio;

	private ModelMapper mapper = new ModelMapper();

	public List<SelecaoResponseDTO> obterTodos() {
		List<Selecao> lista = repositorio.findAll();
		var novaLista = new ArrayList<SelecaoResponseDTO>();
		for (Selecao selecao : lista) {
			novaLista.add(mapper.map(selecao, SelecaoResponseDTO.class));
		}
		return novaLista;
	}

	public Optional<SelecaoResponseDTO> obterPorId(Long id) {
		Optional<Selecao> optSelecao = repositorio.findById(id);
		if (optSelecao.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o selecao com id " + id);
		}
		SelecaoResponseDTO dto = mapper.map(optSelecao.get(), SelecaoResponseDTO.class);
		return Optional.of(dto);
	}

	public SelecaoResponseDTO cadastrar(SelecaoRequestDTO selecao) {
		validarNomeSelecao(selecao);
		validarDescricao(selecao);
		validarquantGols(selecao);
		List<Selecao> selecaoDescricao = repositorio.findByDescricao(selecao.getDescricao());
		if (selecaoDescricao.size() > 0) {
			throw new ResourceBadRequestException("Descrição já cadastrada!");
		}
		var selecaoModel = new Selecao();
		selecaoModel.setNome(selecao.getNome());
		selecaoModel.setDescricao(selecao.getDescricao());
		selecaoModel.setQuantGols(selecao.getQuantGols());
		selecaoModel.setImagemSelecao(converterImagemBase64(selecao.getImagemSelecao()));
		selecaoModel = repositorio.save(selecaoModel);
		var response = mapper.map(selecaoModel, SelecaoResponseDTO.class);
		return response;
	}

	public SelecaoResponseDTO atualizar(Long id, SelecaoRequestDTO selecao) {
		obterPorId(id);
		var selecaoModel = mapper.map(selecao, Selecao.class);
		selecaoModel.setId(id);
		selecaoModel = repositorio.save(selecaoModel);
		return mapper.map(selecaoModel, SelecaoResponseDTO.class);
	}

	public void deletar(Long id) {
		obterPorId(id);
		repositorio.deleteById(id);
	}

	private void validarNomeSelecao(SelecaoRequestDTO selecao) {
		if (selecao.getNome() == null) {
			throw new ResourceBadRequestException("O nome deve ser informado");
		} else if (selecao.getNome().length() > 30) {
			throw new ResourceBadRequestException("Tamanho máximo de 30 caracteres no nome");
		}

	}

	private void validarDescricao(SelecaoRequestDTO selecao) {
		if (selecao.getDescricao().length() > 50) {
			throw new ResourceBadRequestException("Tamanho máximo de 50 caracteres na descrição");
		}

	}

	private void validarquantGols(SelecaoRequestDTO selecao) {
		if (selecao.getQuantGols() == null) {
			throw new ResourceBadRequestException("A quantidade de gols deve ser informada!");
		}

	}

	private String converterImagemBase64(MultipartFile imagem) {
		if (imagem == null) {
			throw new ResourceBadRequestException("A imagem é obrigatória");
		}
		try {
			byte[] imageByteArray = Base64.encodeBase64(imagem.getBytes());
			String result = new String(imageByteArray);
			return result;
		} catch (Exception e) {
			throw new ResourceBadRequestException("Ocorreu um erro na conversão de imagem!");
		}

	}
}

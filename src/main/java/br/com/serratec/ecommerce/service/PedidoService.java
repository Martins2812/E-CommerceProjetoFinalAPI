package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.PedidoDTO;
import br.com.serratec.ecommerce.exception.ResourceBadRequestException;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.repository.PedidoRepository;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repositorio;
	
	private ModelMapper mapper = new ModelMapper();
	
	public List<PedidoDTO> obterTodosOsPedidos() {
		
		List<Pedido> lista = repositorio.findAll();
		
		var novaLista = new ArrayList<PedidoDTO>();
		
		for (Pedido pedido : lista) {
			novaLista.add(mapper.map(pedido, PedidoDTO.class));
		}
		return novaLista;
	}
	
	public Optional<PedidoDTO> obterPedidoPorId(Long id) {
		
		Optional<Pedido> optPedido = repositorio.findById(id);
		
		if (optPedido.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o pedido com id :" + id);
		}
		PedidoDTO dto = mapper.map(optPedido.get(), PedidoDTO.class);
		return Optional.of(dto);
	}
	
	public PedidoDTO cadastrar (PedidoDTO pedido) {
		
		validarModelo(pedido);
		
		var contaModel = mapper.map(pedido, Pedido.class);
		
		contaModel.setId(null);
		contaModel = repositorio.save(contaModel);
		
		var response = mapper.map(contaModel, PedidoDTO.class);
		
		return response;
	}
	
	public PedidoDTO atualizar (Long id, PedidoDTO pedido) {
		obterPedidoPorId(id);
		
		validarModelo(pedido);
		
		var contaModel = mapper.map(pedido, Pedido.class);
		
		contaModel.setId(id);
		contaModel = repositorio.save(contaModel);

		return mapper.map(contaModel, PedidoDTO.class);
	}
	
	public void deletar(Long id) {
		obterPedidoPorId(id);
		repositorio.deleteById(id);
	}

	private void validarModelo(PedidoDTO pedido) {
		
		if(pedido.getId() == null) {
			throw new ResourceBadRequestException("O pedido deve ter um id.");
		}
	}
}

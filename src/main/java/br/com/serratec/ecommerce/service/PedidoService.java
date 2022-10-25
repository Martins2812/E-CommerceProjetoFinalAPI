package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.PedidoRequestDTO;
import br.com.serratec.ecommerce.exception.ResourceBadRequestException;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.repository.PedidoRepository;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repositorio;
	
	private ModelMapper mapper = new ModelMapper();
	
	public List<PedidoRequestDTO> obterTodosOsPedidos() {
		
		List<Pedido> lista = repositorio.findAll();
		
		var novaLista = new ArrayList<PedidoRequestDTO>();
		
		for (Pedido pedido : lista) {
			novaLista.add(mapper.map(pedido, PedidoRequestDTO.class));
		}
		return novaLista;
	}
	
	public Optional<PedidoRequestDTO> obterPedidoPorId(Long id) {
		
		Optional<Pedido> optPedido = repositorio.findById(id);
		
		if (optPedido.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o pedido com id :" + id);
		}
		PedidoRequestDTO dto = mapper.map(optPedido.get(), PedidoRequestDTO.class);
		return Optional.of(dto);
	}
	
	public PedidoRequestDTO cadastrar (PedidoRequestDTO pedido) {
		
		validarModelo(pedido);
		validarDataDeEntrega(pedido);
		validarDataDeEnvio(pedido);
		validarDataDePedido(pedido);
		
		var contaModel = mapper.map(pedido, Pedido.class);
		
		contaModel.setId(null);
		contaModel = repositorio.save(contaModel);
		
		var response = mapper.map(contaModel, PedidoRequestDTO.class);
		
		return response;
	}
	
	public PedidoRequestDTO atualizar (Long id, PedidoRequestDTO pedido) {
		obterPedidoPorId(id);
		
		validarModelo(pedido);
		validarDataDeEntrega(pedido);
		validarDataDeEnvio(pedido);
		validarDataDePedido(pedido);
		
		var contaModel = mapper.map(pedido, Pedido.class);
		
		contaModel.setId(id);
		contaModel = repositorio.save(contaModel);

		return mapper.map(contaModel, PedidoRequestDTO.class);
	}
	
	public void deletar(Long id) {
		obterPedidoPorId(id);
		repositorio.deleteById(id);
	}

	private void validarModelo(PedidoRequestDTO pedido) {
		
		if(pedido.getId() == null) {
			throw new ResourceBadRequestException("O pedido deve ter um id.");
		}
	}
	
	private void validarDataDeEntrega(PedidoRequestDTO pedido) {
		
		if(pedido.getData_entrega() == null) {
			throw new ResourceBadRequestException("O pedido deve ter uma data.");
		} 
	}
	private void validarDataDeEnvio(PedidoRequestDTO pedido) {
	
	if(pedido.getData_envio() == null) {
		throw new ResourceBadRequestException("O pedido deve ter uma data de envio.");
		}
	}
	private void validarDataDePedido(PedidoRequestDTO pedido) {
	
	if(pedido.getData_pedido() == null) {
		throw new ResourceBadRequestException("O pedido deve ter uma data do pedido.");
		}
	}
	
}

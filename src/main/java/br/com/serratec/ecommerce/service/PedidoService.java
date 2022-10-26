package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.serratec.ecommerce.dto.ClienteResponseDTO;
import br.com.serratec.ecommerce.dto.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.PedidoResponseDTO;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.model.MensagemEmail;
import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private EmailService emailService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PedidoRepository repositorio;

	private ModelMapper mapper = new ModelMapper();

	
	public List<PedidoResponseDTO> obterTodosOsPedidos() {

		List<Pedido> lista = repositorio.findAll();
		var novaLista = new ArrayList<PedidoResponseDTO>();
		for (Pedido pedido : lista) {
			novaLista.add(mapper.map(pedido, PedidoResponseDTO.class));
		}
		return novaLista;
	}

	public Optional<PedidoResponseDTO> obterPedidoPorId(Long id) {

		Optional<Pedido> optPedido = repositorio.findById(id);

		if (optPedido.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o pedido com id :" + id);
		}
		PedidoResponseDTO dto = mapper.map(optPedido.get(), PedidoResponseDTO.class);
		return Optional.of(dto);
	}

	public PedidoResponseDTO cadastrar(PedidoRequestDTO pedido) {
		var pedidoModel = mapper.map(pedido, Pedido.class);

		pedidoModel.setId(null);
		pedidoModel = repositorio.save(pedidoModel);
		var response = mapper.map(pedidoModel, PedidoResponseDTO.class);
		Long idCliente = pedido.getCliente().getId();
		var destinatarios = new ArrayList<String>();
		Optional<ClienteResponseDTO> cliente = clienteService.obterClientePorId(idCliente);
		destinatarios.add(cliente.get().getEmail());

		String mensagem = "<h1 style=\"color:red\">  Olá sr(a) " + cliente.get().getNomeUsuario()
				+ "! </h1> <p style=\"color:red\">Conta criada com sucesso!</p> ";

		MensagemEmail email = new MensagemEmail("Nova pedido criada.", mensagem, "turma05serratec@gmail.com",
				destinatarios);
		emailService.enviar(email);

		return response;
	}

	public PedidoResponseDTO atualizar(Long id, PedidoRequestDTO pedido) {
		obterPedidoPorId(id);

		var pedidoModel = mapper.map(pedido, Pedido.class);

		pedidoModel.setId(id);
		pedidoModel = repositorio.save(pedidoModel);

		return mapper.map(pedidoModel, PedidoResponseDTO.class);
	}

	public void deletar(Long id) {
		obterPedidoPorId(id);
		repositorio.deleteById(id);
	}
}

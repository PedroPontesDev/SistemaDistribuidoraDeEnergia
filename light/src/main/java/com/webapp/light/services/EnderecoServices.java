package com.webapp.light.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.light.exceptions.ClienteNotFoundException;
import com.webapp.light.model.DTOs.ClienteDTO;
import com.webapp.light.model.DTOs.EnderecoDTO;
import com.webapp.light.model.DTOs.ReclamacaoDTO;
import com.webapp.light.model.entities.Cliente;
import com.webapp.light.model.entities.Endereco;
import com.webapp.light.model.entities.MedidorEnergia;
import com.webapp.light.model.entities.Reclamacao;
import com.webapp.light.model.mapper.MyMapper;
import com.webapp.light.repositories.ClienteRepository;
import com.webapp.light.repositories.EnderecoRepository;
import com.webapp.light.repositories.MedidorRepository;
import com.webapp.light.repositories.ReclamacaoRepository;

@Service
public class EnderecoServices {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private MedidorRepository medidorRepository;

	@Autowired
	private EnderecoRepository repository;

	@Autowired
	private ReclamacaoRepository reclamacaoRepository;

	private Logger logger = Logger.getLogger(EnderecoServices.class.getName());

	public List<EnderecoDTO> findAll() {
		return MyMapper.parseListObjects(repository.findAll(), EnderecoDTO.class);
	}

	public EnderecoDTO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Endereco not found"));
		return MyMapper.parseObject(entity, EnderecoDTO.class);
	}

	public ClienteDTO findByEndereco(String rua, Integer numero) {
		logger.info("Finding by street!");
		var entities = clienteRepository.findAll();
		var map = MyMapper.parseListObjects(entities, ClienteDTO.class);
		for (ClienteDTO cli : map) {
			if (cli.getEndereco().getRua().equals(rua) || cli.getEndereco().getNumero().equals(numero)) {
				return cli;
			}
		}
		return null;

	}

	public EnderecoDTO createEndereco(EnderecoDTO EnderecoDTO) {
		logger.info("Creating EnderecoDTO!");
		var entity = MyMapper.parseObject(EnderecoDTO, Endereco.class);
		var dto = MyMapper.parseObject(repository.save(entity), EnderecoDTO.class);
		return dto;
	}

	public EnderecoDTO updateEndereco(EnderecoDTO dto) throws Exception {
		logger.info("Update EnderecoDTO!");
		var entidade = repository.findById(dto.getId());
		if (entidade.isPresent()) {
			// entidade.get().setCliente(dto.getCliente());
			entidade.get().setRua(dto.getRua());
			entidade.get().setNumero(dto.getNumero());
			entidade.get().setTemUmaConta(dto.isTemUmaConta());
			entidade.get().setComplemento(dto.getComplemento());
			repository.save(entidade.get());
			var dtoo = MyMapper.parseObject(entidade, EnderecoDTO.class);
			return dtoo;
		}
		throw new Exception("Error!");
	}

	public void associarEndereco(Long clientId, Long enderecoId) throws Exception {
		logger.info("Associating address!");
		Optional<Cliente> clienteExistente = clienteRepository.findById(clientId);
		Optional<Endereco> enderecoEntity = repository.findById(enderecoId);
		if (clienteExistente.isPresent() && enderecoEntity.isPresent()) {
			try {
				Cliente cliente = clienteExistente.get();
				Endereco endereco = enderecoEntity.get();
				if (endereco.getCliente() != null) {
					throw new Exception("Endereço já associado a um cliente");
				}
				cliente.setEndereco(endereco);
				endereco.setCliente(cliente);
				clienteRepository.save(cliente);
				repository.save(endereco);
				logger.info("Cliente associado com sucesso!");

			} catch (Exception e) {
				throw new Exception("Não foi possivel associar endereco a cliente");
			}

		}
	}

	public void associarMedidor(Long medidorId, Long enderecoId) throws Exception {
		logger.info("Associating medidor!");
		Optional<MedidorEnergia> medidorExistente = medidorRepository.findById(medidorId);
		Optional<Endereco> enderecoExistente = repository.findById(enderecoId);
		if (enderecoExistente.isPresent() && medidorExistente.isPresent()) {
			MedidorEnergia medidor = medidorExistente.get();
			Endereco endereco = enderecoExistente.get();
			if (endereco.getMedidor() == null) {
				endereco.setMedidor(medidor);
				medidor.setEndereco(endereco);
				medidorRepository.save(medidor);
				repository.save(endereco);
			} else {
				throw new Exception("Medidor já existe no endereço!");

			}
		}
	}

	public ReclamacaoDTO abrirReclamacao(Long clienteId, ReclamacaoDTO reclamacaoDTO) {
		logger.info("Creating reclamação");
		var cliente = clienteRepository.findById(clienteId);

		if (cliente.isPresent()) {
			Cliente client = cliente.get();
			Reclamacao reclamacao = new Reclamacao();
			reclamacao.setCliente(client);
			reclamacao.setTitulo(reclamacaoDTO.getTitulo());
			reclamacao.setConteudo(reclamacaoDTO.getConteudo());
			reclamacao.setData(LocalDate.now());
			
			client.getReclamacoes().add(reclamacao);

			Reclamacao savedReclamacao = reclamacaoRepository.save(reclamacao);
	        clienteRepository.save(client);

			return MyMapper.parseObject(savedReclamacao, ReclamacaoDTO.class);
		} else {
			throw new ClienteNotFoundException("Cliente não encontrado, verifique e tente novamente");
		}
	}

	public void delete(Long id) {
		var entity = repository.findById(id);
		repository.delete(entity.get());
	}
}

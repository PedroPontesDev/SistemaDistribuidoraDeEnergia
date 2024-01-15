package com.webapp.light.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.light.model.DTOs.ClienteDTO;
import com.webapp.light.model.DTOs.EnderecoDTO;
import com.webapp.light.model.entities.Cliente;
import com.webapp.light.model.entities.Endereco;
import com.webapp.light.model.mapper.MyMapper;
import com.webapp.light.repositories.ClienteRepository;
import com.webapp.light.repositories.EnderecoRepository;

@Service
public class EnderecoServices {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository repository;

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
			entidade.get().setCliente(dto.getCliente());
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
				if(endereco.getCliente() != null) {
					throw new Exception("Endereço já associado a um cliente");
				}
				cliente.setEndereco(endereco);
				clienteRepository.save(cliente);
				logger.info("Cliente associado com sucesso!");

			} catch (Exception e) {
               throw new Exception("Não foi possivel associar endereco a cliente");
			}

		}
	}

	public void delete(Long id) {
		var entity = repository.findById(id);
		repository.delete(entity.get());
	}
}

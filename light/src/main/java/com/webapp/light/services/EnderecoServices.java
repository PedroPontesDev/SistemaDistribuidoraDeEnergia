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

	public EnderecoDTO updateEndereco(EnderecoDTO dto) {
		logger.info("Update EnderecoDTO!");
		var entidade = repository.findById(dto.getId());
		if (entidade.isPresent()) {
			entidade.get().setCliente(dto.getCliente());
			entidade.get().setRua(dto.getRua());
			entidade.get().setNumero(dto.getNumero());
			entidade.get().setTemUmaConta(dto.isTemUmaConta());
			entidade.get().setComplemento(dto.getComplemento());
			var dtoo = MyMapper.parseObject(entidade, EnderecoDTO.class);
			return dtoo;

		}
		return null;

	}

	public void associarEndereco(Long clientId, Endereco endereco) {
		logger.info("Associating address!");
		Optional<Cliente> clienteExistente = clienteRepository.findById(clientId);
		if (clienteExistente.isPresent()) {
			// Salvo o novo endereço no banco de dados
		  	repository.save(endereco);
			// Associe o novo endereço ao cliente existente
			clienteExistente.get().setEndereco(endereco);
			// Atualize o cliente no banco de dados para refletir a associação com o novo endereço
			clienteRepository.save(clienteExistente.get());
		}
	}
}

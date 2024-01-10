package com.webapp.light.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.light.model.DTOs.ClienteDTO;
import com.webapp.light.model.entities.Cliente;
import com.webapp.light.model.entities.Endereco;
import com.webapp.light.model.mapper.MyMapper;
import com.webapp.light.repositories.ClienteRepository;
import com.webapp.light.repositories.EnderecoRepository;

@Service
public class ClienteServices {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	private Logger logger = Logger.getLogger(ClienteServices.class.getName());

	public List<ClienteDTO> findAll() {
		return MyMapper.parseListObjects(repository.findAll(), ClienteDTO.class);
	}

	public ClienteDTO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente not found"));
		return MyMapper.parseObject(entity, ClienteDTO.class);
	}

	public ClienteDTO createCliente(ClienteDTO clienteDTO) {
		logger.info("Creating PersonDTO!");
		var entity = MyMapper.parseObject(clienteDTO, Cliente.class);
		var dto = MyMapper.parseObject(repository.save(entity), ClienteDTO.class);
		return dto;
	}

}

package com.webapp.light.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.light.model.DTOs.ClienteDTO;
import com.webapp.light.model.entities.Cliente;
import com.webapp.light.model.mapper.MyMapper;
import com.webapp.light.repositories.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteServices {

	@Autowired
	ClienteRepository repository;

	private Logger logger = Logger.getLogger(ClienteServices.class.getName());

	public List<ClienteDTO> findAll() {
		return MyMapper.parseListObjects(repository.findAll(), ClienteDTO.class);
	}

	public ClienteDTO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(null);

		return MyMapper.parseObject(entity, ClienteDTO.class);

	}
	
	 @Transactional
	    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
	        // Mapeio o ClienteDTO para a entidade Cliente
	        Cliente clienteEntity = MyMapper.parseObject(clienteDTO, Cliente.class);

	        // Salve a entidade no banco de dados
	        Cliente savedCliente = repository.save(clienteEntity);

	        // Mapeio a entidade salva de volta para ClienteDTO e retorne
	        return MyMapper.parseObject(savedCliente, ClienteDTO.class);
	    }
	}


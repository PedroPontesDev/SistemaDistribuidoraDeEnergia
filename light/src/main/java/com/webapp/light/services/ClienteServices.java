package com.webapp.light.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.RepositoryComposition;
import org.springframework.stereotype.Service;

import com.webapp.light.model.DTOs.ClienteDTO;
import com.webapp.light.model.DTOs.ContaDTO;
import com.webapp.light.model.entities.Cliente;
import com.webapp.light.model.entities.Conta;
import com.webapp.light.model.mapper.MyMapper;
import com.webapp.light.repositories.ClienteRepository;

@Service
public class ClienteServices {

	@Autowired
	private ClienteRepository repository;

	private Logger logger = Logger.getLogger(ClienteServices.class.getName());

	public List<ClienteDTO> findAll() {
		return MyMapper.parseListObjects(repository.findAll(), ClienteDTO.class);
	}

	public ClienteDTO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente not found"));
		return MyMapper.parseObject(entity, ClienteDTO.class);
	}

	public ClienteDTO createCliente(ClienteDTO clienteDTO) {
		logger.info("Creating ClienteDTO");
		var entity = MyMapper.parseObject(clienteDTO, Cliente.class);
		var dto = MyMapper.parseObject(repository.save(entity), ClienteDTO.class);
		return dto;
	}

	public ClienteDTO updateCliente(ClienteDTO clienteDTO) throws Exception {
		logger.info("Updating ClienteDTO");
		try {
			var entity = repository.findById(clienteDTO.getId()); //Atualizo
			entity.get().setEmail(clienteDTO.getEmail());
			entity.get().setEndereco(clienteDTO.getEndereco()); 
			entity.get().setUsername(clienteDTO.getUsername());
			entity.get().setPassword(clienteDTO.getPassword());
			repository.save(entity.get()); //Salvo
			
			return MyMapper.parseObject(entity, ClienteDTO.class); //Converto e retorno
		
		} catch (NoSuchElementException ex) {
			throw new Exception("Cliente not found");
		}
	}

	public void delete(Long id) {
		logger.info("Deleting PersonDTO!");
		var entity = repository.findById(id);
		if (entity != null) {
           repository.delete(entity.get());
		}
	}
	

}

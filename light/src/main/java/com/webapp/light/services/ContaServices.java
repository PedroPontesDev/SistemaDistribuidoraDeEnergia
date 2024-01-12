package com.webapp.light.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.light.model.DTOs.ContaDTO;
import com.webapp.light.model.entities.Cliente;
import com.webapp.light.model.entities.Conta;
import com.webapp.light.model.mapper.MyMapper;
import com.webapp.light.repositories.ClienteRepository;
import com.webapp.light.repositories.ContaRepository;

@Service
public class ContaServices {

	@Autowired
	private ContaRepository repository;

	@Autowired
	private ClienteRepository clienteRepository;

	private Logger logger = Logger.getLogger(ContaServices.class.getName());

	public List<ContaDTO> findAll() {
		return MyMapper.parseListObjects(repository.findAll(), ContaDTO.class);
	}

	public ContaDTO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente not found"));
		return MyMapper.parseObject(entity, ContaDTO.class);
	}

	public ContaDTO criarConta(ContaDTO conta) {
		logger.info("Creating Conta");
		var entity = MyMapper.parseObject(conta, Conta.class);
		var dto = MyMapper.parseObject(repository.save(entity), ContaDTO.class);
		return dto;
	}

	public ContaDTO atualizarConta(ContaDTO conta) throws Exception {
		logger.info("Updating Conta");
		try {
			var entity = repository.findById(conta.getId()); // Atualizo
			entity.get().setEndereco(conta.getEndereco());
			entity.get().setDataDeVencimento(conta.getDataDeVencimento());
			repository.save(entity.get()); // Salvo
			return MyMapper.parseObject(entity, ContaDTO.class); // Converto e retorno

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

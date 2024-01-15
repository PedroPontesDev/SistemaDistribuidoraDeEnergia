package com.webapp.light.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webapp.light.model.DTOs.MedidorDTO;
import com.webapp.light.model.entities.MedidorEnergia;
import com.webapp.light.model.mapper.MyMapper;
import com.webapp.light.repositories.EnderecoRepository;
import com.webapp.light.repositories.MedidorRepository;

@Service
public class MedidorServices {

	@Autowired
	private MedidorRepository repository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	private Logger logger = Logger.getLogger(MedidorServices.class.getName());

	public List<MedidorDTO> findAll() {
		return MyMapper.parseListObjects(repository.findAll(), MedidorDTO.class);
	}

	public MedidorDTO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente not found"));
		return MyMapper.parseObject(entity, MedidorDTO.class);
	}

	public MedidorDTO criarMedidorDTO(MedidorDTO medidorDTO) {
		logger.info("Creating medidorDTO");
		var entity = MyMapper.parseObject(medidorDTO, MedidorEnergia.class);
		var dto = MyMapper.parseObject(repository.save(entity), MedidorDTO.class);
		return dto;
	}

	public MedidorDTO atualizarMedidor(MedidorDTO medidorDTO) throws Exception {
		logger.info("Updating medidorDTO");
		try {
			var entity = repository.findById(medidorDTO.getId()); // Atualizo
			// entity.get().setEndereco(medidorDTO.getEndereco());
			// entity.get().setDataDeVencimento(medidorDTO.getDataDeVencimento());
			repository.save(entity.get()); // Salvo
			return MyMapper.parseObject(entity, MedidorDTO.class); // Converto e retorno

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

	public MedidorDTO associarMedidorEndereco(Long id, MedidorDTO medidorDTO) throws Exception {
		logger.info("Associting Medidor To Endereco");
		var entidadeMedidor = MyMapper.parseObject(medidorDTO, MedidorEnergia.class);
		var endereco = enderecoRepository.findById(id);
		if (endereco.isPresent() && entidadeMedidor != null) {
			endereco.get().getCliente().setEndereco(endereco.get());
			endereco.get().setMedidor(entidadeMedidor);
			repository.save(entidadeMedidor);
			enderecoRepository.save(endereco.get());
			var dto = MyMapper.parseObject(entidadeMedidor, MedidorDTO.class);
			return dto;

		} else {
			throw new Exception("Algo deu errado!");
		}
	}

	public MedidorDTO calcularConsumoDeEndereco(Long enderecoId, MedidorDTO medidor, Double preco, Double hora) throws Exception {
		var endereco = enderecoRepository.findById(enderecoId);
		if(endereco != null && medidor != null) {
			medidor.setEndereco(endereco.get());
			medidor.setPreco(preco);
			medidor.setHora(hora);
			Double calculo = preco + hora;
			medidor.setTotalPrecoPorHora(calculo);
		    var entity = MyMapper.parseObject(medidor, MedidorEnergia.class);
		    repository.save(entity);
			return medidor;
		} else {
			throw new Exception("Algo deu errado!");
		}
	}

}

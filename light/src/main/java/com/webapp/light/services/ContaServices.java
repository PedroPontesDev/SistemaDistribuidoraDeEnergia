package com.webapp.light.services;

import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.light.model.DTOs.ContaDTO;
import com.webapp.light.model.entities.Conta;
import com.webapp.light.model.entities.Endereco;
import com.webapp.light.model.mapper.MyMapper;
import com.webapp.light.repositories.ContaRepository;
import com.webapp.light.repositories.EnderecoRepository;
import com.webapp.light.repositories.MedidorRepository;

@Service
public class ContaServices {

	@Autowired
	private ContaRepository repository;

	@Autowired
	private MedidorRepository medidorRepository;

	@Autowired
	EnderecoRepository enderecoRepository;

	private Logger logger = Logger.getLogger(ContaServices.class.getName());

	public Set<ContaDTO> findAll() {
		return MyMapper.parseSetObjects(repository.findAllAsSet(), ContaDTO.class);
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
			var entity = repository.findById(conta.getId());
			entity.ifPresent(contaEntity -> {
				contaEntity.setDataDeVencimento(conta.getDataDeVencimento());
				repository.save(contaEntity);
			});
			return MyMapper.parseObject(entity.orElseThrow(), ContaDTO.class);

		} catch (NoSuchElementException ex) {
			throw new Exception("Cliente not found");
		}
	}

	public void delete(Long id) {
		logger.info("Deleting PersonDTO!");
		repository.deleteById(id);
	}

	public void associarContaAendereco(Long enderecoId, Long contaId) throws Exception {
		logger.info("Associting Conta TO Cliente");
		var entidadeConta = repository.findById(contaId);
		var endereco = enderecoRepository.findById(enderecoId);
		if (endereco.isPresent() && entidadeConta.isPresent()) {
			Endereco enderecos = endereco.get();
			Conta conta = entidadeConta.get();
			enderecos.getConta().add(conta);
			conta.setEndereco(enderecos);
			enderecoRepository.save(enderecos);
			repository.save(conta);
		} else {
			throw new Exception("Algo deu errado!");
		}
	}

	public Set<ContaDTO> calcularJurosContasDoEndereco(Long medidorId, Long enderecoId) throws Exception {
	    var medidorOptional = medidorRepository.findById(medidorId);
	    var enderecoOptional = enderecoRepository.findById(enderecoId);
	    
	    if (medidorOptional.isPresent() && enderecoOptional.isPresent()) {
	        var medidor = medidorOptional.get();
	        var endereco = enderecoOptional.get();
	        Set<ContaDTO> contasAtualizadas = new HashSet<>();
	        
	        for (Conta conta : endereco.getConta()) {
	            if (conta.getDataDeVencimento().isAfter(conta.getDataDeEmissao())) {
	                long diasAtraso = ChronoUnit.DAYS.between(conta.getDataDeVencimento(), conta.getDataDeEmissao());
	                // Aplica uma taxa de juros de 1% ao dia
	                double taxaJuros = 0.05;
	                double juros = medidor.getTotalPrecoPorHora() * taxaJuros * diasAtraso;
	                // Adiciona os juros ao preço total da conta
	                conta.setPrecoTotal(conta.getPrecoTotal() + juros);
	                conta.setEstaEmAberto(true);
	                // Salva as alterações
	                repository.save(conta);
	                contasAtualizadas.add(MyMapper.parseObject(conta, ContaDTO.class));
	            } else {
	                // Se a conta não está vencida, mantenha estaEmAberto como false
	                conta.setEstaEmAberto(false);
	                // Salva as alterações
	                repository.save(conta);
	                contasAtualizadas.add(MyMapper.parseObject(conta, ContaDTO.class));
	            }
	        }

	        return contasAtualizadas;
	    }

	    return Collections.emptySet();
	}


	public Set<ContaDTO> RestaurarHistoricoDeEndereco(Long enderecoId) {
		var enderecos = enderecoRepository.findById(enderecoId);
		if (enderecos.isPresent() && enderecos.get().getConta() != null) {
			var contas = enderecos.get().getConta();
			return MyMapper.parseSetObjects(new HashSet<>(contas), ContaDTO.class);
		}
		return Collections.emptySet();
	}
}

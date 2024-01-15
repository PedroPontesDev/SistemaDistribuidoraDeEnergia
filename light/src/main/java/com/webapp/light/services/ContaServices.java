package com.webapp.light.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("#{T(java.time.LocalDate).now()}")
	private LocalDate currentDate;

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

	public void associarContaAendereco(Long enderecoId, Long contaId) throws Exception {
		logger.info("Associting Conta TO Cliente");
		var entidadeConta = repository.findById(contaId);
		var endereco = enderecoRepository.findById(enderecoId);
		if (endereco.isPresent() && entidadeConta.isPresent()) {
		   Endereco enderecos = endereco.get();
		   Conta conta = entidadeConta.get();
		   enderecos.setConta(conta);
		   enderecoRepository.save(enderecos);
		   repository.save(conta);
		} else {
			throw new Exception("Algo deu errado!");
		}
	}
	public ContaDTO calcularJurosConta(Long medidorId, Long contaId) {
        var medidorOptional = medidorRepository.findById(medidorId);
        var contaOptional = repository.findById(contaId);
        if (medidorOptional.isPresent() && contaOptional.isPresent()) {
            var medidor = medidorOptional.get();
            var conta = contaOptional.get();
            if (conta.getDataDeVencimento().isAfter(currentDate)) {
                long diasAtraso = ChronoUnit.DAYS.between(conta.getDataDeVencimento(), currentDate);

                // Aplica uma taxa de juros de 1% ao dia
                double taxaJuros = 0.01;
                double juros = medidor.getTotalPrecoPorHora() * taxaJuros * diasAtraso;

                // Adiciona os juros ao preço total da conta
                conta.setPrecoTotal(conta.getPrecoTotal() + juros);

                // Salva as alterações
                repository.save(conta);
            }

            return MyMapper.parseObject(conta, ContaDTO.class);
        }

        return null;
    }
	public List<ContaDTO> restaurarHistoricoConta() {
		return null;
	}

	public ContaDTO fecharContaEmAberto() {
		return null;
	}

}

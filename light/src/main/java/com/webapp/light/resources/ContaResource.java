package com.webapp.light.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.light.model.DTOs.ContaDTO;
import com.webapp.light.model.entities.Cliente;
import com.webapp.light.services.ClienteServices;
import com.webapp.light.services.ContaServices;

@RestController
@RequestMapping(path = "/conta")
public class ContaResource {


	@Autowired
	ContaServices contaService;
	
	@Autowired
	ClienteServices clienteServices;

	@GetMapping
	public ResponseEntity<List<ContaDTO>> findAllContas() {
		List<ContaDTO> all = contaService.findAll();
		return ResponseEntity.ok().body(all);

	}

	@PostMapping(path = "/emitir-conta")
	public ResponseEntity<ContaDTO> criarConta(@RequestBody ContaDTO contaDTO) {
		ContaDTO created = contaService.criarConta(contaDTO);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/cliente/{id}/associar-conta") //Funcionalidades de contas a clientes ficam em /conta/cliente/
	public ResponseEntity<String> associarContaCliente(@PathVariable Long id, Long contaId) throws Exception {
            contaService.associarContaAendereco(id, contaId);
            return ResponseEntity.ok("Nova conta associada a endereco com sucesso!");
	}
	
	@PutMapping (path = "/atualizar-conta")
	public ResponseEntity<ContaDTO> atualizarConta(@RequestBody ContaDTO  contaDTO) throws Exception  {
		return ResponseEntity.ok().body(contaDTO);
	}
	
	@DeleteMapping(value = "/deletar-conta")
	public ResponseEntity<?>deletarContaDTO(@PathVariable Long id)  {
		contaService.delete(id);
	    return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<ContaDTO> recuperarHistoricoConta() {
		return null;
	}
	
	@PostMapping
	public ResponseEntity<ContaDTO> calcularJurosConta(@PathVariable Long medidorId, @PathVariable Long contaId) {
		return null;
	}
	
	public ResponseEntity<ContaDTO> fecharContaEmAberto() {
		return null;
	}
	
	
	
}

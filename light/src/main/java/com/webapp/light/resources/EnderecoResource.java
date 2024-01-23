package com.webapp.light.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.light.model.DTOs.ClienteDTO;
import com.webapp.light.model.DTOs.EnderecoDTO;
import com.webapp.light.model.entities.Endereco;
import com.webapp.light.services.ClienteServices;
import com.webapp.light.services.EnderecoServices;

@RestController
@RequestMapping(path = "/endereco")
public class EnderecoResource {

	@Autowired
	ClienteServices clienteService;

	@Autowired
	EnderecoServices enderecoService;

	@GetMapping(path = "/")
	public ResponseEntity<List<EnderecoDTO>> findAllEnderecos() {
		List<EnderecoDTO> all = enderecoService.findAll();
		return ResponseEntity.ok().body(all);

	}

	@GetMapping(path = "/proucar/{rua}/{numero}")
	public ResponseEntity<ClienteDTO> findByRuaAndNumero(@PathVariable String rua, @PathVariable Integer numero) {
		var end = enderecoService.findByEndereco(rua, numero);
		return new ResponseEntity<>(end, HttpStatus.OK);
	}

	@PostMapping(path = "/cadastrar-endereco")
	public ResponseEntity<EnderecoDTO> createEndereco(@RequestBody EnderecoDTO enderecoDTO) {
		EnderecoDTO createdEndereco = enderecoService.createEndereco(enderecoDTO);
		return new ResponseEntity<>(createdEndereco, HttpStatus.CREATED);
	}
	
	@PutMapping("/atualizar-endereco")
	public ResponseEntity<EnderecoDTO> updateEndereco(@RequestBody EnderecoDTO enderecoDTO) throws Exception {
		var end = enderecoService.updateEndereco(enderecoDTO);
		return ResponseEntity.ok().body(end);
	}


	@PostMapping("{clienteId}/{enderecoId}/associar-endereco/")
	public ResponseEntity<String> associarEndereco(@PathVariable Long clienteId, @PathVariable Long enderecoId) throws Exception {
		enderecoService.associarEndereco(clienteId, enderecoId);
		return ResponseEntity.ok("Novo endereço associado ao cliente com sucesso!");
	}
	
	@DeleteMapping(path = "{id}/deletarEndereco")
     public ResponseEntity<?>deletePersonDTO(@PathVariable Long id)  {
		enderecoService.delete(id);
	    return ResponseEntity.ok("Deletado com succeso");
	}
}

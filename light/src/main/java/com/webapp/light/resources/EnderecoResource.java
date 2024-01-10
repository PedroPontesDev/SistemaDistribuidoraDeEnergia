package com.webapp.light.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping
	public ResponseEntity<List<EnderecoDTO>> findAllEnderecos() {
		List<EnderecoDTO> all = enderecoService.findAll();
		return ResponseEntity.ok().body(all);

	}

	@PostMapping(path = "/cadastrarEndereco")
	public ResponseEntity<EnderecoDTO> createEndereco(@RequestBody EnderecoDTO enderecoDTO) {
		EnderecoDTO createdEndereco = enderecoService.createEndereco(enderecoDTO);
		return new ResponseEntity<>(createdEndereco, HttpStatus.CREATED);
	}

	@PostMapping("{clienteId}/associar-endereco")
	public ResponseEntity<String> associarEndereco(@PathVariable Long clienteId, @RequestBody Endereco novoEndereco) {
		enderecoService.associarEndereco(clienteId, novoEndereco);
		return ResponseEntity.ok("Novo endereço associado ao cliente com sucesso!");
	}
	
	@GetMapping(path = "/proucar/{rua}/{numero}")
	public ResponseEntity<ClienteDTO> findByRuaAndNumero(@PathVariable String rua, @PathVariable Integer numero) {
		var end = enderecoService.findByEndereco(rua, numero);
		return new ResponseEntity<>(end, HttpStatus.OK);
	}
}

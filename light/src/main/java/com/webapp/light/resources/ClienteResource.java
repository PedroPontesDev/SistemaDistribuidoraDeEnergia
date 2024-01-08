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
import com.webapp.light.model.entities.Endereco;
import com.webapp.light.services.ClienteServices;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteResource {

	@Autowired
	ClienteServices clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAllClientes() {
		List<ClienteDTO> all = clienteService.findAll();
		return ResponseEntity.ok().body(all);

	}

	@PostMapping(path = "/cadastrarUsuario")
	public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
		ClienteDTO createdCliente = clienteService.createCliente(clienteDTO);
		return new ResponseEntity<>(createdCliente, HttpStatus.CREATED);
	}
	
	 @PostMapping("/{clienteId}/associar-endereco")
	    public ResponseEntity<String> associarEndereco(@PathVariable Long clienteId, @RequestBody Endereco novoEndereco) {
	        clienteService.associarEndereco(clienteId, novoEndereco);
	        return ResponseEntity.ok("Novo endereço associado ao cliente com sucesso!");
	   }
}

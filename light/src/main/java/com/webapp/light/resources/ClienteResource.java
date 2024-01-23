package com.webapp.light.resources;

import java.util.List;

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

import com.webapp.light.model.DTOs.ClienteDTO;
import com.webapp.light.model.mapper.MyMapper;
import com.webapp.light.services.ClienteServices;
import com.webapp.light.services.ContaServices;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteResource {

	@Autowired
	ClienteServices clienteService;
	
	@Autowired
	ContaServices contaServices;

	@GetMapping(path = "/")
	public ResponseEntity<List<ClienteDTO>> findAllClientes() {
		List<ClienteDTO> all = clienteService.findAll();
		return ResponseEntity.ok().body(all);

	}

	@GetMapping(path = "/procurar/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) throws Exception {
		var entity = clienteService.findById(id);
		if (entity != null) {
		 var entityy = MyMapper.parseObject(entity, ClienteDTO.class);	
         return ResponseEntity.ok().body(entityy);
		}
		throw new Exception("Algo deu errado!");

	}

	@PostMapping(path = "/cadastrar-usuario")
	public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
		ClienteDTO createdCliente = clienteService.createCliente(clienteDTO);
		return new ResponseEntity<>(createdCliente, HttpStatus.CREATED);
	}

	@PutMapping(path = "/atualizar-cadastro")
	public ResponseEntity<ClienteDTO> updatePersonDTO(@RequestBody ClienteDTO clienteDTO) throws Exception {
		return ResponseEntity.ok().body(clienteDTO);
	}

	@DeleteMapping(value = "/deletar-cadastro")
	public ResponseEntity<?> deletePersonDTO(@PathVariable Long id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
}

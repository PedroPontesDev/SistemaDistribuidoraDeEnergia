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

import com.webapp.light.model.DTOs.ContaDTO;

import com.webapp.light.services.ContaServices;

@RestController
@RequestMapping(path = "/conta")
public class ContaResource {


	@Autowired
	ContaServices contaService;

	@GetMapping
	public ResponseEntity<List<ContaDTO>> findAllContas() {
		List<ContaDTO> all = contaService.findAll();
		return ResponseEntity.ok().body(all);

	}

	@PostMapping(path = "/criarConta")
	public ResponseEntity<ContaDTO> criarConta(@RequestBody ContaDTO contaDTO) {
		ContaDTO created = contaService.criarConta(contaDTO);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	@PutMapping (path = "/atualizarConta")
	public ResponseEntity<ContaDTO> atualizarConta(@RequestBody ContaDTO  contaDTO) throws Exception  {
		return ResponseEntity.ok().body(contaDTO);
	}
	
	@DeleteMapping(value = "/deletarConta")
	public ResponseEntity<?>deletarContaDTO(@PathVariable Long id)  {
		contaService.delete(id);
	    return ResponseEntity.noContent().build();
	}
}

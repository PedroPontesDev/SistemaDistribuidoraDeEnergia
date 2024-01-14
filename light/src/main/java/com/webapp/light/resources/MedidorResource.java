package com.webapp.light.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.webapp.light.model.DTOs.MedidorDTO;
import com.webapp.light.model.mapper.MyMapper;
import com.webapp.light.services.ContaServices;
import com.webapp.light.services.MedidorServices;

@RestController
@RequestMapping(path = "/clientes/medidor")
public class MedidorResource {

	@Autowired
	MedidorServices medidorService;

	@Autowired
	ContaServices contaServices;

	@GetMapping(path = "/")
	public ResponseEntity<List<MedidorDTO>> findAllClientes() {
		List<MedidorDTO> all;
		return null;

	}

	@GetMapping(path = "/procurar/{id}")
	public ResponseEntity<MedidorDTO> findById(@PathVariable Long id) throws Exception {
		var entity = medidorService.findById(id);
		return ResponseEntity.ok().body(entity);

	}

	@PostMapping(path = "/criarMedidor")
	public ResponseEntity<MedidorDTO> criarMedidor(@RequestBody MedidorDTO medidor) {
		medidorService.criarMedidorDTO(medidor);
        return null;
	}

	@PutMapping(path = "/atualizarMedidor")
	public ResponseEntity<MedidorDTO> updatePersonDTO(@RequestBody MedidorDTO medidor) throws Exception {
		return ResponseEntity.ok().body(medidor);
	}

	@DeleteMapping(value = "/deletarMedidor")
	public ResponseEntity<?> deletePersonDTO(@PathVariable Long id) {
		medidorService.delete(id);
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<ContaDTO> associarMedidorEndereco(@PathVariable Long enderecoId,
			@RequestBody MedidorDTO medidor) {
		return null;
	}

	public ResponseEntity<ContaDTO> calcularConsumoEndereco() {
		return null;
	}

}

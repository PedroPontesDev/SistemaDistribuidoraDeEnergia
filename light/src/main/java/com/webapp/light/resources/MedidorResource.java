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
import com.webapp.light.services.MedidorServices;

@RestController
@RequestMapping(path = "/medidor")
public class MedidorResource {

	@Autowired
	MedidorServices medidorService;

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

	@PostMapping(path = "/criar-medidor")
	public ResponseEntity<MedidorDTO> criarMedidor(@RequestBody MedidorDTO medidor) {
		medidorService.criarMedidorDTO(medidor);
		return ResponseEntity.ok().body(medidor);
	}

	@PutMapping(path = "/atualizar-medidor")
	public ResponseEntity<MedidorDTO> updateMedidor(@RequestBody MedidorDTO medidor) throws Exception {
		return ResponseEntity.ok().body(medidor);
	}

	@DeleteMapping(value = "/deletar-medidor")
	public ResponseEntity<?> deleteMedidor(@PathVariable Long id) {
		medidorService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(path = "/{enderecoId}/{medidorId}/associar-medidor")
	public ResponseEntity<String> associarMedidorEndereco(@PathVariable Long enderecoId, @PathVariable Long medidorId)
			throws Exception {
		medidorService.associarMedidorEndereco(medidorId, enderecoId);
		return ResponseEntity.ok("Medidor Associado A Endereco!");

	}

	@PostMapping(path = "/calcular-consumo")
	public ResponseEntity<MedidorDTO> calcularConsumoEndereco(@RequestBody MedidorDTO medidor) throws Exception {
		MedidorDTO medidorEntity = medidorService.calcularConsumo(medidor);
		return ResponseEntity.ok().body(medidorEntity);

	}
}

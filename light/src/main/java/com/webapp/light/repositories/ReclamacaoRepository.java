package com.webapp.light.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.light.model.DTOs.ClienteDTO;
import com.webapp.light.model.entities.Cliente;
import com.webapp.light.model.entities.Reclamacao;

public interface ReclamacaoRepository extends JpaRepository<Reclamacao, Long>{

}

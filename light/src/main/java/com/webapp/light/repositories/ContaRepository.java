package com.webapp.light.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.light.model.DTOs.ClienteDTO;
import com.webapp.light.model.entities.Cliente;
import com.webapp.light.model.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

}

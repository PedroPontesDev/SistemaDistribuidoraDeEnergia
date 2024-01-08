package com.webapp.light.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.light.model.DTOs.ClienteDTO;
import com.webapp.light.model.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}

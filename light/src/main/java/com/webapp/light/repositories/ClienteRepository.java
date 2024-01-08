package com.webapp.light.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.light.model.DTOs.ClienteDTO;

public interface ClienteRepository extends JpaRepository<ClienteDTO, Long>{

}

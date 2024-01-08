package com.webapp.light.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.light.model.entities.Endereco;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{}

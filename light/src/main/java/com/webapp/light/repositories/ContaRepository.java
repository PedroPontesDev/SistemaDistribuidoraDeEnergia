package com.webapp.light.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.webapp.light.model.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{
	
    @Query("SELECT c FROM Conta c")
    Set<Conta> findAllAsSet();
}

package com.webapp.light.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.light.model.entities.MedidorEnergia;

public interface MedidorRepository extends JpaRepository<MedidorEnergia, Long>{

}

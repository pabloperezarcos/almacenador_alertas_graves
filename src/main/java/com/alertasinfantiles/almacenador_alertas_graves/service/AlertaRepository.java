package com.alertasinfantiles.almacenador_alertas_graves.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alertasinfantiles.almacenador_alertas_graves.model.AlertaInfantilEntity;

@Repository
public interface AlertaRepository extends JpaRepository<AlertaInfantilEntity, Long> {
}

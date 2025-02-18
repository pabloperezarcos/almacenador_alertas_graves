package com.alertasinfantiles.almacenador_alertas_graves.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.alertasinfantiles.almacenador_alertas_graves.model.AlertaInfantil;
import com.alertasinfantiles.almacenador_alertas_graves.service.AlertaService;

@Component
public class AlertaInfantilListener {

    @Autowired
    private AlertaService alertaService;

    @KafkaListener(topics = "alertas_infantiles_graves", groupId = "grupo-analizador")
    public void escucharAlerta(AlertaInfantil alerta) {
        System.out.println("📥 Alerta recibida: " + alerta);
        alertaService.procesarAlerta(alerta);
    }
}

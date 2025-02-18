package com.alertasinfantiles.almacenador_alertas_graves.kafka;

import com.alertasinfantiles.almacenador_alertas_graves.model.AlertaInfantil;
import com.alertasinfantiles.almacenador_alertas_graves.service.AlertaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AlertaInfantilListener {

    @Autowired
    private AlertaService alertaService;

    private final ObjectMapper objectMapper;

    public AlertaInfantilListener() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule()); // Soporte para LocalDateTime
    }

    @KafkaListener(topics = "alertas_graves_infantiles", groupId = "grupo-analizador")
    public void escucharAlerta(String alertaJson) {
        try {
            // Deserializar el mensaje recibido de Kafka
            AlertaInfantil alerta = objectMapper.readValue(alertaJson, AlertaInfantil.class);
            System.out.println("📥 Alerta recibida y convertida: " + alerta);

            // Guardar en la base de datos Oracle
            alertaService.procesarAlerta(alerta);
        } catch (Exception e) {
            System.err.println("❌ Error al deserializar la alerta: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

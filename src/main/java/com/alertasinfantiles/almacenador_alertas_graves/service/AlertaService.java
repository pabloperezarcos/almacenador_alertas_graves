package com.alertasinfantiles.almacenador_alertas_graves.service;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.alertasinfantiles.almacenador_alertas_graves.model.AlertaInfantil;
import com.alertasinfantiles.almacenador_alertas_graves.model.AlertaInfantilEntity;

@Service
public class AlertaService {

    // private static final String TOPIC_ALERTAS_GRAVES = "alertas_graves_infantiles";

    // @Autowired
    // private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private AlertaRepository alertaRepository;

    public void procesarAlerta(AlertaInfantil alerta) {
        if ("Alta".equalsIgnoreCase(alerta.getNivelAlerta())) {
            guardarAlertaEnBD(alerta);
        }
    }

    private void guardarAlertaEnBD(AlertaInfantil alerta) {
        AlertaInfantilEntity entidad = new AlertaInfantilEntity();
        entidad.setNombrePaciente(alerta.getNombrePaciente());
        entidad.setTipoAlerta(alerta.getTipoAlerta());
        entidad.setNivelAlerta(alerta.getNivelAlerta());
        entidad.setFechaAlerta(alerta.getFechaAlerta());

        alertaRepository.save(entidad);
        System.out.println("✅ Alerta almacenada en BD: " + alerta);
    }
}

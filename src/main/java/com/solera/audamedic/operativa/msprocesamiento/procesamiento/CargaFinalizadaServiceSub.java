package com.solera.audamedic.operativa.msprocesamiento.procesamiento;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CargaFinalizadaServiceSub {

    private final ProcesarComandaService procesarComandaService;

    @RabbitListener(queues = "${rabbitmq.queue.cargafinalizada}")
    public void handleCargaFinalizadaEvent(CargaFinalizadaEvent event) {
        log.info("Carga finalizada event");
        log.info(event.getIdRegistroCarga().toString());
        procesarComandaService.procesarComanda(event);
    }

}

package com.solera.audamedic.operativa.msprocesamiento.procesamiento;

import com.solera.audamedic.operativa.msprocesamiento.entity.ProcesamientoComandaLog;

public interface ProcesarComandaService {
    ProcesamientoComandaLog procesarComanda(CargaFinalizadaEvent event);
}

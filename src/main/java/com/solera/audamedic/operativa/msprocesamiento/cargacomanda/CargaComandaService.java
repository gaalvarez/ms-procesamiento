package com.solera.audamedic.operativa.msprocesamiento.cargacomanda;

import com.solera.audamedic.operativa.msprocesamiento.entity.CargaComandaLog;

import java.util.Optional;

public interface CargaComandaService {
    Optional<CargaComandaLog> findCargaComandaLogById(Long id);
}

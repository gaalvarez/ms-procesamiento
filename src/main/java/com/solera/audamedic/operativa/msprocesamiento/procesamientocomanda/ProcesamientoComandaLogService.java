package com.solera.audamedic.operativa.msprocesamiento.procesamientocomanda;

import com.solera.audamedic.operativa.msprocesamiento.entity.ProcesamientoComandaLog;
import jakarta.transaction.Transactional;

public interface ProcesamientoComandaLogService {
    ProcesamientoComandaLog createProcesamientoComandaLog(ProcesamientoComandaLog procesamientoComandaLog);

    @Transactional
    void updateProcesamientoComandaLogEstado(Long id, String newEstado);
}

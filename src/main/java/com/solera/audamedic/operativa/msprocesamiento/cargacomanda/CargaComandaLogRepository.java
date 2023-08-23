package com.solera.audamedic.operativa.msprocesamiento.cargacomanda;

import com.solera.audamedic.operativa.msprocesamiento.entity.CargaComandaLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CargaComandaLogRepository extends JpaRepository<CargaComandaLog, Long> {
}
package com.solera.audamedic.operativa.msprocesamiento.procesamientocomanda;

import com.solera.audamedic.operativa.msprocesamiento.entity.ProcesamientoComandaLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcesamientoComandaLogRepository extends JpaRepository<ProcesamientoComandaLog, Long> {

    @Modifying
    @Query("update ProcesamientoComandaLog p set p.procesamientoComandaEstado = :estado where p.id = :id")
    int updateEstado(Long id, String estado);
}
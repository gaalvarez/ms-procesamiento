package com.solera.audamedic.operativa.msprocesamiento.procesamiento;

import com.solera.audamedic.operativa.msprocesamiento.cargacomanda.CargaComandaService;
import com.solera.audamedic.operativa.msprocesamiento.configuracion.ConfiguracionClient;
import com.solera.audamedic.operativa.msprocesamiento.domain.PortafolioDTO;
import com.solera.audamedic.operativa.msprocesamiento.entity.CargaComandaLog;
import com.solera.audamedic.operativa.msprocesamiento.entity.ProcesamientoComandaLog;
import com.solera.audamedic.operativa.msprocesamiento.procesamientocomanda.ProcesamientoComandaLogService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcesarComandaServiceImpl implements ProcesarComandaService {


    private final CargaComandaService cargaComandaService;
    private final ConfiguracionClient configuracionClient;
    private final ProcesamientoComandaLogService procesamientoComandaLogService;

    @Override
    public ProcesamientoComandaLog procesarComanda(CargaFinalizadaEvent event) {
        final CargaComandaLog cargaComandaLog = cargaComandaService
                .findCargaComandaLogById(event.getIdRegistroCarga())
                .orElseThrow(() -> new EntityNotFoundException("No existe un registro de carga con el id: %d".formatted(event.getIdRegistroCarga())));
        final List<PortafolioDTO> portafolios = configuracionClient
                .getPortafoliosByProveedorId(cargaComandaLog.getProveedorId());
        if (portafolios.isEmpty()) {
            throw new EntityNotFoundException("EL proveedor con id: %d no tiene un portafolio con el que procesar las comadnas".formatted(cargaComandaLog.getProveedorId()));
        }
        for (PortafolioDTO portafolio :
                portafolios) {
            log.info("Portafolio:");
            log.info(portafolio.getNombrePortafolio());
        }
        final ProcesamientoComandaLog procesamientoComandaLog = new ProcesamientoComandaLog();
        procesamientoComandaLog.setMipsProcesadas(cargaComandaLog.getMipsCargadas());
        procesamientoComandaLog.setProveedorId(cargaComandaLog.getProveedorId());
        procesamientoComandaLog.setProcesamientoComandaEstado("Procesada");
        return procesamientoComandaLogService.createProcesamientoComandaLog(procesamientoComandaLog);
    }


}

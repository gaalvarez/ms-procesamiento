package com.solera.audamedic.operativa.msprocesamiento.configuracion;

import com.solera.audamedic.operativa.msprocesamiento.domain.PortafolioDTO;

import java.util.List;
import java.util.Optional;

public interface ConfiguracionClient {
    List<PortafolioDTO> getPortafoliosByProveedorId(Long id);
}

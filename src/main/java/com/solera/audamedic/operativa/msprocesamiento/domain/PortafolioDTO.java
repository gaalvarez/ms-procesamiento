package com.solera.audamedic.operativa.msprocesamiento.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortafolioDTO {
    private Long portafolioID;
    private ProveedorDTO proveedor;
    private String nombrePortafolio;
    private String descripcion;
}

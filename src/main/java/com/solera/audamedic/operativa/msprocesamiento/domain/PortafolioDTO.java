package com.solera.audamedic.operativa.msprocesamiento.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortafolioDTO implements Serializable {
    private Long portafolioID;
    private ProveedorDTO proveedor;
    private String nombrePortafolio;
    private String descripcion;
}

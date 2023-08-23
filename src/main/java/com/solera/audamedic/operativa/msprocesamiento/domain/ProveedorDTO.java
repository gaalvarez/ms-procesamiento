package com.solera.audamedic.operativa.msprocesamiento.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDTO {
    private Integer proveedorID;
    private String nombre;
    private String direccion;
    private String telefono;
}
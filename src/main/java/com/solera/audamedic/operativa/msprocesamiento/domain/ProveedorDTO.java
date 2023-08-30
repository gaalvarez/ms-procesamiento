package com.solera.audamedic.operativa.msprocesamiento.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDTO implements Serializable {
    private Integer proveedorID;
    private String nombre;
    private String direccion;
    private String telefono;
}

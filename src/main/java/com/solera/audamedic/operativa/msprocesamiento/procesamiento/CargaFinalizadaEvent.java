package com.solera.audamedic.operativa.msprocesamiento.procesamiento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargaFinalizadaEvent {
    private Long idRegistroCarga;
    private String tipoCarga;
}
package com.solera.audamedic.operativa.msprocesamiento.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfiguracionUpdatedEvent {
    private List<String> updatedEntityNames;
    private Boolean allUpdated;
}
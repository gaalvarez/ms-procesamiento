package com.solera.audamedic.operativa.msprocesamiento.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ProcesamientoComandaLog")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcesamientoComandaLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "procesamiento_comanda_sequence")
    @SequenceGenerator(name = "procesamiento_comanda_sequence", sequenceName = "seq_procesamiento_comanda", allocationSize = 1)
    @Column(name = "procesamiento_comanda_id")
    private Long id;

    @JoinColumn(name = "proveedor_id")
    private Long proveedorId;

    @JoinColumn(name = "procesamiento_comanda_estado")
    private String procesamientoComandaEstado;
    
    @JoinColumn(name = "mips_procesadas")
    private String mipsProcesadas;
}

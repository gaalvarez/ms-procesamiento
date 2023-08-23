package com.solera.audamedic.operativa.msprocesamiento.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "CargaComandaLog")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CargaComandaLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carga_comanda_sequence")
    @SequenceGenerator(name = "carga_comanda_sequence", sequenceName = "seq_carga_comanda", allocationSize = 1)
    @Column(name = "carga_comanda_id")
    private Long id;

    @JoinColumn(name = "proveedor_id")
    private Long proveedorId;

    @JoinColumn(name = "carga_comanda_estado")
    private String cargaComandaEstado;

    @JoinColumn(name = "mips_cargadas")
    private String mipsCargadas;
}

package com.solera.audamedic.operativa.msprocesamiento.cargacomanda;

import com.solera.audamedic.operativa.msprocesamiento.entity.CargaComandaLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CargaComandaLogServiceImpl implements CargaComandaService {

    private final CargaComandaLogRepository cargaComandaLogRepository;

    @Override
    public Optional<CargaComandaLog> findCargaComandaLogById(Long id) {
        return cargaComandaLogRepository.findById(id);
    }
}
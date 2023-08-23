package com.solera.audamedic.operativa.msprocesamiento.procesamientocomanda;
import com.solera.audamedic.operativa.msprocesamiento.entity.ProcesamientoComandaLog;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcesamientoComandaLogServiceImpl implements ProcesamientoComandaLogService {

    private final ProcesamientoComandaLogRepository procesamientoComandaLogRepository;

    @Override
    public ProcesamientoComandaLog createProcesamientoComandaLog(ProcesamientoComandaLog procesamientoComandaLog) {
        return procesamientoComandaLogRepository.save(procesamientoComandaLog);
    }

    @Override
    @Transactional
    public void updateProcesamientoComandaLogEstado(Long id, String newEstado) {
        int updated = procesamientoComandaLogRepository.updateEstado(id, newEstado);
        if (updated == 0) {
            throw new EntityNotFoundException("ProcesamientoComandaLog not found with id: " + id);
        }
    }
}



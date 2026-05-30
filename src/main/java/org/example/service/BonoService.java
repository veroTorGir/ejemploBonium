package org.example.service;

import org.example.entities.BonoEntity;
import java.util.List;

public interface BonoService {
    BonoEntity createBono(BonoEntity bono);
    List<BonoEntity> getAllBonos();
    BonoEntity getBonoById(Long id);
    void deleteBono(Long id);
}

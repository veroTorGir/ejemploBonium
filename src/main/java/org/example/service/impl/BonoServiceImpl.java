package org.example.service.impl;

import org.example.entities.BonoEntity;
import org.example.repositories.BonoRepository;
import org.example.service.BonoService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BonoServiceImpl implements BonoService {
    private final BonoRepository bonoRepository;
    public BonoServiceImpl(BonoRepository bonoRepository) {
        this.bonoRepository = bonoRepository;
    }
    @Override
    public BonoEntity createBono(BonoEntity bono) {
        return bonoRepository.save(bono);
    }
    @Override
    public List<BonoEntity> getAllBonos() {
        return bonoRepository.findAll();
    }
    @Override
    public BonoEntity getBonoById(Long id) {
        return bonoRepository.findById(id).orElseThrow(() -> new RuntimeException("Bono not found"));
    }
    @Override
    public void deleteBono(Long id) {
        bonoRepository.deleteById(id);
    }
}

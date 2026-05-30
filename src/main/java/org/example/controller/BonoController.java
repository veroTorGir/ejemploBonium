package org.example.controller;

import org.example.entities.BonoEntity;
import org.example.service.BonoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bonos")
public class BonoController {
    private final BonoService bonoService;
    public BonoController(BonoService bonoService) {
        this.bonoService = bonoService;
    }
    @PostMapping
    public ResponseEntity<BonoEntity> createBono(@RequestBody BonoEntity bono) {
        BonoEntity created = bonoService.createBono(bono);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @GetMapping
    public List<BonoEntity> getAllBonos() {
        return bonoService.getAllBonos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<BonoEntity> getBonoById(@PathVariable Long id) {
        BonoEntity bono = bonoService.getBonoById(id);
        return ResponseEntity.ok(bono);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBono(@PathVariable Long id) {
        bonoService.deleteBono(id);
        return ResponseEntity.noContent().build();
    }
}

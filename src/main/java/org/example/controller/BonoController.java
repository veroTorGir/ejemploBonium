// Paquete donde está ubicado el controlador
package org.example.controller;

// Importación de la entidad BonoEntity
import org.example.entities.BonoEntity;
// Importación de la interfaz de servicio
import org.example.services.BonoService;
// Importaciones de Spring para respuestas HTTP
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// Anotaciones de Spring MVC para definir endpoints REST
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para gestionar operaciones con bonos
 * Expone endpoints HTTP para que los clientes puedan interactuar con la API
 */
@RestController  // Indica que esta clase es un controlador REST (retorna JSON)
@RequestMapping("/bonos")  // Define la ruta base: http://localhost:8080/bonos
public class BonoController {

    // Inyección de dependencias del servicio (final = inmutable)
    private final BonoService bonoService;

    // Constructor con inyección de dependencias
    public BonoController(BonoService bonoService) {
        this.bonoService = bonoService;
    }

    /**
     * Endpoint POST /bonos
     * Crea un nuevo bono en el sistema
     * 
     * @param bono El bono recibido en el cuerpo de la petición JSON
     * @return ResponseEntity con el bono creado y código HTTP 201 (CREATED)
     */
    @PostMapping  // Mapea este método a solicitudes POST en /bonos
    public ResponseEntity<BonoEntity> createBono(@RequestBody BonoEntity bono) {
        // Llama al servicio para crear el bono
        BonoEntity createdBono = bonoService.createBono(bono);
        // Retorna el bono creado con estado HTTP 201 (CREATED)
        return new ResponseEntity<>(createdBono, HttpStatus.CREATED);
    }
}
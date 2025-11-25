package com.salud360.salud360.controller;

import com.salud360.salud360.models.Paciente;
import com.salud360.salud360.models.Cita;
import com.salud360.salud360.dto.PacienteDTO;
import com.salud360.salud360.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> obtenerTodos() {
        List<Paciente> pacientes = pacienteService.obtenerTodos();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPorId(@PathVariable String id) {
        return pacienteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Paciente> crearPaciente(@Valid @RequestBody PacienteDTO pacienteDTO) {
        Paciente nuevoPaciente = pacienteService.crearPaciente(pacienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizarPaciente(
            @PathVariable String id,
            @Valid @RequestBody PacienteDTO pacienteDTO) {
        try {
            Paciente pacienteActualizado = pacienteService.actualizarPaciente(id, pacienteDTO);
            return ResponseEntity.ok(pacienteActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable String id) {
        try {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/citas")
    public ResponseEntity<List<Cita>> obtenerHistorialCitas(@PathVariable String id) {
        try {
            List<Cita> citas = pacienteService.obtenerHistorialCitas(id);
            return ResponseEntity.ok(citas);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<Paciente> buscarPorCorreo(@RequestParam String correo) {
        return pacienteService.buscarPorCorreo(correo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
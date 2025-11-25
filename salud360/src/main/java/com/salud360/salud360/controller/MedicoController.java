package com.salud360.salud360.controller;

import com.salud360.salud360.models.Medico;
import com.salud360.salud360.models.Cita;
import com.salud360.salud360.dto.MedicoDTO;
import com.salud360.salud360.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@CrossOrigin(origins = "*")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> obtenerTodos() {
        List<Medico> medicos = medicoService.obtenerTodos();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerPorId(@PathVariable String id) {
        return medicoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Medico> crearMedico(@Valid @RequestBody MedicoDTO medicoDTO) {
        Medico nuevoMedico = medicoService.crearMedico(medicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMedico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizarMedico(
            @PathVariable String id,
            @Valid @RequestBody MedicoDTO medicoDTO) {
        try {
            Medico medicoActualizado = medicoService.actualizarMedico(id, medicoDTO);
            return ResponseEntity.ok(medicoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedico(@PathVariable String id) {
        try {
            medicoService.eliminarMedico(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/agenda")
    public ResponseEntity<List<Cita>> obtenerAgenda(@PathVariable String id) {
        try {
            List<Cita> agenda = medicoService.obtenerAgenda(id);
            return ResponseEntity.ok(agenda);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<Medico>> buscarPorEspecialidad(@PathVariable String especialidad) {
        List<Medico> medicos = medicoService.buscarPorEspecialidad(especialidad);
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Medico> buscarPorCorreo(@RequestParam String correo) {
        return medicoService.buscarPorCorreo(correo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
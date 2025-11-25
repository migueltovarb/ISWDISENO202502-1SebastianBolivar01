package com.salud360.salud360.controller;

import com.salud360.salud360.models.Cita;
import com.salud360.salud360.dto.CitaDTO;
import com.salud360.salud360.service.CitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public ResponseEntity<List<Cita>> obtenerTodas() {
        List<Cita> citas = citaService.obtenerTodas();
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerPorId(@PathVariable String id) {
        return citaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cita> crearCita(@Valid @RequestBody CitaDTO citaDTO) {
        try {
            Cita nuevaCita = citaService.crearCita(citaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCita);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizarCita(
            @PathVariable String id,
            @Valid @RequestBody CitaDTO citaDTO) {
        try {
            Cita citaActualizada = citaService.actualizarCita(id, citaDTO);
            return ResponseEntity.ok(citaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarCita(@PathVariable String id) {
        try {
            citaService.eliminarCita(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Cita>> obtenerCitasPorPaciente(@PathVariable String pacienteId) {
        List<Cita> citas = citaService.obtenerCitasPorPaciente(pacienteId);
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Cita>> obtenerCitasPorMedico(@PathVariable String medicoId) {
        List<Cita> citas = citaService.obtenerCitasPorMedico(medicoId);
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/paciente/{pacienteId}/futuras")
    public ResponseEntity<List<Cita>> obtenerCitasFuturasPaciente(@PathVariable String pacienteId) {
        List<Cita> citas = citaService.obtenerCitasFuturasPaciente(pacienteId);
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/medico/{medicoId}/futuras")
    public ResponseEntity<List<Cita>> obtenerCitasFuturasMedico(@PathVariable String medicoId) {
        List<Cita> citas = citaService.obtenerCitasFuturasMedico(medicoId);
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Cita>> obtenerCitasPorTipo(@PathVariable String tipo) {
        List<Cita> citas = citaService.obtenerCitasPorTipo(tipo);
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<Cita>> obtenerCitasPorFecha(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        List<Cita> citas = citaService.obtenerCitasPorFecha(fecha);
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/rango")
    public ResponseEntity<List<Cita>> obtenerCitasEnRango(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        List<Cita> citas = citaService.obtenerCitasEnRango(fechaInicio, fechaFin);
        return ResponseEntity.ok(citas);
    }
}
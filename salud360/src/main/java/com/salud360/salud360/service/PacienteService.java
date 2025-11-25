package com.salud360.salud360.service;

import com.salud360.salud360.models.Paciente;
import com.salud360.salud360.models.Cita;
import com.salud360.salud360.dto.PacienteDTO;
import com.salud360.salud360.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> obtenerTodos() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> obtenerPorId(String id) {
        return pacienteRepository.findById(id);
    }

    public Paciente crearPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setNombre(pacienteDTO.getNombre());
        paciente.setCorreo(pacienteDTO.getCorreo());
        paciente.setContrasenia(pacienteDTO.getContrasenia());
        return pacienteRepository.save(paciente);
    }

    public Paciente actualizarPaciente(String id, PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));

        paciente.setNombre(pacienteDTO.getNombre());
        paciente.setCorreo(pacienteDTO.getCorreo());

        if (pacienteDTO.getContrasenia() != null && !pacienteDTO.getContrasenia().isEmpty()) {
            paciente.setContrasenia(pacienteDTO.getContrasenia());
        }

        return pacienteRepository.save(paciente);
    }

    public void eliminarPaciente(String id) {
        pacienteRepository.deleteById(id);
    }

    public List<Cita> obtenerHistorialCitas(String pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + pacienteId));
        return paciente.getHistorialCitas();
    }

    public Optional<Paciente> buscarPorCorreo(String correo) {
        return pacienteRepository.findByCorreo(correo);
    }
}
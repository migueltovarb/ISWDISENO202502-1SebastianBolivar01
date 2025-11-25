package com.salud360.salud360.service;

import com.salud360.salud360.models.Cita;
import com.salud360.salud360.models.Paciente;
import com.salud360.salud360.models.Medico;
import com.salud360.salud360.dto.CitaDTO;
import com.salud360.salud360.repository.CitaRepository;
import com.salud360.salud360.repository.PacienteRepository;
import com.salud360.salud360.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Cita> obtenerTodas() {
        return citaRepository.findAll();
    }

    public Optional<Cita> obtenerPorId(String id) {
        return citaRepository.findById(id);
    }

    public Cita crearCita(CitaDTO citaDTO) {
        Paciente paciente = pacienteRepository.findById(citaDTO.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + citaDTO.getPacienteId()));

        Medico medico = medicoRepository.findById(citaDTO.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + citaDTO.getMedicoId()));

        Cita cita = new Cita();
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setFecha(citaDTO.getFecha());
        cita.setHora(citaDTO.getHora());
        cita.setTipo(citaDTO.getTipo());

        return citaRepository.save(cita);
    }

    public Cita actualizarCita(String id, CitaDTO citaDTO) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));

        if (citaDTO.getPacienteId() != null) {
            Paciente paciente = pacienteRepository.findById(citaDTO.getPacienteId())
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
            cita.setPaciente(paciente);
        }

        if (citaDTO.getMedicoId() != null) {
            Medico medico = medicoRepository.findById(citaDTO.getMedicoId())
                    .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
            cita.setMedico(medico);
        }
        if (citaDTO.getFecha() != null) {
        cita.setFecha(citaDTO.getFecha());
        }

        if (citaDTO.getHora() != null) {
            cita.setHora(citaDTO.getHora());
        }

        if (citaDTO.getTipo() != null) {
            cita.setTipo(citaDTO.getTipo());
        }

        return citaRepository.save(cita);
    }

    public void eliminarCita(String id) {
        citaRepository.deleteById(id);
    }

    public List<Cita> obtenerCitasPorPaciente(String pacienteId) {
        return citaRepository.findByPaciente_Id(pacienteId);
    }

    public List<Cita> obtenerCitasPorMedico(String medicoId) {
        return citaRepository.findByMedico_Id(medicoId);
    }

    public List<Cita> obtenerCitasFuturasPaciente(String pacienteId) {
        return citaRepository.findByFechaGreaterThanEqualOrderByFechaAscHoraAsc(new Date());
    }

    public List<Cita> obtenerCitasFuturasMedico(String medicoId) {
        return citaRepository.findByFechaGreaterThanEqualOrderByFechaAscHoraAsc(new Date());
    }

    public List<Cita> obtenerCitasPorTipo(String tipo) {
        return citaRepository.findByTipo(tipo);
    }

    public List<Cita> obtenerCitasPorFecha(Date fecha) {
        return citaRepository.findByFecha(fecha);
    }

    public List<Cita> obtenerCitasEnRango(Date fechaInicio, Date fechaFin) {
        return citaRepository.findByFechaBetweenOrderByFechaAscHoraAsc(fechaInicio, fechaFin);
    }

}
package com.salud360.salud360.service;

import com.salud360.salud360.models.Medico;
import com.salud360.salud360.models.Cita;
import com.salud360.salud360.dto.MedicoDTO;
import com.salud360.salud360.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> obtenerTodos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> obtenerPorId(String id) {
        return medicoRepository.findById(id);
    }

    public Medico crearMedico(MedicoDTO medicoDTO) {
        Medico medico = new Medico();
        medico.setNombre(medicoDTO.getNombre());
        medico.setCorreo(medicoDTO.getCorreo());
        medico.setContrasenia(medicoDTO.getContrasenia());
        medico.setEspecialidad(medicoDTO.getEspecialidad());
        return medicoRepository.save(medico);
    }

    public Medico actualizarMedico(String id, MedicoDTO medicoDTO) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + id));

        medico.setNombre(medicoDTO.getNombre());
        medico.setCorreo(medicoDTO.getCorreo());
        medico.setEspecialidad(medicoDTO.getEspecialidad());

        if (medicoDTO.getContrasenia() != null && !medicoDTO.getContrasenia().isEmpty()) {
            medico.setContrasenia(medicoDTO.getContrasenia());
        }

        return medicoRepository.save(medico);
    }

    public void eliminarMedico(String id) {
        medicoRepository.deleteById(id);
    }

    public List<Cita> obtenerAgenda(String medicoId) {
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + medicoId));
        return medico.getAgenda();
    }

    public List<Medico> buscarPorEspecialidad(String especialidad) {
        return medicoRepository.findByEspecialidad(especialidad);
    }

    public Optional<Medico> buscarPorCorreo(String correo) {
        return medicoRepository.findByCorreo(correo);
    }
}
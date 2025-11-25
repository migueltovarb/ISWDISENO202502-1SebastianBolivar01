package com.salud360.salud360.dto;

import jakarta.validation.constraints.*;
import java.util.Date;

public class CitaDTO {
    private String id;

    @NotNull(message = "El ID del paciente es obligatorio")
    private String pacienteId;

    private String pacienteNombre;

    @NotNull(message = "El ID del médico es obligatorio")
    private String medicoId;
    
    private String medicoNombre;
    private String medicoEspecialidad;
    
    @NotNull(message = "La fecha es obligatoria")
    private Date fecha;
    
    @NotBlank(message = "La hora es obligatoria")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Formato de hora inválido (HH:mm)")
    private String hora;
    
    @NotBlank(message = "El tipo de cita es obligatorio")
    private String tipo;

    public CitaDTO() {
    }

    public CitaDTO(String id, String pacienteId, String pacienteNombre, String medicoId,
                    String medicoNombre, String medicoEspecialidad, Date fecha, String hora, String tipo) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.pacienteNombre = pacienteNombre;
        this.medicoId = medicoId;
        this.medicoNombre = medicoNombre;
        this.medicoEspecialidad = medicoEspecialidad;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(String pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }

    public String getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(String medicoId) {
        this.medicoId = medicoId;
    }

    public String getMedicoNombre() {
        return medicoNombre;
    }

    public void setMedicoNombre(String medicoNombre) {
        this.medicoNombre = medicoNombre;
    }

    public String getMedicoEspecialidad() {
        return medicoEspecialidad;
    }

    public void setMedicoEspecialidad(String medicoEspecialidad) {
        this.medicoEspecialidad = medicoEspecialidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

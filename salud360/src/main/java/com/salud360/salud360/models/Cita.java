package com.salud360.salud360.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Date;

@Document(collection = "citas")
public class Cita {

    @Id
    private String id;

    @DBRef
    @JsonBackReference(value = "paciente-citas")
    private Paciente paciente;

    @DBRef
    @JsonBackReference(value = "medico-citas")
    private Medico medico;

    private Date fecha;

    private String hora;

    private String tipo;

    public Cita() {
    }

    public Cita(String id, Paciente paciente, Medico medico, Date fecha, String hora, String tipo) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
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
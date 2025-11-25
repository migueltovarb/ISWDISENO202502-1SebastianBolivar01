package com.salud360.salud360.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "medicos")
public class Medico extends Usuario {

    private String especialidad;

    @DBRef
    @JsonManagedReference(value = "medico-citas")
    private List<Cita> agenda = new ArrayList<>();

    public Medico() {
        super();
    }

    public Medico(String id, String nombre, String correo, String contrasenia, String especialidad) {
        super(id, nombre, correo, contrasenia);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Cita> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Cita> agenda) {
        this.agenda = agenda;
    }

    public void agregarCita(Cita cita) {
        agenda.add(cita);
        cita.setMedico(this);
    }
}

package com.salud360.salud360.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "pacientes")
public class Paciente extends Usuario {

    @DBRef
    @JsonManagedReference(value = "paciente-citas")
    private List<Cita> historialCitas = new ArrayList<>();

    public Paciente() {
        super();
    }

    public Paciente(String id, String nombre, String correo, String contrasenia) {
        super(id, nombre, correo, contrasenia);
    }

    public List<Cita> getHistorialCitas() {
        return historialCitas;
    }

    public void setHistorialCitas(List<Cita> historialCitas) {
        this.historialCitas = historialCitas;
    }

    public void agregarCita(Cita cita) {
        historialCitas.add(cita);
        cita.setPaciente(this);
    }
}
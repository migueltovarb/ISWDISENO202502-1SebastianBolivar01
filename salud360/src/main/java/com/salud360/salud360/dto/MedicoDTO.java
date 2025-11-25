package com.salud360.salud360.dto;

import jakarta.validation.constraints.*;

public class MedicoDTO {
    private String id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo inválido")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String contrasenia;

    @NotBlank(message = "La especialidad es obligatoria")
    private String especialidad;

    private Integer totalCitas;

    public MedicoDTO() {
    }

    public MedicoDTO(String id, String nombre, String correo, String contrasenia, String especialidad, Integer totalCitas) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.especialidad = especialidad;
        this.totalCitas = totalCitas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Integer getTotalCitas() {
        return totalCitas;
    }

    public void setTotalCitas(Integer totalCitas) {
        this.totalCitas = totalCitas;
    }
}

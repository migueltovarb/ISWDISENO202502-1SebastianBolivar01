package com.vehiculos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VehiculoDTO {
    
    private String id;
    
    @NotBlank(message = "La marca es requerida")
    private String marca;
    
    @NotBlank(message = "El modelo es requerido")
    private String modelo;
    
    @NotBlank(message = "El tipo de llantas es requerido")
    private String tipoLlantas;
    
    @NotNull(message = "El número de puertas es requerido")
    private Integer numeroPuertas;
    
    private String plantaId;
}
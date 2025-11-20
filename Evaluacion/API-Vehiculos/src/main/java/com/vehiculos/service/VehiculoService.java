package com.vehiculos.service;

import com.vehiculos.dto.VehiculoDTO;
import com.vehiculos.exception.InvalidOperationException;
import com.vehiculos.exception.ResourceNotFoundException;
import com.vehiculos.model.Vehiculo;
import com.vehiculos.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculoService {
    
    private final VehiculoRepository vehiculoRepository;
    // private final PlantaService plantaService; // Descomentar si implementas Planta

    public Vehiculo crearVehiculo(VehiculoDTO dto) {
        // Validaciones de negocio
        if (dto.getNumeroPuertas() <= 0) {
            throw new InvalidOperationException("El número de puertas debe ser mayor a 0");
        }
        
        Vehiculo vehiculo = new Vehiculo(
                dto.getMarca(),
                dto.getModelo(),
                dto.getTipoLlantas(),
                dto.getNumeroPuertas(),
                dto.getPlantaId()
        );
        return vehiculoRepository.save(vehiculo);
    }

    public List<Vehiculo> obtenerTodosLosVehiculos() {
        return vehiculoRepository.findAll();
    }

    public Vehiculo obtenerVehiculoPorId(String id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo no encontrado con ID: " + id));
    }
    
    // ... Agrega los métodos actualizar y eliminar siguiendo el patrón del repositorio original
}
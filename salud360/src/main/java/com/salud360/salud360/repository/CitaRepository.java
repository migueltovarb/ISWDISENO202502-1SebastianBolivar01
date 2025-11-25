package com.salud360.salud360.repository;


import com.salud360.salud360.models.Cita;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface CitaRepository extends MongoRepository<Cita, String> {

    List<Cita> findByPaciente_Id(String pacienteId);

    List<Cita> findByMedico_Id(String medicoId);

    List<Cita> findByTipo(String tipo);

    List<Cita> findByFecha(Date fecha);

    List<Cita> findByFechaGreaterThanEqualOrderByFechaAscHoraAsc(Date fecha);

    List<Cita> findByFechaBetweenOrderByFechaAscHoraAsc(Date fechaInicio, Date fechaFin);

    Long countByTipo(String tipo);

    List<Cita> findByMedico_IdAndFecha(String medicoId, Date fecha);
}
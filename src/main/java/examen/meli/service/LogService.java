package examen.meli.service;

import examen.meli.dto.StatisticsDTO;
import examen.meli.entity.LogEntity;
import examen.meli.exception.ConexionErrorException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogService {

    public List<LogEntity> findAll();
    public StatisticsDTO findByMinMax() throws ConexionErrorException;


}

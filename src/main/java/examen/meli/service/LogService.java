package examen.meli.service;

import examen.meli.entity.LogEntity;
import examen.meli.exception.SearchInvalidException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogService {

    public List<LogEntity> findAll();

    public float findByMinMax(String letra) throws SearchInvalidException;


}

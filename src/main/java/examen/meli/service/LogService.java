package examen.meli.service;

import examen.meli.entity.LogEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogService {

    public List<LogEntity> findAll();

}

package examen.meli.service;

import examen.meli.entity.LogEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogService {

    public List<LogEntity> findAll();
    public List<LogEntity> findMin();
    public List<LogEntity> findMax();
    public Double findProm();


}

package examen.meli.service;

import examen.meli.dto.StatisticsDTO;
import examen.meli.entity.LogEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface LogService {

    public List<LogEntity> findAll();
    public List<LogEntity> findMin();
    public List<LogEntity> findMax();
    public Double findProm();

    public CompletableFuture<StatisticsDTO> getLogMinMaxProm();


}

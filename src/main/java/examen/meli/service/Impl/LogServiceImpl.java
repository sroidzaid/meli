package examen.meli.service.Impl;

import examen.meli.entity.LogEntity;
import examen.meli.exception.SearchInvalidException;
import examen.meli.repository.LogRepository;
import examen.meli.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    public List<LogEntity> findAll() {

        List<LogEntity> listLogs = logRepository.findAll();
        return listLogs;
    }



    public float findByMinMax(String letra) throws SearchInvalidException {

        float distancia;
        switch (letra.toUpperCase()){
            case "C":
                distancia = logRepository.findMin();
                break;
            case "L":
                distancia =  logRepository.findMax();
                break;
            case "P":
                distancia =  logRepository.findProm();
                break;
            default:
                throw new SearchInvalidException(letra);
        }
        return distancia;
    }

}

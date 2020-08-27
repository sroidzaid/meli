package examen.meli.service.Impl;

import examen.meli.entity.LogEntity;
import examen.meli.exception.ConexionErrorException;
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
        try{
            List<LogEntity> listLogs = logRepository.findAll();
            return listLogs;
        }catch (Exception e){
            throw new ConexionErrorException();
        }

    }

    public List<LogEntity> findMin() {
        try{
            List<LogEntity> listLogs = logRepository.findMin();
            return listLogs;
    }catch (Exception e){
        throw new ConexionErrorException();
    }
    }

    public List<LogEntity> findMax() {
        try{
            List<LogEntity> listLogs = logRepository.findMax();
            return listLogs;
    }catch (Exception e){
            throw new ConexionErrorException();
        }
    }

    public Double findProm() {
        try{
            Double prom = logRepository.findProm();
            return prom;
        }catch (Exception e){
            throw new ConexionErrorException();
        }
    }



}

package examen.meli.service.Impl;

import examen.meli.dto.StatisticsDTO;
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

        List<LogEntity> listLogs = logRepository.findAll();
        return listLogs;
    }



    public StatisticsDTO findByMinMax() throws ConexionErrorException {

        try{
            StatisticsDTO statisticsDTO = new StatisticsDTO();
            statisticsDTO.setDistance_min(logRepository.findMin());
            statisticsDTO.setDistance_max(logRepository.findMax());
            statisticsDTO.setDistance_prom(logRepository.findProm());
            return statisticsDTO;
        }catch (Exception e){
            throw new ConexionErrorException();
        }

    }

}

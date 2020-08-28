package examen.meli.service.Impl;

import examen.meli.dto.LogDTO;
import examen.meli.dto.StatisticsDTO;
import examen.meli.entity.LogEntity;
import examen.meli.exception.ConexionErrorException;
import examen.meli.exception.DataNotFoundException;
import examen.meli.repository.LogRepository;
import examen.meli.service.LogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Async
    public CompletableFuture<StatisticsDTO>  getLogMinMaxProm(){
        try{
            List<LogDTO> listMin = findMin().stream()
                    .map(log -> modelMapper.map(log, LogDTO.class))
                    .collect(Collectors.toList());
            List<LogDTO> listMax = findMax().stream()
                    .map(log -> modelMapper.map(log, LogDTO.class))
                    .collect(Collectors.toList());

            if(!listMin.isEmpty() && !listMax.isEmpty()){
                StatisticsDTO statisticsDTO = new StatisticsDTO();
                statisticsDTO.setList_min(listMin);
                statisticsDTO.setList_max(listMax);
                statisticsDTO.setDistance_prom(findProm());
                return CompletableFuture.completedFuture(statisticsDTO);
            }else{
                throw new DataNotFoundException();
            }
        }catch (Exception e){
            throw new ConexionErrorException();
        }

    }

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

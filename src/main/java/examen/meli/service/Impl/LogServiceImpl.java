package examen.meli.service.Impl;

import examen.meli.entity.IpEntity;
import examen.meli.entity.LogEntity;
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


}

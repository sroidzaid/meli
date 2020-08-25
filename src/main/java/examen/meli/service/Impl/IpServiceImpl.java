package examen.meli.service.Impl;

import examen.meli.entity.IpEntity;
import examen.meli.entity.LogEntity;
import examen.meli.exception.IpInvalidException;
import examen.meli.repository.LogRepository;
import examen.meli.service.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Service
public class IpServiceImpl implements IpService {

    @Autowired
    private LogRepository logRepository;

    public IpEntity findByIP(String ip){
        IpEntity ipInformation = new IpEntity();

        if (!validIP(ip)) {
            throw new IpInvalidException(IpEntity.class.getSimpleName(), ip);
        }

        //Consumir Servicios



        String country = "";
        Long distance = 213123L;

        LogEntity logEntity = logRepository.findByCountry(country);
        if(logEntity!=null){
            long invocaciones = logEntity.getInvocations();
            invocaciones++;
            logEntity.setInvocations(invocaciones);
            logRepository.save(logEntity);
        }else{
            LogEntity logAGuardar = new LogEntity(country, distance);
            logRepository.save(logAGuardar);
        }


        return ipInformation;
    }



    private static boolean validIP(String ip) {
        if (ip == null || ip.isEmpty()) return false;
            ip = ip.trim();
        if ((ip.length() < 6) & (ip.length() > 15)) return false;

        try {
            Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
            Matcher matcher = pattern.matcher(ip);
            return matcher.matches();
        } catch (PatternSyntaxException ex) {
            return false;
        }
    }
}

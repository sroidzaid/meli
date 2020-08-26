package examen.meli.service;

import examen.meli.model.IpInformation;
import org.springframework.stereotype.Service;

@Service
public interface IpInformationService {

    public IpInformation findByIP(String ip);
}

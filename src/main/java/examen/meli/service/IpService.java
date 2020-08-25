package examen.meli.service;

import examen.meli.entity.IpEntity;
import org.springframework.stereotype.Service;

@Service
public interface IpService {

    public IpEntity findByIP(String ip);
}

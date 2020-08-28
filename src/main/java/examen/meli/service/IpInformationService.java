package examen.meli.service;

import examen.meli.dto.IpInformationDTO;
import examen.meli.model.IpInformation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public interface IpInformationService {

    public CompletableFuture<IpInformationDTO> findByIP(String ip);

}

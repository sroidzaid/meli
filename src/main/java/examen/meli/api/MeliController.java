package examen.meli.api;

import examen.meli.dto.IpInformationDTO;
import examen.meli.dto.LogDTO;
import examen.meli.dto.MinMaxPromDTO;
import examen.meli.exception.*;
import examen.meli.model.IpInformation;
import examen.meli.service.IpInformationService;
import examen.meli.service.LogService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE})
@RequestMapping("meli")
public class MeliController {
    @Autowired
    LogService logService;

    @Autowired
    IpInformationService ipInformationService;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Ver Log completo de IPs")
    @GetMapping("/stats")
    ResponseEntity<?> getLogs() {
        try{
            List<LogDTO> listLog = logService.findAll().stream()
                    .map(log -> modelMapper.map(log, LogDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(listLog);
        }catch (Exception e){
            throw new ApiException();
        }
    }

    // C: Distancia mas cercana
    // L: Distancia mas lejana
    // P: Promedio
    @ApiOperation(value = "Ver Distancia mínima, máxima, y promedio desde Bs As")
    @GetMapping("/stats/{letra}")
    ResponseEntity<?> getLogMinMaxProm(@PathVariable String letra) {

        MinMaxPromDTO minMaxPromDTO = new MinMaxPromDTO();
        try{
            Double distancia = logService.findByMinMax(letra);
            if("C".equals(letra.toUpperCase())){
                minMaxPromDTO = new MinMaxPromDTO("Distancia mas cercana desde Bs As", distancia);
            }else if("L".equals(letra.toUpperCase())){
                minMaxPromDTO = new MinMaxPromDTO("Distancia mas lejana desde Bs As", distancia);
            }else{
                minMaxPromDTO = new MinMaxPromDTO("Distancia promedio desde Bs As", distancia);
            }
            return ResponseEntity.ok(minMaxPromDTO);

        }catch (SearchInvalidException e){
            throw e;
        }catch (Exception e){
            throw new ApiException();
        }

    }

    @ApiOperation(value = "Obtener infomación relacionada a la IP")
    @PostMapping("/trace")
    ResponseEntity<?> getIpInformation(@RequestBody IpInformationDTO ip) {

        try{
            IpInformation ipInformation = ipInformationService.findByIP(ip.getIp());
            return ResponseEntity.ok(modelMapper.map(ipInformation, IpInformationDTO.class));
        }catch (DataNotFound | ConexionErrorException | IpInvalidException ii){
            throw ii;
        }catch (Exception e){
            throw new ApiException();
        }

    }

}


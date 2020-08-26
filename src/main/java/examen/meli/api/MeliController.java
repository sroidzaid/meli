package examen.meli.api;

import examen.meli.dto.IpInformationDTO;
import examen.meli.dto.LogDTO;
import examen.meli.dto.MinMaxPromDTO;
import examen.meli.exception.ConexionErrorException;
import examen.meli.exception.IpInvalidException;
import examen.meli.exception.SearchInvalidException;
import examen.meli.model.IpInformation;
import examen.meli.service.IpInformationService;
import examen.meli.service.LogService;
import examen.meli.util.ErrorService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    IpInformationService ipService;

    @Autowired
    private ModelMapper modelMapper;


    @ApiOperation(value = "Ver Log")
    @GetMapping("/stats")
    List<LogDTO> getLogs() {
        return logService.findAll().stream()
                .map(log -> modelMapper.map(log, LogDTO.class))
                .collect(Collectors.toList());
    }

    // C: Distancia mas cercana
    // L: Distancia mas lejana
    // P: Promedio
    @ApiOperation(value = "Ver Distancia mínima, máxima, y promedio desde Bs As")
    @GetMapping("/stats/{letra}")
    MinMaxPromDTO getLogMinMaxProm(@PathVariable String letra) {

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
        }catch (SearchInvalidException e){
            throw new ErrorService(e.getMessage(), "002");
        }catch (Exception e){
            throw new ErrorService(e.getMessage(), "001");
        }

        return minMaxPromDTO;

    }

    @ApiOperation(value = "Obtener infomación de IP")
    @PostMapping("/trace")
    IpInformationDTO getIpInformation(@RequestBody IpInformationDTO ip) {

        try{
            IpInformation ipInformation = ipService.findByIP(ip.getIp());
            return modelMapper.map(ipInformation, IpInformationDTO.class);
        }catch (ConexionErrorException | IpInvalidException ii){
            throw new ErrorService(ii.getMessage(), "002");
        }catch (Exception e){
            throw new ErrorService(e.getMessage(), "001");
        }



    }

}


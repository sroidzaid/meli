package examen.meli.api;

import examen.meli.dto.IpInformationDTO;
import examen.meli.dto.LogDTO;
import examen.meli.dto.MinMaxPromDTO;
import examen.meli.exception.*;
import examen.meli.model.IpInformation;
import examen.meli.service.IpInformationService;
import examen.meli.service.LogService;
import examen.meli.util.ErrorInfo;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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


    @GetMapping(path="/stats", produces = MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Ver Log completo de IPs", notes = "Obtener un log completo de las ips consultadas", response = LogDTO[].class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Búsqueda exitosa", response = LogDTO[].class),
            @ApiResponse(code = 400, message = "Búsqueda erronea", response = ErrorInfo.class),
            @ApiResponse(code = 500, message = "Error inesperado en el server", response = ErrorInfo.class)
    })
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
    @GetMapping(path="/stats/{letra}", produces = MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Distancia mas cercana, lejana y promedio desde Bs As", notes = "ver distancia mas cercana, lejana y promedio desde Bs As utilizando las letras C,L,P respectivamente", response = MinMaxPromDTO[].class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Búsqueda exitosa", response = MinMaxPromDTO[].class),
            @ApiResponse(code = 400, message = "Búsqueda erronea", response = ErrorInfo.class),
            @ApiResponse(code = 500, message = "Error inesperado en el server", response = ErrorInfo.class)
    })
    ResponseEntity<?> getLogMinMaxProm(@ApiParam(value = "Letra: C,L,P") @PathVariable String letra) {

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

    @PostMapping(path="/trace", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Obtener infomación relacionada a la IP", notes = "Dada una ip, obtenemos a que país corresponde y datos asociados al mismo", response = IpInformationDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Búsqueda exitosa", response = IpInformationDTO.class),
            @ApiResponse(code = 400, message = "Búsqueda Erronea", response = ErrorInfo.class),
            @ApiResponse(code = 500, message = "Error inesperado en el server", response = ErrorInfo.class)
    })

    ResponseEntity<?> getIpInformation(@ApiParam(value = "Ejemplo: {\"ip\": \"5.6.7.8\"}") @RequestBody IpInformationDTO ip) {

        try{
            IpInformation ipInformation = ipInformationService.findByIP(ip.getIp());
            return ResponseEntity.ok(modelMapper.map(ipInformation, IpInformationDTO.class));
        }catch (DataNotFoundException | ConexionErrorException | IpInvalidException ii){
            throw ii;
        }catch (Exception e){
            throw new ApiException();
        }

    }

}


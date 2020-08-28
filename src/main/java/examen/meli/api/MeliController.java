package examen.meli.api;

import examen.meli.dto.IpInformationDTO;
import examen.meli.dto.IpInformationRequestDTO;
import examen.meli.dto.LogDTO;
import examen.meli.dto.StatisticsDTO;
import examen.meli.exception.ApiException;
import examen.meli.exception.ConexionErrorException;
import examen.meli.exception.DataNotFoundException;
import examen.meli.exception.IpInvalidException;
import examen.meli.service.IpInformationService;
import examen.meli.service.LogService;
import examen.meli.util.ErrorInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
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
    @ApiOperation(value = "Distancia mas cercana, lejana y promedio desde Bs As", notes = "Obtener distancia en KM mas cercana, lejana y promedio desde donde se consultó una IP; país y cantidad de invocaciones", response = StatisticsDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Búsqueda exitosa", response = StatisticsDTO.class),
            @ApiResponse(code = 400, message = "Datos no encontrados", response = ErrorInfo.class),
            @ApiResponse(code = 500, message = "Error inesperado en el server", response = ErrorInfo.class)
    })
    public CompletableFuture<ResponseEntity> getLogMinMaxProm() {
        try{
            return logService.getLogMinMaxProm().<ResponseEntity>thenApply(ResponseEntity::ok);
        } catch (ConexionErrorException | DataNotFoundException e){
            throw e;
        } catch (Exception e){
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

    public CompletableFuture<ResponseEntity> getIpInformation (@ApiParam(value = "Ejemplo: {\"ip\": \"5.6.7.8\"}") @RequestBody IpInformationRequestDTO ip) {

        try{
            return ipInformationService.findByIP(ip.getIp()).<ResponseEntity>thenApply(ResponseEntity::ok);
                    //.exceptionally(handleGetInformationFailure);
        }catch (DataNotFoundException | ConexionErrorException | IpInvalidException ii){
            throw ii;
        }catch (Exception e){
            e.printStackTrace();
            throw new ApiException();
        }

    }

//    private static Function<Throwable, ResponseEntity<? extends IpInformationDTO>> handleGetInformationFailure = throwable -> {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    };


    @GetMapping(path="/stats/all", produces = MediaType.APPLICATION_JSON_VALUE )
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

}


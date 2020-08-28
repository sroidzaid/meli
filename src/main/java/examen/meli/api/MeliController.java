package examen.meli.api;

import examen.meli.dto.IpInformationDTO;
import examen.meli.dto.LogDTO;
import examen.meli.dto.StatisticsDTO;
import examen.meli.exception.ApiException;
import examen.meli.exception.ConexionErrorException;
import examen.meli.exception.DataNotFoundException;
import examen.meli.exception.IpInvalidException;
import examen.meli.model.IpInformation;
import examen.meli.service.IpInformationService;
import examen.meli.service.LogService;
import examen.meli.util.ErrorInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
            @ApiResponse(code = 500, message = "Error inesperado en el server", response = ErrorInfo.class)
    })
    ResponseEntity<?> getLogMinMaxProm() {
        try{
            List<LogDTO> listMin = logService.findMin().stream()
                    .map(log -> modelMapper.map(log, LogDTO.class))
                    .collect(Collectors.toList());
            List<LogDTO> listMax = logService.findMax().stream()
                    .map(log -> modelMapper.map(log, LogDTO.class))
                    .collect(Collectors.toList());
            if(!listMin.isEmpty() && !listMax.isEmpty()){
                StatisticsDTO statisticsDTO = new StatisticsDTO();
                statisticsDTO.setList_min(listMin);
                statisticsDTO.setList_max(listMax);
                statisticsDTO.setDistance_prom(logService.findProm());
                return ResponseEntity.ok(statisticsDTO);
            }else{
                throw new DataNotFoundException();
            }
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

    public CompletableFuture<ResponseEntity> getIpInformation (@ApiParam(value = "Ejemplo: {\"ip\": \"5.6.7.8\"}") @RequestBody IpInformationDTO ip) {

        try{

            return ipInformationService.findByIP(ip.getIp()).<ResponseEntity>thenApply(ResponseEntity::ok)
                    .exceptionally(handleGetCarFailure);


        }catch (DataNotFoundException | ConexionErrorException | IpInvalidException ii){
            throw ii;
        }catch (Exception e){
            e.printStackTrace();
            throw new ApiException();
        }

    }

    private static Function<Throwable, ResponseEntity<? extends IpInformationDTO>> handleGetCarFailure = throwable -> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };


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


    @RequestMapping (path="/stats/test", method = RequestMethod.GET, consumes={MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity getAllCars() {
        try {
            CompletableFuture<IpInformationDTO> cars1=ipInformationService.findByIP("5.6.7.8");
            CompletableFuture<IpInformationDTO> cars2=ipInformationService.findByIP("5.6.7.8");
            CompletableFuture<IpInformationDTO> cars3=ipInformationService.findByIP("5.6.7.8");
            CompletableFuture<IpInformationDTO> cars4=ipInformationService.findByIP("5.6.7.8");
            CompletableFuture<IpInformationDTO> cars5=ipInformationService.findByIP("5.6.7.8");
            CompletableFuture<IpInformationDTO> cars6=ipInformationService.findByIP("5.6.7.8");
            CompletableFuture<IpInformationDTO> cars7=ipInformationService.findByIP("5.6.7.8");
            CompletableFuture<IpInformationDTO> cars22=ipInformationService.findByIP("5.6.7.8");
            CompletableFuture<IpInformationDTO> cars71=ipInformationService.findByIP("5.6.7.8");
            CompletableFuture<IpInformationDTO> cars12=ipInformationService.findByIP("5.6.7.8");
            CompletableFuture<IpInformationDTO> cars55=ipInformationService.findByIP("120.6.7.8");
            CompletableFuture<IpInformationDTO> cars88=ipInformationService.findByIP("5.6.7.8");
            CompletableFuture.allOf(cars1, cars2, cars3).join();
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch(final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    }


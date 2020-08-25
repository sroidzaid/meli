package examen.meli.api;

import examen.meli.dto.IpDTO;
import examen.meli.dto.LogDTO;
import examen.meli.service.IpService;
import examen.meli.service.LogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE})
@RequestMapping("/meli")
public class MeliController {
    @Autowired
    LogService logService;

    @Autowired
    IpService ipService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/stats")
    List<LogDTO> getLogs() {
        return logService.findAll().stream()
                .map(log -> modelMapper.map(log, LogDTO.class))
                .collect(Collectors.toList());
    }


    @PostMapping("/trace")
    IpDTO getIpInformation(@Valid IpDTO ip) {
        return modelMapper.map(ipService.findByIP(ip.getIp()), IpDTO.class);
    }

}


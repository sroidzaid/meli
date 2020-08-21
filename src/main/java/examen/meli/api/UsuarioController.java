package examen.meli.api;

import examen.meli.dto.UsuarioDTO;
import examen.meli.service.UsuariosService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE})
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuariosService usuarioService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("")
    List<UsuarioDTO> all() {
        return usuarioService.obtenerUsuarios().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

}


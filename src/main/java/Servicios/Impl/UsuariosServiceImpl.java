package Servicios.Impl;
import Modelo.Usuario;
import Repositorio.UsuarioRepository;
import Servicios.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosServiceImpl implements UsuariosService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> obtenerUsuarios() {

        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        return listaUsuarios;
    }
}

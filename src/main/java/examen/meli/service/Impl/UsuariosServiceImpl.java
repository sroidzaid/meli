package examen.meli.service.Impl;
import examen.meli.entity.Usuario;
import examen.meli.repository.UsuarioRepository;
import examen.meli.service.UsuariosService;
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

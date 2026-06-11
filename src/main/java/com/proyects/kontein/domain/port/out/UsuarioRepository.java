package com.proyects.kontein.domain.port.out;

import com.proyects.kontein.domain.model.Usuario;
import com.proyects.kontein.domain.model.valueobject.Correo;
import com.proyects.kontein.domain.model.valueobject.UsuarioId;

import java.util.Optional;

public interface UsuarioRepository {
    
    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorId(UsuarioId id);

    Optional<Usuario> buscarPorCorreo(Correo correo);

    boolean existePorCorreo(Correo correo);
}

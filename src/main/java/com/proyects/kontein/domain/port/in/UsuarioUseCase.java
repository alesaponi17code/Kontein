package com.proyects.kontein.domain.port.in;

import com.proyects.kontein.domain.model.Usuario;

public interface UsuarioUseCase {

    Usuario autenticar(String correo, String contrasena);

    Usuario registrar(Usuario usuario);

    Usuario buscarPorCorreo(String correo);
}

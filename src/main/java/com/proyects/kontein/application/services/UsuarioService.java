package com.proyects.kontein.application.services;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.proyects.kontein.domain.port.in.UsuarioUseCase;
import com.proyects.kontein.domain.model.Usuario;
import com.proyects.kontein.domain.model.valueobject.Contrasena;
import com.proyects.kontein.domain.model.valueobject.Correo;
import com.proyects.kontein.domain.port.out.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.proyects.kontein.domain.exception.CorreoYaRegistradoException;
import com.proyects.kontein.domain.exception.CredencialesIncorrectasException;
import com.proyects.kontein.domain.exception.UsuarioNotFoundException;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UsuarioUseCase {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Usuario autenticar(String correoRaw, String contrasenaRaw) {
        Correo correo = new Correo(correoRaw);

        Usuario usuario = usuarioRepository.buscarPorCorreo(correo)
                .orElseThrow(() -> new CredencialesIncorrectasException("Credenciales incorrectas"));
        if (!passwordEncoder.matches(contrasenaRaw, usuario.getContrasena().value())) {
            throw new CredencialesIncorrectasException("Credenciales incorrectas");
        }
        return usuario;
    }

    @Override
    public Usuario registrar(Usuario usuario) {
        if (usuarioRepository.existePorCorreo(usuario.getCorreo())) {
            throw new CorreoYaRegistradoException("El correo ya está registrado");
        }

        String hashContrasena = passwordEncoder.encode(usuario.getContrasena().value());

        Usuario usuarioConHash = Usuario.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .correo(usuario.getCorreo())
                .rol(usuario.getRol())
                .fechaCreacion(usuario.getFechaCreacion())
                .contrasena(new Contrasena(hashContrasena))
                .build();
        return usuarioRepository.guardar(usuarioConHash);
    }

    @Override
    public Usuario buscarPorCorreo(String correoRaw) {
        Correo correo = new Correo(correoRaw);
        return usuarioRepository.buscarPorCorreo(correo)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado: " + correoRaw));
    }
}

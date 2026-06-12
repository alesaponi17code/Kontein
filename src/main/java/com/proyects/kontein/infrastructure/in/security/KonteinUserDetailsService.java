package com.proyects.kontein.infrastructure.in.security;

import com.proyects.kontein.domain.port.out.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.proyects.kontein.domain.model.valueobject.Correo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KonteinUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        return usuarioRepository.buscarPorCorreo(new Correo(correo))
                .map(usuario -> new User(
                        usuario.getCorreo().value(),
                        usuario.getContrasena().value(),
                        List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name()))
                ))
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario no encontrado: " + correo
                ));
    }
}
package com.proyects.kontein.infrastructure.in.controller;

import com.proyects.kontein.domain.model.Usuario;
import com.proyects.kontein.domain.model.valueobject.Contrasena;
import com.proyects.kontein.domain.model.valueobject.Correo;
import com.proyects.kontein.domain.model.valueobject.RolUsuario;
import com.proyects.kontein.domain.model.valueobject.UsuarioId;
import com.proyects.kontein.domain.port.in.UsuarioUseCase;
import com.proyects.kontein.infrastructure.in.controller.dto.LoginRequestDTO;
import com.proyects.kontein.infrastructure.in.controller.dto.LoginResponseDTO;
import com.proyects.kontein.infrastructure.in.controller.dto.RegistroRequestDTO;
import com.proyects.kontein.infrastructure.in.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private static final long EXPIRACION_MS = 24 * 60 * 60 * 1000L;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioUseCase usuarioUseCase;

        @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {

        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.correo(), request.contrasena())
        );

        var usuario = usuarioUseCase.buscarPorCorreo(request.correo());

        String token = jwtService.generarToken(usuario.getCorreo().value(), usuario.getRol().name());

        LoginResponseDTO response = new LoginResponseDTO(
            token,
            usuario.getCorreo().value(),
            usuario.getRol().name(),
            EXPIRACION_MS
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registro")
    public ResponseEntity<LoginResponseDTO> registro(@Valid @RequestBody RegistroRequestDTO request) {

        Usuario nuevoUsuario = Usuario.builder()
                .id(UsuarioId.nuevo())
                .nombre(request.nombre())
                .apellidos(request.apellidos())
                .correo(new Correo(request.correo()))
                .contrasena(new Contrasena(request.contrasena())) // se hashea dentro del service
                .rol(RolUsuario.USER) // fijo, el cliente no puede elegirlo
                .fechaCreacion(LocalDateTime.now())
                .build();

        Usuario usuarioGuardado = usuarioUseCase.registrar(nuevoUsuario);

        String token = jwtService.generarToken(usuarioGuardado.getCorreo().value(), usuarioGuardado.getRol().name());

        LoginResponseDTO response = new LoginResponseDTO(
                token,
                usuarioGuardado.getCorreo().value(),
                usuarioGuardado.getRol().name(),
                EXPIRACION_MS
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

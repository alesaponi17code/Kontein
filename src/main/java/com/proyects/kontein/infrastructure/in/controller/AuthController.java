package com.proyects.kontein.infrastructure.in.controller;

import com.proyects.kontein.domain.port.in.UsuarioUseCase;
import com.proyects.kontein.infrastructure.in.controller.dto.LoginRequestDTO;
import com.proyects.kontein.infrastructure.in.controller.dto.LoginResponseDTO;
import com.proyects.kontein.infrastructure.in.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioUseCase usuarioUseCase;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {

        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.correo(), request.contrasena())
        );

        var usuario = usuarioUseCase.buscarPorCorreo(request.correo());

        String token = jwtService.generarToken(usuario.getCorreo().value(), usuario.getRol().name());

        LoginResponseDTO response = new LoginResponseDTO(
            token,
            usuario.getCorreo().value(),
            usuario.getRol().name(),
            86000000L // 24 horas en milisegundos
        );
        return ResponseEntity.ok(response);
    }
}

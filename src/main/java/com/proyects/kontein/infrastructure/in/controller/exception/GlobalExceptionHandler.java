package com.proyects.kontein.infrastructure.in.controller.exception;

import com.proyects.kontein.domain.exception.ContenedorNotFoundException;
import com.proyects.kontein.domain.exception.CorreoYaRegistradoException;
import com.proyects.kontein.domain.exception.UsuarioNotFoundException;
import com.proyects.kontein.domain.exception.CredencialesIncorrectasException;
import com.proyects.kontein.domain.exception.DockerConnectionException;
import com.proyects.kontein.infrastructure.in.controller.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ContenedorNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleContenedorNotFound(ContenedorNotFoundException ex) {
        return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUsuarioNotFound(UsuarioNotFoundException ex) {
        return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(CredencialesIncorrectasException.class)
    public ResponseEntity<ErrorResponseDTO> handleCredencialesIncorrectas(CredencialesIncorrectasException ex) {
        return construirRespuesta(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadCredentials(BadCredentialsException ex) {
        return construirRespuesta(HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrectos");
    }

    @ExceptionHandler(DockerConnectionException.class)
    public ResponseEntity<ErrorResponseDTO> handleDockerConnection(DockerConnectionException ex) {
        return construirRespuesta(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneric(Exception ex) {
        return construirRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno: " + ex.getMessage());
    }

    @ExceptionHandler(CorreoYaRegistradoException.class)
    public ResponseEntity<ErrorResponseDTO> handleCorreoYaRegistrado(CorreoYaRegistradoException ex) {
        return construirRespuesta(HttpStatus.CONFLICT, ex.getMessage());
    }

    private ResponseEntity<ErrorResponseDTO> construirRespuesta(HttpStatus status, String mensaje) {
        ErrorResponseDTO error = new ErrorResponseDTO(status.value(), mensaje, LocalDateTime.now());
        return ResponseEntity.status(status).body(error);
    }
}
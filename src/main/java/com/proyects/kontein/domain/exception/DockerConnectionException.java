package com.proyects.kontein.domain.exception;

public class DockerConnectionException extends RuntimeException {
    public DockerConnectionException(String mensaje) {
        super(mensaje);
    }
    
    public DockerConnectionException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}


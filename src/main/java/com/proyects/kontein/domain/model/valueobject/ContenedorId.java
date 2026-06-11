package com.proyects.kontein.domain.model.valueobject;

public record ContenedorId(String valor) {
    
    public ContenedorId {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("ContenedorId no puede ser nulo o vacío");
        }
    }

    @Override
    public String toString() {
        return valor;
    }
}


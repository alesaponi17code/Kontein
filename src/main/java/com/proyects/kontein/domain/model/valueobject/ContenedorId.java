package com.proyects.kontein.domain.model.valueobject;

public record ContenedorId(String value) {
    
    public ContenedorId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("ContenedorId no puede ser nulo o vacío");
        }
    }

    @Override
    public String toString() {
        return value;
    }
}


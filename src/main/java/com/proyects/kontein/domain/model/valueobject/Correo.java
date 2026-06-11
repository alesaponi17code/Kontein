package com.proyects.kontein.domain.model.valueobject;

public record Correo(String value) {
    
    public Correo {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Correo no puede ser nulo o vacío");
        }
        if (!value.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Correo no tiene un formato válido: " + value);
        }
    }

    public static Correo of(String value) {
        return new Correo(value);
    }

    @Override
    public String toString() {
        return value;
    }
} 

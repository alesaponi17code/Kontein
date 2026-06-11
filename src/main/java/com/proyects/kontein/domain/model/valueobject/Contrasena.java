package com.proyects.kontein.domain.model.valueobject;

public record Contrasena(String value) {
    
    public Contrasena {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Contraseña no puede ser nula o vacía");
        }
    }

    public static Contrasena of(String value) {
        return new Contrasena(value);
    }

    @Override
    public String toString() {
        return value;
    }
    
}

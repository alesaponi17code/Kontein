package com.proyects.kontein.domain.model.valueobject;

import java.util.UUID;

public record UsuarioId(UUID value) {
    
    public UsuarioId {
        if (value == null) {
            throw new IllegalArgumentException("UsuarioId no puede ser nulo");
        }
    }

    public static UsuarioId nuevo() {
        return new UsuarioId(UUID.randomUUID());
    }

    public static UsuarioId of(UUID value) {
        return new UsuarioId(UUID.fromString(value.toString()));
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

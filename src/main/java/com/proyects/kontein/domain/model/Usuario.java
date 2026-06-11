package com.proyects.kontein.domain.model;

import com.proyects.kontein.domain.model.valueobject.Contrasena;
import com.proyects.kontein.domain.model.valueobject.UsuarioId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.proyects.kontein.domain.model.valueobject.Correo;
import com.proyects.kontein.domain.model.valueobject.RolUsuario;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Usuario {
    private UsuarioId id;
    private String nombre;
    private String apellidos;
    private Contrasena contrasena;
    private Correo correo;
    private RolUsuario rol;
    private LocalDateTime fechaCreacion;
}

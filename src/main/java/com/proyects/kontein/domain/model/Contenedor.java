package com.proyects.kontein.domain.model;

import java.time.LocalDateTime;

import com.proyects.kontein.domain.model.valueobject.ContenedorId;
import com.proyects.kontein.domain.model.valueobject.EstadoContenedor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Contenedor {
    private ContenedorId id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private EstadoContenedor estado;
    private Servidor servidor;
    private String puertos;
    private LocalDateTime fechaCreacion;
}

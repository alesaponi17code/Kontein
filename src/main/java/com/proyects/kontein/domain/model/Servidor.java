package com.proyects.kontein.domain.model;

import com.proyects.kontein.domain.model.valueobject.EstadoServidor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Servidor {
    private String servidorId;
    private String nombre;
    private String host;
    private EstadoServidor estado;
}

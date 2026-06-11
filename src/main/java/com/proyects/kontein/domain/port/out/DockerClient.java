package com.proyects.kontein.domain.port.out;

import com.proyects.kontein.domain.model.Contenedor;
import com.proyects.kontein.domain.model.valueobject.ContenedorId;

import java.util.List;

public interface DockerClient {
    
    List<Contenedor> listarContenedores();

    void iniciarContenedor(ContenedorId id);

    void pararContenedor(ContenedorId id);

    void eliminarContenedor(ContenedorId id);
}

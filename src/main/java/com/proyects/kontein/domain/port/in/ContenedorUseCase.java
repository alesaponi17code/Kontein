package com.proyects.kontein.domain.port.in;

import java.util.List;

import com.proyects.kontein.domain.model.Contenedor;

public interface ContenedorUseCase {
    
    List<Contenedor> listarTodos();

    Contenedor buscarPorId(String id);

    void iniciar(String id);

    void parar(String id);

    void eliminar(String id);
}

package com.proyects.kontein.domain.port.out;

import com.proyects.kontein.domain.model.Contenedor;
import com.proyects.kontein.domain.model.valueobject.ContenedorId;

import java.util.List;
import java.util.Optional;

public interface ContenedorRepository {
    
    List<Contenedor> findAll();

    Optional<Contenedor> findById(ContenedorId id);

    Contenedor guardar(Contenedor contenedor);

    void eliminar(ContenedorId id);
}

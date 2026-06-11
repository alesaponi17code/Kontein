package com.proyects.kontein.infrastructure.out.persistence.postgres.adapters;

import java.util.List;

import org.springframework.stereotype.Component;
import com.proyects.kontein.domain.model.Contenedor;
import com.proyects.kontein.domain.model.valueobject.ContenedorId;
import com.proyects.kontein.domain.port.out.ContenedorRepository;
import com.proyects.kontein.infrastructure.out.persistence.postgres.entity.ContenedorEntity;
import com.proyects.kontein.infrastructure.out.persistence.postgres.mappers.ContenedorMapper;
import com.proyects.kontein.infrastructure.out.persistence.postgres.repository.ContenedorPostgresRepository;

import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ContenedorPostgresAdapter implements ContenedorRepository {
    
    private final ContenedorPostgresRepository contenedorPostgresRepository;
    private final ContenedorMapper contenedorMapper;

    @Override
    public List<Contenedor> findAll() {
        return contenedorPostgresRepository.findAll()
                .stream()
                .map(contenedorMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Contenedor> findById(ContenedorId id) {
        return contenedorPostgresRepository.findById(id.valor())
                .map(contenedorMapper::toDomain);
    }

    @Override
    public Contenedor guardar(Contenedor contenedor) {
        ContenedorEntity entity = contenedorMapper.toEntity(contenedor);
        ContenedorEntity savedEntity = contenedorPostgresRepository.save(entity);
        return contenedorMapper.toDomain(savedEntity);
    }

    @Override
    public void eliminar(ContenedorId id) {
        contenedorPostgresRepository.deleteById(id.valor());
    }
}

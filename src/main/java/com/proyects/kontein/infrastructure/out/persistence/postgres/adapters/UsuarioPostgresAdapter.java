package com.proyects.kontein.infrastructure.out.persistence.postgres.adapters;

import org.springframework.stereotype.Component;
import com.proyects.kontein.domain.model.Usuario;
import com.proyects.kontein.domain.model.valueobject.Correo;
import com.proyects.kontein.domain.model.valueobject.UsuarioId;
import com.proyects.kontein.domain.port.out.UsuarioRepository;
import com.proyects.kontein.infrastructure.out.persistence.postgres.entity.UsuarioEntity;
import com.proyects.kontein.infrastructure.out.persistence.postgres.mappers.UsuarioMapper;
import com.proyects.kontein.infrastructure.out.persistence.postgres.repository.UsuarioPostgresRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class UsuarioPostgresAdapter implements UsuarioRepository {

    private final UsuarioPostgresRepository usuarioPostgresRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioEntity entity = usuarioMapper.toEntity(usuario);
        UsuarioEntity savedEntity = usuarioPostgresRepository.save(entity);
        return usuarioMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Usuario> buscarPorId(UsuarioId id) {
        return usuarioPostgresRepository.findById(id.value())
                .map(usuarioMapper::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(Correo correo) {
        return usuarioPostgresRepository.findByCorreo(correo.value())
                .map(usuarioMapper::toDomain);
    }

    @Override
    public boolean existePorCorreo(Correo correo) {
        return usuarioPostgresRepository.existsByCorreo(correo.value());
    }
}

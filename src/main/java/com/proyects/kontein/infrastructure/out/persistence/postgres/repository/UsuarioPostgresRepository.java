package com.proyects.kontein.infrastructure.out.persistence.postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyects.kontein.infrastructure.out.persistence.postgres.entity.UsuarioEntity;
import java.util.UUID;

import java.util.Optional;

public interface UsuarioPostgresRepository extends JpaRepository<UsuarioEntity, UUID> {

    Optional<UsuarioEntity> findByCorreo(String correo);

    boolean existsByCorreo(String correo);
    
} 

package com.proyects.kontein.infrastructure.out.persistence.postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyects.kontein.infrastructure.out.persistence.postgres.entity.ContenedorEntity;

public interface ContenedorPostgresRepository extends JpaRepository<ContenedorEntity, String> {
    
}

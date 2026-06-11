package com.proyects.kontein.infrastructure.out.persistence.postgres.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.proyects.kontein.domain.model.valueobject.EstadoContenedor;
import java.time.LocalDateTime;

@Entity
@Table(name = "contenedores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContenedorEntity {
    
    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(nullable = false)
    private String nombre;

    @Column
    private String descripcion;

    @Column(nullable = false)
    private String imagen;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoContenedor estado;

    @Column(nullable = false)
    private String servidorId;

    @Column
    private String puertos;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;
}

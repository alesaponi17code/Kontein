package com.proyects.kontein.infrastructure.out.persistence.postgres.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.proyects.kontein.domain.model.Contenedor;
import com.proyects.kontein.domain.model.valueobject.ContenedorId;
import com.proyects.kontein.infrastructure.out.persistence.postgres.entity.ContenedorEntity;

@Mapper(
    componentModel = "spring",
    imports = { ContenedorId.class }
)
public interface ContenedorMapper {

    @Mapping(target = "id", expression = "java(new ContenedorId(entity.getId()))")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "servidor", ignore = true)
    Contenedor toDomain(ContenedorEntity entity);

    @Mapping(target = "id", expression = "java(domain.getId().valor())")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "servidorId", expression = "java(domain.getServidor() != null ? domain.getServidor().getServidorId() : null)")
    ContenedorEntity toEntity(Contenedor domain);
}

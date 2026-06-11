package com.proyects.kontein.infrastructure.out.persistence.postgres.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.proyects.kontein.domain.model.Usuario;
import com.proyects.kontein.infrastructure.out.persistence.postgres.entity.UsuarioEntity;
import com.proyects.kontein.domain.model.valueobject.Correo;
import com.proyects.kontein.domain.model.valueobject.UsuarioId;
import com.proyects.kontein.domain.model.valueobject.Contrasena;


@Mapper(componentModel = "spring", 
    imports = {UsuarioId.class, Correo.class, Contrasena.class}
)
public interface UsuarioMapper {
    
    @Mapping(target = "id", expression = "java(new UsuarioId(entity.getId()))")
    @Mapping(target = "correo", expression = "java(new Correo(entity.getCorreo()))")
    @Mapping(target = "contrasena", expression = "java(new Contrasena(entity.getContrasena()))")
    Usuario toDomain(UsuarioEntity entity);

    @Mapping(target = "id", expression = "java(domain.getId().value())")
    @Mapping(target = "correo", expression = "java(domain.getCorreo().value())")
    @Mapping(target = "contrasena", expression = "java(domain.getContrasena().value())")
    UsuarioEntity toEntity(Usuario domain);
}

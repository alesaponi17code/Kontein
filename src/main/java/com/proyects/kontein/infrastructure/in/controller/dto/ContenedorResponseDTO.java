package com.proyects.kontein.infrastructure.in.controller.dto;

public record ContenedorResponseDTO(
    String id,
    String nombre,
    String descripcion,
    String imagen,
    String estado,
    String puertos
) {}

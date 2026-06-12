package com.proyects.kontein.infrastructure.in.controller.dto;

public record LoginResponseDTO(
    String token,
    String correo,
    String rol,
    long expiracionMs
) {}
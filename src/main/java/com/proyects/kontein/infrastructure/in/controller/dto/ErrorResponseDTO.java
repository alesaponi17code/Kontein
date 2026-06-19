package com.proyects.kontein.infrastructure.in.controller.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        int status,
        String mensaje,
        LocalDateTime timestamp
) {}
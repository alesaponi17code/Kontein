package com.proyects.kontein.infrastructure.in.controller;

import com.proyects.kontein.domain.model.Contenedor;
import com.proyects.kontein.domain.port.in.ContenedorUseCase;
import com.proyects.kontein.infrastructure.in.controller.dto.ContenedorResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contenedores")
@RequiredArgsConstructor
public class ContenedorController {

    private final ContenedorUseCase contenedorUseCase;

    private ContenedorResponseDTO toDTO(Contenedor c) {
        return new ContenedorResponseDTO(
                c.getId().value(),
                c.getNombre(),
                c.getDescripcion(),
                c.getImagen(),
                c.getEstado().name(),
                c.getPuertos()
        );
    }

    @GetMapping
    public ResponseEntity<List<ContenedorResponseDTO>> listarTodos() {
        List<ContenedorResponseDTO> contenedores = contenedorUseCase.listarTodos()
                .stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(contenedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContenedorResponseDTO> buscarPorId(@PathVariable String id) {
        Contenedor contenedor = contenedorUseCase.buscarPorId(id);
        return ResponseEntity.ok(toDTO(contenedor));
    }

    @PostMapping("/{id}/iniciar")
    public ResponseEntity<Void> iniciar(@PathVariable String id) {
        contenedorUseCase.iniciar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/parar")
    public ResponseEntity<Void> parar(@PathVariable String id) {
        contenedorUseCase.parar(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        contenedorUseCase.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
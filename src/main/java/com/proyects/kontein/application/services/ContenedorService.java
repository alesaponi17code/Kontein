package com.proyects.kontein.application.services;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.proyects.kontein.domain.port.in.ContenedorUseCase;
import com.proyects.kontein.domain.port.out.ContenedorRepository;
import com.proyects.kontein.domain.model.Contenedor;
import com.proyects.kontein.domain.model.valueobject.ContenedorId;
import com.proyects.kontein.domain.port.out.DockerClient;
import com.proyects.kontein.domain.exception.ContenedorNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContenedorService implements ContenedorUseCase {
    
    private final ContenedorRepository contenedorRepository;
    private final DockerClient dockerClient;

    @Override
    public List<Contenedor> listarTodos() {
        return dockerClient.listarContenedores();
    }

    @Override
    public Contenedor buscarPorId(String id) {
        ContenedorId contenedorId = new ContenedorId(id);
        return contenedorRepository.findById(contenedorId)
                .orElseThrow(() -> new ContenedorNotFoundException("Contenedor no encontrado: " + id));
    }

    @Override
    public void iniciar(String id) {
        ContenedorId contenedorId = new ContenedorId(id);

        contenedorRepository.findById(contenedorId)
                .orElseThrow(() -> new ContenedorNotFoundException("Contenedor no encontrado: " + id));
        
        dockerClient.iniciarContenedor(contenedorId);
    }

    @Override
    public void parar(String id) {
        ContenedorId contenedorId = new ContenedorId(id);

        contenedorRepository.findById(contenedorId)
                .orElseThrow(() -> new ContenedorNotFoundException("Contenedor no encontrado: " + id));
        
        dockerClient.pararContenedor(contenedorId);
    }

    @Override
    public void eliminar(String id) {
        ContenedorId contenedorId = new ContenedorId(id);

        contenedorRepository.findById(contenedorId)
                .orElseThrow(() -> new ContenedorNotFoundException("Contenedor no encontrado: " + id));
        
        dockerClient.eliminarContenedor(contenedorId);
        contenedorRepository.eliminar(contenedorId);
    }
}

package com.proyects.kontein.infrastructure.out.docker;

import org.springframework.stereotype.Component;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.proyects.kontein.domain.model.Contenedor;
import com.proyects.kontein.domain.model.valueobject.ContenedorId;
import com.proyects.kontein.domain.model.valueobject.EstadoContenedor;
import com.proyects.kontein.domain.exception.DockerConnectionException;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DockerJavaAdapter implements com.proyects.kontein.domain.port.out.DockerClient {

    private final DockerClient dockerClient;

    @Override
    public List<Contenedor> listarContenedores() {
        try {
            List<Container> containers = dockerClient.listContainersCmd().withShowAll(true).exec();
            return containers.stream()
                    .map(this::toContenedor)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DockerConnectionException("Error al listar contenedores", e);
        }
    }
    

    @Override
    public void iniciarContenedor(ContenedorId id) {
        try {
            dockerClient.startContainerCmd(id.valor()).exec();
        } catch (Exception e) {
            throw new DockerConnectionException("Error al iniciar contenedor: " + id.valor(), e);
        }
    }

    @Override
    public void pararContenedor(ContenedorId id) {
        try {
            dockerClient.stopContainerCmd(id.valor()).exec();
        } catch (Exception e) {
            throw new DockerConnectionException("Error al parar contenedor: " + id.valor(), e);
        }
    }


    @Override
    public void eliminarContenedor(ContenedorId id) {
        try {
            dockerClient.removeContainerCmd(id.valor()).withForce(true).exec();
        } catch (Exception e) {
            throw new DockerConnectionException("Error al eliminar contenedor: " + id.valor(), e);
        }
    }

    private Contenedor toContenedor(Container container) {
        String estado = "running".equals(container.getState())
        ? EstadoContenedor.ACTIVO.name()
        : EstadoContenedor.INACTIVO.name();

        String puertos = container.getPorts() != null
                    ? java.util.Arrays.stream(container.getPorts())
                    .map(p -> p.getPublicPort() + ":" + p.getPrivatePort())
                    .collect(Collectors.joining(", "))
                : "";

        return Contenedor.builder()
                .id(new ContenedorId(container.getId()))
                .nombre(container.getNames() != null && container.getNames().length > 0 
                        ? container.getNames()[0].replace("/", "")
                        : container.getId())
                .imagen(container.getImage())
                .estado(EstadoContenedor.valueOf(estado))
                .puertos(puertos)
                .build();
    }
}

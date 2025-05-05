package com.example.gestionenvios.controller;

import com.example.gestionenvios.model.Envios;
import com.example.gestionenvios.service.EnviosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/envios")
public class EnviosController {

    @Autowired
    private EnviosService enviosService;

    private static final Logger logger = Logger.getLogger(EnviosController.class.getName());

    // GET: Obtener todos los envíos con HATEOAS
    @GetMapping
    public CollectionModel<EntityModel<Envios>> getAllEnvios() {
        logger.info("GET /envios - Obteniendo todos los envíos");

        List<EntityModel<Envios>> enviosList = enviosService.getAllEnvios().stream()
                .map(envio -> EntityModel.of(envio,
                        linkTo(methodOn(EnviosController.class).getEnvioById(envio.getnSeguimiento())).withSelfRel(),
                        linkTo(methodOn(EnviosController.class).getAllEnvios()).withRel("all-envios")
                ))
                .collect(Collectors.toList());

        return CollectionModel.of(enviosList,
                linkTo(methodOn(EnviosController.class).getAllEnvios()).withSelfRel());
    }

    // GET: Obtener un envío por número de seguimiento con HATEOAS
    @GetMapping("/{nSeguimiento}")
    public EntityModel<Envios> getEnvioById(@PathVariable Long nSeguimiento) {
        logger.info("GET /envios/" + nSeguimiento + " - Obteniendo envío por ID");

        Optional<Envios> envioOptional = enviosService.getEnvioById(nSeguimiento);
        if (envioOptional.isEmpty()) {
            throw new RuntimeException("Envío no encontrado con número de seguimiento: " + nSeguimiento);
        }

        Envios envio = envioOptional.get();

        return EntityModel.of(envio,
                linkTo(methodOn(EnviosController.class).getEnvioById(nSeguimiento)).withSelfRel(),
                linkTo(methodOn(EnviosController.class).getAllEnvios()).withRel("all-envios")
        );
    }

    // POST: Crear un nuevo envío con HATEOAS
    @PostMapping
    public EntityModel<Envios> createEnvio(@RequestBody Envios envios) {
        logger.info("POST /envios - Creando nuevo envío");

        Envios nuevoEnvio = enviosService.createEnvio(envios);

        return EntityModel.of(nuevoEnvio,
                linkTo(methodOn(EnviosController.class).getEnvioById(nuevoEnvio.getnSeguimiento())).withSelfRel(),
                linkTo(methodOn(EnviosController.class).getAllEnvios()).withRel("all-envios")
        );
    }

    // PUT: Actualizar un envío existente con HATEOAS
    @PutMapping("/{nSeguimiento}")
    public EntityModel<Envios> updateEnvio(@PathVariable Long nSeguimiento, @RequestBody Envios envios) {
        logger.info("PUT /envios/" + nSeguimiento + " - Actualizando envío");

        Envios actualizado = enviosService.updateEnvio(nSeguimiento, envios);
        if (actualizado == null) {
            throw new RuntimeException("No se pudo actualizar. Envío con número de seguimiento " + nSeguimiento + " no encontrado.");
        }

        return EntityModel.of(actualizado,
                linkTo(methodOn(EnviosController.class).getEnvioById(nSeguimiento)).withSelfRel(),
                linkTo(methodOn(EnviosController.class).getAllEnvios()).withRel("all-envios")
        );
    }

    // DELETE: Eliminar un envío por su número de seguimiento (sin retorno HATEOAS)
    @DeleteMapping("/{nSeguimiento}")
    public void deleteEnvio(@PathVariable Long nSeguimiento) {
        logger.info("DELETE /envios/" + nSeguimiento + " - Eliminando envío");
        enviosService.deleteEnvio(nSeguimiento);
    }
}

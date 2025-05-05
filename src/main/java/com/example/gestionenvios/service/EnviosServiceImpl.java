package com.example.gestionenvios.service;

import com.example.gestionenvios.repository.EnviosRepository;
import com.example.gestionenvios.model.Envios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.logging.Logger;


@Service
public class EnviosServiceImpl implements EnviosService {

    @Autowired
    private EnviosRepository enviosRepository;

    private static final Logger logger = Logger.getLogger(EnviosServiceImpl.class.getName());


    @Override
    public List<Envios> getAllEnvios() {
        logger.info("Obteniendo todos los envíos...");
        return enviosRepository.findAll();
    }

    @Override
    public Optional<Envios> getEnvioById(Long nSeguimiento) {
        logger.info("Obteniendo envío con número de seguimiento: " + nSeguimiento);
        return enviosRepository.findById(nSeguimiento);
    }

    @Override
    public Envios createEnvio(Envios envios) {
        logger.info("Creando un nuevo envío: " + envios);
        return enviosRepository.save(envios);
    }

    @Override
    public Envios updateEnvio(Long nSeguimiento, Envios envios) {
        logger.info("Actualizando el envío con número de seguimiento: " + nSeguimiento);
        if (enviosRepository.existsById(nSeguimiento)) {
            envios.setnSeguimiento(nSeguimiento); 
            return enviosRepository.save(envios);
        } else {
            logger.warning("El envío con número de seguimiento no existe" + nSeguimiento);
            return null;
        }
    }

    @Override
    public void deleteEnvio(Long nSeguimiento) {
        logger.info("Eliminando el envío con número de seguimiento: " + nSeguimiento);
        enviosRepository.deleteById(nSeguimiento);
    }
}

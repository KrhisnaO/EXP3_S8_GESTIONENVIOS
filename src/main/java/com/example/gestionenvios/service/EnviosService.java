package com.example.gestionenvios.service;

import com.example.gestionenvios.model.Envios;

import java.util.List;
import java.util.Optional;

public interface EnviosService {

    List<Envios> getAllEnvios();
    Optional<Envios> getEnvioById(Long nSeguimiento);
    Envios createEnvio(Envios envios);
    Envios updateEnvio(Long nSeguimiento, Envios envios);
    void deleteEnvio(Long nSeguimiento);
}


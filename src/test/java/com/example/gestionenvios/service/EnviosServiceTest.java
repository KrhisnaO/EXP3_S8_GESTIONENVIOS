package com.example.gestionenvios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import com.example.gestionenvios.model.Envios;
import com.example.gestionenvios.repository.EnviosRepository;

public class EnviosServiceTest {

    @InjectMocks
    private EnviosServiceImpl enviosService;

    @Mock
    private EnviosRepository enviosRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    // Test para crear un envío exitosamente
    public void testCreateEnvio() {
        Envios nuevoEnvio = new Envios();
        nuevoEnvio.setDestinatario("Juan Pérez");
        nuevoEnvio.setEmpresaEnvio("Courier Express");
        nuevoEnvio.setPaisOrigen("Chile");
        nuevoEnvio.setPaisDestino("Argentina");
        nuevoEnvio.setUbicacionActual("Santiago, Chile");
        nuevoEnvio.setEstado("En tránsito");
        nuevoEnvio.setFechaSalida(LocalDate.of(2025, 3, 15));
        nuevoEnvio.setfechaEntrega(LocalDate.of(2025, 3, 20));

        Envios envioEsperado = new Envios();
        envioEsperado.setnSeguimiento(1L);
        envioEsperado.setDestinatario("Juan Pérez");

        // Simular el guardado del envío
        when(enviosRepository.save(any(Envios.class))).thenReturn(envioEsperado);

        Envios resultado = enviosService.createEnvio(nuevoEnvio);

        assertEquals(envioEsperado.getDestinatario(), resultado.getDestinatario(), "El destinatario debe coincidir");
        assertEquals(envioEsperado.getnSeguimiento(), resultado.getnSeguimiento(), "El número de seguimiento debe coincidir");
    }

    @Test
    // Test para crear un envío con error de integridad de datos
    public void testCreateEnvioError() {
        Envios nuevoEnvio = new Envios();
        nuevoEnvio.setDestinatario("Juan Pérez");
        nuevoEnvio.setEmpresaEnvio("Courier Express");
        nuevoEnvio.setPaisOrigen("Chile");
        nuevoEnvio.setPaisDestino("Argentina");
        nuevoEnvio.setUbicacionActual("Santiago, Chile");
        nuevoEnvio.setEstado("En tránsito");
        nuevoEnvio.setFechaSalida(LocalDate.of(2025, 3, 15));
        nuevoEnvio.setfechaEntrega(LocalDate.of(2025, 3, 20));

        // Simula una excepción de violación de integridad al guardar
        when(enviosRepository.save(any(Envios.class)))
            .thenThrow(new DataIntegrityViolationException("Violación de integridad de datos"));

        assertThrows(DataIntegrityViolationException.class, () -> {
            enviosService.createEnvio(nuevoEnvio);
        });
    }

    @Test
    // Test para obtener un envío por su ID
    public void testGetEnvioById() {
        Envios envio = new Envios();
        envio.setnSeguimiento(1L);
        envio.setDestinatario("Juan Pérez");
        envio.setEmpresaEnvio("Courier Express");
        envio.setPaisOrigen("Chile");
        envio.setPaisDestino("Argentina");
        envio.setUbicacionActual("Santiago, Chile");
        envio.setEstado("En tránsito");
        envio.setFechaSalida(LocalDate.of(2025, 3, 15));
        envio.setfechaEntrega(LocalDate.of(2025, 3, 20));

        // Simular la búsqueda del envío por su ID
        when(enviosRepository.findById(1L)).thenReturn(Optional.of(envio));

        Envios envioEncontrado = enviosService.getEnvioById(1L).get();

        assertEquals(envio.getDestinatario(), envioEncontrado.getDestinatario(), "El destinatario debe ser correcto");
        assertEquals(envio.getEmpresaEnvio(), envioEncontrado.getEmpresaEnvio(), "La empresa de envío debe ser correcta");
        assertEquals(envio.getPaisOrigen(), envioEncontrado.getPaisOrigen(), "El país de origen debe ser correcto");
        assertEquals(envio.getPaisDestino(), envioEncontrado.getPaisDestino(), "El país de destino debe ser correcto");
        assertEquals(envio.getUbicacionActual(), envioEncontrado.getUbicacionActual(), "La ubicación actual debe ser correcta");
        assertEquals(envio.getEstado(), envioEncontrado.getEstado(), "El estado debe ser correcto");
        assertEquals(envio.getFechaSalida(), envioEncontrado.getFechaSalida(), "La fecha de salida debe ser correcta");
        assertEquals(envio.getfechaEntrega(), envioEncontrado.getfechaEntrega(), "La fecha de entrega debe ser correcta");
    }

}
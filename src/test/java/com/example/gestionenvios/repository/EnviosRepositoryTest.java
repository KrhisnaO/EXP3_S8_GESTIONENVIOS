package com.example.gestionenvios.repository;

import com.example.gestionenvios.model.Envios;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EnviosRepositoryTest {

    @Autowired
    private EnviosRepository enviosRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test // Test para guardar y recuperar un envío
    @DisplayName("Debe guardar y recuperar un envío correctamente")
    @Timeout(5) // Máximo 5 segundos de ejecución
    public void testGuardarYRecuperarEnvio() {

        // Crear un nuevo envío
        Envios envio = new Envios();
        envio.setDestinatario("Juan Pérez");
        envio.setEmpresaEnvio("Courier Express");
        envio.setPaisOrigen("Chile");
        envio.setPaisDestino("Argentina");
        envio.setUbicacionActual("Santiago, Chile");
        envio.setEstado("En tránsito");
        envio.setFechaSalida(LocalDate.of(2025, 3, 15));
        envio.setfechaEntrega(LocalDate.of(2025, 3, 20));

        // Guardar el envío usando TestEntityManager
        Envios enviadoGuardado = entityManager.persistAndFlush(envio);

        // Verificar que el objeto guardado no sea nulo y tenga un ID asignado
        assertNotNull(enviadoGuardado.getnSeguimiento(), "El número de seguimiento no debe ser nulo");

        // Recuperar el envío por su ID
        Envios envioRecuperado = enviosRepository.findById(enviadoGuardado.getnSeguimiento()).orElse(null);

        // Verificar que el envío recuperado no sea nulo y que los valores sean correctos
        assertNotNull(envioRecuperado, "El envío recuperado no debe ser nulo");
        assertEquals("Juan Pérez", envioRecuperado.getDestinatario(), "El destinatario es incorrecto");
        assertEquals("Courier Express", envioRecuperado.getEmpresaEnvio(), "La empresa de envío es incorrecta");
        assertEquals("Chile", envioRecuperado.getPaisOrigen(), "El país de origen es incorrecto");
        assertEquals("Argentina", envioRecuperado.getPaisDestino(), "El país de destino es incorrecto");
        assertEquals("Santiago, Chile", envioRecuperado.getUbicacionActual(), "La ubicación actual es incorrecta");
        assertEquals("En tránsito", envioRecuperado.getEstado(), "El estado del envío es incorrecto");
        assertEquals(LocalDate.of(2025, 3, 15), envioRecuperado.getFechaSalida(), "La fecha de salida es incorrecta");
        assertEquals(LocalDate.of(2025, 3, 20), envioRecuperado.getfechaEntrega(), "La fecha de entrega es incorrecta");
    }

    @Test // Test para no encontrar un envío por su ID
    @DisplayName("No debe encontrar un envío con ID inexistente")
    @Timeout(3)
    public void testNoEncontrarEnvioPorId() {
        // Intentar recuperar un envío con un ID que no existe
        Optional<Envios> envioRecuperado = enviosRepository.findById(88L);

        // Verificar que no se haya encontrado el envío
        assertTrue(envioRecuperado.isEmpty(), "El envío no debería haber sido encontrado");
    }
}

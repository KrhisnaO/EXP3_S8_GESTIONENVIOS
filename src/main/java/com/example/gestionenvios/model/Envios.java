package com.example.gestionenvios.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "envios")
public class Envios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nSeguimiento", unique = true, nullable = false)
    private Long nSeguimiento;

    @NotBlank(message = "El nombre del destinatario no puede estar vacío")
    @Size(min = 5, max = 100, message = "El nombre del destinatario no puede tener más de 255 caracteres")
    @Column(name = "destinatario", nullable = false)
    private String destinatario;

    @NotBlank(message = "La empresa de envío no puede estar vacía")
    @Size(min = 5, max = 100, message = "El nombre de la empresa de envío no puede tener más de 255 caracteres")
    @Column(name = "empresaEnvio", nullable = false)
    private String empresaEnvio;

    @NotBlank(message = "El país de origen no puede estar vacío")
    @Size(min = 5, max = 50, message = "El país de origen no puede tener más de 255 caracteres")
    @Column(name = "paisOrigen", nullable = false)
    private String paisOrigen;

    @NotBlank(message = "El país destino no puede estar vacío")
    @Size(min = 5, max = 50, message = "El país destino no puede tener más de 255 caracteres")
    @Column(name = "paisDestino", nullable = false)
    private String paisDestino;

    @NotBlank(message = "La ubicación actual no puede estar vacía")
    @Size(min = 5, max = 150, message = "La ubicación actual no puede tener más de 255 caracteres")
    @Column(name = "ubicacionActual", nullable = false)
    private String ubicacionActual;

    @NotBlank(message = "El estado no puede estar vacío")
    @Size(min = 5, max = 50, message = "El estado no puede tener más de 50 caracteres")
    @Column(name = "estado", nullable = false)
    private String estado;

    @NotNull(message = "La fecha de salida no puede estar vacía")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fechaSalida", nullable = false)
    private LocalDate fechaSalida;

    @NotNull(message = "La fecha estimada de llegada no puede estar vacía")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fechaEntrega", nullable = false)
    private LocalDate fechaEntrega;

    // Getters y Setters

    public Long getnSeguimiento() {
        return nSeguimiento;
    }

    public void setnSeguimiento(Long nSeguimiento) {
        this.nSeguimiento = nSeguimiento;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getEmpresaEnvio() {
        return empresaEnvio;
    }

    public void setEmpresaEnvio(String empresaEnvio) {
        this.empresaEnvio = empresaEnvio;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    public String getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(String ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDate getfechaEntrega() {
        return fechaEntrega;
    }

    public void setfechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
}


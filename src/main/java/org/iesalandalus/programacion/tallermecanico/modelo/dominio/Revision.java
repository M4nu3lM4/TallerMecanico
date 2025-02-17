package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Revision {
    private static final float PRECIO_HORA = 30;
    private static final float PRECIO_DIA = 10;
    private static final float PRECIO_MATERIAL = 1.5f;
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
    }


    public Revision(Revision revision) {
        Objects.requireNonNull(revision,"No puedo copiar una revision nula.");
        setCliente(revision.cliente);
        setVehiculo(revision.vehiculo);
        setFechaInicio(revision.fechaInicio);
        this.horas = revision.horas;
        this.precioMaterial = revision.precioMaterial;
        this.fechaFin = revision.fechaFin;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente,"El cliente no puede ser nulo.");

        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo,"El vehículo no puede ser nulo.");

        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        if (fechaInicio == null || fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a hoy.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        if (fechaFin == null || fechaFin.isBefore(fechaInicio) || fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no es válida.");
        }
        this.fechaFin = fechaFin;
    }

    public float getHoras() {
        return horas;
    }

    public void anadirHoras(float horas) {
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas deben ser positivas.");
        }
        this.horas += horas;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial) {
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material debe ser positivo.");
        }
        this.precioMaterial += precioMaterial;
    }
    public boolean estaCerrada() {
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) {
        setFechaFin(fechaFin);
    }

    public float getPrecio() {
        return (horas * PRECIO_HORA) + precioMaterial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return Objects.equals(cliente, revision.cliente) &&
                Objects.equals(vehiculo, revision.vehiculo) &&
                Objects.equals(fechaInicio, revision.fechaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, vehiculo, fechaInicio);
    }

    @Override
    public String toString() {
        return "Revision{" +
                "cliente=" + cliente +
                ", vehiculo=" + vehiculo +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", horas=" + horas +
                ", precioMaterial=" + precioMaterial +
                ", precioTotal=" + getPrecio() +
                '}';
    }
}
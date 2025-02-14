package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Revision {
    private static final float PRECIO_HORA = 30.0f;
    private static final float PRECIO_DIA = 10.0f;
    private static final float PRECIO_MATERIAL = 1.5f;
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;
    private Cliente cliente;
    private Vehiculo vehiculo;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        horas = 0;
        precioMaterial = 0;
    }

    public Revision(Revision revision) {
        if (revision == null) {
            throw new NullPointerException("No se puede copiar una revisión nula.");
        }
        setCliente(new Cliente(revision.getCliente()));
        setVehiculo(revision.getVehiculo());
        setFechaInicio(revision.getFechaInicio());
        setFechaFin(revision.getFechaFin());
        horas = revision.getHoras();
        precioMaterial = revision.getPrecioMaterial();
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("El cliente no puede ser nulo.");
        }
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    private void setVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new NullPointerException("El vehículo no puede ser nulo.");
        }
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio) {
        if (fechaInicio == null) {
            throw new NullPointerException("La fecha de inicio no puede ser nula.");
        }
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a hoy.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        if (fechaFin != null) {
            if (fechaFin.isBefore(fechaInicio) || fechaFin.isEqual(fechaInicio)) {
                throw new IllegalArgumentException("La fecha de fin no puede ser anterior o igual a la fecha de inicio.");
            }
            if (fechaFin.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("La fecha de fin no puede ser posterior a hoy.");
            }
        }
        this.fechaFin = fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public void anadirHoras(int horas) {
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser positivas.");
        }
        this.horas += horas;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial) {
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser positivo.");
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
        if (!estaCerrada()) {
            throw new IllegalStateException("La revisión no está cerrada.");
        }
        return horas * PRECIO_HORA + getDias() * PRECIO_DIA + precioMaterial * PRECIO_MATERIAL;
    }

    private float getDias() {
        if (!estaCerrada()) {
            throw new IllegalStateException("La revisión no está cerrada.");
        }
        return (float) java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaFin);
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
        return String.format("Revisión de %s - %s: Fecha inicio=%s, Fecha fin=%s, Horas=%d, Precio material=%.2f",
                cliente.getDni(), vehiculo.get(getVehiculo().matricula()),
                fechaInicio.format(FORMATO_FECHA),
                fechaFin != null ? fechaFin.format(FORMATO_FECHA) : "Sin finalizar",
                horas, precioMaterial);
    }
}
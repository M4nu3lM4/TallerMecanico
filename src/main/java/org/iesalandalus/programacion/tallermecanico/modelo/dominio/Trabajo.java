package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;

public abstract class Trabajo {
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final float FACTOR_DIA = 10.0F;

    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;

    protected Trabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        fechaFin = null;
        horas = 0;
    }

    protected Trabajo(Trabajo trabajo) {
        Objects.requireNonNull(trabajo,"El trabajo no puede ser nulo.");
        cliente = new Cliente(trabajo.cliente);
        vehiculo = trabajo.vehiculo;
        fechaInicio = trabajo.fechaInicio;
        fechaFin = trabajo.fechaFin;
        horas = trabajo.horas;

    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente,"El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo,"El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio,"La fecha de inicio no puede ser nula.");

        if (fechaInicio.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }

        this.fechaInicio = fechaInicio;
    }

    protected void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin,"La fecha de fin no puede ser nula.");

        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        this.fechaFin = fechaFin;
    }

    public void anadirHoras(int horas) throws TallerMecanicoExcepcion {
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        if (estaCerrado()){
            throw new TallerMecanicoExcepcion("No se puede añadir horas, ya que el trabajo está cerrado.");
        }
        this.horas += horas;
    }

    public boolean estaCerrado() {
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin)throws TallerMecanicoExcepcion {
        Objects.requireNonNull(fechaFin,"La fecha de fin no puede ser nula.");
        if (estaCerrado()){
            throw new TallerMecanicoExcepcion("El trabajo ya está cerrado.");
        }
        if (fechaFin.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        this.fechaFin = fechaFin;
        setFechaFin(fechaFin);
    }

    public float getPrecioFijo() {
        return (estaCerrado()) ? FACTOR_DIA * getDias() : 0;
    }

    private float getDias () {
        if (!estaCerrado()) {
            return 0;
        }
        return fechaInicio.until(fechaFin).getDays();
    }

    public abstract float getPrecioEspecifico();

    public float getPrecio() {
        return getPrecioFijo() + getPrecioEspecifico();
    }

    public static Trabajo get(Vehiculo vehiculo) {
        return new Revision(new Cliente("Test", "00000000X", "600000000"), vehiculo, LocalDate.now());
    }

    public static Trabajo copiar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo,"El trabajo no puede ser nulo.");
        Trabajo trabajoCopiado = null;
        if (trabajo instanceof Revision revision) {
            trabajoCopiado = new Revision(revision);
        } else if (trabajo instanceof Mecanico mecanico) {
            trabajoCopiado = new Mecanico(mecanico);
        }
        return trabajoCopiado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trabajo trabajo = (Trabajo) o;
        return cliente.equals(trabajo.cliente) && vehiculo.equals(trabajo.vehiculo) && fechaInicio.equals(trabajo.fechaInicio);
    }

    @Override
    public int hashCode() {
        return cliente.hashCode() + vehiculo.hashCode() + fechaInicio.hashCode();
    }

}
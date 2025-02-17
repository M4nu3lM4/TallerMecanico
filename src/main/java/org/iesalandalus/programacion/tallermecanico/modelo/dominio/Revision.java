package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        setCliente(cliente);
        setVehiculo(vehiculo);
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }

    public Revision(Revision revision){
        Objects.requireNonNull(revision, "La revisión no puede ser nula.");
        this.cliente = new Cliente(revision.cliente);
        this.vehiculo = revision.vehiculo;
        this.fechaInicio = revision.fechaInicio;
        this.fechaFin = revision.fechaFin;
        this.horas = revision.horas;
        this.precioMaterial = revision.precioMaterial;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return this.vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin, "La fecha de inicio no puede ser futura.");
        this.fechaFin = fechaFin;
    }

    public int getHoras() {
        return this.horas;
    }

    public void anadirHoras(int horas) throws TallerMecanicoExcepcion{
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        if (fechaFin != null){
            throw new TallerMecanicoExcepcion("No se puede añadir horas, ya que la revisión está cerrada.");
        }
        this.horas += horas;
    }

    public float getPrecioMaterial() {
        return this.precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial) throws TallerMecanicoExcepcion {
        if(precioMaterial <= 0) throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        if(fechaFin != null) throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que la revisión está cerrada.");
        this.precioMaterial += precioMaterial;
    }


    public boolean estaCerrada() {
        return this.fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if (fechaFin.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }
        if (fechaFin.isBefore(fechaInicio)){
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        if (estaCerrada()){
            throw new TallerMecanicoExcepcion("La revisión ya está cerrada.");
        }
        this.fechaFin = fechaFin;
    }

    public float getPrecio(){
        return (float) (this.horas * PRECIO_HORA +getDias() * PRECIO_DIA + getPrecioMaterial() * PRECIO_MATERIAL);
    }

    public float getDias(){
        if(fechaFin == null){
            return 0;
        }
        return fechaInicio.until(fechaFin, ChronoUnit.DAYS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, fechaInicio, vehiculo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Revision other = (Revision) obj;
        return Objects.equals(cliente, other.cliente) && Objects.equals(fechaInicio, other.fechaInicio)
                && Objects.equals(vehiculo, other.vehiculo);
    }

    public String toString() {
        if(!estaCerrada()) {
            return String.format("%s - %s: (%s - ), %d horas, %.2f € en material",
                    this.cliente.toString(), this.vehiculo.toString(),
                    fechaInicio.format(Revision.FORMATO_FECHA), getHoras(), getPrecioMaterial());
        }
        return String.format("%s - %s: (%s - %s), %d horas, %.2f € en material, %.2f € total",
                this.cliente.toString(), this.vehiculo.toString(),
                fechaInicio.format(Revision.FORMATO_FECHA),
                fechaFin.format(Revision.FORMATO_FECHA), getHoras(),
                getPrecioMaterial(), getPrecio());
    }



}
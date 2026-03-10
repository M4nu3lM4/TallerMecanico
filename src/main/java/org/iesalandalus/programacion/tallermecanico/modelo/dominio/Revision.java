package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Revision {
    private static final float PRECIO_HORA = 30;
    private static final float PRECIO_DIA = 10;
    private static final float PRECIO_MATERIAL = 1.5f;
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;

    private Cliente cliente;
    private Vehiculo vehiculo;


    public Revision(Cliente cliente,Vehiculo vehiculo,LocalDate fechaInicio){
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        horas = 0;
        precioMaterial = 0;
        fechaFin = null;
    }

    public Revision(Revision revision){
        Objects.requireNonNull(revision,"La revisión no puede ser nula.");
        this.cliente = new Cliente(revision.cliente);
        this.setVehiculo(revision.vehiculo);
        this.setFechaInicio(revision.fechaInicio);
        this.setFechaFin(revision.fechaFin);
        this.horas = revision.getHoras();
        this.precioMaterial = revision.precioMaterial;
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
        Objects.requireNonNull(fechaInicio,"La fecha de inicio no puede ser nula.");

        if (fechaInicio.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {

        if (fechaFin.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }

        if (fechaFin.isBefore(fechaInicio)){
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        this.fechaFin = fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public boolean estaCerrada() {
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(fechaFin,"La fecha de fin no puede ser nula.");

        if (estaCerrada()){
            throw new TallerMecanicoExcepcion("La revisión ya está cerrada.");
        }

        if (fechaFin.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }

        if (fechaFin.isBefore(fechaInicio)){
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        this.fechaFin = fechaFin;
        setFechaFin(fechaFin);
    }

    public void anadirHoras(int horas) throws TallerMecanicoExcepcion {
        if (estaCerrada()){
            throw new TallerMecanicoExcepcion("No se puede añadir horas, ya que la revisión está cerrada.");
        }
        if (horas <= 0){
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        this.horas += horas;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial) throws TallerMecanicoExcepcion {
        if (estaCerrada()) {
            throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que la revisión está cerrada.");
        }
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        this.precioMaterial += precioMaterial;
    }

    private float getDias(){
        if (fechaFin == null){
            return 0;
        }
        return fechaInicio.until(fechaFin).getDays();
    }

    public float getPrecio() {
        if (!estaCerrada()) {
            return 0;
        }
        return (getDias() * PRECIO_DIA) + (horas * PRECIO_HORA) + (precioMaterial * PRECIO_MATERIAL);
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return horas == revision.horas && Objects.equals(fechaInicio, revision.fechaInicio) && Objects.equals(vehiculo, revision.vehiculo) && Objects.equals(cliente, revision.cliente);
    }

    @Override
    public int hashCode () {
        return Objects.hash(cliente, vehiculo, fechaInicio);
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

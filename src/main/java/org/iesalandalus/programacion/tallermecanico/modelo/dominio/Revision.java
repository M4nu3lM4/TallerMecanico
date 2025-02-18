package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Revision {
    /**1º DECLARAMOS LOS ATRIBUTOS Y VARIABLES**/
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
    /**2º CREAMOS EL CONSTRUCTOR POR PARAMETROS DE REVISION**/
    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        setCliente(cliente);
        setVehiculo(vehiculo);
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }
    /**3º CREAMOS EL CONSTRUCTOR COPIA DE REVISION**/
    public Revision(Revision revision){
        Objects.requireNonNull(revision, "La revisión no puede ser nula.");
        this.cliente = new Cliente(revision.cliente);
        this.vehiculo = revision.vehiculo;
        this.fechaInicio = revision.fechaInicio;
        this.fechaFin = revision.fechaFin;
        this.horas = revision.horas;
        this.precioMaterial = revision.precioMaterial;
    }
    /**4º CREAMOS EL METODO GETCLIENTE**/
    public Cliente getCliente() {
        return this.cliente;
    }
    /**5º CREAMOS EL METODO SETCLIENTE**/
    public void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }
    /**6º CREAMOS EL METODO GET VEHICULO**/
    public Vehiculo getVehiculo() {
        return this.vehiculo;
    }
    /**7º CREAMOS EL METODO SETVEHICULO**/
    public void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }
    /**8º CREAMOS EL METODO GETFECHAINICIO**/
    public LocalDate getFechaInicio() {
        return this.fechaInicio;
    }
    /**9º CREAMOS EL METODO SETFECHAINICIO**/
    public void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        this.fechaInicio = fechaInicio;
    }
    /**10º CREAMOS EL METODO GETFECHAFIN**/
    public LocalDate getFechaFin() {
        return this.fechaFin;
    }
    /**11º CREAMOS EL METODO SETFECHAFIN**/
    public void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin, "La fecha de inicio no puede ser futura.");
        this.fechaFin = fechaFin;
    }
    /**12º CREAMOS EL METODO GETHORAS**/
    public int getHoras() {
        return this.horas;
    }
    /**13º CREAMOS EL METODO AÑADIRHORAS**/
    public void anadirHoras(int horas) throws TallerMecanicoExcepcion{
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        if (fechaFin != null){
            throw new TallerMecanicoExcepcion("No se puede añadir horas, ya que la revisión está cerrada.");
        }
        this.horas += horas;
    }
    /**14º CREAMOS EL METODO GETPRECIOMATERIAL**/
    public float getPrecioMaterial() {
        return this.precioMaterial;
    }
    /**15º CREAMOS EL METODO ANADIRPRECIOMATERIAL**/
    public void anadirPrecioMaterial(float precioMaterial) throws TallerMecanicoExcepcion {
        if(precioMaterial <= 0) throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        if(fechaFin != null) throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que la revisión está cerrada.");
        this.precioMaterial += precioMaterial;
    }

    /**16º CREAMOS EL METODO ESTACERRADA**/
    public boolean estaCerrada() {
        return this.fechaFin != null;
    }
    /**17º CREAMOS EL METODO CERRAR**/
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
    /**18º CREAMOS EL METODO GETPRECIO**/
    public float getPrecio(){
        return (float) (this.horas * PRECIO_HORA +getDias() * PRECIO_DIA + getPrecioMaterial() * PRECIO_MATERIAL);
    }
    /**19º CREAMOS EL METODO GETDIAS**/
    public float getDias(){
        if(fechaFin == null){
            return 0;
        }
        return fechaInicio.until(fechaFin, ChronoUnit.DAYS);
    }
    /**20º CREAMOS EL METODO HASHCODE PARA CALCULAR EL HASH DE LA REVISION**/
    @Override
    public int hashCode() {
        return Objects.hash(cliente, fechaInicio, vehiculo);
    }
    /**21º CREAMOS EL METODO EQUALS PARA COMPARAR SI DOS REVISIONES SON IGUALES**/
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
    /**22º CREAMOS EL METODO TOSTRING PARA IMPRIMIR LOS DATOS DE LA REVISION**/
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
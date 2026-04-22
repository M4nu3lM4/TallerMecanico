package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;

import java.util.Objects;

public class Revision extends Trabajo {

    private static final float FACTOR_HORA = 35.0f;

    public Revision(Cliente cliente,Vehiculo vehiculo,LocalDate fechaInicio){
        super(cliente,vehiculo,fechaInicio);
    }

    public Revision(Revision revision){
        super(revision);
        Objects.requireNonNull(revision,"La revisión no puede ser nula");
    }

    @Override
    public float getPrecioEspecifico() {
        return (estaCerrado() ? FACTOR_HORA * getHoras() : 0);
    }

    @Override
    public String toString() {
        if(!estaCerrado()) {
            return String.format("Revisión -> %s - %s (%s - ): %d horas",
                    this.getCliente().toString(), this.getVehiculo().toString(),
                    getFechaInicio().format(Revision.FORMATO_FECHA), getHoras());
        }
        return String.format("Revisión -> %s - %s (%s - %s): %d horas, %.2f € total",
                this.getCliente().toString(), this.getVehiculo().toString(),
                getFechaInicio().format(Revision.FORMATO_FECHA),
                getFechaFin().format(Revision.FORMATO_FECHA), getHoras(),
                getPrecio());
    }

}

package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Revisiones {
    private final List<Revision> coleccionRevisiones;

    public Revisiones() {
        coleccionRevisiones = new ArrayList<>();
    }

    public List<Revision> get() {
        return new ArrayList<>(coleccionRevisiones);
    }

    public List<Revision> get(Cliente cliente) {
        Objects.requireNonNull(cliente,"No se pueden buscar revisiones de un cliente nulo.");

        List<Revision> revisionesCliente = new ArrayList<>();
        for (Revision revision : coleccionRevisiones) {
            if (revision.getCliente().equals(cliente)) {
                revisionesCliente.add(revision);
            }
        }
        return revisionesCliente;
    }

    public List<Revision> get(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo,"No se pueden buscar revisiones de un vehículo nulo.");

        List<Revision> revisionesVehiculo = new ArrayList<>();
        for (Revision revision : coleccionRevisiones) {
            if (revision.getVehiculo().equals(vehiculo)) {
                revisionesVehiculo.add(revision);
            }
        }
        return revisionesVehiculo;
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision,"No se puede insertar una revisión nula.");

        for (Revision r : coleccionRevisiones) {
            if (!r.estaCerrada()) {
                if (r.getCliente().equals(revision.getCliente())) {
                    throw new TallerMecanicoExcepcion("El cliente tiene otra revisión en curso.");
                }
                if (r.getVehiculo().equals(revision.getVehiculo())) {
                    throw new TallerMecanicoExcepcion("El vehículo está actualmente en revisión.");
                }
            } else {
                if (r.getCliente().equals(revision.getCliente()) && r.getFechaInicio().isAfter(revision.getFechaInicio())) {
                    throw new TallerMecanicoExcepcion("El cliente tiene una revisión posterior.");
                }
                if (r.getVehiculo().equals(revision.getVehiculo()) && r.getFechaInicio().isAfter(revision.getFechaInicio())) {
                    throw new TallerMecanicoExcepcion("El vehículo tiene una revisión posterior.");
                }
            }
        }

        coleccionRevisiones.add(revision);
    }

    public Revision buscar(Revision revision) {
        Objects.requireNonNull(revision,"No se puede buscar una revisión nula.");

        int indice = coleccionRevisiones.indexOf(revision);
        return (indice != -1) ? coleccionRevisiones.get(indice) : null;
    }

    public void borrar(Revision revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision,"No se puede borrar una revisión nula.");

        if (!coleccionRevisiones.remove(revision)) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
    }

    public void anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion {
       Objects.requireNonNull(revision,"No puedo operar sobre una revisión nula.");

        Revision revisionExistente = buscar(revision);
        if (revisionExistente == null) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        revisionExistente.anadirHoras(horas);
    }

    private Revision getRevision(Revision revision){
        Objects.requireNonNull(revision,"No puedo operar sobre una revisión nula.");

        int indice = coleccionRevisiones.indexOf(revision);
        return (indice!= -1) ? coleccionRevisiones.get(indice) : null;
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision,"No puedo operar sobre una revisión nula.");
        Revision revisionExistente = buscar(revision);
        if (revisionExistente == null) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        revisionExistente.anadirPrecioMaterial(precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision,"No puedo operar sobre una revisión nula.");
        Revision revisionExistente = buscar(revision);
        if (revisionExistente == null) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        revisionExistente.cerrar(fechaFin);
    }
}
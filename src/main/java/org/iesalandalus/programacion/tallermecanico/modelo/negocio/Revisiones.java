package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Revisiones {
    private final List<Revision> coleccionRevisiones;

    public Revisiones(){
        coleccionRevisiones = new ArrayList<>();
    }

    public List<Revision> get(){
        return new ArrayList<>(coleccionRevisiones);
    }

    public List<Revision> get(Cliente cliente){
        List<Revision> revisionesClientes = new ArrayList<>();
        for (Revision r : coleccionRevisiones){
            if (r.getCliente().equals(cliente)){
                revisionesClientes.add(r);
            }
        }
        return revisionesClientes;
    }

    public List<Revision> get(Vehiculo vehiculo){
        List<Revision> revisionesVehiculos = new ArrayList<>();
        for (Revision v : coleccionRevisiones){
            if (v.getVehiculo().equals(vehiculo)){
                revisionesVehiculos.add(v);
            }
        }
        return revisionesVehiculos;
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion {

        Objects.requireNonNull(revision,"No se puede insertar una revisión nula.");

        comprobarRevision(revision.getCliente(),revision.getVehiculo(),revision.getFechaInicio());
        Revision revisionExistente = buscar(revision);

        if (revisionExistente != null){
            if (!revisionExistente.estaCerrada()){
                throw new TallerMecanicoExcepcion("Ya existe una revisión igual");
            }else {
                coleccionRevisiones.remove(revisionExistente);
            }
        }
        coleccionRevisiones.add(revision);
    }

    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws TallerMecanicoExcepcion {
        for (Revision r : coleccionRevisiones){
            if (!r.estaCerrada()){
                if(r.getCliente().equals(cliente)){
                    throw new TallerMecanicoExcepcion("El cliente tiene otra revisión en curso.");
                }
                if (r.getVehiculo().equals(vehiculo)){
                    throw new TallerMecanicoExcepcion("El vehículo está actualmente en revisión.");
                }

            }
            if (r.estaCerrada() && !r.getFechaFin().isBefore(fechaRevision)){
                if (r.getCliente().equals(cliente)){
                    throw new TallerMecanicoExcepcion("El cliente tiene una revisión posterior.");
                }
                if (r.getVehiculo().equals(vehiculo)){
                    throw new TallerMecanicoExcepcion("El vehículo tiene una revisión posterior.");
                }
            }
        }
    }

    private Revision getRevision(Revision revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision,"La revisión no puede ser nula.");

        Revision revisionExistente = buscar(revision);

        if (revisionExistente == null){
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        return revisionExistente;
    }


    public Revision anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision,"No puedo operar sobre una revisión nula.");

        Revision revision1 = getRevision(revision);

        revision1.anadirHoras(horas);

        return revision1;

    }

    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision,"No puedo operar sobre una revisión nula.");

        Revision revision1 = getRevision(revision);

        revision1.anadirPrecioMaterial(precioMaterial);

        return revision1;

    }

    public Revision cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision,"No puedo operar sobre una revisión nula.");

        Revision revisionCerrada = getRevision(revision);

        revisionCerrada.cerrar(fechaFin);

        return revisionCerrada;
    }

    public Revision buscar(Revision revision){
        Objects.requireNonNull(revision,"No se puede buscar una revisión nula.");

        for (Revision r : coleccionRevisiones){
            if (r.equals(revision)){
                return r;
            }
        }
        return null;
    }

    public void borrar(Revision revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision,"No se puede borrar una revisión nula.");

        Revision revisionExistente = buscar(revision);

        if (revisionExistente == null){
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        coleccionRevisiones.remove(revisionExistente);
    }





}

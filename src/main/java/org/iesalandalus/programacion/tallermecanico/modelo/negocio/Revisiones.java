package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Revisiones {
    /**1º CREAMOS LA LISTA coleccionRevisiones**/
    private final List<Revision> coleccionRevisiones;

    /**2º CREAMOS EL METODO REVISIONES PARA INICIAR LA LISTA**/
    public Revisiones() {
        coleccionRevisiones = new ArrayList<>();
    }
    /**3º CREAMOS EL METODO GET PARA OBTENER LA LISTA**/
    public List<Revision> get() {
        return new ArrayList<>(coleccionRevisiones);
    }

    public List<Revision> get(Cliente cliente) {
        List<Revision> revisionesCliente = new ArrayList<>();
        for (Revision revision : coleccionRevisiones) {
            if (revision.getCliente().equals(cliente)) {
                revisionesCliente.add(revision);
            }
        }
        return revisionesCliente;
    }

    public List<Revision> get(Vehiculo vehiculo) {
        List<Revision> revisionesVehiculo = new ArrayList<>();
        for (Revision revision : coleccionRevisiones) {
            if (revision.getVehiculo().equals(vehiculo)) {
                revisionesVehiculo.add(revision);
            }
        }
        return revisionesVehiculo;
    }
    /**6º CREAMOS EL METODO INSERTAR PARA AÑADIR UNA REVISION A LA LISTA**/
    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("No se puede insertar una revisión nula.");
        }
        comprobarRevision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio());
        coleccionRevisiones.add(revision);
    }
    /**7º CREAMOS EL METODO COMPROBARREVISION PARA SABER SI HAY UNA REVISION EN LA LISTA**/
    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision)throws TallerMecanicoExcepcion{
        for(Revision r : coleccionRevisiones){
            if(!r.estaCerrada()) {
                if (r.getCliente().equals(cliente)) {
                    throw new TallerMecanicoExcepcion("El cliente tiene otra revisión en curso.");
                }
                if (r.getVehiculo().equals(vehiculo)) {
                    throw new TallerMecanicoExcepcion("El vehículo está actualmente en revisión.");
                }
            }
                if(r.estaCerrada() && !r.getFechaFin().isBefore(fechaRevision)){
                    if(r.getCliente().equals(cliente)){
                        throw new TallerMecanicoExcepcion("El cliente tiene una revisión posterior.");
                    }
                    if(r.getVehiculo().equals(vehiculo)){
                        throw new TallerMecanicoExcepcion("El vehículo tiene una revisión posterior.");
                    }
                }

        }

    }
    /**8º CREAMOS EL METODO AÑADIR HORAS PARA SUMAR HORAS A LAS REVISIONES**/
    public void anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion {
        Revision revision1 = getRevision(revision);
        revision1.anadirHoras(horas);

    }
    /**9º CREAMOS EL METODO GETrEVISION PARA OBTENER LA REVISION**/
    private Revision getRevision(Revision revision) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("No puedo operar sobre una revisión nula.");
        }
        Revision revisionExistente = buscar(revision);
        if (revisionExistente == null) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        return revisionExistente;
    }
    /**10º CREAMOS EL METODO AÑADIR PRECIO MATERIAL PARA SUMAR PRECIO A LA REVISION**/
    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion {
        Revision revision1 = getRevision(revision);
        revision1.anadirPrecioMaterial(precioMaterial);

    }
    /**11º CREAMOS EL METODO CERRAR PARA PONER FIN A LA REVISION**/
    public void cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("No puedo operar sobre una revisión nula.");
        }
        Revision revisionExistente = buscar(revision);
        if (revisionExistente == null) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        revisionExistente.cerrar(fechaFin);
    }
    /**12 CREAMOS EL METODO BORRAR PARA ELIMINAR UNA REVISION**/
    public void borrar(Revision revision) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("No se puede borrar una revisión nula.");
        }
        if (!coleccionRevisiones.remove(revision)) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
    }

    public Revision buscar(Revision revision) {
        if (revision == null) {
            throw new NullPointerException("No se puede buscar una revisión nula.");
        }
        int index = coleccionRevisiones.indexOf(revision);
        return (index != -1) ? coleccionRevisiones.get(index) : null;
    }

}
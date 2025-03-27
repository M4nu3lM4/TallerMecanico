package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.IModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Controlador {
    private IModelo modeloCascada;
    private Vista vista;

    public Controlador(IModelo modeloCascada, Vista vista) {
        Objects.requireNonNull(modeloCascada, "El modelo no puede ser nulo.");
        Objects.requireNonNull(vista, "La vista no puede ser nula.");
        this.modeloCascada = modeloCascada;
        this.vista = vista;
        this.vista.setControlador(this);
    }

    public void comenzar() {
        modeloCascada.comenzar();
        vista.comenzar();
    }

    public void terminar() {
        modeloCascada.terminar();
        vista.terminar();
    }

    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        modeloCascada.insertar(cliente);
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        modeloCascada.insertar(vehiculo);
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        modeloCascada.insertar(revision);
    }

    public Cliente buscar(Cliente cliente) {
        return modeloCascada.buscar(cliente);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return modeloCascada.buscar(vehiculo);
    }

    public Revision buscar(Revision revision) {
        return modeloCascada.buscar(revision);
    }

    public void modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        modeloCascada.modificar(cliente, nombre, telefono);
    }

    public void anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion {
        modeloCascada.anadirHoras(revision, horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion {
        modeloCascada.anadirPrecioMaterial(revision, precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        modeloCascada.cerrar(revision, fechaFin);
    }

    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        modeloCascada.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        modeloCascada.borrar(vehiculo);
    }

    public void borrar(Revision revision) throws TallerMecanicoExcepcion {
        modeloCascada.borrar(revision);
    }

    public List<Cliente> getClientes() {
        return modeloCascada.getClientes();
    }

    public List<Vehiculo> getVehiculos() {
        return modeloCascada.getVehiculos();
    }

    public List<Revision> getRevisiones() {
        return modeloCascada.getRevisiones();
    }

    public List<Revision> getRevisiones(Cliente cliente) {
        return modeloCascada.getRevisiones(cliente);
    }

    public List<Revision> getRevisiones(Vehiculo vehiculo) {
        return modeloCascada.getRevisiones(vehiculo);
    }
}
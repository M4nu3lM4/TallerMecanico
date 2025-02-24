package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;

import java.time.LocalDate;
import java.util.List;

public class Modelo {
    private Clientes clientes;
    private Vehiculos vehiculos;
    private Revisiones revisiones;

    public Modelo() {
        comenzar();
    }

    public void comenzar() {
        this.clientes = new Clientes();
        this.vehiculos = new Vehiculos();
        this.revisiones = new Revisiones();
    }

    public void terminar() {
        System.out.println("Modelo terminado.");
    }

    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        clientes.insertar(new Cliente(cliente));
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        Cliente cliente = buscar(revision.getCliente());
        Vehiculo vehiculo = buscar(revision.getVehiculo());
        if (cliente != null && vehiculo != null) {
            revisiones.insertar(new Revision(revision));
        } else {
            return;
        }
    }

    public Cliente buscar(Cliente cliente) {
        Cliente clienteEncontrado = clientes.buscar(cliente);
        return clienteEncontrado != null ? new Cliente(clienteEncontrado) : null;
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return vehiculos.buscar(vehiculo);
    }

    public Revision buscar(Revision revision){
        Revision revisionEncontrada = revisiones.buscar(revision);
        return revisionEncontrada != null ? new Revision(revisionEncontrada) : null;
    }

    public void modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        clientes.modificar(cliente, nombre, telefono);
    }

    public void anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion {
        revisiones.anadirHoras(revision, horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion {
        revisiones.anadirPrecioMaterial(revision, precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        revisiones.cerrar(revision, fechaFin);
    }

    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        List<Revision> revisionesCliente = revisiones.get(cliente);
        for (Revision revision : revisionesCliente) {
            revisiones.borrar(revision);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        List<Revision> revisionesVehiculo = revisiones.get(vehiculo);
        for (Revision revision : revisionesVehiculo) {
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }

    public void borrar(Revision revision) throws TallerMecanicoExcepcion {
        revisiones.borrar(revision);
    }

    public List<Cliente> getClientes() {
        List<Cliente> listaClientes = clientes.get();
        return listaClientes.stream().map(Cliente::new).toList();
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos.get();
    }

    public List<Revision> getRevisiones() {
        List<Revision> listaRevisiones = revisiones.get();
        return listaRevisiones.stream().map(Revision::new).toList();
    }

    public List<Revision> getRevisiones(Cliente cliente) {
        List<Revision> revisionesCliente = revisiones.get(cliente);
        return revisionesCliente.stream().map(Revision::new).toList();
    }

    public List<Revision> getRevisiones(Vehiculo vehiculo) {
        List<Revision> revisionesVehiculo = revisiones.get(vehiculo);
        return revisionesVehiculo.stream().map(Revision::new).toList();
    }
}
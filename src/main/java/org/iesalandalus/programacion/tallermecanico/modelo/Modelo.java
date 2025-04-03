package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;

import java.time.LocalDate;
import java.util.List;

public interface Modelo {
    void comenzar();

    void terminar();

    void insertar(Cliente cliente) throws TallerMecanicoExcepcion;

    void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion;

    void insertar(Trabajo trabajo) throws TallerMecanicoExcepcion;

    Cliente buscar(Cliente cliente);

    Vehiculo buscar(Vehiculo vehiculo);

    Trabajo buscar(Trabajo trabajo);

    void modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion;

    void anadirHoras(Trabajo trabajo, int horas) throws TallerMecanicoExcepcion;

    void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws TallerMecanicoExcepcion;

    void cerrar(Trabajo trabajo, LocalDate fechaFin) throws TallerMecanicoExcepcion;

    void borrar(Cliente cliente) throws TallerMecanicoExcepcion;

    void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion;

    void borrar(Trabajo trabajo) throws TallerMecanicoExcepcion;

    List<Cliente> getClientes();

    List<Vehiculo> getVehiculos();

    List<Trabajo> getTrabajos();

    List<Trabajo> getTrabajos(Cliente cliente);

    List<Trabajo> getTrabajos(Vehiculo vehiculo);
}
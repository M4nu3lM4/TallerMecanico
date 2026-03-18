package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import java.time.LocalDate;
import java.util.List;

public class Modelo {

    private Clientes clientes;
    private Vehiculos vehiculos;
    private Revisiones revisiones;


    public void Modelo(){
        comenzar();
    }

    public void comenzar(){
        this.clientes = new Clientes();
        this.vehiculos = new Vehiculos();
        this.revisiones = new Revisiones();
    }

    public void terminar(){
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
        if (cliente != null && vehiculo != null){
            revisiones.insertar(new Revision(revision));
        } else {
            return;
        }
    }

    public Cliente buscar(Cliente cliente){
        Cliente clienteEncontrado = clientes.buscar(cliente);
        return clienteEncontrado != null ? new Cliente(clienteEncontrado) : null;
    }

    public Vehiculo buscar(Vehiculo vehiculo){
        return vehiculos.buscar(vehiculo);
    }

    public Revision buscar(Revision revision){
        Revision revisionEncontrada = revisiones.buscar(revision);
        return revisionEncontrada != null ? new Revision(revisionEncontrada) : null;
    }

    public Cliente modificar(Cliente cliente,String nombre,String telefono){

    }

    public Revision anadirHoras(Revision revision, int horas){

    }

    public Revision anadirPrecioMaterial(Revision revision, int precioMaterial){

    }

    public Revision cerrar(Revision revision, LocalDate fechaFin){

    }

    public void borrar(Cliente cliente){

    }

    public void borrar(Vehiculo vehiculo){

    }

    public void borrar(Revision revision){

    }

    public List<Cliente> getClientes(){

    }

    public List<Vehiculo> getVehiculos(){

    }

    public List<Revision> getRevisiones(){

    }

    public List<Revision> getRevisiones(Cliente cliente){

    }

    public List<Revision> getRevisiones(Vehiculo vehiculo){

    }


}

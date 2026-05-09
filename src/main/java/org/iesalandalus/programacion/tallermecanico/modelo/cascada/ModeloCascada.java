package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.Vehiculos;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;



public class ModeloCascada implements Modelo {

    private IClientes clientes;
    private IVehiculos vehiculos;
    private ITrabajos trabajos;
    private FabricaFuenteDatos fabricaFuenteDatos;

    public ModeloCascada(FabricaFuenteDatos fabricaFuenteDatos){
        Objects.requireNonNull(fabricaFuenteDatos,"La factoría de la fuente de datos no puede ser nula.");
        this.fabricaFuenteDatos = fabricaFuenteDatos;
    }

    @Override
    public void comenzar(){
        IFuenteDatos fuenteDatos = fabricaFuenteDatos.crear();
        this.clientes = fuenteDatos.crearClientes();
        this.vehiculos = fuenteDatos.crearVehiculos();
        this.trabajos = fuenteDatos.crearTrabajos();
        clientes.comenzar();
        vehiculos.comenzar();
        trabajos.comenzar();
    }

    @Override
    public void terminar() throws TallerMecanicoExcepcion {
        clientes.terminar();
        vehiculos.terminar();
        trabajos.terminar();
        System.out.println("Modelo terminado.");
    }

    @Override
    public void insertar (Cliente cliente) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente,"El cliente no puede ser nulo.");
        clientes.insertar(new Cliente(cliente));
    }

    @Override
    public void insertar (Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(vehiculo,"El vehículo no puede ser nulo.");
        vehiculos.insertar(vehiculo);
    }

    @Override
    public void insertar (Trabajo trabajo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        Cliente cliente = buscar(trabajo.getCliente());
        Vehiculo vehiculo = buscar(trabajo.getVehiculo());
        if (cliente != null && vehiculo != null) {
            if (trabajo instanceof Revision revision) {
                Revision nuevaRevision = new Revision(cliente, vehiculo, revision.getFechaInicio());
                trabajos.insertar(nuevaRevision);
            } else if (trabajo instanceof Mecanico mecanico) {
                Mecanico nuevoMecanico = new Mecanico(cliente, vehiculo, mecanico.getFechaInicio());
                trabajos.insertar(nuevoMecanico);
            }
        }
    }

    @Override
    public Cliente buscar (Cliente cliente) {
        Cliente clienteEncontrado = clientes.buscar(cliente);
        return clienteEncontrado != null ? new Cliente(clienteEncontrado) : null;
    }

    @Override
    public Vehiculo buscar (Vehiculo vehiculo) {
        return vehiculos.buscar(vehiculo);
    }

    @Override
    public Trabajo buscar (Trabajo trabajo) {
        Trabajo trabajoEncontrado = trabajos.buscar(trabajo);
        return (trabajoEncontrado != null) ? Trabajo.copiar(trabajoEncontrado) : null;
    }

    @Override
    public Cliente modificar(Cliente cliente, String nombre, String telefono)throws TallerMecanicoExcepcion{
        Objects.requireNonNull(cliente,"El cliente no puede ser nulo.");
        Objects.requireNonNull(nombre,"El nombre no puede ser nulo.");
        Objects.requireNonNull(telefono,"El teléfono no puede ser nulo.");
        clientes.modificar(cliente,nombre,telefono);
        return new Cliente(cliente);
    }

    @Override
    public Trabajo anadirHoras(Trabajo trabajo, int horas) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(trabajo,"La revisión no puede ser nula.");
        trabajos.anadirHoras(trabajo, horas);
        return Trabajo.copiar(trabajo);
    }

    @Override
    public Trabajo anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(trabajo,"La revisión no puede ser nula.");
        return trabajos.anadirPrecioMaterial(trabajo,precioMaterial);
    }

    @Override
    public Trabajo cerrar(Trabajo trabajo, LocalDate fechaFin) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(trabajo,"La revisión no puede ser nula.");
        Objects.requireNonNull(fechaFin,"La fecha no puede ser nula.");

        trabajos.cerrar(trabajo,fechaFin);
        return Trabajo.copiar(trabajo);
    }

    @Override
    public void borrar (Cliente cliente) throws TallerMecanicoExcepcion {
        List<Trabajo> trabajoClientes = trabajos.get(cliente);
        for (Trabajo trabajo : trabajoClientes) {
            trabajos.borrar(trabajo);
        }
        clientes.borrar(cliente);
    }

    @Override
    public void borrar (Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        List<Trabajo> trabajoVehiculos = trabajos.get(vehiculo);
        for (Trabajo trabajo : trabajoVehiculos) {
            trabajos.borrar(trabajo);
        }
        vehiculos.borrar(vehiculo);
    }

    @Override
    public void borrar (Trabajo trabajo) throws TallerMecanicoExcepcion {
        trabajos.borrar(trabajo);
    }

    @Override
    public List<Cliente> getClientes () {
        List<Cliente> listaClientes = clientes.get();
        return listaClientes.stream().map(Cliente::new).toList();
    }

    @Override
    public List<Vehiculo> getVehiculos () {
        return vehiculos.get();
    }

    @Override
    public List<Trabajo> getTrabajos () {
        return trabajos.get().stream().map(trabajo -> {
            if (trabajo instanceof Revision) {
                return new Revision((Revision) trabajo);
            } else {
                return new Mecanico((Mecanico) trabajo);
            }
        }).toList();
    }

    @Override
    public List<Trabajo> getTrabajos (Cliente cliente) {
        return trabajos.get(cliente).stream().map(Trabajo::copiar).toList();
    }

    @Override
    public List<Trabajo> getTrabajos (Vehiculo vehiculo) {
        return trabajos.get(vehiculo).stream().map(Trabajo::copiar).toList();
    }

    @Override
    public Map<TipoTrabajo,Integer> getEstadisticasMensuales(LocalDate mes){
        return trabajos.getEstadisticasMensuales(mes);
    }
}

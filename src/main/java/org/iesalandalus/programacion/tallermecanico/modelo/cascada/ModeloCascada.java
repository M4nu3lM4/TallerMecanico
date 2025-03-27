package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.IModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Mecanico;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ModeloCascada implements IModelo {
    private IClientes clientes;
    private IVehiculos vehiculos;
    private ITrabajos trabajos;

    public ModeloCascada(FabricaFuenteDatos fabricaFuenteDatos) {
        Objects.requireNonNull(fabricaFuenteDatos,"La factoria de la fuente de datos no puede ser nula.");
        IFuenteDatosMemoria fuenteDatosMemoria = fabricaFuenteDatos.crear();
        clientes = fuenteDatosMemoria.crearClientes();
        vehiculos = fuenteDatosMemoria.crearVehiculos();
        trabajos = fuenteDatosMemoria.crearTrabajos();
    }

    @Override
    public void comenzar() {
        System.out.println("Modelo comenzado");
    }

    @Override
    public void terminar() {
        System.out.println("Modelo terminado.");
    }

    @Override
    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        clientes.insertar(new Cliente(cliente));
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        vehiculos.insertar(vehiculo);
    }

    @Override
    public void insertar(Trabajo trabajo) throws TallerMecanicoExcepcion {
        Cliente cliente = buscar(trabajo.getCliente());
        Vehiculo vehiculo = buscar(trabajo.getVehiculo());
        if (cliente != null && vehiculo != null) {
            if (trabajo instanceof Revision) {
                trabajos.insertar(new Revision((Revision) trabajo));
            } else if (trabajo instanceof Mecanico) {
                trabajos.insertar(new Mecanico((Mecanico) trabajo));
            }
        }
    }

    @Override
    public Cliente buscar(Cliente cliente) {
        Cliente clienteEncontrado = clientes.buscar(cliente);
        return clienteEncontrado != null ? new Cliente(clienteEncontrado) : null;
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        return vehiculos.buscar(vehiculo);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        Trabajo trabajoEncontrado = trabajos.buscar(trabajo);
        if (trabajoEncontrado instanceof Revision) {
            return new Revision((Revision) trabajoEncontrado);
        } else if (trabajoEncontrado instanceof Mecanico) {
            return new Mecanico((Mecanico) trabajoEncontrado);
        }
        return null;
    }

    @Override
    public void modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        clientes.modificar(cliente, nombre, telefono);
    }

    @Override
    public void anadirHoras(Trabajo trabajo, int horas)throws TallerMecanicoExcepcion {
        trabajos.anadirHoras(trabajo, horas);
    }

    @Override
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws TallerMecanicoExcepcion {
        if ((trabajo instanceof Mecanico)) {
            throw new TallerMecanicoExcepcion("Solo se puede añadir precio de material a trabajos mecánicos");
        }
            trabajos.anadirPrecioMaterial(trabajo, precioMaterial);

    }

    @Override
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        trabajos.cerrar(trabajo, fechaFin);
    }

    @Override
    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        for (Trabajo trabajo : trabajos.get(cliente)) {
            trabajos.borrar(trabajo);
        }
        clientes.borrar(cliente);
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        for (Trabajo trabajo : trabajos.get(vehiculo)) {
            trabajos.borrar(trabajo);
        }
        vehiculos.borrar(vehiculo);
    }

    @Override
    public void borrar(Trabajo trabajo) throws TallerMecanicoExcepcion {
        trabajos.borrar(trabajo);
    }

    @Override
    public List<Cliente> getClientes() {
        return clientes.get().stream().map(Cliente::new).toList();
    }

    @Override
    public List<Vehiculo> getVehiculos() {
        return vehiculos.get();
    }

    @Override
    public List<Trabajo> getTrabajos() {
        return trabajos.get().stream().map(trabajo -> {
            if (trabajo instanceof Revision) {
                return new Revision((Revision) trabajo);
            } else {
                return new Mecanico((Mecanico) trabajo);
            }
        }).toList();
    }

    @Override
    public List<Trabajo> getTrabajos(Cliente cliente) {
        return trabajos.get(cliente).stream().map(trabajo -> {
            if (trabajo instanceof Revision) {
                return new Revision((Revision) trabajo);
            } else {
                return new Mecanico((Mecanico) trabajo);
            }
        }).toList();
    }

    @Override
    public List<Trabajo> getTrabajos(Vehiculo vehiculo) {
        return trabajos.get(vehiculo).stream().map(trabajo -> {
            if (trabajo instanceof Revision) {
                return new Revision((Revision) trabajo);
            } else {
                return new Mecanico((Mecanico) trabajo);
            }
        }).toList();
    }
}
package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes implements IClientes {
    private final List<Cliente> coleccionClientes;

    public Clientes(){
        coleccionClientes = new ArrayList<>();
    }

    @Override
    public List<Cliente> get(){
        return new ArrayList<>(coleccionClientes);
    }

    @Override
    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {

        Objects.requireNonNull(cliente,"No se puede insertar un cliente nulo.");

        if (coleccionClientes.contains(cliente)){
            throw new TallerMecanicoExcepcion("Ya existe un cliente con ese DNI.");
        }

        coleccionClientes.add(cliente);
    }

    @Override
    public Cliente modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {

        Objects.requireNonNull(cliente,"No se puede modificar un cliente nulo.");

        if (!coleccionClientes.contains(cliente)){
            throw new TallerMecanicoExcepcion("No existe ningún cliente con ese DNI.");
        }

        Cliente clienteExistente = buscar(cliente);

        if (nombre != null && !nombre.isBlank()){
            clienteExistente.setNombre(nombre);
        }

        if (telefono != null && !telefono.isBlank()){
            clienteExistente.setTelefono(telefono);
        }
        return clienteExistente;

    }

    @Override
    public Cliente buscar(Cliente cliente){

        Objects.requireNonNull(cliente,"No se puede buscar un cliente nulo.");

        for (Cliente c : coleccionClientes){
            if (c.equals(cliente)){
                return c;
            }
        }
        return null;
    }

    @Override
    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {

        Objects.requireNonNull(cliente,"No se puede borrar un cliente nulo.");

        Cliente clienteExistente = buscar(cliente);

        if (clienteExistente == null){
            throw new TallerMecanicoExcepcion("No existe ningún cliente con ese DNI.");
        }

        coleccionClientes.remove(clienteExistente);



    }
}

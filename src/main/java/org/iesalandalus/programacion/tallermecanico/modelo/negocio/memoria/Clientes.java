package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import java.util.ArrayList;
import java.util.List;

public class Clientes implements org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes {
    /**1º CREAMOS LA LISTA DE COLECCIONCLIENTES**/
    private final List<Cliente> coleccionClientes;

    /**2º CREAMOS EL CONSTRUCTOR PARA INICIALIZAR LA LISTA**/
    public Clientes() {
        coleccionClientes = new ArrayList<>();
    }

    /**3º CREAMOS EL CONSTRUCTOR PARA OBTENER LA LISTA **/
    @Override
    public List<Cliente> get() {
        return new ArrayList<>(coleccionClientes);
    }
    /**4º CREAMOS EL CONSTRUCTOR INSERTAR PARA AÑADIR UN CLIENTE A LA LISTA DE CLIENTES**/
    @Override
    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        if (cliente == null) {
            throw new NullPointerException("No se puede insertar un cliente nulo.");
        }
        if (coleccionClientes.contains(cliente)) {
            throw new TallerMecanicoExcepcion("Ya existe un cliente con ese DNI.");
        }
        coleccionClientes.add(cliente);
    }
    /**5º CREAMOS EL CONSTRUCTOR MODIFICAR PARA MODIFICAR UN CLIENTE DE LA LISTA**/
    @Override
    public Cliente modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        if (cliente == null) {
            throw new NullPointerException("No se puede modificar un cliente nulo.");
        }
        Cliente clienteExistente = buscar(cliente);
        if (clienteExistente == null) {
            throw new TallerMecanicoExcepcion("No existe ningún cliente con ese DNI.");
        }
        if (nombre != null && !nombre.isBlank()) {
            clienteExistente.setNombre(nombre);
        }
        if (telefono != null && !telefono.isBlank()) {
            clienteExistente.setTelefono(telefono);
        }
        return new Cliente(cliente);
    }
    /**6º CREAMOS EL CONSTRUCTOR BUSCAR PARA PODER BUSCAR UN CLIENTE EN LA LISTA**/
    @Override
    public Cliente buscar(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("No se puede buscar un cliente nulo.");
        }
        for (Cliente c : coleccionClientes) {
            if (c.equals(cliente)) {
                return c;
            }
        }
        return null;
    }

    /**7º CREAMOS EL CONSTRUCTOR BORRAR PARA PODER BORRAR UN CLIENTE DE LA LISTA**/
    @Override
    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        if (cliente == null) {
            throw new NullPointerException("No se puede borrar un cliente nulo.");
        }
        Cliente clienteExistente = buscar(cliente);
        if (clienteExistente == null) {
            throw new TallerMecanicoExcepcion("No existe ningún cliente con ese DNI.");
        }
        coleccionClientes.remove(clienteExistente);
    }
}

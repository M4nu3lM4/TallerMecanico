package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.vista.Opcion;
import org.iesalandalus.programacion.tallermecanico.vista.Consola;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Vista {
    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador,"El controlador no puede ser nulo.");

        this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        System.out.println("¡Hasta luego! Gracias por usar nuestra aplicación.");
    }

    public void ejecutar(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_CLIENTE:
                insertarCliente();
                break;
            case INSERTAR_VEHICULO:
                insertarVehiculo();
                break;
            case INSERTAR_REVISION:
                insertarRevision();
                break;
            case BUSCAR_CLIENTE:
                buscarCliente();
                break;
            case BUSCAR_VEHICULO:
                buscarVehiculo();
                break;
            case BUSCAR_REVISION:
                buscarRevision();
                break;
            case MODIFICAR_CLIENTE:
                modificarCliente();
                break;
            case ANADIR_HORAS_REVISION:
                anadirHoras();
                break;
            case ANADIR_PRECIO_MATERIAL_REVISION:
                anadirPrecioMaterial();
                break;
            case CERRAR_REVISION:
                cerrarRevision();
                break;
            case BORRAR_CLIENTE:
                borrarCliente();
                break;
            case BORRAR_VEHICULO:
                borrarVehiculo();
                break;
            case BORRAR_REVISION:
                borrarRevision();
                break;
            case LISTAR_CLIENTES:
                listarClientes();
                break;
            case LISTAR_VEHICULOS:
                listarVehiculos();
                break;
            case LISTAR_REVISIONES:
                listarRevisiones();
                break;
            case LISTAR_REVISIONES_CLIENTE:
                listarRevisionesCliente();
                break;
            case LISTAR_REVISIONES_VEHICULO:
                listarRevisionesVehiculo();
                break;
            case SALIR:
                terminar();
                break;
        }
    }

    private void insertarCliente() {
        try {
            Cliente cliente = Consola.leerCliente();
            controlador.insertar(cliente);
            System.out.println("Cliente insertado correctamente.");
        } catch (TallerMecanicoExcepcion e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
        }
    }

    private void insertarVehiculo() {
        try {
            Vehiculo vehiculo = Consola.leerVehiculo();
            controlador.insertar(vehiculo);
            System.out.println("Vehículo insertado correctamente.");
        } catch (TallerMecanicoExcepcion e) {
            System.out.println("Error al insertar vehículo: " + e.getMessage());
        }
    }

    private void insertarRevision() {
        try {
            Revision revision = Consola.leerRevision();
            controlador.insertar(revision);
            System.out.println("Revisión insertada correctamente.");
        } catch (TallerMecanicoExcepcion e) {
            System.out.println("Error al insertar revisión: " + e.getMessage());
        }
    }

    private void buscarCliente() {
        String dni = String.valueOf(Consola.leerClienteDni());
        Cliente cliente = controlador.buscar(Cliente.get(dni));
        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("No se encontró el cliente.");
        }
    }

    private void buscarVehiculo() {
        String matricula = String.valueOf(Consola.leerVehiculoMatricula());
        Vehiculo vehiculo = controlador.buscar(Vehiculo.get(matricula));
        if (vehiculo != null) {
            System.out.println(vehiculo);
        } else {
            System.out.println("No se encontró el vehículo.");
        }
    }

    private void buscarRevision() {
        Vehiculo vehiculo = Consola.leerVehiculo();
        LocalDate fecha = Consola.leerFechaCierre();
        Cliente cliente = Consola.leerCliente();
        Revision revision = controlador.buscar(new Revision(cliente,vehiculo,fecha));
        if (revision != null) {
            System.out.println(revision);
        } else {
            System.out.println("No se encontró la revisión.");
        }
    }

    private void modificarCliente() {
        try {
            String dni = String.valueOf(Consola.leerCliente());
            Cliente cliente = controlador.buscar(Cliente.get(dni));
            if (cliente != null) {
                Cliente clienteModificado = Consola.leerCliente();
                controlador.modificar(cliente, cliente.getNombre(), cliente.getTelefono());
                System.out.println("Cliente modificado correctamente.");
            } else {
                System.out.println("No se encontró el cliente.");
            }
        } catch (TallerMecanicoExcepcion e) {
            System.out.println("Error al modificar cliente: " + e.getMessage());
        }
    }

    private void anadirHoras() {
        try {
            Revision revision = Consola.leerRevision();
            int horas = Consola.leerHoras();
            controlador.anadirHoras(revision, horas);
            System.out.println("Horas añadidas correctamente.");
        } catch (TallerMecanicoExcepcion e) {
            System.out.println("Error al añadir horas: " + e.getMessage());
        }
    }

    private void anadirPrecioMaterial() {
        try {
            Revision revision = Consola.leerRevision();
            float precioMaterial = Consola.leerPrecioMaterial();
            controlador.anadirPrecioMaterial(revision, precioMaterial);
            System.out.println("Precio de material añadido correctamente.");
        } catch (TallerMecanicoExcepcion e) {
            System.out.println("Error al añadir precio de material: " + e.getMessage());
        }
    }

    private void cerrarRevision() {
        try {
            Revision revision = Consola.leerRevision();
            LocalDate fechaFin = Consola.leerFechaCierre();
            controlador.cerrar(revision, fechaFin);
            System.out.println("Revisión cerrada correctamente.");
        } catch (TallerMecanicoExcepcion e) {
            System.out.println("Error al cerrar revisión: " + e.getMessage());
        }
    }

    private void borrarCliente() {
        try {
            Cliente cliente = Consola.leerCliente();
            controlador.borrar(cliente);
            System.out.println("Cliente borrado correctamente.");
        } catch (TallerMecanicoExcepcion e) {
            System.out.println("Error al borrar cliente: " + e.getMessage());
        }
    }

    private void borrarVehiculo() {
        try {
            Vehiculo vehiculo = Consola.leerVehiculo();
            controlador.borrar(vehiculo);
            System.out.println("Vehículo borrado correctamente.");
        } catch (TallerMecanicoExcepcion e) {
            System.out.println("Error al borrar vehículo: " + e.getMessage());
        }
    }

    private void borrarRevision() {
        try {
            Revision revision = Consola.leerRevision();
            controlador.borrar(revision);
            System.out.println("Revisión borrada correctamente.");
        } catch (TallerMecanicoExcepcion e) {
            System.out.println("Error al borrar revisión: " + e.getMessage());
        }
    }

    private void listarClientes() {
        List<Cliente> clientes = controlador.getClientes();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    private void listarVehiculos() {
        List<Vehiculo> vehiculos = controlador.getVehiculos();
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
        } else {
            for (Vehiculo vehiculo : vehiculos) {
                System.out.println(vehiculo);
            }
        }
    }

    private void listarRevisiones() {
        List<Revision> revisiones = controlador.getRevisiones();
        if (revisiones.isEmpty()) {
            System.out.println("No hay revisiones registradas.");
        } else {
            for (Revision revision : revisiones) {
                System.out.println(revision);
            }
        }
    }

    private void listarRevisionesCliente() {
        Cliente cliente = Consola.leerCliente();
        List<Revision> revisiones = controlador.getRevisiones(cliente);
        if (revisiones.isEmpty()) {
            System.out.println("No hay revisiones para este cliente.");
        } else {
            for (Revision revision : revisiones) {
                System.out.println(revision);
            }
        }
    }

    private void listarRevisionesVehiculo() {
        Vehiculo vehiculo = Consola.leerVehiculo();
        List<Revision> revisiones = controlador.getRevisiones(vehiculo);
        if (revisiones.isEmpty()) {
            System.out.println("No hay revisiones para este vehículo.");
        } else {
            for (Revision revision : revisiones) {
                System.out.println(revision);
            }
        }
    }
}
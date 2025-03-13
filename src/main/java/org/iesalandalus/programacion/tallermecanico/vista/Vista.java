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
        controlador.terminar();
    }

    public void terminar() {
        System.out.println("¡Hasta luego! Gracias por usar nuestra aplicación.");
    }

    public void ejecutar(Opcion opcion) {
        try {
            switch (opcion) {
                case INSERTAR_CLIENTE -> insertarCliente();
                case BUSCAR_CLIENTE -> buscarCliente();
                case BORRAR_CLIENTE ->  borrarCliente();
                case LISTAR_CLIENTES -> listarClientes();
                case MODIFICAR_CLIENTE -> modificarCliente();
                case INSERTAR_VEHICULO -> insertarVehiculo();
                case BUSCAR_VEHICULO -> buscarVehiculo();
                case BORRAR_VEHICULO -> borrarVehiculo();
                case LISTAR_VEHICULOS -> listarVehiculos();
                case INSERTAR_REVISION -> insertarRevision();
                case BUSCAR_REVISION -> buscarRevision();
                case BORRAR_REVISION -> borrarRevision();
                case LISTAR_REVISIONES -> listarRevisiones();
                case LISTAR_REVISIONES_CLIENTE -> listarRevisionesCliente();
                case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculo();
                case ANADIR_HORAS_REVISION -> anadirHoras();
                case ANADIR_PRECIO_MATERIAL_REVISION -> anadirPrecioMaterial();
                case CERRAR_REVISION -> cerrarRevision();
                case SALIR -> salir();

            }
        } catch (Exception e) {
            System.out.println("ERROR:"+ e.getMessage());
        }
    }

    private void insertarCliente()throws TallerMecanicoExcepcion{
        Consola.mostrarCabecera("Insertar Cliente");
            controlador.insertar(Consola.leerCliente());
            System.out.println("Cliente insertado correctamente.");

    }

    private void insertarVehiculo()throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Insertar Vehiculo");
            controlador.insertar(Consola.leerVehiculo());
            System.out.println("Vehículo insertado correctamente.");

    }

    private void insertarRevision() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Insertar Revision");
            controlador.insertar(Consola.leerRevision());
            System.out.println("Revisión insertada correctamente.");
    }

    private void buscarCliente() {
        Consola.mostrarCabecera("Buscar Cliente");
        Cliente cliente = controlador.buscar(Consola.leerCliente());
        System.out.println((cliente != null) ? cliente :"No existe ningún cliente con dicho DNI");
    }

    private void buscarVehiculo() {
        Consola.mostrarCabecera("Buscar Vehiculo");
        Vehiculo vehiculo = controlador.buscar(Consola.leerVehiculo());
        System.out.println((vehiculo != null) ? vehiculo :"No existe ningún vehículo con dicha matrícula");
    }

    private void buscarRevision() {
        Consola.mostrarCabecera("Buscar Revision");
        Revision revision = controlador.buscar(Consola.leerRevision());
        System.out.println((revision != null) ? revision :"No existe ninguna revision para ese cliente , vehículo y fecha");
    }

    private void modificarCliente()throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Modificar Cliente");
        controlador.modificar(Consola.leerClienteDni(),Consola.leerNuevoNombre(),Consola.leerNuevoTelefono());
        System.out.println("El cliente se ha modificado correctamente");
    }

    private void anadirHoras() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Añadir Horas");
            Revision revision = Consola.leerRevision();
            int horas = Consola.leerHoras();
            controlador.anadirHoras(revision, horas);
            System.out.println("Horas añadidas correctamente.");

    }

    private void anadirPrecioMaterial() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Añadir PrecioMaterial");
            Revision revision = Consola.leerRevision();
            float precioMaterial = Consola.leerPrecioMaterial();
            controlador.anadirPrecioMaterial(revision, precioMaterial);
            System.out.println("Precio de material añadido correctamente.");

    }

    private void cerrarRevision() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Cerrar Revision");
            controlador.cerrar(Consola.leerRevision(), Consola.leerFechaCierre());
            System.out.println("Revisión cerrada correctamente.");

    }

    private void borrarCliente() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Borrar Revision");
            Cliente cliente = Consola.leerCliente();
            controlador.borrar(cliente);
            System.out.println("Cliente borrado correctamente.");

    }

    private void borrarVehiculo() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Borrar Vehiculo");
            Vehiculo vehiculo = Consola.leerVehiculo();
            controlador.borrar(vehiculo);
            System.out.println("Vehículo borrado correctamente.");

    }

    private void borrarRevision()throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Borrar Revision");
        Revision revision = Consola.leerRevision();
            controlador.borrar(revision);
            System.out.println("Revisión borrada correctamente.");

    }

    private void listarClientes() {
        Consola.mostrarCabecera("Listar Clientes");
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
        Consola.mostrarCabecera("Listar Vehiculos");
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
        Consola.mostrarCabecera("Listar Revisiones");
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
        Consola.mostrarCabecera("Listar Revisiones Cliente");
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
        Consola.mostrarCabecera("Listar Revisiones Cliente");
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
    private void salir(){
    /** No se hace nada**/
    }
}
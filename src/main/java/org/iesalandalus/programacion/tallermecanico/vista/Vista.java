package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vista {

    Controlador controlador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador,"El controlador no puede ser nulo.");

        this.controlador = controlador;
    }

    public void comenzar(){
        Opcion opcion;

        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        }while (opcion != Opcion.SALIR);
    }

    public void terminar(){
        System.out.println("Hasta luego. !Gracias por utilizar nuestra aplicación¡");
    }

    private void ejecutar(Opcion opcion){
        try {
            switch (opcion){
                case INSERTAR_CLIENTE -> insertarCliente();
                case BUSCAR_CLIENTE -> buscarCliente();
                case BORRAR_CLIENTE -> borrarCliente();
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
                case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculos();
                case ANADIR_HORAS_REVISION -> anadirHoras();
                case ANADIR_PRECIO_MATERIAL_REVISION -> anadirPrecioMaterial();
                case CERRAR_REVISION -> cerrarRevision();
                case SALIR -> salir();
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+ e.getMessage());
        }
    }

    private void insertarCliente()throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Insertar Cliente: ");
        controlador.insertar(Consola.leerCliente());
        System.out.println("Cliente insertado.");

    }

    private void insertarVehiculo()throws TallerMecanicoExcepcion{
        Consola.mostrarCabecera("Insertar Vehículo: ");
        controlador.insertar(Consola.leerVehiculo());
        System.out.println("Vehículo insertado.");
    }

    private void insertarRevision()throws TallerMecanicoExcepcion{
        Consola.mostrarCabecera("Insertar Revision: ");
        controlador.insertar(Consola.leerRevision());
        System.out.println("Revisión insertada.");
    }

    private void buscarCliente(){
        Consola.mostrarCabecera("Buscar Cliente: ");
        Cliente cliente = controlador.buscar(Consola.leerClienteDni());
        System.out.println(cliente != null ? cliente :"No existe ningún cliente con dicho DNI.");
    }

    private void buscarVehiculo(){
        Consola.mostrarCabecera("Buscar Vehiculo: ");
        Vehiculo vehiculo = controlador.buscar(Consola.leerVehiculoMatricula());
        System.out.println(vehiculo != null ? vehiculo : "No existe ningún vehiculo con dicha matrícula.");
    }

    private void buscarRevision(){
        Consola.mostrarCabecera("Buscar Revision: ");
        Revision revision = controlador.buscar(Consola.leerRevision());
        System.out.println(revision != null ? revision : "No existe ninguna revisión.");

    }

    private void modificarCliente()throws TallerMecanicoExcepcion{
        Consola.mostrarCabecera("Modificar Cliente: ");
        controlador.modificar(Consola.leerClienteDni(),Consola.leerNuevoNombre(),Consola.leerNuevoTelefono());
        System.out.println("Cliente modificado.");
    }

    private void anadirHoras()throws TallerMecanicoExcepcion{
        Consola.mostrarCabecera("Añadir Horas: ");
        Revision revision = Consola.leerRevision();
        int horas = Consola.leerHoras();
        controlador.anadirHoras(revision, horas);
        System.out.println("Horas añadidas correctamente.");
    }

    private void anadirPrecioMaterial()throws TallerMecanicoExcepcion{
        Consola.mostrarCabecera("Añadir PrecioMaterial: ");
        Revision revision = Consola.leerRevision();
        float precioMaterial = Consola.leerPrecioMaterial();
        controlador.anadirPrecioMaterial(revision, precioMaterial);
        System.out.println("Precio de material añadido correctamente.");
    }

    private void cerrarRevision()throws TallerMecanicoExcepcion{
        Consola.mostrarCabecera("Cerrar Revisión: ");
        controlador.cerrar(Consola.leerRevision(),Consola.leerFechaCierre());
        System.out.println("Revisión cerrada correctamente.");
    }

    private void borrarCliente()throws TallerMecanicoExcepcion{
        Consola.mostrarCabecera("Borrar Cliente: ");
        Cliente cliente = Consola.leerCliente();
        controlador.borrar(cliente);
        System.out.println("Cliente borrado correctamente.");

    }

    private void borrarVehiculo()throws TallerMecanicoExcepcion{
        Consola.mostrarCabecera("Borrar Vehiculo: ");
        Vehiculo vehiculo = Consola.leerVehiculo();
        controlador.borrar(vehiculo);
        System.out.println("Vehiculo borrado correctamente.");
    }

    private void borrarRevision()throws TallerMecanicoExcepcion{
        Consola.mostrarCabecera("Borrar Revisión: ");
        Revision revision = Consola.leerRevision();
        controlador.borrar(revision);
        System.out.println("Revisión borrada correctamente");
    }

    private void listarClientes(){
        Consola.mostrarCabecera("Listar Clientes: ");
        List<Cliente> clientes = controlador.getClientes();
        if (clientes.isEmpty()){
            System.out.println("No hay clientes registrados.");
        }else {
            for (Cliente cliente : clientes){
                System.out.println(cliente);
            }
        }
    }

    private void listarVehiculos(){
        Consola.mostrarCabecera("Listar Vehiculos: ");
        List<Vehiculo> vehiculos = controlador.getVehiculos();
        if (vehiculos.isEmpty()){
            System.out.println("No hay vehículos registrados.");
        }else {
            for (Vehiculo vehiculo : vehiculos){
                System.out.println(vehiculo);
            }
        }
    }

    private void listarRevisiones(){
        Consola.mostrarCabecera("Listar Revisiones: ");
        List<Revision> revisiones = controlador.getRevisiones();
        if (revisiones.isEmpty()){
            System.out.println("No hay revisiones registradas.");
        }else {
            for (Revision revision : revisiones){
                System.out.println(revision);
            }
        }
    }

    private void listarRevisionesCliente(){
        Consola.mostrarCabecera("Listar revisiones por cliente: ");
        Cliente cliente = Consola.leerCliente();
        List<Revision> revisiones = controlador.getRevisiones(cliente);
        if (revisiones.isEmpty()){
            System.out.println("No hay revisiones registradas para ese cliente.");
        }else {
            for (Revision revision : revisiones){
                System.out.println(revision);
            }
        }
    }

    private void listarRevisionesVehiculos(){
        Consola.mostrarCabecera("Listar revisiones por vehiculo: ");
        Vehiculo vehiculo = Consola.leerVehiculo();
        List<Revision> revisiones = controlador.getRevisiones(vehiculo);
        if (revisiones.isEmpty()){
            System.out.println("No hay revisiones registradas para ese vehiculo");
        }else {
            for (Revision revision : revisiones){
                System.out.println(revision);
            }
        }
    }

    private void salir(){
        System.out.print("Hasta luego. ¡Gracias por utilizar nuestra aplicación!.");
    }
}

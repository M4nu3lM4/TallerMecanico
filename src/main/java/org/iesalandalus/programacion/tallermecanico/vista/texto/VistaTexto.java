package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.cascada.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.tallermecanico.vista.texto.Consola;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class VistaTexto implements Vista {
    private GestorEventos gestorEventos = new GestorEventos(Evento.values());

    @Override
    public GestorEventos getGestorEventos() {
        return gestorEventos;
    }

    @Override
    public void comenzar() {
        System.out.println("Bienvenido a la aplicación de taller mecánico!");
        Evento opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        }while (opcion != Evento.SALIR);
    }

    @Override
    public void ejecutar(Evento opcion) {
        Consola.mostrarCabecera(opcion.toString());
        gestorEventos.notificar(opcion);
    }

    @Override
    public void terminar() {
        System.out.println("Gracias por usar la aplicación. ¡Hasta pronto!");

    }
    @Override
    public Cliente leerCliente(){
        String dni = Consola.leerCadena("Introduce el DNI del cliente: ");
        String nombre = Consola.leerCadena("Introduce el nombre del cliente: ");
        String telefono = Consola.leerCadena("Introduce el teléfono del cliente: ");
        return new Cliente(dni, nombre, telefono);
    }
    @Override
    public Cliente leerClienteDni(){
        return Cliente.get(Consola.leerCadena("Introduce el DNI"));
    }
    @Override
    public String leerNuevoNombre(){
        return Consola.leerCadena("Introduce el nuevo nombre del cliente: ");

    }


    @Override
    public String leerNuevoTelefono(){
        return Consola.leerCadena("Introduce el nuevo teléfono del cliente: ");

    }
    @Override
    public Vehiculo leerVehiculo(){
        String matricula = Consola.leerCadena("Introduce la matrícula del vehículo: ");
        String marca = Consola.leerCadena("Introduce la marca del vehículo: ");
        String modelo = Consola.leerCadena("Introduce el modelo del vehículo: ");
        return new Vehiculo(matricula, marca, modelo);
    }

    @Override
    public Vehiculo leerVehiculoMatricula(){
        return Vehiculo.get(Consola.leerCadena("Introduce la matrícula"));
    }

    @Override
    public Revision leerRevision(){
        LocalDate fechaInicio = Consola.leerFecha("Introduce la fecha de inicio:");
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        return new Revision(cliente, vehiculo, fechaInicio);
    }

    @Override
    public Trabajo leerMecanico(){
        LocalDate fechaInicio = Consola.leerFecha("Introduce la fecha de inicio:");
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        return new Revision(cliente, vehiculo, fechaInicio);
    }

    @Override
    public Trabajo leerTrabajoVehiculo(){
        return Trabajo.get(leerVehiculoMatricula());
    }

    @Override
    public int leerHoras(){
        return Consola.leerEntero("Introduce las horas del trabajo: ");

    }
    @Override
    public float leerPrecioMaterial(){
        return Consola.leerReal("Introduce el precio del material: ");

    }
    @Override
    public LocalDate leerFechaCierre(){
        return Consola.leerFecha("Introduce la fecha de cierre del trabajo: ");

    }


    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito) {
        System.out.println(exito ? "ÉXITO: " : "ERROR: " + texto);
    }

    @Override
    public void mostrarCliente(Cliente cliente) {
        System.out.println((cliente!= null) ? cliente : "No existe ningún cliente con ese DNI");
    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {
        System.out.println((vehiculo!= null) ? vehiculo : "No existe ningún cliente con ese DNI");
    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo) {
        System.out.println((trabajo!= null) ? trabajo : "No existe ningún trabajo para ese cliente, vehiculo y fecha");
    }

    @Override
    public void mostrarClientes(List<Cliente>clientes) {
        if (!clientes.isEmpty()){
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }else{
            System.out.println("No hay clientes que mostrar");
        }
    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {
        if (!vehiculos.isEmpty()){
            for (Vehiculo vehiculo : vehiculos) {
                System.out.println(vehiculo);
            }
        }else{
            System.out.println("No hay vehiculos que mostrar");
        }
    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {
        if (!trabajos.isEmpty()){
            for (Trabajo trabajo : trabajos) {
                System.out.println(trabajo);
            }
        }else{
            System.out.println("No hay vehiculos que mostrar");
        }
    }
    @Override
    public void mostrarTrabajosCliente(List<Trabajo> trabajosCliente) {
        if (!trabajosCliente.isEmpty()){
            for (Trabajo trabajo : trabajosCliente) {
                System.out.println(trabajo);
            }
        }else{
            System.out.println("No hay trabajos para este cliente");
        }
    }
    @Override
    public void mostrarTrabajosVehiculo(List<Trabajo> trabajosVehiculo) {
        if (!trabajosVehiculo.isEmpty()){
            for (Trabajo trabajo : trabajosVehiculo) {
                System.out.println(trabajo);
            }
        }else{
            System.out.println("No hay trabajos para este vehículo");
        }
    }
}
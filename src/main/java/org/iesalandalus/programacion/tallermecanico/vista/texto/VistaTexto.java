package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento.*;
import static org.iesalandalus.programacion.tallermecanico.vista.texto.Consola.*;

public class VistaTexto implements org.iesalandalus.programacion.tallermecanico.vista.Vista {
    private GestorEventos gestorEventos = new GestorEventos(Evento.values());
    private EnumMap<TipoTrabajo,Integer> estadisticas = new EnumMap<>(TipoTrabajo.class);

    @Override
    public GestorEventos getGestorEventos () {
        return gestorEventos;
    }

    @Override
    public void comenzar () {
        Evento opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Evento.SALIR);
    }

    @Override
    public void terminar () {
        System.out.println("¡Hasta luego! Gracias por usar nuestra aplicación.");
    }

    private void ejecutar(Evento opcion) {
        switch (opcion){
            case INSERTAR_CLIENTE -> gestorEventos.notificar(INSERTAR_CLIENTE);
            case BUSCAR_CLIENTE -> gestorEventos.notificar(BUSCAR_CLIENTE);
            case BORRAR_CLIENTE -> gestorEventos.notificar(BORRAR_CLIENTE);
            case LISTAR_CLIENTES -> gestorEventos.notificar(LISTAR_CLIENTES);
            case MODIFICAR_CLIENTE -> gestorEventos.notificar(MODIFICAR_CLIENTE);
            case INSERTAR_VEHICULO -> gestorEventos.notificar(INSERTAR_VEHICULO);
            case BUSCAR_VEHICULO -> gestorEventos.notificar(BUSCAR_VEHICULO);
            case BORRAR_VEHICULO -> gestorEventos.notificar(BORRAR_VEHICULO);
            case LISTAR_VEHICULOS -> gestorEventos.notificar(LISTAR_VEHICULOS);
            case INSERTAR_REVISION -> gestorEventos.notificar(INSERTAR_REVISION);
            case INSERTAR_MECANICO -> gestorEventos.notificar(INSERTAR_MECANICO);
            case BUSCAR_TRABAJO -> gestorEventos.notificar(BUSCAR_TRABAJO);
            case BORRAR_TRABAJO -> gestorEventos.notificar(BORRAR_TRABAJO);
            case LISTAR_TRABAJOS -> gestorEventos.notificar(LISTAR_TRABAJOS);
            case LISTAR_TRABAJOS_CLIENTE -> gestorEventos.notificar(LISTAR_TRABAJOS_CLIENTE);
            case LISTAR_TRABAJOS_VEHICULO ->  gestorEventos.notificar(LISTAR_TRABAJOS_VEHICULO);
            case ANADIR_HORAS_TRABAJO -> gestorEventos.notificar(ANADIR_HORAS_TRABAJO);
            case ANADIR_PRECIO_MATERIAL_TRABAJO ->  gestorEventos.notificar(ANADIR_PRECIO_MATERIAL_TRABAJO);
            case CERRAR_TRABAJO -> gestorEventos.notificar(CERRAR_TRABAJO);
            case MOSTRAR_ESTADISTICAS_MENSUALES -> gestorEventos.notificar(MOSTRAR_ESTADISTICAS_MENSUALES);
            case SALIR -> gestorEventos.notificar(SALIR);
        }
    }

    @Override
    public Cliente leerCliente () {
        String nombre = Consola.leerCadena("Introduce el nombre: ");
        String dni = Consola.leerCadena("Introduce el DNI: ");
        String telefono = Consola.leerCadena("Introduce el teléfono: ");
        return new Cliente(nombre, dni, telefono);
    }

    @Override
    public Cliente leerClienteDni () {
        return Cliente.get(Consola.leerCadena("Introduce el DNI: "));
    }

    @Override
    public String leerNuevoNombre () {
        return Consola.leerCadena("Introduce el nuevo nombre: ");
    }

    @Override
    public String leerNuevoTelefono () {
        return Consola.leerCadena("Introduce el nuevo teléfono: ");
    }

    @Override
    public Vehiculo leerVehiculo () {
        String marca = Consola.leerCadena("Introduce la marca: ");
        String modelo = Consola.leerCadena("Introduce el modelo: ");
        String matricula = Consola.leerCadena("Introduce la matrícula: ");
        return new Vehiculo(marca, modelo, matricula);
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
        return Vehiculo.get(Consola.leerCadena("Introduce la matrícula: "));
    }

    @Override
    public Trabajo leerRevision () {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = Consola.leerFecha("Introduce la fecha de inicio");
        return new Revision(cliente, vehiculo, fechaInicio);
    }

    @Override
    public Trabajo leerMecanico () {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = Consola.leerFecha("Introduce la fecha de inicio");
        return new Mecanico(cliente,vehiculo,fechaInicio);
    }

    @Override
    public Trabajo leerTrabajoVehiculo () {
        return Trabajo.get(leerVehiculoMatricula());
    }

    @Override
    public int leerHoras () {
        return Consola.leerEntero("Introduce las horas a añadir: ");
    }

    @Override
    public float leerPrecioMaterial () {
        return Consola.leerReal("Introduce el precio del material: ");
    }

    @Override
    public LocalDate leerFechaCierre () {
        return Consola.leerFecha("Introduce la fecha de cierre");
    }

    @Override
    public LocalDate leerMes(){
        return leerFecha("Pon la fecha.");
    }

    @Override
    public void notificarResultado (Evento evento, String texto, boolean exito) {
        if (exito) {
            System.out.println(texto);
        } else {
            System.out.printf("ERROR: %s%n", texto);
        }
    }

    @Override
    public void mostrarCliente (Cliente cliente) {
        System.out.println((cliente != null) ? cliente : "No existe ningún cliente que tenga ese DNI.");
    }

    @Override
    public void mostrarVehiculo (Vehiculo vehiculo) {
        System.out.println((vehiculo != null) ? vehiculo : "No existe ningún vehículo que tenga esa matrícula.");
    }

    @Override
    public void mostrarTrabajo (Trabajo trabajo) {
        System.out.println((trabajo != null) ? trabajo : "No hay ningún trabajo que cumpla esos requisitos.");
    }

    @Override
    public void mostrarClientes (List<Cliente> clientes) {
        if (!clientes.isEmpty()) {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        } else {
            System.out.println("No hay ningún cliente.");
        }
    }

    @Override
    public void mostrarVehiculos (List<Vehiculo> vehiculos) {
        if (!vehiculos.isEmpty()) {
            for (Vehiculo vehiculo : vehiculos) {
                System.out.println(vehiculo);
            }
        } else {
            System.out.println("No hay ningún vehículo.");
        }
    }

    @Override
    public void mostrarTrabajos (List<Trabajo> trabajos) {
        if (!trabajos.isEmpty()) {
            for (Trabajo trabajo : trabajos) {
                System.out.println(trabajo);
            }
        } else {
            System.out.println("No hay ningún trabajo.");
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


    public void mostrarEstadisticasMensuales(Map<TipoTrabajo,Integer> estadisticas){
        Objects.requireNonNull(estadisticas,"Las estadísticas no pueden ser nulas.");
        System.out.println(estadisticas);
    }
}

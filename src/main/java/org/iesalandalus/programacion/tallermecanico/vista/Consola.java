package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";


    private Consola() {
    }

    public static void mostrarCabecera(String mensaje) {
        System.out.println(mensaje);
        System.out.println("-".repeat(mensaje.length()));
    }

    public static void mostrarMenu() {
        mostrarCabecera("Taller Mecánico - Gestión de Vehículos");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Entrada.entero();
    }

    public static float leerReal(String mensaje) {
        System.out.print(mensaje);
        return Entrada.real();
    }

    public static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    public static LocalDate leerFecha(String mensaje) {
       LocalDate fecha;
       DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
       mensaje = String.format("%s (%s): ",mensaje ,CADENA_FORMATO_FECHA);

       try {
           fecha = LocalDate.parse(leerCadena(mensaje),formatoFecha);
       }catch (DateTimeParseException e){
           fecha = null;
       }
       return fecha;
    }

    public static Opcion elegirOpcion() {
        int ordinal;
        do {
            ordinal = leerEntero("Seleccione una opción: ");
        } while (ordinal < 0 || ordinal >= Opcion.values().length);
        return Opcion.values()[ordinal];
    }

    public static Cliente leerCliente() {
        String nombre = leerCadena("Introduce el nombre del cliente: ");
        String dni = leerCadena("Introduce el DNI del cliente: ");
        String telefono = leerCadena("Introduce el teléfono del cliente: ");
        return new Cliente(nombre, dni, telefono);
    }

    public static Cliente leerClienteDni() {
        String dni = leerCadena("Introduce el DNI del cliente: ");
        return Cliente.get(dni);
    }

    public static String leerNuevoNombre() {
        return leerCadena("Introduce el nuevo nombre: ");
    }

    public static String leerNuevoTelefono() {
        return leerCadena("Introduce el nuevo teléfono: ");
    }

    public static Vehiculo leerVehiculo() {
        String marca = leerCadena("Introduce la marca del vehículo: ");
        String modelo = leerCadena("Introduce el modelo del vehículo: ");
        String matricula = leerCadena("Introduce la matrícula del vehículo: ");
        return new Vehiculo(marca, modelo, matricula);
    }

    public static Vehiculo leerVehiculoMatricula() {
        String matricula = leerCadena("Introduce la matrícula del vehículo: ");
        return Vehiculo.get(matricula);
    }

    public static Revision leerRevision(){
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio (" + CADENA_FORMATO_FECHA + "): ");
        return new Revision(cliente, vehiculo, fechaInicio);
    }

    public static int leerHoras() {
        return leerEntero("Introduce las horas: ");
    }

    public static float leerPrecioMaterial() {
        return leerReal("Introduce el precio del material: ");
    }

    public static LocalDate leerFechaCierre() {
        return leerFecha("Introduce la fecha de cierre (" + CADENA_FORMATO_FECHA + "): ");
    }
}
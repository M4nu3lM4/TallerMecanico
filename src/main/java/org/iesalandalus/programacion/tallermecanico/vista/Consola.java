package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);

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
        LocalDate fecha = null;
        do {
            try {
                System.out.print(mensaje);
                String fechaString = Entrada.cadena();
                fecha = LocalDate.parse(fechaString, FORMATO_FECHA);
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato de fecha incorrecto. Use el formato " + CADENA_FORMATO_FECHA);
            }
        } while (fecha == null);
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

    public static String leerNombre() {
        return leerCadena("Introduce el nombre: ");
    }

    public static String leerTelefono() {
        return leerCadena("Introduce el teléfono: ");
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
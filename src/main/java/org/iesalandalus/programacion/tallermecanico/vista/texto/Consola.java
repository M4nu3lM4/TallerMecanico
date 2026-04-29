package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {

    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola(){}

     static void mostrarCabecera(String mensaje){
        System.out.println(mensaje);
        System.out.println("-".repeat(mensaje.length()));

    }

     static void mostrarMenu(){
        mostrarCabecera("Gestión de Taller Mecánico");
        for (Evento opcion : Evento.values()){
            System.out.println(opcion);
        }
    }

     static float leerReal(String mensaje) {
        System.out.print(mensaje);
        return Entrada.real();
    }

     static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Entrada.entero();
    }

     static String leerCadena(String mensaje){
        System.out.print(mensaje);
        return Entrada.cadena();
    }

     static LocalDate leerFecha(String mensaje){

        LocalDate fecha = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        do {
            try {
                System.out.print(mensaje + " (" + CADENA_FORMATO_FECHA + "):");
                String fechaString = Entrada.cadena();
                fecha = LocalDate.parse(fechaString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha no válido. Inténtalo de nuevo.");
            }
        } while (fecha == null);
        return fecha;
    }

     static Evento elegirOpcion() {
        int numeroOpcion = leerEntero("Elige una opción: ");
        if (Evento.esValido(numeroOpcion)) {
            return Evento.get(numeroOpcion);
        } else {
            System.out.println("Opción no válida, intentalo de nuevo.");
            return elegirOpcion();
        }
    }




}

package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.utilidades.Entrada;
import org.iesalandalus.programacion.tallermecanico.vista.texto.VistaTexto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";


    private Consola() {
    }

    public static void mostrarCabecera(String mensaje) {
        System.out.printf("%n%s%n",mensaje);
        String formatoStr = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(formatoStr,0).replace("0","-"));
    }

    public static void mostrarMenu() {
        mostrarCabecera("Taller Mecánico - Gestión de Vehículos");
        for (Evento evento : Evento.values()) {
            System.out.printf("%d.- %s%n", evento.ordinal(), evento.toString());
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

    public static Evento elegirOpcion() {
        Evento opcion = null;
        do{
            try{
                opcion = Evento.get(leerEntero("\nElija una opcion: "));
            }catch (IllegalArgumentException e){
                System.out.println("ERROR: %s%n" + e.getMessage());
            }
        }while (opcion == null);
        return opcion;
    }




}
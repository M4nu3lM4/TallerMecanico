package org.iesalandalus.programacion.tallermecanico.vista;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.vista.Opcion;

import java.time.LocalDate;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola(){}

    public static void mostrarCabecera(String mensaje) {
        mensaje = "====Taller Mecanico====";
        System.out.println(mensaje);
        System.out.println("______________________");

    }
    public static void mostrarMenu(){
        Consola.mostrarCabecera();
    }
    public static Opcion elegirOpcion(){

    }
    private static int LeerEntero(String mensaje){

    }
    private static float LeerReal(String mensaje){

    }
    private static String LeerCadena(String mensaje){

    }
    private static LocalDate LeerFecha(String mensaje){

    }
    public static Cliente leerCliente(){

    }

    public static Cliente leerClienteDni(){

    }

    public static String leerNuevoNombre(){

    }

    public static String leerNuevoTelefono(){

    }

    public static Vehiculo leerVehiculo(){

    }

    public static Vehiculo leerVehiculoMatricula(){

    }

    public static Revision leerRevision(){

    }

    public static int leerHoras(){

    }
    public static float leerPrecioMaterial(){

    }
    public static LocalDate leerFechaCierre(){

    }

}

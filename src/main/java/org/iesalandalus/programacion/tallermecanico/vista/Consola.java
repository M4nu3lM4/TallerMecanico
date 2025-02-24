package org.iesalandalus.programacion.tallermecanico.vista;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola(){}

    public static void mostrarCabecera() {
        System.out.println("====================================");
        System.out.println("  Taller Mecánico - Gestión de Vehículos");
        System.out.println("====================================");
        System.out.println();
    }
    public static void mostrarMenu(){
        Consola.mostrarCabecera();
    }
}

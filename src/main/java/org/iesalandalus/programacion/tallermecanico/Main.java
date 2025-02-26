package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;

public class Main {
    public static void main(String[] args) {
        /**Creamos las instancias de Modelo, Vista y Controlador**/
        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo, vista);

        /**Creamos el metodo comenzar del controlador**/
        controlador.comenzar();
    }
}
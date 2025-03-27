package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.tallermecanico.modelo.IModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.cascada.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;

public class Main {
    public static void main(String[] args) {
        /**Creamos las instancias de Modelo, Vista y Controlador**/
        IModelo modeloCascada = new ModeloCascada();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modeloCascada, vista);

        /**Creamos el metodo comenzar del controlador**/
        controlador.comenzar();
    }
}
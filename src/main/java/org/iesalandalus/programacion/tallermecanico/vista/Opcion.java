package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.util.Map;
import java.util.TreeMap;

public enum Opcion {
    INSERTAR_CLIENTE(1,"Insertar cliente."),
    BUSCAR_CLIENTE(2,"Buscar cliente."),
    BORRAR_CLIENTE(3,"Borrar cliente."),
    LISTAR_CLIENTE(4,"Listar clientes."),
    MODIFICAR_CLIENTE(5,"Modificar cliente."),
    INSERTAR_VEHICULO(6,"Insertar vehículo."),
    BUSCAR_VEHICULO(7,"Buscar vehículo."),
    BORRAR_VEHICULO(8,"Borrar vehículo."),
    LISTAR_VEHICULO(9,"Listar vehículos."),
    INSERTAR_REVISION(10,"Insertar revisión."),
    BUSCAR_REVISION(11,"Buscar revisión."),
    BORRAR_REVISION(12,"Borrar revision."),
    LISTAR_REVISIONES(13,"Listar revisiones."),
    LISTAR_REVISIONES_CLIENTES(14,"Listar revisiones por cliente."),
    LISTAR_REVISIONES_VEHICULOS(15,"Listar revisiones por vehiculo."),
    ANADIR_HORAS_REVISION(16,"Añadir horas a la revisión."),
    ANADIR_PRECIO_MATERIAL_REVISION(17,"añadir precio material a la revisión."),
    CERRAR_REVISION(18,"Cerrar revisión."),
    SALIR(19,"Salir.");

    private int numeroOpcion;

    private String mensaje;

    private static Map<Integer,Opcion> opciones = new TreeMap<>();

    private Opcion(int numeroOpcion,String mensaje){

    }

    public static boolean esValida(int numeroOpcion){
        if (!opciones.containsKey(numeroOpcion)){
            return false;
        }
        return true;
    }

    public static Opcion get(int numeroOpcion) throws TallerMecanicoExcepcion {

        if (!esValida(numeroOpcion)){
            throw new TallerMecanicoExcepcion("La opción no es válida");
        }
        return Opcion.get(numeroOpcion);
    }

    @Override
    public String toString() {
        return String.format("%s, %s", numeroOpcion, mensaje);
    }
}

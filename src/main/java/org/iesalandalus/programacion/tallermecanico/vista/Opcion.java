package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.Map;
import java.util.HashMap;

public enum Opcion {
    INSERTAR_CLIENTE(11, "Insertar Cliente"),
    BUSCAR_CLIENTE(12, "Buscar Cliente"),
    BORRAR_CLIENTE(13, "Borrar Cliente"),
    LISTAR_CLIENTES(14, "Listar Clientes"),
    MODIFICAR_CLIENTE(15, "Modificar Cliente"),
    INSERTAR_VEHICULO(21, "Insertar Vehículo"),
    BUSCAR_VEHICULO(22, "Buscar Vehículo"),
    BORRAR_VEHICULO(23, "Borrar Vehículo"),
    LISTAR_VEHICULOS(24, "Listar Vehículos"),
    INSERTAR_REVISION(31, "Insertar Revisión"),
    BUSCAR_REVISION(32, "Buscar Revisión"),
    BORRAR_REVISION(33, "Borrar Revisión"),
    LISTAR_REVISIONES(34, "Listar Revisiones"),
    LISTAR_REVISIONES_CLIENTE(35, "Listar Revisiones por Cliente"),
    LISTAR_REVISIONES_VEHICULO(36, "Listar Revisiones por Vehículo"),
    ANADIR_HORAS_REVISION(37, "Añadir Horas a Revisión"),
    ANADIR_PRECIO_MATERIAL_REVISION(38, "Añadir Precio de Material a Revisión"),
    CERRAR_REVISION(39, "Cerrar Revisión"),
    SALIR(0, "Salir");

    private final int numeroOpcion;
    private final String mensaje;
    private static final Map<Integer, Opcion> opciones = new HashMap<>();

    static {
        for (Opcion opcion : values()) {
            opciones.put(opcion.numeroOpcion, opcion);
        }
    }

    private Opcion(int numeroOpcion, String mensaje) {
        this.numeroOpcion = numeroOpcion;
        this.mensaje = mensaje;
    }

    public static boolean esValida(int numeroOpcion) {
        return opciones.containsKey(numeroOpcion);
    }

    public static Opcion get(int numeroOpcion) {
        if (!esValida(numeroOpcion)) {
            throw new IllegalArgumentException("Opción no válida: " + numeroOpcion);
        }
        return opciones.get(numeroOpcion);
    }

    @Override
    public String toString() {
        return String.format("%d.- %s%n ",numeroOpcion,mensaje);
    }
}

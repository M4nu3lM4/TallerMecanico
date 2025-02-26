package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.Map;
import java.util.TreeMap;

public enum Opcion {
    INSERTAR_CLIENTE(0, "Insertar Cliente"),
    BUSCAR_CLIENTE(1, "Buscar Cliente"),
    BORRAR_CLIENTE(2, "Borrar Cliente"),
    LISTAR_CLIENTES(3, "Listar Clientes"),
    MODIFICAR_CLIENTE(4, "Modificar Cliente"),
    INSERTAR_VEHICULO(5, "Insertar Vehículo"),
    BUSCAR_VEHICULO(6, "Buscar Vehículo"),
    BORRAR_VEHICULO(7, "Borrar Vehículo"),
    LISTAR_VEHICULOS(8, "Listar Vehículos"),
    INSERTAR_REVISION(9, "Insertar Revisión"),
    BUSCAR_REVISION(10, "Buscar Revisión"),
    BORRAR_REVISION(11, "Borrar Revisión"),
    LISTAR_REVISIONES(12, "Listar Revisiones"),
    LISTAR_REVISIONES_CLIENTE(13, "Listar Revisiones por Cliente"),
    LISTAR_REVISIONES_VEHICULO(14, "Listar Revisiones por Vehículo"),
    ANADIR_HORAS_REVISION(15, "Añadir Horas a Revisión"),
    ANADIR_PRECIO_MATERIAL_REVISION(16, "Añadir Precio de Material a Revisión"),
    CERRAR_REVISION(17, "Cerrar Revisión"),
    SALIR(18, "Salir");

    private final int numeroOpcion;
    private final String mensaje;
    private static final Map<Integer, Opcion> opciones = new TreeMap<>();

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
        return numeroOpcion + ". " + mensaje;
    }
}

package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class Vehiculos {
    /**1º CREAMOS LA LISTA DE coleccionVehiculos**/
    private final List<Vehiculo> coleccionVehiculos;

    /**2º CREAMOS EL CONSTRUCTOR PARA INCIALIZAR LA LISTA**/
    public Vehiculos() {
        coleccionVehiculos = new ArrayList<>();
    }
    /**3º CREAMOS EL CONSTRUCTOR PARA OBTENER LA LISTA**/
    public List<Vehiculo> get() {
        return new ArrayList<>(coleccionVehiculos);
    }
    /**4º CREAMOS EL METODO INSERTAR PARA AÑADIR UN VEHICULO A LA LISTA DE VEHICULOS**/
    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        if (vehiculo == null) {
            throw new NullPointerException("No se puede insertar un vehículo nulo.");
        }
        if (coleccionVehiculos.contains(vehiculo)) {
            throw new TallerMecanicoExcepcion("Ya existe un vehículo con esa matrícula.");
        }
        coleccionVehiculos.add(vehiculo);
    }

    /**5º CREAMOS EL METODO BUSCAR PARA COMPROBAR QUE UN VEHICULO EXISTA EN LA LISTA**/
    public Vehiculo buscar(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new NullPointerException("No se puede buscar un vehículo nulo.");
        }
        for(Vehiculo v: coleccionVehiculos){
            if(v.equals(vehiculo)){
                return v;
            }
        }
        return null;
    }

    /**6º CREAMOS EL METODO BORRAR PARA ELIMINAR UN VEHICULO DE LA LISTA**/
    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        if (vehiculo == null) {
            throw new NullPointerException("No se puede borrar un vehículo nulo.");
        }
        Vehiculo vehiculoExistente = buscar(vehiculo);
        if (vehiculoExistente == null) {
            throw new TallerMecanicoExcepcion("No existe ningún vehículo con esa matrícula.");
        }
        coleccionVehiculos.remove(vehiculoExistente);
    }
}

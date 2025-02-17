package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public class Cliente {
    /**1º DECLARAMOS LAS EXPRESIONES REGULARES Y LOS ATRIBUTOS**/
    private static final String ER_NOMBRE = "^[A-Z][a-zñáéíóú]+( [A-Z][a-zñáéíóú]+)*$";
    private static final String ER_DNI = "^[0-9]{8}[A-Z]$";
    private static final String ER_TELEFONO = "\\d{9}";

    private String nombre;
    private String dni;
    private String telefono;

    /**2º CREAMOS EL CONSTRUCTOR POR DEFECTO**/
    public Cliente(String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }
    /**3º CREAMOS EL CONSTRUCTOR COPIA**/
    public Cliente(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("No es posible copiar un cliente nulo.");
        }
        nombre = (cliente.nombre);
        dni = (cliente.dni);
        telefono = (cliente.telefono);
    }

    /**4º CREAMOS EL METODO GET NOMBRE PARA OBTENER UN NOMBRE**/
    public String getNombre() {
        return nombre;
    }
    /**5º CREAMOS EL METODO SET NOMBRE QUE ASIGNA UN NOMBRE DEPENDIENDO DE SI ES NULO O NO TIENE UN FORMATO VÁLIDO**/
    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre,"El nombre no puede ser nulo.");
        if (!nombre.matches(ER_NOMBRE)) {
            throw new IllegalArgumentException("El nombre no tiene un formato válido.");
        }
        this.nombre = nombre;
    }
    /**6º CREAMOS EL METODO GET DNI PARA ONTENER UN DNI**/
    public String getDni() {
        return dni;
    }
    /**7º CREAMOS EL METODO SET DNI QUE ASIGNA UN DNI DEPENDIENDO DE SI ES NULO O NO TIENE UN FORMATO VÁLIDO Y QUE LA LETRA DEL DNI ES CORRECTA**/
    private void setDni(String dni) {
        Objects.requireNonNull(dni, "El DNI no puede ser nulo.");
        if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("La letra del DNI no es correcta.");
        }
        this.dni = dni;
    }
    /**8º CREAMOS EL METODO GET TELEFONO PARA OBTENER UN TELEFONO**/
    public String getTelefono() {
        return telefono;
    }
    /**9º CREAMOS EL METODO SET TELEFONO QUE ASIGNARA UN TELEFONO DEPENDIENDO DE SI EL TELEFONO ES NULO O TIENE UN FORMATO NO VÁLIDO**/
    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono,"El teléfono no puede ser nulo.");

        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    private static boolean comprobarLetraDni(String dni) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = Integer.parseInt(dni.substring(0, 8));
        char letraCalculada = letras.charAt(numero % 23);
        return dni.charAt(8) == letraCalculada;
    }

    public static Cliente get(String dni) {
        return new Cliente("José", dni, "999999999");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", nombre, dni, telefono);
    }
}
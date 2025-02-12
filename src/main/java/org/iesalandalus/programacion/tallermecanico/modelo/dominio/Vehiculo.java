package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public record Vehiculo(String marca, String modelo, String matricula) {
    private static String ER_MARCA = "^(Seat|Land Rover|KIA|Rolls-Royce|SsangYong)$";
    private static String ER_MATRICULA = "^[0-9]{4}[A-Z]{3}$";


    public Vehiculo{
        validarMarca(marca);
        validarMatricula(matricula);
        validarModelo(modelo);
    }

    private void validarMarca(String marca) {
        Objects.requireNonNull(marca,"La marca no puede ser nula.");

        if (!marca.matches(ER_MARCA)) {
            throw new IllegalArgumentException("La marca no tiene un formato válido.");
        }

    }

    private void validarMatricula(String matricula) {
        Objects.requireNonNull(matricula,"La matricula no puede ser nula.");


        if (!matricula.matches(ER_MATRICULA)) {
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
    }

    private void validarModelo(String modelo) {
        Objects.requireNonNull(modelo,"El modelo no puede ser nulo.");

        if (!modelo.matches(ER_MATRICULA)) {
            throw new IllegalArgumentException("El modelo no tiene un formato válido.");
        }

    }

    public static Vehiculo get(String matricula) {

        return new Vehiculo("Desconocido","Desconocido", matricula);
    }

    @Override
    public final boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        Vehiculo vehiculo = (Vehiculo) obj;
        return this.matricula.equals(vehiculo.matricula);
    }

    @Override
    public final int hashCode() {
        return matricula.hashCode();
    }


    @Override
    public String toString() {
        return "Marca: " + marca + ", Modelo: " + modelo + ", Matricula: " + matricula;
    }
}
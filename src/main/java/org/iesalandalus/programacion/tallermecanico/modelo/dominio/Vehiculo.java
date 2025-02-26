package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public record Vehiculo(String marca, String modelo, String matricula) {

    private static final String ER_MARCA = "^(?!^\\s+$)([A-Z][A-Za-z]*|[A-Z][a-z]*(?:[-\\s]?[a-z]+)?(?:[-\\s][a-zA-Z][a-z]*)*|[A-Z]+)$";

    /**(?!^\s+$): Negative lookahead que asegura que la cadena no consista únicamente de espacios en blanco.
([A-Z][A-Za-z]*: Coincide con una palabra que comienza con una letra mayúscula seguida opcionalmente por cero o más letras mayúsculas o minúsculas.
|: Operador OR que permite que la expresión coincida con una de las opciones.
[A-Z][a-z]*(?:[-\s]?[a-z]+)?: Coincide con una palabra que comienza con una letra mayúscula seguida de cero o más letras minúsculas, seguida opcionalmente por un guion o un espacio en blanco y una o más letras minúsculas.
(?:[-\s][a-zA-Z][a-z]*)*: Grupo de no captura que coincide con cero o más grupos que consisten en un guion o un espacio en blanco seguido de una letra (mayúscula o minúscula) y cero o más letras minúsculas.
[A-Z]+): Coincide con una palabra que consiste en una o más letras mayúsculas.
     **/
    private static final String ER_MATRICULA = "^(?![AEIOQU])[0-9]{4}(?![AEIOQUÑ])(?!CH|LL)[BCDFGHJKLMNPRSTVWXYZ]{3}$";
    /**
     (?![AEIOQU]): Este es un "negative lookahead" que asegura que el primer carácter de las letras no sea una de las letras prohibidas (A, E, I, O, Q, U).
[0-9]{4}: Representa exactamente cuatro dígitos del 0 al 9.
(?![AEIOQUÑ]): Otro "negative lookahead" que asegura que ninguna de las siguientes letras sea una de las letras prohibidas (A, E, I, O, Q, U, Ñ).
(?!CH|LL): Este es un "negative lookahead" que asegura que no haya una de las combinaciones prohibidas (CH o LL) en las siguientes letras.
[BCDFGHJKLMNPRSTVWXYZ]{3}: Representa exactamente tres letras del alfabeto español, excluyendo las letras prohibidas y las combinaciones prohibidas.
     */

    public Vehiculo {
        validarMarca(marca);
        validarModelo(modelo);
        validarMatricula(matricula);
    }

    private void validarMarca(String marca) {
        Objects.requireNonNull(marca, "La marca no puede ser nula.");
        if(!marca.matches(ER_MARCA)) {
            throw new IllegalArgumentException("La marca no tiene un formato válido.");
        }
    }

    private void  validarModelo(String modelo) {
        Objects.requireNonNull(modelo, "El modelo no puede ser nulo.");
        if(modelo.trim().isBlank()) throw new IllegalArgumentException("El modelo no puede estar en blanco.");
    }

    private void validarMatricula(String matricula) {
        Objects.requireNonNull(matricula, "La matrícula no puede ser nula.");
        if(!matricula.matches(ER_MATRICULA)) {
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
    }

    public static Vehiculo get(String matricula){
        Objects.requireNonNull(matricula, "La matrícula no puede ser nula.");
        if(!matricula.matches(ER_MATRICULA)) {
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
        return new Vehiculo("Renault", "Megane", matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vehiculo other = (Vehiculo) obj;
        return Objects.equals(matricula, other.matricula);
    }

    public String toString(){
        return String.format("%s %s - %s", this.marca, this.modelo, this.matricula);
    }
}
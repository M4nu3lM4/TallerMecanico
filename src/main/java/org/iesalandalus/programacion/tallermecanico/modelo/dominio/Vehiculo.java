package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

public record Vehiculo(String marca, String modelo, String matricula) {
    private static String ER_MARCA = "^[A-Z][a-z]+( [A-Z][a-z]+)$";
    private static String ER_MATRICULA = "^[0-9]{4}[A-Z]{3}$";


    public Vehiculo{
        validarMarca(marca);
        validarMatricula(matricula);
        validarModelo(modelo);
    }

    private void validarMarca(String marca) {
        if (!marca.matches(ER_MARCA)) {
            throw new IllegalArgumentException("Marca incorrecta.");
        }
    }

    private void validarMatricula(String matricula) {
        if (!matricula.matches(ER_MATRICULA)) {
            throw new IllegalArgumentException("Matricula incorrecta.");
        }
    }

    private void validarModelo(String modelo) {
        if (!modelo.matches(ER_MATRICULA)) {
            throw new IllegalArgumentException("Modelo incorrecto.");
        }
    }

    public Vehiculo get(String matricula) {
        return new Vehiculo(this.marca,this.matricula,this.modelo);
    }

    @Override
    public final boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        Vehiculo vehiculo = (Vehiculo) obj;
        return this.matricula.equals(vehiculo.matricula);
    }







}
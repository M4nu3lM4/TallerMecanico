package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

public enum FabricaFuenteDatos {

    MEMORIA {
        @Override
        public IFuenteDatos crear() {
            return null;
        }
    };



    public abstract IFuenteDatos crear();

}

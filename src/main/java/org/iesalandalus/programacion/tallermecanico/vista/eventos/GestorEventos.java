package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.*;

public class GestorEventos {
    private Map<Evento, List<ReceptorEventos>> receptores = new EnumMap<>(Evento.class);


    public GestorEventos(Evento... eventos){
        Objects.requireNonNull(eventos,"Se debe gestionar algún evento.");

        for (Evento evento : eventos){
            receptores.put(evento,new ArrayList<>());
        }
    }

    public void suscribir(ReceptorEventos receptor, Evento... eventos){
        Objects.requireNonNull(receptor,"El receptor de eventos no puede ser nulo.");
        Objects.requireNonNull(eventos,"Te debes suscribir a algún evento.");

        for (Evento evento : eventos){
            List<ReceptorEventos> subscriptores = receptores.get(evento);
            subscriptores.add(receptor);
        }

    }

    public void desuscribir(ReceptorEventos receptor, Evento... eventos){
        Objects.requireNonNull(receptor,"El receptor de eventos no puede ser nulo.");
        Objects.requireNonNull(eventos,"Te debes suscribir a algún evento.");

        for (Evento evento : eventos){
            List<ReceptorEventos> subscriptores = receptores.remove(evento);
            subscriptores.remove(receptor);
        }
    }

    public void notificar(Evento evento){
        Objects.requireNonNull(evento,"El evento no puede ser nulo.");


        List<ReceptorEventos> subscriptores = receptores.get(evento);
        for (ReceptorEventos receptor : subscriptores){
            receptor.actualizar(evento);
        }
    }
}

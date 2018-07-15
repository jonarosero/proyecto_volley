package Clases;

import java.util.ArrayList;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */

public class Inscripcion {

    private String observacion;
    private String fecha;
    public ArrayList<Campeonato> lstCampeonatos;
    public ArrayList<Equipo> lstEquipos;
    public ArrayList<Jugador> lstJugadores;

    // Constructores
    public Inscripcion() {
    }

    public Inscripcion(String observacion, String fecha, ArrayList<Campeonato> lstCampeonatos,
            ArrayList<Equipo> lstEquipos, ArrayList<Jugador> lstJugadores) {
        this.observacion = observacion;
        this.fecha = fecha;
        this.lstCampeonatos = lstCampeonatos;
        this.lstEquipos = lstEquipos;
        this.lstJugadores = lstJugadores;
    }

    
    // Metodos set y get
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Campeonato> getLstCampeonatos() {
        return lstCampeonatos;
    }

    public void setLstCampeonatos(ArrayList<Campeonato> lstCampeonatos) {
        this.lstCampeonatos = lstCampeonatos;
    }

    public ArrayList<Equipo> getLstEquipos() {
        return lstEquipos;
    }

    public void setLstEquipos(ArrayList<Equipo> lstEquipos) {
        this.lstEquipos = lstEquipos;
    }

    public ArrayList<Jugador> getLstJugadores() {
        return lstJugadores;
    }

    public void setLstJugadores(ArrayList<Jugador> lstJugadores) {
        this.lstJugadores = lstJugadores;
    }

    @Override
    public String toString() {
        return "Inscripcion{" + "observacion=" + observacion + ", fecha=" + fecha
                + ", lstCampeonatos=" + lstCampeonatos + ", lstEquipos=" + lstEquipos
                + ", lstJugadores=" + lstJugadores + '}';
    }

}

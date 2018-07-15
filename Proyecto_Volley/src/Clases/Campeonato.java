package Clases;

import java.util.ArrayList;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */

public class Campeonato {

    //Clase donde se definen los campeonatos con sus atributos
    private int idCampeonato;
    private String nombre;
    private String representante;
    private String Anio;
    public ArrayList<Equipo> lstEquipos;

    
    // Constructores
    public Campeonato() {
    }

    public Campeonato(int idCampeonato, String nombre, String representante, String Anio) {
        this.idCampeonato = idCampeonato;
        this.nombre = nombre;
        this.representante = representante;
        this.Anio = Anio;
    }
   
    public Campeonato(int idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public Campeonato(String nombre) {
        this.nombre = nombre;
    }

    public Campeonato(String nombre, String representante, String Anio) {
        this.nombre = nombre;
        this.representante = representante;
        this.Anio = Anio;
    }

    public Campeonato(int idCampeonato, String nombre, String representante, String Anio, ArrayList<Equipo> lstEquipos) {
        this.idCampeonato = idCampeonato;
        this.nombre = nombre;
        this.representante = representante;
        this.Anio = Anio;
        this.lstEquipos = lstEquipos;
    }

    // Metodos get and set
    public int getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(int idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getAnio() {
        return Anio;
    }

    public void setAnio(String Anio) {
        this.Anio = Anio;
    }

    public ArrayList<Equipo> getLstEquipos() {
        return lstEquipos;
    }

    public void setLstEquipos(ArrayList<Equipo> lstEquipos) {
        this.lstEquipos = lstEquipos;
    }

    @Override
    public String toString() {
        return "Campeonato{" + "idCampeonato=" + idCampeonato + ", nombre="
                + nombre + ", representante=" + representante + ", Anio=" + Anio
                + ", lstEquipos=" + lstEquipos + '}';
    }

}

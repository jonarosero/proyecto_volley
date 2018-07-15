package Clases;

import java.util.ArrayList;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */

public class Jugador extends Persona {

    /* Clase Jugador
    Hereda los atributos de Persona y va a almacenar los jugadores registrados
     */
    private int idJugador;
    private Double Estatura;
    private Double Peso;
    private String TipoSangre;
    private String Posicion;
    private int NumDorsal;
    public Colegio objColegio;
    private int Estado;


//Constructores
    public Jugador(Double Estatura, Double Peso, String TipoSangre, String Posicion, int NumDorsal, int Estado) {
        this.Estatura = Estatura;
        this.Peso = Peso;
        this.TipoSangre = TipoSangre;
        this.Posicion = Posicion;
        this.NumDorsal = NumDorsal;
        this.Estado= Estado;

    }

    public Jugador() {
    }

    public Jugador(String Nombres, String Apellidos, String Cedula, int Edad, String Genero, Colegio objColegio) {
        super(Nombres, Apellidos, Cedula, Edad, Genero);
        this.objColegio = objColegio;
    }

    public Jugador(String Nombres, String Apellidos, String Cedula, int Edad) {
        super(Nombres, Apellidos, Cedula, Edad);
    }
     public Jugador( String Nombres, String Apellidos, String Cedula, int Edad,int Estado) {
        super(Nombres, Apellidos, Cedula, Edad);
        this.Estado = Estado;
    }

    // Metodos get y Set
    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public Double getEstatura() {
        return Estatura;
    }

    public void setEstatura(Double Estatura) {
        this.Estatura = Estatura;
    }

    public Double getPeso() {
        return Peso;
    }

    public void setPeso(Double Peso) {
        this.Peso = Peso;
    }

    public String getTipoSangre() {
        return TipoSangre;
    }

    public void setTipoSangre(String TipoSangre) {
        this.TipoSangre = TipoSangre;
    }

    public String getPosicion() {
        return Posicion;
    }

    public void setPosicion(String Posicion) {
        this.Posicion = Posicion;
    }

    public int getNumDorsal() {
        return NumDorsal;
    }

    public void setNumDorsal(int NumDorsal) {
        this.NumDorsal = NumDorsal;
    }

    public Colegio getObjColegio() {
        return objColegio;
    }

    public void setObjColegio(Colegio objColegio) {
        this.objColegio = objColegio;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
    
    
    @Override
    public String toString() {
        return super.getCedula() + " " + super.getNombres() + " " + super.getApellidos() + " " + super.getEdad();
    }

}

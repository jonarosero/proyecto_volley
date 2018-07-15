package Clases;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */

public class Persona {

    //Clase Padre que heredará a las clases Jugador y Arbitro
    private int idPersona;
    private String Nombres;
    private String Apellidos;
    private String Cedula;
    private int Edad;
    private String Genero;

    //Constructores
    public Persona() {
    }

    public Persona(String Nombres, String Apellidos, String Cedula, int Edad, String Genero) {
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.Cedula = Cedula;
        this.Edad = Edad;
        this.Genero = Genero;
    }

    public Persona(String Nombres, String Apellidos, String Cedula, int Edad) {
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.Cedula = Cedula;
        this.Edad = Edad;
    }

    public Persona(String Nombres, String Apellidos) {
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
    }

    //Métodos get y set
    public Persona(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public String toString() {
        return "Persona{" + "Nombres=" + Nombres + ", Apellidos=" + Apellidos + ", Cedula=" + Cedula + ", Edad=" + Edad + ", Genero=" + Genero + '}';
    }

}

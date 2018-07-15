package Clases;


/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */

// Clase partido
public class Partido {

    private int idPar;
    private int Categoria;
    private String fecha;
    private int Hora;
    private int NumCancha;
    public Campeonato objCamp;

    // Constructores
    public Partido(int Categoria, String fecha, int Hora, int NumCancha, Campeonato objCamp) {
        this.Categoria = Categoria;
        this.fecha = fecha;
        this.Hora = Hora;
        this.NumCancha = NumCancha;
        this.objCamp = objCamp;
    }

    // Metodos set y get
    public int getIdPar() {
        return idPar;
    }

    public void setIdPar(int idPar) {
        this.idPar = idPar;
    }

    public Campeonato getObjCamp() {
        return objCamp;
    }

    public void setObjCamp(Campeonato objCamp) {
        this.objCamp = objCamp;
    }
    
    public Partido(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNumPart() {
        return idPar;
    }

    public void setNumPart(int NumPart) {
        this.idPar = NumPart;
    }

    public int getCategoria() {
        return Categoria;
    }

    public void setCategoria(int Categoria) {
        this.Categoria = Categoria;
    }


    public int getHora() {
        return Hora;
    }

    public void setHora(int Hora) {
        this.Hora = Hora;
    }

    public int getNumCancha() {
        return NumCancha;
    }

    public void setNumCancha(int NumCancha) {
        this.NumCancha = NumCancha;
    }


    @Override
    public String toString() {
        return "Partido{" + "idPar=" + idPar + ", Categoria=" + Categoria + ", fecha=" + fecha + ", Hora=" + Hora + ", NumCancha=" + NumCancha + ", objCamp=" + objCamp + '}';
    }

}

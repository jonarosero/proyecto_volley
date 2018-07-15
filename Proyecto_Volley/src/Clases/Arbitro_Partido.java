package Clases;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */

public class Arbitro_Partido {
    
    //Clase que relaciona los partidos que pita un/varios arbitro
    private String observacion;
    public Arbitro objArbitro;
    public Partido objPartido;

    // Constructores
    public Arbitro_Partido() {

    }

    public Arbitro_Partido(String observacion, Arbitro objArbitro, Partido objPartido) {
        this.observacion = observacion;
        this.objArbitro = objArbitro;
        this.objPartido = objPartido;
    }

    public Arbitro_Partido(String observacion) {
        this.observacion = observacion;
    }

    // Metodos set and get
    
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Arbitro getObjArbitro() {
        return objArbitro;
    }

    public void setObjArbitro(Arbitro objArbitro) {
        this.objArbitro = objArbitro;
    }

    public Partido getObjPartido() {
        return objPartido;
    }

    public void setObjPartido(Partido objPartido) {
        this.objPartido = objPartido;
    }

    // Metodo toString de la Clase 
    @Override
    public String toString() {
        return "Arbitro_Partido{" + "observacion=" + observacion + ", objArbitro=" + objArbitro + ", objPartido=" + objPartido + '}';
    }

}

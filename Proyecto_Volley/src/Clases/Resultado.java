package Clases;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando
 * León
 */
// Clase Resultado donde va a alamacenar los resultados registrados
public class Resultado {

    private String Fecha;
    private int PuntosEqp;
    private String Equipo1;
    private String Equipo2;
    private String Equipos;
    public Partido objPartido;

    public Resultado() {

    }

    public Resultado(String Fecha, int PuntosEqp, String Equipos) {
        this.Fecha = Fecha;
        this.PuntosEqp = PuntosEqp;
        this.Equipos = Equipos;
    }

    public Resultado(String Fecha, int PuntosEqp, String Equipo1, String Equipo2) {
        this.Fecha = Fecha;
        this.PuntosEqp = PuntosEqp;
        this.Equipo1 = Equipo1;
        this.Equipo2 = Equipo2;
    }

    public Resultado(String Fecha, int PuntosEqp, String Equipo1, String Equipo2, String Equipos, Partido objPartido) {
        this.Fecha = Fecha;
        this.PuntosEqp = PuntosEqp;
        this.Equipo1 = Equipo1;
        this.Equipo2 = Equipo2;
        this.Equipos = Equipos;
        this.objPartido = new Partido(objPartido.getCategoria(), objPartido.getFecha(),
                objPartido.getHora(), objPartido.getNumCancha(), objPartido.getObjCamp());
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public int getPuntosEqp() {
        return PuntosEqp;
    }

    public void setPuntosEqp(int PuntosEqp) {
        this.PuntosEqp = PuntosEqp;
    }

    public String getEquipo1() {
        return Equipo1;
    }

    public void setEquipo1(String Equipo1) {
        this.Equipo1 = Equipo1;
    }

    public String getEquipo2() {
        return Equipo2;
    }

    public void setEquipo2(String Equipo2) {
        this.Equipo2 = Equipo2;
    }

    public String getEquipos() {
        return Equipos;
    }

    public void setEquipos(String Equipos) {
        this.Equipos = Equipos;
    }

    public Partido getObjPartido() {
        return objPartido;
    }

    public void setObjPartido(Partido objPartido) {
        this.objPartido = objPartido;
    }

    @Override
    public String toString() {
        return "Resultado{" + "Fecha=" + Fecha + ", PuntosEqp=" + PuntosEqp + ", Equipo1=" + Equipo1 + ", Equipo2=" + Equipo2 + ", Equipos=" + Equipos + ", objPartido=" + objPartido + '}';
    }

}

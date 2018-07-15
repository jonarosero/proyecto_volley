package Clases;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */

public class Equipo {

    /* Clase Equipos
    Almacena los equipos registrados
     */
    private String NombreEq;
    private String NombreEq1;
    private String NombreEq2;
    private int Categoria;

    /*
    0 - inferior femenino
    1 - media femenino
    2 - superior femenino
    3 - inferior masculina
    4 - media masculina
    5 - superior masculina
     */
    public Colegio objColegio;

    public Equipo(String NombreEq, int Categoria) {
        this.NombreEq = NombreEq;
        this.Categoria = Categoria;
    }

    public Equipo(String NombreEq) {
        this.NombreEq = NombreEq;
    }

    public Equipo(String NombreEq1, String NombreEq2) {
        this.NombreEq1 = NombreEq1;
        this.NombreEq2 = NombreEq2;
    }

    public Equipo(String NombreEq, int Categoria, Colegio objColegio) {
        this.NombreEq = NombreEq;
        this.Categoria = Categoria;
        this.objColegio = objColegio;
    }

    // Métodos Get y Set
    public String getNombreEq() {
        return NombreEq;
    }

    public void setNombreEq(String NombreEq) {
        this.NombreEq = NombreEq;
    }

    public int getCategoria() {
        return Categoria;
    }

    public void setCategoria(int Categoria) {
        this.Categoria = Categoria;
    }

    public Colegio getObjColegio() {
        return objColegio;
    }

    public void setObjColegio(Colegio objColegio) {
        this.objColegio = objColegio;
    }

    public String getNombreEq1() {
        return NombreEq1;
    }

    public void setNombreEq1(String NombreEq1) {
        this.NombreEq1 = NombreEq1;
    }

    public String getNombreEq2() {
        return NombreEq2;
    }

    public void setNombreEq2(String NombreEq2) {
        this.NombreEq2 = NombreEq2;
    }

    @Override
    public String toString() {
        return "Equipo{" + "NombreEq=" + NombreEq + ", Categoria=" + Categoria
                + ", objColegio=" + objColegio + '}';
    }

}

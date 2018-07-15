package Clases;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */

public class Arbitro extends Persona {

    /*Clase Arbitro
    Hereda los atributos de Persona y va a almacenar los árbitros registrados
     */
    private int idArbitro;
    private String TipoArb;
    /**
     * Tipo Arbitro: 1 ----- Principal 0 ----- Secundario
     */
    private String Confederacion;

    public Arbitro() {
    }

    public Arbitro(String Nombres, String Apellidos) {
        super(Nombres, Apellidos);
    }
    
    
    public Arbitro(String Nombres, String Apellidos, String Cedula, int Edad, String Genero,String TipoArb, String Confederacion) {
        super(Nombres, Apellidos, Cedula, Edad, Genero);
        this.TipoArb = TipoArb;
        this.Confederacion = Confederacion;
    }

    
    public Arbitro(String Cedula) {
        super(Cedula);
    }

    //Métodos get y set
    public int getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }

    public String getTipoArb() {
        return TipoArb;
    }

    public void setTipoArb(String TipoArb) {
        this.TipoArb = TipoArb;
    }

    public String getConfederacion() {
        return Confederacion;
    }

    public void setConfederacion(String Confederacion) {
        this.Confederacion = Confederacion;
    }

    @Override
    public String toString() {
        return super.toString() + "\nArbitro{" + "TipoArb=" + TipoArb + ", Confederacion=" + Confederacion + '}';
    }
    
}

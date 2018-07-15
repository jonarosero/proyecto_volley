package Clases;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */

public class Usuario {

    private String Usuario;
    private String Clave;
    private String Tipo;
    public Arbitro objArbitro;

    public Usuario() {

    }    

    public Usuario(String Usuario, String Clave, String Tipo) {
        this.Usuario = Usuario;
        this.Clave = Clave;
        this.Tipo = Tipo;
    }

    public Usuario(String Usuario, String Clave) {
        this.Usuario = Usuario;
        this.Clave = Clave;
    }
    
    public Usuario(String Usuario, String Clave, Arbitro arbitro) {
        this.Usuario = Usuario;
        this.Clave = Clave;
        this.objArbitro = new Arbitro(arbitro.getNombres(), arbitro.getApellidos(),
                arbitro.getCedula(), arbitro.getEdad(), arbitro.getGenero(),
                arbitro.getTipoArb(), arbitro.getConfederacion());//Composicion
    }

    //Metodos set y get 
    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        this.Clave = Clave;
    }

    public Arbitro getObjArbitro() {
        return objArbitro;
    }

    public void setObjArbitro(Arbitro objArbitro) {
        this.objArbitro = objArbitro;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "Usuario=" + Usuario + ", Clave=" + Clave + ", Tipo=" + Tipo + ", objArbitro=" + objArbitro + '}';
    }
}

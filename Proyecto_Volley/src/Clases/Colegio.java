package Clases;
 
/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */

public class Colegio {
 
    private int idColegio; 
    private String NombreCole;
    private String NombreRep;

    public Colegio() {
    }
    
    //Constructor que permite actualizar el representante
    public Colegio(String NombreCole, String NombreRep) {  
        this.NombreCole = NombreCole;
        this.NombreRep = NombreRep;
    }
    
    //Constructor que permite listar los colegios
    public Colegio(int idColegio, String NombreCole, String NombreRep) {
        this.idColegio = idColegio;
        this.NombreCole = NombreCole;
        this.NombreRep = NombreRep;
    }

    public Colegio(String NombreCole) {
        this.NombreCole = NombreCole;
    }
    
    public int getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(int idColegio) {
        this.idColegio = idColegio;
    }

    public String getNombreCole() {
        return NombreCole;
    }

    public void setNombreCole(String NombreCole) {
        this.NombreCole = NombreCole;
    }

    public String getNombreRep() {
        return NombreRep;
    }

    public void setNombreRep(String NombreRep) {
        this.NombreRep = NombreRep;
    }

    @Override
    public String toString() {
        return "Colegio{" + "idColegio=" + idColegio + ", NombreCole=" + NombreCole
                + ", NombreRep=" + NombreRep + '}';
    }
    
}
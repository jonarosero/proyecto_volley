package Data;

import Clases.Campeonato;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */

public class Dat_Campeonato {

    Dat_ConexionBD con = new Dat_ConexionBD();

    //Metodo para consultar campeonato
    public ResultSet ConsultarCampeonato() throws ClassNotFoundException, SQLException {
        PreparedStatement pst = con.AbrirConexion().prepareStatement("SELECT * FROM campeonato");
        ResultSet rs = pst.executeQuery();
        return rs;
    }

    // Metodo para Insertar campeonato
    public int Insertar(String nombre, String repre, int anio) throws ClassNotFoundException, SQLException {
        Statement st = con.AbrirConexion().createStatement();
        String Sentencia = "INSERT INTO campeonato_volley.campeonato VALUES (0,'"
                + nombre + "','" + repre + "'," + anio + ")";
        int rs = st.executeUpdate(Sentencia);
        return rs;
    }

    // Metodo para Actualizar campeonato
    public int Actualizar(Campeonato objCam, String nombre) throws ClassNotFoundException, SQLException {
        Statement st = con.AbrirConexion().createStatement();
        String Sentencia = "UPDATE campeonato_volley.campeonato \n"
                + "SET Nombre='" + objCam.getNombre() + "',\n"
                + "Representante='" + objCam.getRepresentante() + "',\n"
                + "Anio= '" + objCam.getAnio() + "'\n"
                + "WHERE idCampeonato = (SELECT idCampeonato FROM (SELECT idCampeonato FROM Campeonato WHERE \n"
                + "Nombre = '" + nombre + "') AS id)";
        int rs = st.executeUpdate(Sentencia);
        return rs;
    }

    //Metodo para consultar Id de campeonato
    public ResultSet ConsultarIdCampeonato(String nomb,String Rep, int anio) throws ClassNotFoundException, SQLException {
        Statement pst = con.AbrirConexion().createStatement();
        String Sentencia="SELECT idCampeonato FROM campeonato \n"
                + "WHERE Nombre = '"+nomb+"'\n"
                + "AND Representante='"+Rep+"'\n"
                + "AND Anio = "+anio;
        ResultSet rs = pst.executeQuery(Sentencia);
        return rs;
    }

}

package Data;

import Clases.Equipo;
import Clases.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */
public class Dat_Equipos {

    Dat_ConexionBD con = new Dat_ConexionBD();

    public ResultSet ListarEquipos(String genero, String colegio) throws ClassNotFoundException, SQLException {
        Statement st = con.AbrirConexion().createStatement();
        String sentencia = "SELECT p.Cedula,p.Nombres,p.Apellidos,p.Edad, j.Estado  FROM persona p,jugador j, equipo e,colegio c\n"
                + "WHERE p.Genero = '"+genero+"'\n"
                + "AND j.idPersona = p.idPersona\n"
                + "AND j.idColegio = c.idColegio\n"
                + "AND c.idColegio = e.idEquipo\n"
                + "AND c.NombreColegio = '"+colegio+"'";
        ResultSet rs = st.executeQuery(sentencia);
        PreparedStatement pst = con.AbrirConexion().prepareStatement(sentencia);
        return rs;
    }

    public int InsertarEquipo(String nombreEquipo, int cate, String nombreColegio) throws ClassNotFoundException, SQLException {
        Statement st = con.AbrirConexion().createStatement();
        String categoria = "";
        if (cate == 0 || cate == 3) {
            categoria = "INFERIOR";
        } else if (cate == 1 || cate == 4) {
            categoria = "MEDIA";
        } else if (cate == 2 || cate == 5) {
            categoria = "SUPERIOR";
        }
        String Sentencia = "INSERT INTO equipo(idEquipo,NombreEquipo,Categoria,idColegio)SELECT\n"
                + "0 as idEquipo,\n"
                + "'" + nombreEquipo + "' as NombreEquipo,\n"
                + "'" + categoria + "' as Categoria,\n"
                + "c.idColegio\n"
                + "FROM colegio c\n"
                + "WHERE c.NombreColegio='" + nombreColegio + "'";
        int rs = st.executeUpdate(Sentencia);
        return rs;
    } 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando
 * León
 */
public class Dat_HorarioPart {
    Dat_ConexionBD conect = new Dat_ConexionBD();
    // Metodo para listar equipos por categoria
    public ResultSet ListarEquipoCat(int cate) throws ClassNotFoundException, SQLException {
        Statement st = conect.AbrirConexion().createStatement();
        String genero = "";
        String categoria = "";
        if (cate == 0 || cate == 3) {
            categoria = "INFERIOR";
        } else if (cate == 1 || cate == 4) {
            categoria = "MEDIA";
        } else if (cate == 2 || cate == 5) {
            categoria = "SUPERIOR";
        }
        if (cate == 0 || cate == 1 || cate == 2) {
            genero = "FEMENINO";
        } else if (cate == 3 || cate == 4 || cate == 5) {
            genero = "MASCULINO";
        }
        String Sentencia = "SELECT distinct e.NombreEquipo FROM persona p,jugador j, equipo e, inscripcion i\n"
                + "WHERE j.idJugador =  i.idJugador\n"
                + "AND p.Genero='" + genero + "'\n"
                + "AND e.Categoria='" + categoria + "'\n"
                + "AND e.idEquipo = i.idEquipo\n"
                + "AND j.idPersona = p.idPersona;";
        ResultSet rs = st.executeQuery(Sentencia);
        return rs;
    }

    // Metodo para insertar equipos 
    public int InsertarEquipo(String cate, String Fecha, String hora, int cancha, int idCamp) throws ClassNotFoundException, SQLException {
        Statement st = conect.AbrirConexion().createStatement();
        String Sentencia = "INSERT INTO campeonato_volley.partido (idPartido, Categoria, "
                + "Fecha, Hora, NumCancha, idCampeonato) \n"
                + "VALUES (0,'" + cate + "', '" + Fecha + "',\n"
                + " '" + hora + "',\n"
                + "'" + cancha + "', '" + idCamp + "');";
        int rs = st.executeUpdate(Sentencia);
        return rs;
    }

}

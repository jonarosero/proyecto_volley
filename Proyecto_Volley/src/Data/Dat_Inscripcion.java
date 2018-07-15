/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */
public class Dat_Inscripcion {
    Dat_ConexionBD con = new Dat_ConexionBD();
    // Metodo para inscribir jugadores
    public int InscribirJugadores(String fecha, String cedula, String nombreEquipo, int idCam) throws ClassNotFoundException, SQLException {
        Statement st = con.AbrirConexion().createStatement();
        String Sentencia = "INSERT INTO inscripcion(idInscripcion, idEquipo, idJugador, idCampeonato, Fecha)\n"
                + "SELECT\n"
                + "0 as idInscripcion,\n"
                + "e.idEquipo, j.idJugador,\n"
                + "" + idCam + " as idCam,\n"
                + "'" + fecha + "' as fecha\n"
                + "FROM equipo e, jugador j, persona p\n"
                + "WHERE p.Cedula = '" + cedula + "'\n"
                + "AND p.idPersona = j.idPersona \n"
                + "AND e.NombreEquipo='" + nombreEquipo + "'";
        int rs = st.executeUpdate(Sentencia);
        return rs;
    }

}

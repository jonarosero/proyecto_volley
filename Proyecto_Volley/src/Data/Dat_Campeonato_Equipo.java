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
public class Dat_Campeonato_Equipo {
    Dat_ConexionBD con = new Dat_ConexionBD();
    // Metodo para inserta Equipos
    public int InsertarEquipoCamp(String nombreEquipo, int idCam) throws ClassNotFoundException, SQLException {
        Statement st = con.AbrirConexion().createStatement();
        String Sentencia = "INSERT INTO campeonato_equipo(Campeonato_Equipo_id, idCampeonato, idEquipo)\n"
                + "SELECT\n"
                + "0 AS Campeonato_Equipo_id,\n"
                + ""+idCam+" AS idCampeonato,\n"
                + "idEquipo\n"
                + "FROM equipo\n"
                + "WHERE NombreEquipo='"+nombreEquipo+"'";
        int rs = st.executeUpdate(Sentencia);
        return rs;
    }

}

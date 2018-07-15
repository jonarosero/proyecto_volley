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
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */
public class Dat_Partido {
    Dat_ConexionBD con = new Dat_ConexionBD();
    // Metodo para consultar los partidos
    public ResultSet Consultar() throws ClassNotFoundException, SQLException {
        Statement st = con.AbrirConexion().createStatement();
        String Sentencia = "SELECT distinct fecha FROM partido;";
        ResultSet rs = st.executeQuery(Sentencia);
        return rs;
    }
}

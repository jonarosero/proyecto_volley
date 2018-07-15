/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Clases.Resultado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando
 * León
 */
public class Dat_Resultados {

    Dat_ConexionBD con = new Dat_ConexionBD();

    // Metodo para consultar los resultados de los partidos
    public ResultSet Consultar(String fecha) throws ClassNotFoundException, SQLException {
        Statement st = con.AbrirConexion().createStatement();
        String Sentencia = "SELECT eq.NombreEquipo AS Equipo1, eq2.NombreEquipo AS Equipo2\n"
                + "FROM equipos_partidos ep\n"
                + "JOIN partido p ON ep.idPartido = p.idPartido\n"
                + "JOIN equipo eq ON ep.idEquipo1 = eq.idEquipo \n"
                + "JOIN equipo eq2 ON eq2.idEquipo = ep.idEquipo2\n"
                + "WHERE p.Fecha ='" + fecha + "' ";
        ResultSet rs = st.executeQuery(Sentencia);
        return rs;
    }

    // Metodo para insertar los resultados
    public int InsertarResultados(Resultado objResul, String fecha, String nombre, String nombre1, int punt) {
        int retorno = 0;
        int estado = 0;
        String p = "";
        if (punt == 1) {
            p = "eq";
        } else {
            p = "eq2";
        }
        try {
            String strSQL = "INSERT INTO campeonato_volley.resultados (idResultados,Fecha,Puntos,idEquipo, idPartido) \n"
                    + "SELECT\n"
                    + "0 AS idResultados, \n"
                    + "? AS Fecha, \n"
                    + "? AS Puntos, \n"
                    + "" + p + ".idEquipo,p.idPartido\n"
                    + "FROM partido p, equipos_partidos ep\n"
                    + "JOIN equipo eq ON ep.idEquipo1 = eq.idEquipo \n"
                    + "JOIN equipo eq2 ON ep.idEquipo2 = eq2.idEquipo \n"
                    + "WHERE " + p + ".NombreEquipo = ?\n"
                    + "AND p.Fecha = ?\n"
                    + "AND p.idPartido = ep.idPartido\n"
                    + "AND eq.NombreEquipo=?\n"
                    + "AND eq2.NombreEquipo=?;";
            PreparedStatement pst = con.AbrirConexion().prepareStatement(strSQL);
            pst.setString(1, objResul.getFecha());
            pst.setInt(2, objResul.getPuntosEqp());
            pst.setString(3, objResul.getEquipos());
            System.out.println(strSQL);
            System.out.println(objResul.getPuntosEqp());
            System.out.println(objResul.getEquipos());
            pst.setString(4, fecha);
            pst.setString(5, nombre);
            pst.setString(6, nombre1);
            retorno = pst.executeUpdate();
            pst.close();
            con.CerrarConexion();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return retorno;
    }

}

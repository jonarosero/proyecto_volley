/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Clases.Colegio;
import Logica.BL_Colegio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */
public class Dat_Colegio {

    Dat_ConexionBD con = new Dat_ConexionBD();

    //Método para consultar todos los colegios registrados
    public ResultSet ConsultarColegio() throws ClassNotFoundException, SQLException {
        PreparedStatement pst = con.AbrirConexion().prepareStatement("SELECT * FROM Colegio");
        ResultSet rs = pst.executeQuery();
        return rs;
    }

    //Método para actualizar representantes de los colegios mediante prepared statement
    public int ActualizarRepresentante(Colegio objCol) throws ClassNotFoundException, SQLException {
        int retorno = 0;
        try {
            String strSQL = "UPDATE campeonato_volley.colegio \n"
                    + "SET NombreRepres=?\n"
                    + "WHERE idColegio = \n"
                    + "(SELECT idColegio FROM ( \n"
                    + "SELECT idColegio FROM Colegio WHERE \n"
                    + "NombreColegio = ?) AS id)";
            PreparedStatement pst = con.AbrirConexion().prepareStatement(strSQL);
            pst.setString(1, objCol.getNombreRep());
            pst.setString(2, objCol.getNombreCole());
            retorno = pst.executeUpdate();
            pst.close();
            con.CerrarConexion();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return retorno;
    }
}

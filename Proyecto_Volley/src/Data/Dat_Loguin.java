package Data;

import Clases.Arbitro;
import Clases.Encriptacion_Desencriptacion;
import Clases.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */
public class Dat_Loguin {
    Dat_ConexionBD con = new Dat_ConexionBD();
    //Clase para encriptar y desencriptar el password
    Encriptacion_Desencriptacion encrip_desencrip = new Encriptacion_Desencriptacion();
    // Metodo para validar los usuarios
    public ResultSet ValidarUsuario() throws ClassNotFoundException, SQLException {
        PreparedStatement pst = con.AbrirConexion().prepareStatement("SELECT Usuario, Contraseña, Tipo FROM usuario");
        ResultSet rs = pst.executeQuery();
        return rs;
    }
    // Metodos para Insertar Credenciales
    public int InsertarCredenciales(String Cedula, String pass) {
        int retorno = 0;
        try {
            String strSQL = "INSERT INTO usuario(idUsuario, Usuario, Contraseña, Tipo, idArbitro)\n"
                    + "SELECT\n"
                    + "0 AS idUsuario,\n"
                    + "? AS Usuario,\n"
                    + "? AS Contraseña,\n"
                    + "'ARBITRO' AS Tipo,\n"
                    + "a.idArbitro\n"
                    + "FROM arbitro a, persona p\n"
                    + "WHERE p.Cedula=?\n"
                    + "AND a.idPersona= p.idPersona;";
            PreparedStatement pst = con.AbrirConexion().prepareStatement(strSQL);
            pst.setString(1, Cedula);
            pst.setString(2, pass);
            pst.setString(3, Cedula);
            retorno = pst.executeUpdate();
            pst.close();
            con.CerrarConexion();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return retorno;
    }

}

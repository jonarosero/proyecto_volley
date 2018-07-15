/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Clases.Arbitro;
import Clases.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */

public class Dat_Arbitro {

    Dat_ConexionBD con = new Dat_ConexionBD();

    // Metodo para presentar los Arbitros del sistema
    public ResultSet Consultar() throws ClassNotFoundException, SQLException {
        Statement st = con.AbrirConexion().createStatement();
        String Sentencia = "SELECT p.Cedula, p.Nombres, p.Apellidos, p.Edad, p.Genero, a.TipoArbitro, a.Confederacion FROM persona p, arbitro a\n"
                + "where a.idPersona = p.idPersona;";
        ResultSet rs = st.executeQuery(Sentencia);
        return rs;
    }

    // Metodo para presentar los Arbitros del sistema
    public ResultSet ConsultarNombArbiLateral() throws ClassNotFoundException, SQLException {
        Statement st = con.AbrirConexion().createStatement();
        String sentencia = "SELECT p.Nombres, p.Apellidos\n"
                + "FROM persona p, arbitro a\n"
                + "where a.idPersona = p.idPersona\n"
                + "and a.TipoArbitro = 'LATERAL'";
        ResultSet rs = st.executeQuery(sentencia);
        return rs;
    }
    
    // Metodo para presentar los Arbitros del sistema
    public ResultSet ConsultarNombArbiCentral() throws ClassNotFoundException, SQLException {
        Statement st = con.AbrirConexion().createStatement();
        String sentencia = "SELECT p.Nombres, p.Apellidos\n"
                + "FROM persona p, arbitro a\n"
                + "where a.idPersona = p.idPersona\n"
                + "and a.TipoArbitro = 'CENTRAL'";
        ResultSet rs = st.executeQuery(sentencia);
        return rs;
    }

    // Metodo para insertar los datos de arbitro 
    public int InsertarArbitros(Arbitro objArbitro, String Cedula) {
        int retorno = 0;
        try {
            String strSQL = "INSERT INTO arbitro (TipoArbitro, Confederacion, idPersona) \n"
                    + "SELECT\n"
                    + "? As TipoArbitro,\n"
                    + "? as  Confederacion,\n"
                    + "p.idPersona\n"
                    + "FROM persona p\n"
                    + "WHERE p.Cedula=?";
            PreparedStatement pst = con.AbrirConexion().prepareStatement(strSQL);
            pst.setString(1, objArbitro.getTipoArb());
            pst.setString(2, objArbitro.getConfederacion());
            pst.setString(3, Cedula);
            System.out.println(objArbitro.getTipoArb());
            System.out.println(objArbitro.getConfederacion());
            retorno = pst.executeUpdate();
            pst.close();
            con.CerrarConexion();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return retorno;
    }
    

    // Metodo para modificar los atributo de arbitro
    public int ModificarArbitro(Persona objPersona, Arbitro objArbitro, String Cedula) throws ClassNotFoundException, SQLException {
        int retorno = 0;
        try {
            String strSQL = "UPDATE arbitro a, persona p \n"
                    + "SET TipoArbitro=?, Confederacion=?,\n"
                    + "p.Cedula=?, p.Nombres=?, p.Apellidos=?, p.edad= ? , p.genero=?\n"
                    + "WHERE p.idPersona =(SELECT idPersona FROM ( SELECT idPersona FROM persona WHERE Cedula= ?) AS idP)\n"
                    + "AND a.idPersona = p.idPersona";
            PreparedStatement pst = con.AbrirConexion().prepareStatement(strSQL);
            pst.setString(1, objArbitro.getTipoArb());
            pst.setString(2, objArbitro.getConfederacion());
            pst.setString(3, objPersona.getCedula());
            pst.setString(4, objPersona.getNombres());
            pst.setString(5, objPersona.getApellidos());
            pst.setInt(6, objPersona.getEdad());
            pst.setString(7, objPersona.getGenero());
            pst.setString(8, Cedula);
            retorno = pst.executeUpdate();
            pst.close();
            con.CerrarConexion();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return retorno;
    }

    
    //Metodo para Borrar Arbitro
    public int DeleteArbitro(int id) {
        int retorno = 0;
        try {
            String strSQL = "DELETE FROM UsuARIO WHERE us_name = ?";
            PreparedStatement pst = con.AbrirConexion().prepareStatement(strSQL);
            pst.setInt(1, id);
            retorno = pst.executeUpdate();
            pst.close();
            con.CerrarConexion();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return retorno;
    }
}

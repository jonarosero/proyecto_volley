/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.PreparedStatement;
/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */
public class Dat_Persona {
    Dat_ConexionBD con = new Dat_ConexionBD();
    // Metodo para insertar Personas
    public int InsertarPersona( String Cedula, String Nombres, String Apellidos, int Edad, String Genero) {
        int retorno = 0;
        int estado = 0;
        try {
            String strSQL = "INSERT INTO persona (Nombres, Apellidos, Cedula, Edad, Genero) " + " VALUES (?,?,?,?,?)";
            PreparedStatement pst = con.AbrirConexion().prepareStatement(strSQL);
            pst.setString(1, Cedula);
            pst.setString(2, Nombres);
            pst.setString(3, Apellidos);
            pst.setInt(4, Edad);
            pst.setString(5, Genero);
            System.out.println(strSQL);
            retorno = pst.executeUpdate();
            pst.close();
            con.CerrarConexion();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }
}

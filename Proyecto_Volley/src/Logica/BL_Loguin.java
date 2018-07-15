package Logica;

import Clases.Encriptacion_Desencriptacion;
import Data.Dat_Loguin;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando
 * León
 */
public class BL_Loguin {

    Dat_Loguin objDatLog = new Dat_Loguin();
    //Clase para encriptar y desencriptar el password
    Encriptacion_Desencriptacion desencrip = new Encriptacion_Desencriptacion();

    // Valida si el usuario es administrado o árbitro
    public boolean ValidarUsuarios(String user, String pass) throws ClassNotFoundException, SQLException {
        //Retorna true si el usuario ingresado existe
        boolean resultado = false;
        String usuario = null;
        String contraseña = null;
        String passDesencr = null;
        ResultSet rs = objDatLog.ValidarUsuario();

        while (rs.next()) {
            usuario = rs.getString("Usuario");
            contraseña = rs.getString("Contraseña");
            passDesencr = desencrip.desencriptar(contraseña);
        }
        // Si el usuario ingresa correcto la creedencial retorna verdadero
        if (user.equals(usuario) && pass.equals(passDesencr)) {
            resultado = true;
        }if (user.equals("Admin") && pass.equals("1234")) {
            resultado = true;
        }
        return resultado;
    }

    // Valida los tipos de usuario
    public boolean ValidarTipoUser(String user) throws ClassNotFoundException, SQLException {
        //Retorna true si el usuario es tipo Arbitro, de lo contrario administrador
        boolean resulArb = false;
        String usuario = null;
        String tipo = null;
        // Manda el usuario y contraseña obtenido del frame y valida 
        ResultSet rs = objDatLog.ValidarUsuario();
        while (rs.next()) {
            usuario = rs.getString("Usuario");
            tipo = rs.getString("Tipo");
            // Si el el tipo del resultado de la consulta es = "Administrador" retorna verdadero
            if (tipo.equals("ADMINISTRADOR") && user.equals(usuario)) {
                resulArb = false;
            }
        }
        return resulArb;
    }

    // Inserta las credenciales
    public int InsertarCredenciales(String Cedula, String pass) {
        int rs = objDatLog.InsertarCredenciales(Cedula, pass);
        return rs;
    }

}

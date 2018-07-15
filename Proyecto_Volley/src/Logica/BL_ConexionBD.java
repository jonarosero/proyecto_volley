package Logica;

import Data.Dat_ConexionBD;
import java.sql.SQLException;

/**
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando
 * León
 */
public class BL_ConexionBD {

    //Para poder acceder a paquete Model
    Dat_ConexionBD ManejadorConexion = new Dat_ConexionBD();

    public void CerrarConexion() throws SQLException {
        ManejadorConexion.CerrarConexion();
    }
}

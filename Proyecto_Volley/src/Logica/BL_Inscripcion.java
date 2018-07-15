
package Logica;

import Data.Dat_Equipos;
import Data.Dat_Inscripcion;
import java.sql.SQLException;

/**
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 * 
 */
public class BL_Inscripcion {

    Dat_Inscripcion datInscripcion = new Dat_Inscripcion();

    // Inserta los jugadores para su inscripcion
    public int InscribirJugadores(String fecha, String cedula, String nombreEquipo, int idCam) throws ClassNotFoundException, SQLException {
        int rs = datInscripcion.InscribirJugadores(fecha, cedula, nombreEquipo, idCam);
        return rs;
    }
}

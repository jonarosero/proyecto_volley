package Logica;

import Data.Dat_Campeonato_Equipo;
import java.sql.SQLException;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 * 
 */
public class BL_Campeonato_Equipo {

    Dat_Campeonato_Equipo objDatEquipo = new Dat_Campeonato_Equipo();

    // Insertar equipos en el campeonato
    public int InsertarEquipoCamp(String nombreEquipo, int idCam) throws ClassNotFoundException, SQLException {
        int rs = objDatEquipo.InsertarEquipoCamp(nombreEquipo, idCam);
        return rs;
    }
}

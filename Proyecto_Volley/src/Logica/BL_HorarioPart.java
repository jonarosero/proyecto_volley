package Logica;

import Clases.Equipo;
import Data.Dat_HorarioPart;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 * 
 */
public class BL_HorarioPart {

    Dat_HorarioPart datHorar = new Dat_HorarioPart();

    // Lista los equipos por categoria
    public ArrayList<Equipo> ListarEquipoCat(int cate) throws ClassNotFoundException, SQLException {
        ArrayList<Equipo> lstEquipos = new ArrayList<>();
        ResultSet rs = datHorar.ListarEquipoCat(cate);
        while (rs.next()) {
            String nombre = rs.getString("NombreEquipo");
            Equipo objEqui = new Equipo(nombre);
            lstEquipos.add(objEqui);
        }
        return lstEquipos;
    }

    // Inserta los equipos
    public int InsertarEquipo(String categoria, String Fecha, String hora, int cancha, int idCamp) throws ClassNotFoundException, SQLException {
        int rs = datHorar.InsertarEquipo(categoria, Fecha, hora, cancha, idCamp);
        return rs;
    }

}

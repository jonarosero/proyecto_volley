package Logica;

import Clases.Colegio;
import Clases.Equipo;
import Clases.Jugador;
import Clases.Persona;
import Data.Dat_Equipos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */

public class BL_Equipos {

    Dat_Equipos datEquipos = new Dat_Equipos();
    
    // Consulta los jugadores
    public ArrayList<Jugador> ConsultarJugadores(String gen, String cole) throws ClassNotFoundException, SQLException {
        ArrayList<Jugador> lstEquipos = new ArrayList<>();
        ResultSet rs = datEquipos.ListarEquipos(gen, cole);
        while (rs.next()) {
            String cedula = rs.getString("Cedula");
            String nombre = rs.getString("Nombres");
            String Apellido = rs.getString("Apellidos");
            int Edad = rs.getInt("Edad");
            int Estado = rs.getInt("Estado");
            Jugador objJug = new Jugador(nombre, Apellido, cedula, Edad, Estado);
            lstEquipos.add(objJug);
        }
        return lstEquipos;
    }
    
    // Inserta los equipos
    public int InsertarEquipo(String nombreEquipo, int cate, String nombreColegio) throws ClassNotFoundException, SQLException {
        int rs = datEquipos.InsertarEquipo(nombreEquipo, cate, nombreColegio);
        return rs;
    }
}

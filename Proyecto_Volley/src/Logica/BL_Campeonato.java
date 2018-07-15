package Logica;

import Clases.Campeonato;
import Clases.Colegio;
import Data.Dat_Campeonato;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando
 * León
 */
public class BL_Campeonato {

    Dat_Campeonato mp = new Dat_Campeonato();

    // Consula los campeonatos que se encuentran en la base
    public ArrayList<Campeonato> ConsultarCamp(ArrayList<Campeonato> ArrayCampeonato) throws SQLException, ClassNotFoundException {
        ResultSet rs = mp.ConsultarCampeonato();
        while (rs.next()) {
            int idCampeonato = rs.getInt("idCampeonato");
            String Nombre = rs.getString("Nombre");
            String Representante = rs.getString("Representante");
            String Anio = rs.getString("Anio");
            Campeonato objCam = new Campeonato(idCampeonato, Nombre, Representante, Anio);
            ArrayCampeonato.add(objCam);
        }
        return (ArrayCampeonato);
    }

    // Inserta campeonato en la base 
    public int InsertarCampeonato(String nombre, String repre, int anio) throws ClassNotFoundException, SQLException {
        int rs = mp.Insertar(nombre, repre, anio);
        return rs;
    }

    // Actualiza los campeonatos
    public int ActualizaCampeonato(Campeonato objCam, String nombre) throws ClassNotFoundException, SQLException {
        int rs = mp.Actualizar(objCam, nombre);
        return rs;
    }

    // Consulta los id de campeonatos de la base
    public ArrayList<Campeonato> ConsultarIdCamp(ArrayList<Campeonato> ArrayCampeonato, String nombre, String rep, int anio) throws SQLException, ClassNotFoundException {
        ResultSet rs = mp.ConsultarIdCampeonato(nombre, rep, anio);
        while (rs.next()) {
            int idCampeonato = rs.getInt("idCampeonato");
            Campeonato objCam = new Campeonato(idCampeonato);
            ArrayCampeonato.add(objCam);
        }
        return (ArrayCampeonato);
    }
}

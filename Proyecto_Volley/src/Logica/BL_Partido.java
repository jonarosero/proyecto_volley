/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Clases.Partido;
import Data.Dat_Partido;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando
 * León
 */
public class BL_Partido {

    Dat_Partido com = new Dat_Partido();

    // Metodo para consultar la fecha de los partidos
    public ArrayList<Partido> consultarFechaPartido() throws ClassNotFoundException, SQLException {
        ArrayList<Partido> lstArbi = new ArrayList<>();
        ResultSet rs = com.Consultar();
        while (rs.next()) {
            String fecha = rs.getString("Fecha");
            lstArbi.add(new Partido(fecha));
        }
        return lstArbi;
    }
}

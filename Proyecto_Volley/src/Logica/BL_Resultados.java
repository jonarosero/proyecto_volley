/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Clases.Arbitro;
import Clases.Equipo;
import Clases.Partido;
import Clases.Resultado;
import Data.Dat_Arbitro;
import Data.Dat_Resultados;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando
 * León
 */
public class BL_Resultados {

    Dat_Resultados ObjResultados = new Dat_Resultados();

    // Metodo para consultar los partidos
    public ArrayList<Equipo> consultarPartido(String fecha) throws ClassNotFoundException, SQLException {
        ArrayList<Equipo> lstEquipo = new ArrayList<>();
        ResultSet rs = ObjResultados.Consultar(fecha);
        while (rs.next()) {
            String eq1 = rs.getString("Equipo1");
            String eq2 = rs.getString("Equipo2");
            lstEquipo.add(new Equipo(eq1, eq2));
        }
        return lstEquipo;
    }

    // Metodo para insertarResultados
    public int insertarResultado(Resultado objResul, String fecha, String nombre, String nombre1, int punt) {
        int rs = ObjResultados.InsertarResultados(objResul, fecha, nombre, nombre1, punt);
        return rs;
    }
}

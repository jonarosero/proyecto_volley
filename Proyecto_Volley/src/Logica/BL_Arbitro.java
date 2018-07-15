package Logica;

import Clases.Arbitro;
import Clases.Persona;
import Data.Dat_Arbitro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 * 
 */
public class BL_Arbitro {

    Dat_Arbitro Objarbitro = new Dat_Arbitro();

    // Consulta los arbitros que están en la base
    public ArrayList<Arbitro> consultarArbitro() throws ClassNotFoundException, SQLException {
        ArrayList<Arbitro> lstArbitros = new ArrayList<>();
        ResultSet rs = Objarbitro.Consultar();
        while (rs.next()) {
            String nombre = rs.getString("Nombres");
            String apellido = rs.getString("Apellidos");
            String cedula = rs.getString("Cedula");
            int edad = rs.getInt("Edad");
            String genero = rs.getString("Genero");
            String tipo = rs.getString("TipoArbitro");
            String Confederacion = rs.getString("Confederacion");
            lstArbitros.add(new Arbitro(nombre, apellido, cedula, edad, genero, tipo, Confederacion));
        }
        return lstArbitros;
    }

    // Consulta los nombres de los arbitros de tipo lateral
    public ArrayList<Arbitro> consultarNomArbitroLateral() throws ClassNotFoundException, SQLException {
        ArrayList<Arbitro> lstArbi = new ArrayList<>();
        ResultSet rs = Objarbitro.ConsultarNombArbiLateral();
        while (rs.next()) {
            String nombre = rs.getString("Nombres");
            String apellido = rs.getString("Apellidos");
            lstArbi.add(new Arbitro(nombre, apellido));
        }
        return lstArbi;
    }

    // Consulta los nombres de los arbitros de tipo central
    public ArrayList<Arbitro> consultarNomArbitroCentral() throws ClassNotFoundException, SQLException {
        ArrayList<Arbitro> lstArbi = new ArrayList<>();
        ResultSet rs = Objarbitro.ConsultarNombArbiCentral();
        while (rs.next()) {
            String nombre = rs.getString("Nombres");
            String apellido = rs.getString("Apellidos");
            lstArbi.add(new Arbitro(nombre, apellido));
        }
        return lstArbi;
    }

    // Inserta los arbitros en la base
    public int InsertarArbitro(Arbitro objArbitro, String Cedula) {
        int rs = Objarbitro.InsertarArbitros(objArbitro, Cedula);
        return rs;
    }

    // Actualiza los arbitros
    public int ActualizaArbitro(Persona objPersona, Arbitro objArbitro, String cedula) throws ClassNotFoundException, SQLException {
        int rs = Objarbitro.ModificarArbitro(objPersona, objArbitro, cedula);
        return rs;
    }
}

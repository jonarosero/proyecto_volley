package Logica;

import Clases.Colegio;
import Clases.Jugador;
import Clases.Persona;
import Data.Dat_Jugador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando
 * León
 */
public class BL_Jugadores {

    Dat_Jugador jugador = new Dat_Jugador();

    // Consulta los jugadores masculinos
    public ArrayList<Jugador> consultarMasculino() throws ClassNotFoundException, SQLException {
        ArrayList<Jugador> lstJugador = new ArrayList<Jugador>();
        ResultSet rs = jugador.ConsultarMasculino();
        while (rs.next()) {

            String nombre = rs.getString("Nombres");
            String apellido = rs.getString("Apellidos");
            String cedula = rs.getString("Cedula");
            int edad = rs.getInt("Edad");
            String genero = rs.getString("Genero");
            String colegio = rs.getString("NombreColegio");
            Colegio objCol = new Colegio();
            objCol.setNombreCole(colegio);
            lstJugador.add(new Jugador(nombre, apellido, cedula, edad, genero, objCol));
            //System.out.println(objCol);
        }
        return lstJugador;
    }

    // Consulta los jugadores femeninos
    public ArrayList<Jugador> consultarFmenino() throws ClassNotFoundException, SQLException {
        ArrayList<Jugador> lstJugador = new ArrayList<Jugador>();
        ResultSet rs = jugador.ConsultarFemenino();
        while (rs.next()) {
            String nombre = rs.getString("Nombres");
            String apellido = rs.getString("Apellidos");
            String cedula = rs.getString("Cedula");
            int edad = rs.getInt("Edad");
            String genero = rs.getString("Genero");
            String colegio = rs.getString("NombreColegio");
            Colegio objCol = new Colegio();
            objCol.setNombreCole(colegio);
            lstJugador.add(new Jugador(nombre, apellido, cedula, edad, genero, objCol));
        }
        return lstJugador;
    }

    // Inserta los jugadores
    public int insertarJugador(Jugador objJugador, String cedula, String colegio) {
        int rs = jugador.InsertarJugadores(objJugador, cedula, colegio);
        return rs;
    }

    // Actualiza los jugadores
    public int ActualizaJugador(Persona objPersona, Jugador objJugador, String cedula, String NombCole, String colegioactual) throws ClassNotFoundException, SQLException {
        int rs = jugador.ModificarJugadores(objPersona, objJugador, cedula, NombCole, colegioactual);
        return rs;
    }

    // Actualiza los estados de los jugadores
    public int ActualizarEstado(String cedula) throws ClassNotFoundException, SQLException {
        int rs = jugador.ActualizarEstado(cedula);
        return rs;
    }

    // Elimina los jugadores
    public int EliminarJugador(String cedula) {
        int rs = jugador.DeleteJugador(cedula);
        return rs;
    }
}

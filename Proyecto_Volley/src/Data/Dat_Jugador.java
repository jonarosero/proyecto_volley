package Data;

import Clases.Colegio;
import Clases.Jugador;
import Clases.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando León
 */
public class Dat_Jugador {
    Dat_ConexionBD con = new Dat_ConexionBD();
    // Metodo para presentar los jugadores de genero Masculino
    public ResultSet ConsultarMasculino() throws ClassNotFoundException, SQLException {
        Statement st = con.AbrirConexion().createStatement();
        String Sentencia = "SELECT  p.Cedula, p.Nombres, p.Apellidos, p.edad, p.genero, c.NombreColegio FROM persona p, jugador j, colegio c\n"
                + "WHERE p.Genero='MASCULINO' and c.idColegio=j.idColegio\n"
                + "AND j.idPersona = p.idPersona; ";
        ResultSet rs = st.executeQuery(Sentencia);
        return rs;
    }
    // Metodo para presentar los jugadores de genero Femenino
    public ResultSet ConsultarFemenino() throws ClassNotFoundException, SQLException {
        Statement st = con.AbrirConexion().createStatement();
        String Sentencia = "SELECT  p.Cedula, p.Nombres, p.Apellidos, p.edad, p.genero, c.NombreColegio FROM persona p, jugador j, colegio c\n"
                + "WHERE p.Genero='FEMENINO' and c.idColegio=j.idColegio\n"
                + "AND j.idPersona = p.idPersona;";
        ResultSet rs = st.executeQuery(Sentencia);
        return rs;
    }
    //Método para actualizar el estado del jugador a 1 
    // 1 = jugador inscrito / 0 = jugador no inscrito
    public int ActualizarEstado(String cedula) throws ClassNotFoundException, SQLException {
        Statement st = con.AbrirConexion().createStatement();
        String Sentencia = "UPDATE campeonato_volley.jugador\n"
                + "SET Estado=1\n"
                + "WHERE idPersona = \n"
                + "(SELECT idPersona FROM ( \n"
                + "SELECT idPersona FROM persona WHERE \n"
                + " Cedula= '"+cedula+"') AS id)";
        int rs = st.executeUpdate(Sentencia);
        return rs;
    }
    // Metodo para Guardar los jugadores en la Base
    public int InsertarJugadores(Jugador objJugador, String cedula, String colegio) {
        int retorno = 0;
        int estado = 0;
        try {
            String strSQL = "INSERT INTO jugador  (Estatura, Peso, TipoSangre, Posicion, NumDorsal, idPersona, idColegio, Estado)\n"
                    + "SELECT \n"
                    + "? As Estatura,\n"
                    + "? as Peso,\n"
                    + "? as TipoSangre,\n"
                    + "? as Posicion,\n"
                    + "? as  NumDorsal,\n"
                    + "p.idPersona,c.idColegio,\n"
                    + "? as Estado\n"
                    + "FROM persona p, colegio c\n"
                    + "WHERE p.Cedula=?\n"
                    + "AND c.NombreColegio= ?;";
            PreparedStatement pst = con.AbrirConexion().prepareStatement(strSQL);
            pst.setDouble(1, objJugador.getEstatura());
            pst.setDouble(2, objJugador.getPeso());
            pst.setString(3, objJugador.getTipoSangre());
            pst.setString(4, objJugador.getPosicion());
            pst.setInt(5, objJugador.getNumDorsal());
            pst.setInt(6, objJugador.getEstado());
            pst.setString(7, cedula);
            pst.setString(8, colegio);
            
          
            retorno = pst.executeUpdate();
            pst.close();
            con.CerrarConexion();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return retorno;
    }

    // Metodo para modificar los atributos de los Jugadores
    public int ModificarJugadores(Persona objPersona, Jugador objJugador, String cedula, String NombCole,String colegioactual) {
        int retorno = 0;
        try {
            String strSQL = "UPDATE jugador j, persona p, colegio c \n"
                    + "SET j.Estatura= ?, j.NumDorsal = ?, j.Peso = ?, j.Posicion=?, j.TipoSangre= ?,\n"
                    + "p.Cedula=?, p.Nombres= ?, p.Apellidos=?, p.edad=? , p.genero=?, j.idColegio=c.idColegio\n"
                    + "WHERE p.idPersona =(SELECT idPersona FROM ( SELECT idPersona FROM persona WHERE Cedula= ?) AS idP)\n"
                    + "AND j.idPersona=p.idPersona  															\n"
                    + "AND j.idColegio=(SELECT idColegio FROM ( SELECT idColegio FROM colegio WHERE  NombreColegio=? ) AS idC1)\n"
                    + "AND c.idColegio=(SELECT idColegio FROM ( SELECT idColegio FROM colegio WHERE NombreColegio=? ) AS idC);";
            PreparedStatement pst = con.AbrirConexion().prepareStatement(strSQL);
            pst.setDouble(1, objJugador.getEstatura());
            pst.setInt(2, objJugador.getNumDorsal());
            pst.setDouble(3, objJugador.getPeso());
            pst.setString(4, objJugador.getPosicion());
            pst.setString(5, objJugador.getTipoSangre());
            pst.setString(6, objPersona.getCedula());
            pst.setString(7, objPersona.getNombres());
            pst.setString(8, objPersona.getApellidos());
            pst.setInt(9, objPersona.getEdad());
            pst.setString(10, objPersona.getGenero());
            pst.setString(11, cedula);
            pst.setString(12, NombCole);
            pst.setString(13, colegioactual);
            retorno = pst.executeUpdate();
            pst.close();
            con.CerrarConexion();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return retorno;
    }

    // Metodo para borrar jugador
    public int DeleteJugador(String cedula) {
        int retorno = 0;
        try {
            String strSQL = "DELETE FROM campeonato_volley.persona WHERE Cedula = ?";
            PreparedStatement pst = con.AbrirConexion().prepareStatement(strSQL);
            pst.setString(1, cedula);
            retorno = pst.executeUpdate();
            pst.close();
            con.CerrarConexion();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return retorno;
    }
}

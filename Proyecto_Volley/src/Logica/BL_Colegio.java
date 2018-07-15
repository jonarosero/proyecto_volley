package Logica;

import Clases.Colegio;
import Data.Dat_Colegio;
import Data.Dat_Jugador;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando
 * León
 */
public class BL_Colegio {

    Dat_Colegio objDatCol = new Dat_Colegio();

    // Lista los colegios que se encuentran en la base
    public ArrayList<Colegio> ListarColegios(ArrayList<Colegio> ArrayColegio) throws ClassNotFoundException, SQLException {
        ResultSet rs = objDatCol.ConsultarColegio();
        while (rs.next()) {
            int idColegio = rs.getInt("idColegio");
            String NombreColegio = rs.getString("NombreColegio");
            String NombreRepres = rs.getString("NombreRepres");
            Colegio objCol = new Colegio(idColegio, NombreColegio, NombreRepres);
            ArrayColegio.add(objCol);
        }
        return (ArrayColegio);
    }

    // Actualiza los colegios que están en la base
    public int ActualizaColegio(Colegio objCol) throws ClassNotFoundException, SQLException {
        int rs = objDatCol.ActualizarRepresentante(objCol);
        return rs;
    }

    // Lista los colegios en el combo
    public ArrayList<Colegio> ComboColegio() throws ClassNotFoundException, SQLException, IOException {
        ArrayList<Colegio> lstCole = new ArrayList<>();
        ResultSet rs = objDatCol.ConsultarColegio();
        while (rs.next()) {
            String nomb = rs.getString("NombreColegio");
            String repre = rs.getString("NombreRepres");
            Colegio objAdmin = new Colegio(nomb, nomb);
            lstCole.add(objAdmin);
        }

        return lstCole;
    }
}

package Data;

import java.sql.*;


public class Dat_ConexionBD {

    //Conectarse a la BDD
    public static Connection con;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/campeonato_volley";
        Class.forName(driver);
        return DriverManager.getConnection(url, "root", "");
    }
    //Metodo para Abrir Conexion
    public Connection AbrirConexion() throws ClassNotFoundException, SQLException {
        con = getConnection();
        return con;
    }

    public void CerrarConexion() throws SQLException {
        con.close();
    }

}

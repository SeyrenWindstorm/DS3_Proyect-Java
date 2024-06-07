package proyecto.ds3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conectar {
    //Variable para establecer la conexión con la BD
    private static Connection Conexion;
 
    //Método que realiza la conexión con la BD
    public void MySQLConexion(String user, String pass, String db_name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_name, user, pass);
            System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Método que cierra la conexión con la BD
    public void CerrarConexion() {
        try {
            Conexion.close();
            System.out.println("--------------------------------------------");
            System.out.println("Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Método que crea la BD
    public void createDB(String name) {
        try {
            String Query = "CREATE DATABASE " + name;
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            MySQLConexion("root", "", name);
            JOptionPane.showMessageDialog(null, "Se ha creado la base de datos " + name + " de forma exitosa");
        } catch (SQLException ex) {
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Método que crea la tabla en la BD
    public void createTable(String name) {
        try {
            String Query = "CREATE TABLE" + name + ""
                    + "(NumEm VARCHAR(4),Nombre VARCHAR(25), Sueldo ARCHAR(10),"
                    + " Descuentos ARCHAR(10), SueldoNeto ARCHAR(10), primary key(NumEm))";
            JOptionPane.showMessageDialog(null, "Se ha creado la base de tabla " + name + " de forma exitosa");
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
        } catch (SQLException ex) {
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Método que inserta registros en la BD
    public void InsertarDatos(String table_name, String NumEm, String nom, String suel, String desc, String sueln) {
        try {
            String Query = "INSERT INTO " + table_name + " VALUES(" 
                    + "\"" + NumEm + "\", "
                    + "\"" + nom + "\", "
                    + "\"" + suel + "\", "
                    + "\"" + desc + "\", "
                    + "\"" + sueln + "\")";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Datos Registrados de forma exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }
    }
    //Método que elimina registros de la BD
    public void EliminarRegistros(String table_name, String NumEm) {
        try {
            String Query = "DELETE FROM " + table_name + " WHERE NumEm = \"" + NumEm + "\"";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Registro eliminado de forma exitosa");    
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
        }
    }
}

package Main;


import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class Conexion {
   
    Connection conectar = null;
    boolean mensajeMostrado = false;
    String user = "root";
    String passW = "root";
    String bd = "mediateca";
    String ip = "localhost";
    String puerto = "3306";
    
    String cade = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection establecerConexion(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cade, user, passW);
            if(!mensajeMostrado){
            JOptionPane.showMessageDialog(null, "Se conectó a la base de datos");
            mensajeMostrado = true;
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se conectó a la base de datos, error:" +e.toString());
        }
        
        return conectar;
    }
}

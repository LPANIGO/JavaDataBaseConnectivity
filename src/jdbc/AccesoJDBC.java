
package jdbc;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AccesoJDBC {
    
    //public static final String DRIVER_MYSQL = "com.mysql.jdbc.Driver"; //(es otro driver por si quiero usar otro motor, en este caso mysql)
    public static final String DRIVER_SQLITE = "org.sqlite.JDBC";
    
    public static final String DBURL_MYSQL = "jdbc:mysql://localhost/paradigmas2020?user=root&password=admin";
    public static final String DBURL_SQLITE = "jdbc:sqlite:paradigmas2020.sqlite";

   
    /*
    pasos para manipular una base de datos:
    
    1ero registrar el driver
    2do obtener una conexion (objeto tipo conexion), indicando la ubicacion de mi base de datos (linea 38) tmb paso el protocolo que voy a utilizar, en este caso jdbc:sqlite (linea 14)
    3ro tener un objeto tipo statement (para comunicar una sentencia/declaracion o statement) (linea 23 clase "MateriaJDBC")
    4to ejecutar la consulta
    
    */
    // method to create JDBC connections
    public static Connection createConnection() {
        // Use DRIVER and DBURL to create a connection
        
        Connection conexion = null;
        try {
            //Cargar clase de controlador de base de datos
            Class.forName(DRIVER_SQLITE); //1er forma de registrar el driver
            
            //DriverManager.registerDriver(new org.sqlite.JDBC()); //2da forma de registrar el driver
        
            //Crear el objeto de conexion a la base de datos
            conexion = DriverManager.getConnection(DBURL_SQLITE);
        }
        catch (ClassNotFoundException ex) { //excepcion chequeada (obligacion de definir un catch) para linea 30
            System.out.println(ex);
        }
        catch (SQLException ex) {           //excepcion chequeada (obligacion de definir un catch) para linea 35
            System.out.println(ex);
        }
        
        return conexion;
    }
    
}

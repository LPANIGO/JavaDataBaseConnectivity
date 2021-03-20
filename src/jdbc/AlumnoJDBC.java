package jdbc;

import entidades.Alumno;
import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlumnoJDBC extends AccesoJDBC {
    
    private static AlumnoJDBC instanciaUnica;
    
    public static AlumnoJDBC getInstancia(){
        if (instanciaUnica == null) {
            instanciaUnica = new AlumnoJDBC();
        }
        return instanciaUnica;
    }
    public void crearTabla() {
        Connection conexion = null;
        Statement statement = null;
        
        try {
            conexion = createConnection();
            
            statement = conexion.createStatement();
            
            int result = statement.executeUpdate("CREATE TABLE ALUMNO "
                    + "(LEGAJO INT NOT NULL, "
                    + "APELLIDO VARCHAR(45), "
                    + "COD_MATERIA INT(45))");
            
            System.out.println("Tabla ALUMNO creada satisfactoriamente"); //mejorar codigo agregando validación
            
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        finally{
            try {
                if (conexion != null) {
                    conexion.close();
                }
                if (statement != null) {
                    statement.close();
                }
            }
            catch (SQLException ex) {
                System.out.println(ex);                
            }
        }
    }
           
    public void guardarAlumno(Alumno alumno) {
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
            
        try {
            conexion = createConnection();
                
            preparedStatement = conexion.prepareStatement("INSERT INTO ALUMNO VALUES (?,?,?)");
            preparedStatement.setInt(1, alumno.getLegajo());
            preparedStatement.setString(2, alumno.getApellido());
            preparedStatement.setInt(3, alumno.getMateria().getCodigo()); 
                
            int result = preparedStatement.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }  
    }
        
    public Alumno buscarAlumnoPorApellido(String apellidoAlumno) {
        Alumno alumno = null;
            
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
            
        try {
            //Crear conexion a base de datos
            conexion = createConnection();
                
            //Crear objeto Statement para realizar queries a la base de datos
            preparedStatement = conexion.prepareStatement("SELECT * FROM ALUMNO WHERE ALUMNO.APELLIDO = ?");
            preparedStatement.setString(1, apellidoAlumno);
                
            //Un objeto ResultSet almacena los resultados de una consulta
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alumno = new Alumno();
                alumno.setLegajo(rs.getInt("LEGAJO"));
                alumno.setApellido(rs.getString("APELLIDO"));
                    
                int codigoMateria = rs.getInt("COD_MATERIA");
                   
                MateriaJDBC materiaJDBC = new MateriaJDBC();
                Materia materia = materiaJDBC.buscarMateriaPorCodigo(codigoMateria);
                   
                alumno.setMateria(materia);                   
            }
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            }
            catch (Exception e) {
                System.out.println(e);
            } 
        }
        return alumno;
    }
    
    public void borrarAlumno(int legajo){
        
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        
        try {
            conexion = createConnection();
            preparedStatement = conexion.prepareStatement("DELETE FROM ALUMNO WHERE LEGAJO LIKE ?");
            preparedStatement.setInt(1, legajo);
            
            int result = preparedStatement.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }  
        
    }
                
}

    



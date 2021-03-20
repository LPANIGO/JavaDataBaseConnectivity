package jdbc;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entidades.Materia;
import java.sql.PreparedStatement;

public class MateriaJDBC extends AccesoJDBC {
    
    private static MateriaJDBC instanciaUnicaDeMateria;
    
    //Singleton para objeto MateriaJDBC
    public static MateriaJDBC getInstancia(){
        if (instanciaUnicaDeMateria == null) {
            instanciaUnicaDeMateria = new MateriaJDBC();
        }
        return instanciaUnicaDeMateria;
    }
    
    public void crearTabla() {
        Connection conexion = null;
        Statement statement = null;
        
        try {
            conexion = createConnection();
            
            //Crear objeto Statement para realizar queries a la base de datos
            statement = conexion.createStatement();
            
            int result = statement.executeUpdate("CREATE TABLE MATERIA "
                    + "(CODIGO INT NOT NULL,"
                    + "NOMBRE VARCHAR(45),"
                    + "CUATRIMESTRE INT(45))");
            
            System.out.println("Tabla MATERIA creada satisfactoriamente!"); //mejorar codigo agregando validación
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            }
            catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
    
    public void guardarMateria(Materia materia){
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        
        try {
            conexion = createConnection();
            
            //Crear objeto Statement para realizar queries a la base de datos
            preparedStatement = conexion.prepareStatement("INSERT INTO MATERIA VALUES (?,?,?)");
            preparedStatement.setInt(1, materia.getCodigo());
            preparedStatement.setString(2, materia.getNombre());
            preparedStatement.setString(3, materia.getCuatrimestre());
            
            int result = preparedStatement.executeUpdate();
                    
        }    
        catch (SQLException ex) {
            System.out.println(ex);
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            }
            catch (SQLException ex) {
                System.out.println(ex);
            }
        }           
    }
    
    public Materia buscarMateriaPorCodigo(int codigoMateria) {
        Materia materia = null;
        
        Connection conexion = null;
        Statement statement =  null;
        ResultSet rs = null;
        
        try {
            conexion = createConnection();
            
            //Crear objeto Statement para realizar queries a la base de datos
            statement = conexion.createStatement();
            
            //Un objeto ResultSet, almacena los datos de resultados de una consulta
            rs = statement.executeQuery("SELECT * FROM MATERIA WHERE MATERIA.CODIGO=" + codigoMateria);
            
            while(rs.next()) {
                materia = new Materia();
                materia.setCodigo(rs.getInt("CODIGO"));
                materia.setNombre(rs.getString("NOMBRE"));
                materia.setCuatrimestre(rs.getString("CUATRIMESTRE"));
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
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return materia;
    }
    
    public Materia buscarMateriaPorNombre (String nombreMateria){
        Materia materia = null;
        
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
            conexion = createConnection();
            
            preparedStatement = conexion.prepareStatement("SELECT * FROM MATERIA WHERE MATERIA.NOMBRE= ?");
            preparedStatement.setString(1, nombreMateria);
            
            rs = preparedStatement.executeQuery();
            
            while (rs.next()){
                materia = new Materia();
                materia.setCodigo(rs.getInt("CODIGO"));
                materia.setNombre(rs.getString("NOMBRE"));
                materia.setCuatrimestre(rs.getString("CUATRIMESTRE"));                
            }
        }catch (SQLException ex){
            System.out.println(ex);
        }
        finally {
            
        }
        return materia;
    }
     
    public void borrarMateria(int codigo){
        
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        
        try {
            conexion = createConnection();
            preparedStatement = conexion.prepareStatement("DELETE FROM MATERIA WHERE CODIGO LIKE ?");
            preparedStatement.setInt(1, codigo);
            
            int result = preparedStatement.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }    
    }
    
     //crear metodo para borrar filas repetidas
}

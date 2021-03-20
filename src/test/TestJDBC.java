package test;

import entidades.Alumno;
import entidades.Materia;
import jdbc.MateriaJDBC;
import jdbc.AlumnoJDBC;

public class TestJDBC {
    
    public static void main(String args[]) {
        
        TestJDBC test = new TestJDBC();
        //test.crearTablaMaterias();
        //test.guardarMaterias();
        //test.buscarMateriaPorCodigo();
        //test.buscarMateriaPorNombre();
        //test.borrarMateria();
        
        
        //test.crearTablaAlumno();
        //test.guardarAlumnos();
        //test.buscarAlumno(); 
        //test.borrarAlumno();
        //test.borrarMateria();
    }
    
    public void crearTablaMateria() {
        MateriaJDBC materiaJDBC = MateriaJDBC.getInstancia(); //Singleton
        materiaJDBC.crearTabla();
    }
    
    public void crearTablaAlumno(){
        AlumnoJDBC alumnoJDBC = AlumnoJDBC.getInstancia();
        alumnoJDBC.crearTabla();        
    }
    
    public void guardarMaterias() {
        MateriaJDBC materiaJDBC = MateriaJDBC.getInstancia();
        
        Materia mat1 = new Materia();
        mat1.setCodigo(1);
        mat1.setNombre("Introduccion a los algoritmos");
        mat1.setCuatrimestre("1");
        
        Materia mat2 = new Materia();
        mat2.setCodigo(2);
        mat2.setNombre("Programacion orientada a objetos");
        mat2.setCuatrimestre("2");
        
        Materia mat3 = new Materia();
        mat3.setCodigo(3);
        mat3.setNombre("Paradigmas de programacion");
        mat3.setCuatrimestre("2");
        
        materiaJDBC.guardarMateria(mat1);
        materiaJDBC.guardarMateria(mat2);
        materiaJDBC.guardarMateria(mat3);  
    }
    
    public void guardarAlumnos() {
        AlumnoJDBC alumnoJDBC = AlumnoJDBC.getInstancia();
        MateriaJDBC materiaJDBC = MateriaJDBC.getInstancia(); 
        
        
        Materia materia1 = materiaJDBC.buscarMateriaPorCodigo(3);
        
        Alumno alumno1 = new Alumno();
        alumno1.setLegajo(1);
        alumno1.setApellido("de las Heras");
        alumno1.setMateria(materia1);
        alumnoJDBC.guardarAlumno(alumno1);  

        Materia materia2 = materiaJDBC.buscarMateriaPorNombre("Programacion Orientada a Objetos");
        
        Alumno alumno2 = new Alumno();
        alumno2.setLegajo(2);
        alumno2.setApellido("Perez");
        alumno2.setMateria(materia2);
        alumnoJDBC.guardarAlumno(alumno2);
        
        Alumno alumno3 = new Alumno();
        alumno3.setLegajo(3);
        alumno3.setApellido("Lopez");
        alumno3.setMateria(materia2);
        alumnoJDBC.guardarAlumno(alumno3);
        
        Materia materia3 = materiaJDBC.buscarMateriaPorNombre("Paradigmas de Programacion");
        
        Alumno alumno4 = new Alumno();
        alumno4.setLegajo(4);
        alumno4.setApellido("Flores");
        alumno4.setMateria(materia3);
        alumnoJDBC.guardarAlumno(alumno4);   
    }
    
    public void buscarMateriaPorCodigo() {
        MateriaJDBC materiaJDBC = MateriaJDBC.getInstancia();
        Materia materia = materiaJDBC.buscarMateriaPorCodigo(1);
        if (materia != null) {
            System.out.println("Resultado de la busqueda de materia: " + materia.toString());
        }
        else {
            System.out.println("No se encontraron resultados para la busqueda");
        }
    }
    
    public void buscarMateriaPorNombre() {
        MateriaJDBC materiaJDBC = MateriaJDBC.getInstancia();
        Materia materia = materiaJDBC.buscarMateriaPorNombre("Programacion orientada a objetos");
        
        if (materia != null) {
            System.out.println("Resultado de la busqueda de materia: " + materia.toString());
        }
        else {
            System.out.println("No se encontraron resultados para la busqueda");
        } 
    }
    
    public void buscarAlumno() {
        AlumnoJDBC alumnoJDBC = AlumnoJDBC.getInstancia();
        Alumno alumno = alumnoJDBC.buscarAlumnoPorApellido("de las Heras");
        if (alumno != null) {
            System.out.println("Resulatado de la busqueda de alumno: " + alumno.toString());
        }
        else {
            System.out.println("No se encontraron resultados para la busqueda");
        }
    }
    
    public void borrarAlumno() {
        AlumnoJDBC alumnoJDBC = AlumnoJDBC.getInstancia();
        alumnoJDBC.borrarAlumno(1);    
    }
    
    public void borrarMateria() {
        MateriaJDBC materiaJDBC = MateriaJDBC.getInstancia();
        materiaJDBC.borrarMateria(1);
    }
}

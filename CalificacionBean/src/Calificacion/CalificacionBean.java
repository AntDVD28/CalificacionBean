/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calificacion;

import java.beans.*;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EventListener;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AntDVD
 */
public class CalificacionBean implements Serializable {
    private PropertyChangeSupport propertySupport;
       
    //********************* P R O P I E D A D E S ***************************** 
    protected int id;
    protected String dni;
    protected String nombreModulo;
    protected String curso;
    protected Double nota;
    
    //Vector auxiliar para volcar los datos de la tabla calificaciones
    private Vector ArrayCalificaciones = new Vector();
    
    //Clase auxiliar para el instanciar los objetos que formarán parte del vector
    private class Calificacion{
        
        int id;
        String dni;
        String nombreModulo;
        String curso;
        Double nota;
        
        public Calificacion(){
            
        }
        
        public Calificacion(int id, String dni, String nombreModulo, String curso, Double nota){
            
            this.id = id;
            this.dni = dni;
            this.nombreModulo = nombreModulo;
            this.curso = curso;
            this.nota = nota;
        }
    }
    
     
    //********************* C O N S T R U C T O R *****************************
    public CalificacionBean() {
        propertySupport = new PropertyChangeSupport(this);
        try {
            recargarFilas();
        } catch (ClassNotFoundException ex) {
            this.id = 0;
            this.dni = "";
            this.nombreModulo = "";
            this.curso = "";
            this.nota = 0.0;
            Logger.getLogger(CalificacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Método para volcar el contenido de la tabla alumnos en el vector de objetos ArrayCalificaciones
     * Cada registro de la tabla lo guardaremos en un objeto de tipo Calificacion
     * Cargamos el primer objeto del array en las propiedades de la clase
     * @throws ClassNotFoundException 
     */
    
    private void recargarFilas() throws ClassNotFoundException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/alumnos", "root_ad", "123456");
            java.sql.Statement s = con.createStatement();
            ResultSet rs = s.executeQuery ("select * from calificaciones");
            while (rs.next())
            {
                //Instanciamos un objeto por cada resulset obtenido
                Calificacion c = new Calificacion(
                                      rs.getInt("id"),
                                      rs.getString("DNI"),
                                      rs.getString("NombreModulo"),
                                      rs.getString("Curso"),
                                      rs.getDouble("Nota"));
                //Agregamos el objeto al vector
                ArrayCalificaciones.add(c);
            }
            //Cargamos en las propiedades el primer objeto contenido en el vector
            Calificacion c = new Calificacion();
            c = (Calificacion) ArrayCalificaciones.elementAt(1);
            this.id = c.id;
            this.dni = c.dni;
            this.nombreModulo = c.nombreModulo;
            this.curso = c.curso;
            this.nota = c.nota;
            rs.close();
            con.close();
        } catch (SQLException ex) {
            this.id = 0;
            this.dni = "";
            this.nombreModulo = "";
            this.curso = "";
            this.nota = 0.0;
            Logger.getLogger(CalificacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método para cargar en un objeto0 el objeto guardado en la posición i del ArrayCalificaciones
     * @param i indice del array
     */
    public void seleccionarFila(int i)
    {
        if(i<=ArrayCalificaciones.size())
        {
            Calificacion c = new Calificacion();
            c = (Calificacion) ArrayCalificaciones.elementAt(i);
            this.id = c.id;
            this.dni = c.dni;
            this.nombreModulo = c.nombreModulo;
            this.curso = c.curso;
            this.nota = c.nota;
        }else{
            this.id = 0;
            this.dni = "";
            this.nombreModulo = "";
            this.curso = "";
            this.nota = 0.0;
        }
    }
    
    /**
     * Devolvemos el tamaño del vector
     * @return int tamanio Tamaño del array
     */
    public int getTamanioVector(){
        
        return ArrayCalificaciones.size();
    }
    
    
    //********** M É T O D O S  G E T T E R S / S E T T E R S *****************

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
   
    //
    /**
     *******************G E S T I Ó N  D E  E V E N T O S ********************
     * Cada vez que añadimos un registro nuevo en la BD generamos un evento 
     * el cual da lugar a una acción, que se recargue el componente
     */
        
    private BDModificadaListener receptor;
    
    /**
     * Clase que define los eventos
     */
    public class BDModificadaEvent extends java.util.EventObject
    {
        // constructor
        public BDModificadaEvent(Object source)
        {
            super(source);
        }
    }
    /**
     * Interfaz que define los métodos a implementar
     */
    public interface BDModificadaListener extends EventListener
    {
        public void capturarBDModificada(BDModificadaEvent ev);
    }

    /**
     * Método para agregar un nuevo listener
     * @param receptor 
     */
    public void addBDModificadaListener(BDModificadaListener receptor)
    {
        this.receptor = receptor;
    }
    /**
     * Método para eliminar un listener
     * @param receptor 
     */
    public void removeBDModificadaListener(BDModificadaListener receptor)
    {
        this.receptor=null;
    }

    /**
     * Método que agrega un registro a la Base de Datos
     * Los valores se cargan desde el componente
     * Este método lanzará el evento
     * @throws ClassNotFoundException 
     */
    public void addCalificacion() throws ClassNotFoundException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/alumnos", "root_ad", "123456");
            
            //Tengo que averiguar el id del último registro
            String sentencia_id_last = "SELECT MAX(id) AS id FROM calificaciones";
            java.sql.Statement st = con.createStatement();
            ResultSet r = st.executeQuery(sentencia_id_last);
            r.next();
            //Lo incremento en uno
            int ID = r.getInt(1)+1;
            
            
            String sentencia_insert = "insert into calificaciones values (?,?,?,?,?)";                     
            PreparedStatement s = con.prepareStatement(sentencia_insert);
            
            s.setInt(1, ID);
            s.setString(2, dni);
            s.setString(3, nombreModulo);
            s.setString(4, curso);
            s.setDouble(5, nota);           

            s.executeUpdate ();
            recargarFilas();
            receptor.capturarBDModificada( new BDModificadaEvent(this));
        }
        catch(SQLException ex)
        {
            Logger.getLogger(CalificacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
}

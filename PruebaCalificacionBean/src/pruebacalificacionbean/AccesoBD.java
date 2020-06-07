/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebacalificacionbean;
import Calificacion.CalificacionBean;
import Calificacion.CalificacionBean.BDModificadaEvent;
import Calificacion.CalificacionBean.BDModificadaListener;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AntDVD
 */
public class AccesoBD implements BDModificadaListener{
    
    CalificacionBean cbean;
    
    AccesoBD(){
        
        //Instanciamos el componente
        cbean = new CalificacionBean();
        //Indicamos que el propio bean es un listener
        cbean.addBDModificadaListener( (BDModificadaListener)this );
    }
    
    public void listado()
    {
        int tamanio_vector = cbean.getTamanioVector();
        
        
        for(int i=0; i<tamanio_vector; i++)
        {
            cbean.seleccionarFila(i);
            System.out.println("Id " + i + "\n\tDNI:" + cbean.getDni());
            System.out.println("\tNombre Modulo: " + cbean.getNombreModulo());
            System.out.println("\tCurso: " + cbean.getCurso());
            System.out.println("\tNota: " + cbean.getNota());
        }
        
    }
 
    void anade()
    {
        cbean.setDni("43884828T");
        cbean.setNombreModulo("AD");
        cbean.setCurso("19-20");
        cbean.setNota(9.0);
        try {
            cbean.addCalificacion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Este es el método(acción) que se lanzará tras producirse el evento
     * @param ev 
     */
    public void capturarBDModificada(BDModificadaEvent ev)
    {
        System.out.println("Se ha añadido un elemento a la base de datos");
    }
    
}

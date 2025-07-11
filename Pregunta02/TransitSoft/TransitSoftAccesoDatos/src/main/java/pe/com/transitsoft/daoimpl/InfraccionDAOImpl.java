/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.transitsoft.daoimpl;

import java.util.ArrayList;
import pe.com.transitsoft.dao.InfraccionDAO;
import pe.com.transitsoft.daoimpl.util.Columna;
import pe.com.transitsoft.daoimpl.util.Tipo_Dato;
import pe.com.transitsoft.modelo.Conductor;
import pe.com.transitsoft.modelo.Infraccion;

/**
 *
 * @author Yessica
 */
public class InfraccionDAOImpl extends DAOImplBase implements InfraccionDAO{
    private Infraccion infraccion;
    
    public InfraccionDAOImpl(){
        super("EX1_INFRACCIONES");
        this.retornarLlavePrimaria=true;
        this.infraccion=null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("INFRACCION_ID", Tipo_Dato.ENTERO, true, true));
        this.listaColumnas.add(new Columna("DESCRIPCION", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("MONTO_MULTA", Tipo_Dato.REAL, false, false));
        this.listaColumnas.add(new Columna("GRAVEDAD", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("PUNTOS", Tipo_Dato.ENTERO, false, false));
    }

    @Override
    public Integer insertar(Infraccion infraccion) {
        this.infraccion = infraccion;
        return super.insertar();
    }

    @Override
    public Integer modificar(Infraccion infraccion) {
        this.infraccion = infraccion;
        return super.modificar();
    }

    @Override
    public Integer eliminar(Infraccion infraccion) {
        this.infraccion = infraccion;
        return super.eliminar();
    }

    @Override
    public Infraccion obtenerPorId(Integer idInfraccion) {
        this.infraccion= new Infraccion();
        this.infraccion.setInfraccionId(idInfraccion);
        super.obtenerPorId();
        return this.infraccion;
    }

    @Override
    public ArrayList<Infraccion> listarTodos() {
        return (ArrayList<Infraccion>) super.listarTodos();
    }
    
}

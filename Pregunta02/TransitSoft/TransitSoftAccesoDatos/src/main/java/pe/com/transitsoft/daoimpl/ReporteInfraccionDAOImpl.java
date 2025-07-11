/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.transitsoft.daoimpl;

import java.util.ArrayList;
import pe.com.transitsoft.dao.ReporteInfraccionDAO;
import pe.com.transitsoft.daoimpl.util.Columna;
import pe.com.transitsoft.daoimpl.util.Tipo_Dato;
import pe.com.transitsoft.modelo.ReporteInfraccion;

/**
 *
 * @author Yessica
 */
public class ReporteInfraccionDAOImpl extends DAOImplBase implements ReporteInfraccionDAO {
    private ReporteInfraccion reporteInfraccion;

    public ReporteInfraccionDAOImpl() {
        super("EX1_REPORTES_INFRACCIONES");
        this.retornarLlavePrimaria = true;
        this.reporteInfraccion = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("REPORTE_ID",      Tipo_Dato.ENTERO, true,  true));
        this.listaColumnas.add(new Columna("CONDUCTOR_ID",    Tipo_Dato.ENTERO, false, false));
        this.listaColumnas.add(new Columna("PATERNO",         Tipo_Dato.CADENA,  false, false));
        this.listaColumnas.add(new Columna("MATERNO",         Tipo_Dato.CADENA,  false, false));
        this.listaColumnas.add(new Columna("NOMBRES",         Tipo_Dato.CADENA,  false, false));
        this.listaColumnas.add(new Columna("VEHICULO_ID",     Tipo_Dato.ENTERO, false, false));
        this.listaColumnas.add(new Columna("PLACA",           Tipo_Dato.CADENA,  false, false));
        this.listaColumnas.add(new Columna("MARCA",           Tipo_Dato.CADENA,  false, false));
        this.listaColumnas.add(new Columna("MODELO",          Tipo_Dato.CADENA,  false, false));
        this.listaColumnas.add(new Columna("ANHO",            Tipo_Dato.ENTERO, false, false));
        this.listaColumnas.add(new Columna("INFRACCION_ID",   Tipo_Dato.ENTERO, false, false));
        this.listaColumnas.add(new Columna("DESCRIPCION",     Tipo_Dato.CADENA,  false, false));
        this.listaColumnas.add(new Columna("MONTO",           Tipo_Dato.REAL, false, false));
        this.listaColumnas.add(new Columna("GRAVEDAD",        Tipo_Dato.CADENA,  false, false));
    }

    @Override
    public Integer insertar(ReporteInfraccion dto) {
        this.reporteInfraccion = dto;
        return super.insertar();
    }

    @Override
    public Integer modificar(ReporteInfraccion dto) {
        this.reporteInfraccion = dto;
        return super.modificar();
    }

    @Override
    public Integer eliminar(ReporteInfraccion dto) {
        this.reporteInfraccion = dto;
        return super.eliminar();
    }

    @Override
    public ReporteInfraccion obtenerPorId(Integer id) {
        this.reporteInfraccion = new ReporteInfraccion();
        this.reporteInfraccion.setReporteId(id);
        super.obtenerPorId();
        return this.reporteInfraccion;
    }

    @Override
    public ArrayList<ReporteInfraccion> listarTodos() {
        return (ArrayList<ReporteInfraccion>) super.listarTodos();
    }
}
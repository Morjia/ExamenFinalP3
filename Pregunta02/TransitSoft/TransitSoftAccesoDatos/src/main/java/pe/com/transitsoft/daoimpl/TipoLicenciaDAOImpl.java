/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.transitsoft.daoimpl;

import java.util.ArrayList;
import pe.com.transitsoft.dao.TipoLicenciaDAO;
import pe.com.transitsoft.daoimpl.util.Columna;
import pe.com.transitsoft.daoimpl.util.Tipo_Dato;
import pe.com.transitsoft.modelo.TipoLicencia;

/**
 *
 * @author Yessica
 */
public class TipoLicenciaDAOImpl extends DAOImplBase implements TipoLicenciaDAO {
    private TipoLicencia tipoLicencia;

    public TipoLicenciaDAOImpl() {
        super("EX1_TIPOS_LICENCIAS");
        // La PK es auto-generada
        this.retornarLlavePrimaria = true;
        this.tipoLicencia = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        // Columna(nombre, tipo, esPK, esAutoIncremental)
        this.listaColumnas.add(new Columna("TIPO_LICENCIA_ID", Tipo_Dato.ENTERO, true,  true));
        this.listaColumnas.add(new Columna("NOMBRE",            Tipo_Dato.CADENA,  false, false));
        this.listaColumnas.add(new Columna("DESCRIPCION",       Tipo_Dato.CADENA,  false, false));
    }

    @Override
    public Integer insertar(TipoLicencia dto) {
        this.tipoLicencia = dto;
        return super.insertar();
    }

    @Override
    public Integer modificar(TipoLicencia dto) {
        this.tipoLicencia = dto;
        return super.modificar();
    }

    @Override
    public Integer eliminar(TipoLicencia dto) {
        this.tipoLicencia = dto;
        return super.eliminar();
    }

    @Override
    public TipoLicencia obtenerPorId(Integer id) {
        this.tipoLicencia = new TipoLicencia();
        this.tipoLicencia.setId_tipo_licencia(id);
        super.obtenerPorId();
        return this.tipoLicencia;
    }

    @Override
    public ArrayList<TipoLicencia> listarTodos() {
        return (ArrayList<TipoLicencia>) super.listarTodos();
    }
}

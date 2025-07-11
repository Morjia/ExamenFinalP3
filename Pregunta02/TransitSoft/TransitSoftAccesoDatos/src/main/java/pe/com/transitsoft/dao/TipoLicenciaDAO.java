/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.transitsoft.dao;

import java.util.ArrayList;
import pe.com.transitsoft.modelo.TipoLicencia;

/**
 *
 * @author Yessica
 */
public interface TipoLicenciaDAO extends ICrud<TipoLicencia>{
    public Integer insertar(TipoLicencia conductor);
    public Integer modificar(TipoLicencia conductor);
    public Integer eliminar(TipoLicencia conductor);
    public TipoLicencia obtenerPorId(Integer idConductor);
    public ArrayList<TipoLicencia> listarTodos();
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.transitsoft.dao;

import java.util.ArrayList;
import pe.com.transitsoft.modelo.ReporteInfraccion;

/**
 *
 * @author Yessica
 */
public interface ReporteInfraccionDAO extends ICrud<ReporteInfraccion>{
    public Integer insertar(ReporteInfraccion conductor);
    public Integer modificar(ReporteInfraccion conductor);
    public Integer eliminar(ReporteInfraccion conductor);
    public ReporteInfraccion obtenerPorId(Integer idConductor);
    public ArrayList<ReporteInfraccion> listarTodos();
}
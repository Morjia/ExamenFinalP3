/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.transitsoft.dao;

import java.util.ArrayList;
import pe.com.transitsoft.modelo.Infraccion;

/**
 *
 * @author Yessica
 */
public interface InfraccionDAO extends ICrud<Infraccion>{
    public Integer insertar(Infraccion conductor);
    public Integer modificar(Infraccion conductor);
    public Integer eliminar(Infraccion conductor);
    public Infraccion obtenerPorId(Integer idConductor);
    public ArrayList<Infraccion> listarTodos();
}

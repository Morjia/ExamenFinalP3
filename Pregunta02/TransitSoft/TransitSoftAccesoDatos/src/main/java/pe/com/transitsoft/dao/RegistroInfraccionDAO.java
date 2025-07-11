/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.transitsoft.dao;

import java.util.ArrayList;
import pe.com.transitsoft.modelo.Infraccion;
import pe.com.transitsoft.modelo.RegistroInfraccion;


/**
 *
 * @author Yessica
 */
public interface RegistroInfraccionDAO extends ICrud<RegistroInfraccion>{
    public Integer insertar(RegistroInfraccion conductor);
    public Integer modificar(RegistroInfraccion conductor);
    public Integer eliminar(RegistroInfraccion conductor);
    public RegistroInfraccion obtenerPorId(Integer idConductor);
    public ArrayList<RegistroInfraccion> listarTodos();
}

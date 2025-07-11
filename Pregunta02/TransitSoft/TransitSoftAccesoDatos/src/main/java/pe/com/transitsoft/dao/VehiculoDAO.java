/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.transitsoft.dao;

import java.util.ArrayList;
import pe.com.transitsoft.modelo.Vehiculo;

/**
 *
 * @author Yessica
 */
public interface VehiculoDAO extends ICrud<Vehiculo>{
    public Integer insertar(Vehiculo conductor);
    public Integer modificar(Vehiculo conductor);
    public Integer eliminar(Vehiculo conductor);
    public Vehiculo obtenerPorId(Integer idConductor);
    public ArrayList<Vehiculo> listarTodos();
}

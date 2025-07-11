/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.transitsoft.bo;

import java.util.ArrayList;
import pe.com.transitsoft.dao.InfraccionDAO;
import pe.com.transitsoft.daoimpl.InfraccionDAOImpl;
import pe.com.transitsoft.modelo.Gravedad;
import pe.com.transitsoft.modelo.Infraccion;

/**
 *
 * @author Yessica
 */
public class InfraccionBO {
    private InfraccionDAO infraccionDAO;
    
    public InfraccionBO() {
        this.infraccionDAO = new InfraccionDAOImpl();
    }

    public Integer insertar(String descripcion,
                             double montoMulta,
                             Gravedad gravedad,
                             Integer puntos) {
        Infraccion dto = new Infraccion();
        dto.setDescripcion(descripcion);
        dto.setMontoMulta(montoMulta);
        dto.setGravedad(gravedad);
        dto.setPuntos(puntos);
        return infraccionDAO.insertar(dto);
    }

    public Infraccion obtenerPorId(Integer idInfraccion) {
        return infraccionDAO.obtenerPorId(idInfraccion);
    }

    public ArrayList<Infraccion> listarTodos() {
        return infraccionDAO.listarTodos();
    }

    public Integer modificar(Integer idInfraccion,
                             String descripcion,
                             double montoMulta,
                             Gravedad gravedad,
                             Integer puntos) {
        Infraccion dto = new Infraccion();
        dto.setInfraccionId(idInfraccion);
        dto.setDescripcion(descripcion);
        dto.setMontoMulta(montoMulta);
        dto.setGravedad(gravedad);
        dto.setPuntos(puntos);
        return infraccionDAO.modificar(dto);
    }

    public Integer eliminar(Integer idInfraccion) {
        Infraccion dto = new Infraccion();
        dto.setInfraccionId(idInfraccion);
        return infraccionDAO.eliminar(dto);
    }

}
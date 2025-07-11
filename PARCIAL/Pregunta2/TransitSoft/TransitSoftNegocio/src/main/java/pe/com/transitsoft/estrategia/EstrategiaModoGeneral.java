package pe.com.transitsoft.estrategia;

import pe.com.transitsoft.dao.IConductorDAO;
import pe.com.transitsoft.daoimpl.ConductorDAOImpl;
import pe.com.transitsoft.modelo.Gravedad;

/**
 *
 * @author eric
 */
public class EstrategiaModoGeneral extends EstrategiaCalculo {
    @Override
    public int calcularPuntos(int idConductor) {
        IConductorDAO conductorDAO = new ConductorDAOImpl();
        int totalPuntos=0;
        
        totalPuntos+=conductorDAO.obtenerPuntos(idConductor, Gravedad.LEVE);
        totalPuntos+=conductorDAO.obtenerPuntos(idConductor, Gravedad.GRAVE);
        totalPuntos+=conductorDAO.obtenerPuntos(idConductor, Gravedad.MUY_GRAVE);
        
        return totalPuntos;
    }
}

package pe.com.transitsoft.estrategia;

import pe.com.transitsoft.dao.IConductorDAO;
import pe.com.transitsoft.daoimpl.ConductorDAOImpl;
import pe.com.transitsoft.modelo.Gravedad;

/**
 *
 * @author eric
 */
public class EstrategiaModoGeneral extends EstrategiaCalculo {
    private IConductorDAO conductorDAO;
    
    public EstrategiaModoGeneral(){
        conductorDAO=new ConductorDAOImpl();
    }
    
    @Override
    public int calcularPuntos(int idConductor) {
        int muyGraves= conductorDAO.obtenerPuntos(idConductor, Gravedad.MUY_GRAVE);
        int grave= conductorDAO.obtenerPuntos(idConductor, Gravedad.GRAVE);
        int leve= conductorDAO.obtenerPuntos(idConductor, Gravedad.LEVE);
         
        return grave + muyGraves+leve;
    }

}

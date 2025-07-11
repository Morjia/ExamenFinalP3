package pe.com.transitsoft.estrategia;

import pe.com.transitsoft.modelo.Gravedad;
import pe.com.transitsoft.daoimpl.ConductorDAOImpl;
import pe.com.transitsoft.dao.IConductorDAO;

/**
 *
 * @author eric
 */
public class EstrategiaGravesYMuyGraves extends EstrategiaCalculo {
    private IConductorDAO conductorDAO;
    
    public EstrategiaGravesYMuyGraves(){
        conductorDAO=new ConductorDAOImpl();
    }
    
    @Override
    public int calcularPuntos(int idConductor) {
        int muyGraves= conductorDAO.obtenerPuntos(idConductor, Gravedad.MUY_GRAVE);
        int grave= conductorDAO.obtenerPuntos(idConductor, Gravedad.GRAVE);
        return grave + muyGraves;
    }


}

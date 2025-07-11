package pe.com.transitsoft.estrategia;

import pe.com.transitsoft.dao.IConductorDAO;
import pe.com.transitsoft.daoimpl.ConductorDAOImpl;
import pe.com.transitsoft.modelo.Gravedad;

/**
 *
 * @author eric
 */
public class EstrategiaSoloMuyGraves extends EstrategiaCalculo {
    private IConductorDAO conductorDAO;
    
    public EstrategiaSoloMuyGraves(){
        conductorDAO=new ConductorDAOImpl();
    }
    
    @Override
    public int calcularPuntos(int idConductor) {
        int grave= conductorDAO.obtenerPuntos(idConductor, Gravedad.GRAVE);
        return grave ;
    }


}

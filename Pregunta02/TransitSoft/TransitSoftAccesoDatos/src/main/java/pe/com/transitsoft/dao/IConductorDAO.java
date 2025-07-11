package pe.com.transitsoft.dao;

import java.util.ArrayList;
import pe.com.transitsoft.modelo.Conductor;
import pe.com.transitsoft.modelo.Gravedad;

/**
 *
 * @author eric
 */
public interface IConductorDAO  extends ICrud<Conductor>{
    int obtenerPuntos(int idConductor, Gravedad gravedad);
    public Integer insertar(Conductor conductor);
    public Integer modificar(Conductor conductor);
    public Integer eliminar(Conductor conductor);
    public Conductor obtenerPorId(Integer idConductor);
    public ArrayList<Conductor> listarTodos();
}

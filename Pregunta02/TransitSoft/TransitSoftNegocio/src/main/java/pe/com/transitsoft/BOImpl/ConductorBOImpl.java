package pe.com.transitsoft.BOImpl;

import java.util.ArrayList;
import pe.com.transitsoft.bo.IConductorBO;
import pe.com.transitsoft.bo.TipoCampanha;
import pe.com.transitsoft.dao.IConductorDAO;
import pe.com.transitsoft.daoimpl.ConductorDAOImpl;
import pe.com.transitsoft.estrategia.Contexto;
import pe.com.transitsoft.modelo.Conductor;

/**
 *
 * @author eric
 */
public class ConductorBOImpl implements IConductorBO {
    private IConductorDAO conductorDAO;
    
    public ConductorBOImpl(){
        this.conductorDAO = new ConductorDAOImpl();
    }
    
    public Conductor insertar(Conductor productosDTO){
        Integer id = this.conductorDAO.insertar(productosDTO);
        productosDTO.setConductorId(id);
        return productosDTO;
    }
    public Conductor obtenerPorId(Integer productoId){
        return this.conductorDAO.obtenerPorId(productoId);
    }
    
    public ArrayList<Conductor> listarTodos(){
        return this.conductorDAO.listarTodos();
    }
    
    public Integer modificar(Conductor productosDTO){        
        return this.conductorDAO.modificar(productosDTO);
    }
    
    public Integer eliminar(Integer productoId){
        Conductor productosDTO = new Conductor();
        productosDTO.setConductorId(productoId);
        return this.conductorDAO.eliminar(productosDTO);
    }
    @Override
    public int calcularPuntos(int idConductor, TipoCampanha campanha) {
        Contexto ctx = new Contexto(campanha);
        return ctx.ejecutar(idConductor);
    }
    
}

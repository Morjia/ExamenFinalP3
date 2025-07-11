package pe.com.transitsoft.accesodatos;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import pe.com.transitsoft.dao.IConductorDAO;
import pe.com.transitsoft.daoimpl.ConductorDAOImpl;
import pe.com.transitsoft.modelo.Gravedad;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private IConductorDAO conductorDAO = new ConductorDAOImpl();
        
    @Test
    public void shouldAnswerWithTrue() {
        int resultado = conductorDAO.obtenerPuntos(0, Gravedad.LEVE);
        
        System.out.println(resultado);
    }
}

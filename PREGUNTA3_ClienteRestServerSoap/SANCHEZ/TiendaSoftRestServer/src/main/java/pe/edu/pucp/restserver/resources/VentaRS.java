package pe.edu.pucp.restserver.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pe.edu.pucp.restserver.persistencia.dao.VentaDAO;
import pe.edu.pucp.restserver.persistencia.daoimpl.VentaDAOImpl;

@Path("ventars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VentaRS {
    private final VentaDAO ventaDAO;
    
    public VentaRS(){
        ventaDAO = new VentaDAOImpl();
    }
}

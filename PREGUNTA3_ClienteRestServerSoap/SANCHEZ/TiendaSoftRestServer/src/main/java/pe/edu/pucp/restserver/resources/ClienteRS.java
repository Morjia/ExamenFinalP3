package pe.edu.pucp.restserver.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pe.edu.pucp.restserver.persistencia.dao.ClienteDAO;
import pe.edu.pucp.restserver.persistencia.daoimpl.ClienteDAOImpl;

@Path("clienters")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteRS {
    private final ClienteDAO clienteDAO;
    
    public ClienteRS(){
        clienteDAO = new ClienteDAOImpl();
    }
}

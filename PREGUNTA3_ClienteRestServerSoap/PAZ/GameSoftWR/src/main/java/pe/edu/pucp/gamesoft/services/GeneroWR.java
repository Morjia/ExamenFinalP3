package pe.edu.pucp.gamesoft.services;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import pe.edu.pucp.gamesoft.GeneroBO;
import pe.edu.pucp.gamesoft.model.Genero;

@Path("GeneroWR")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeneroWR {
    private GeneroBO boGenero;
    
    @GET
    @Path("listarTodos")
    public ArrayList<Genero> listarTodos(){
        boGenero = new GeneroBO();
        return boGenero.listarTodos();
    }

}

package pe.edu.pucp.gamesoft.services;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pe.edu.pucp.gamesoft.VideojuegoBO;
import pe.edu.pucp.gamesoft.model.Videojuego;

@Path("VideojuegoWR")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VideojuegoWR {
    
    private VideojuegoBO boVideojuego;
    
    @POST
    @Path("insertar")
    public int insertar(Videojuego videojuego){
        boVideojuego = new VideojuegoBO();
        return boVideojuego.insertar(videojuego);
    }
    
}

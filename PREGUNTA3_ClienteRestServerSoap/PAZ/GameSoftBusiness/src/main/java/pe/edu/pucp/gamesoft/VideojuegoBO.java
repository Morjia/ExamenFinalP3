package pe.edu.pucp.gamesoft;

import pe.edu.pucp.gamesoft.dao.VideojuegoDAO;
import pe.edu.pucp.gamesoft.model.Videojuego;
import pe.edu.pucp.gamesoft.mysql.VideojuegoImpl;

public class VideojuegoBO {
    private final VideojuegoDAO daoVideojuego;
    public VideojuegoBO(){
        this.daoVideojuego = new VideojuegoImpl();
    }
    public int insertar(Videojuego videojuego){
        return daoVideojuego.insertar(videojuego);
    }
}

package pe.edu.pucp.gamesoft.mysql;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.gamesoft.config.DBManager;
import pe.edu.pucp.gamesoft.dao.VideojuegoDAO;
import pe.edu.pucp.gamesoft.model.Videojuego;

public class VideojuegoImpl implements VideojuegoDAO{

    @Override
    public int insertar(Videojuego videojuego) {
        Map<Integer,Object> parametrosSalida = new HashMap<>();
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, videojuego.getGenero().getIdGenero());
        parametrosEntrada.put(3, videojuego.getCategoria());
        parametrosEntrada.put(4, videojuego.getNombre());
        parametrosEntrada.put(5, videojuego.getFechaLanzamiento());
        parametrosEntrada.put(6, videojuego.getPrecioAproxMercado());
        parametrosEntrada.put(7, videojuego.getNumeroJugadoresMC());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_VIDEOJUEGO", parametrosEntrada, parametrosSalida);
        videojuego.setIdVideojuego((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro del videojuego");
        return videojuego.getIdVideojuego();
    }

    @Override
    public int modificar(Videojuego modelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int eliminar(int idModelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Videojuego> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

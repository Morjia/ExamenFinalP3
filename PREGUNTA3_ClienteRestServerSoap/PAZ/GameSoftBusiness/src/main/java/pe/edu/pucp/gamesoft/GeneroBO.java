package pe.edu.pucp.gamesoft;

import java.util.ArrayList;
import pe.edu.pucp.gamesoft.dao.GeneroDAO;
import pe.edu.pucp.gamesoft.model.Genero;
import pe.edu.pucp.gamesoft.mysql.GeneroImpl;

public class GeneroBO {
    private final GeneroDAO daoGenero;

    public GeneroBO(){
        this.daoGenero = new GeneroImpl();
    }
    public ArrayList<Genero> listarTodos(){
        return daoGenero.listarTodos();
    }
}

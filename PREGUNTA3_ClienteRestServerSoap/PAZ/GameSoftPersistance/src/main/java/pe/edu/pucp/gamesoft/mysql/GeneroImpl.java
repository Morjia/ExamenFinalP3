package pe.edu.pucp.gamesoft.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.gamesoft.config.DBManager;
import pe.edu.pucp.gamesoft.dao.GeneroDAO;
import pe.edu.pucp.gamesoft.model.Genero;

public class GeneroImpl implements GeneroDAO{

    private ResultSet rs;
    
    @Override
    public int insertar(Genero genero) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int modificar(Genero modelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int eliminar(int idModelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Genero> listarTodos() {
        ArrayList<Genero> generos = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_GENEROS", null);
        System.out.println("Lectura de generos...");
        try{
            while(rs.next()){
                if(generos == null) generos = new ArrayList<>();
                Genero genero = new Genero();
                genero.setIdGenero(rs.getInt("id_genero"));
                genero.setDescripcion(rs.getString("descripcion_genero"));
                generos.add(genero);
            }
        }catch(SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return generos;
    }
    
}

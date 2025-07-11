package pe.edu.pucp.restserver.persistencia.dao;

import java.util.List;
import pe.edu.pucp.dbmanager.BaseDAO;
import pe.edu.pucp.dominio.Producto;

public interface ProductoDAO extends BaseDAO<Producto> {
    List<Producto> buscarPorNombre(String nombre);
    List<Producto> listarPorEstado(Producto.EstadoProducto estado);
    void actualizarStock(Integer id, int cantidad);
}

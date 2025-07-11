package pe.edu.pucp.restserver.persistencia.daoimpl;

import pe.edu.pucp.restserver.persistencia.dao.ProductoDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.dbmanager.BaseDAOImpl;
import pe.edu.pucp.dbmanager.DBManager;
import pe.edu.pucp.dominio.Producto;

public class ProductoDAOImpl extends BaseDAOImpl<Producto> implements ProductoDAO {

    @Override
    protected PreparedStatement getInsertPS(Connection conn, Producto producto) throws SQLException {
        String query = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, producto.getNombre());
        ps.setDouble(2, producto.getPrecio());
        ps.setInt(3, producto.getStock());
        return ps;
    }

    @Override
    protected PreparedStatement getUpdatePS(Connection conn, Producto producto) throws SQLException {
        String query = "UPDATE productos SET nombre = ?, precio = ?, stock = ?, estado = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, producto.getNombre());
        ps.setDouble(2, producto.getPrecio());
        ps.setInt(3, producto.getStock());
        ps.setString(4, producto.getEstado().name());
        ps.setInt(5, producto.getId());
        return ps;
    }

    @Override
    protected PreparedStatement getDeletePS(Connection conn, Integer id) throws SQLException {
        String query = "DELETE FROM productos WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement getSelectByIdPS(Connection conn, Integer id) throws SQLException {
        String query = "SELECT * FROM productos WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement getSelectAllPS(Connection conn) throws SQLException {
        String query = "SELECT * FROM productos";
        return conn.prepareStatement(query);
    }

    @Override
    protected Producto createFromResultSet(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getInt("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setPrecio(rs.getDouble("precio"));
        producto.setStock(rs.getInt("stock"));
        producto.setEstado(Producto.EstadoProducto.valueOf(rs.getString("estado")));
        return producto;
    }

    @Override
    protected void setId(Producto producto, Integer id) {
        producto.setId(id);
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        String sql = "SELECT id, nombre, precio, stock, estado FROM productos WHERE nombre LIKE ? AND estado='Activo'";
        List<Producto> productos = new ArrayList<>();

        try (Connection conn = DBManager.getInstance().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    productos.add(createFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar productos por nombre", e);
        }
        return productos;
    }

    @Override
    public List<Producto> listarPorEstado(Producto.EstadoProducto estado) {
        String sql = "SELECT id, nombre, precio, stock, estado FROM productos WHERE estado=?";
        List<Producto> productos = new ArrayList<>();

        try (Connection conn = DBManager.getInstance().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estado.name());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    productos.add(createFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar productos por estado", e);
        }
        return productos;
    }

    @Override
    public void actualizarStock(Integer id, int cantidad) {
        String sql = "UPDATE productos SET stock=stock+? WHERE id=?";
        try (Connection conn = DBManager.getInstance().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cantidad);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar stock", e);
        }
    }
}

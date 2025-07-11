package pe.edu.pucp.restserver.persistencia.daoimpl;

import pe.edu.pucp.restserver.persistencia.dao.VentaDAO;
import java.sql.*;
import java.util.List;
import java.util.Date;
import pe.edu.pucp.dbmanager.BaseDAOImpl;
import pe.edu.pucp.dbmanager.DBManager;
import pe.edu.pucp.dominio.Cliente;
import pe.edu.pucp.dominio.DetalleVenta;
import pe.edu.pucp.dominio.Empleado;
import pe.edu.pucp.dominio.Venta;
import pe.edu.pucp.dominio.Venta.EstadoVenta;
import pe.edu.pucp.restserver.persistencia.dao.VentaDAO;

public class VentaDAOImpl extends BaseDAOImpl<Venta> implements VentaDAO {

    @Override
    protected PreparedStatement getInsertPS(Connection conn, Venta venta) throws SQLException {
        String query = "{CALL sp_registrar_venta(?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, venta.getCliente().getId());
        cs.setInt(2, venta.getEmpleado().getId());
        cs.setDouble(3, venta.getTotal());
        cs.registerOutParameter(4, Types.INTEGER);
        return cs;
    }

    @Override
    protected PreparedStatement getUpdatePS(Connection conn, Venta venta) throws SQLException {
        String query = "{CALL sp_actualizar_estado_venta(?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, venta.getId());
        cs.setString(2, venta.getEstado().name());
        return cs;
    }

    @Override
    protected PreparedStatement getDeletePS(Connection conn, Integer id) throws SQLException {
        String query = "DELETE FROM ventas WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement getSelectByIdPS(Connection conn, Integer id) throws SQLException {
        String query = "SELECT v.id, v.cliente_id, v.empleado_id, v.fecha_venta, v.total, v.estado, " +
                      "pc.nombre as cliente_nombre, pc.apellido as cliente_apellido, " +
                      "pc.dni as cliente_dni, c.direccion_envio, c.ciudad, " +
                      "pe.nombre as empleado_nombre, pe.apellido as empleado_apellido, " +
                      "pe.dni as empleado_dni, e.cargo, e.salario, e.departamento " +
                      "FROM ventas v " +
                      "INNER JOIN clientes c ON v.cliente_id = c.id_cliente " +
                      "INNER JOIN personas pc ON c.id_cliente = pc.id " +
                      "INNER JOIN empleados e ON v.empleado_id = e.id_empleado " +
                      "INNER JOIN personas pe ON e.id_empleado = pe.id " +
                      "WHERE v.estado != 'Cancelada' and v.id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement getSelectAllPS(Connection conn) throws SQLException {
        String query = "SELECT v.id, v.cliente_id, v.empleado_id, v.fecha_venta, v.total, v.estado, " +
                      "pc.nombre as cliente_nombre, pc.apellido as cliente_apellido, " +
                      "pc.dni as cliente_dni, c.direccion_envio, c.ciudad, " +
                      "pe.nombre as empleado_nombre, pe.apellido as empleado_apellido, " +
                      "pe.dni as empleado_dni, e.cargo, e.salario, e.departamento " +
                      "FROM ventas v " +
                      "INNER JOIN clientes c ON v.cliente_id = c.id_cliente " +
                      "INNER JOIN personas pc ON c.id_cliente = pc.id " +
                      "INNER JOIN empleados e ON v.empleado_id = e.id_empleado " +
                      "INNER JOIN personas pe ON e.id_empleado = pe.id " +
                      "WHERE v.estado != 'Cancelada'";
        return conn.prepareStatement(query);
    }

    @Override
    protected Venta createFromResultSet(ResultSet rs) throws SQLException {
        Venta venta = new Venta();
        venta.setId(rs.getInt("id"));
        
        // Crear y setear Cliente
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("cliente_id"));
        cliente.setNombre(rs.getString("cliente_nombre"));
        cliente.setApellido(rs.getString("cliente_apellido"));
        cliente.setDni(rs.getString("cliente_dni"));
        cliente.setDireccionEnvio(rs.getString("direccion_envio"));
        cliente.setCiudad(rs.getString("ciudad"));
        venta.setCliente(cliente);
        
        // Crear y setear Empleado
        Empleado empleado = new Empleado();
        empleado.setId(rs.getInt("empleado_id"));
        empleado.setNombre(rs.getString("empleado_nombre"));
        empleado.setApellido(rs.getString("empleado_apellido"));
        empleado.setDni(rs.getString("empleado_dni"));
        empleado.setCargo(rs.getString("cargo"));
        empleado.setSalario(rs.getDouble("salario"));
        empleado.setDepartamento(rs.getString("departamento"));        
        venta.setEmpleado(empleado);
        
        venta.setFechaVenta(rs.getTimestamp("fecha_venta"));
        venta.setTotal(rs.getDouble("total"));
        venta.setEstado(Venta.EstadoVenta.valueOf(rs.getString("estado")));
        return venta;
    }

    @Override
    protected void setId(Venta venta, Integer id) {
        venta.setId(id);
    }

    @Override
    public void agregar(Venta venta) {
        try (Connection conn = DBManager.getInstance().obtenerConexion()) {
            conn.setAutoCommit(false);
            try {
                // Registrar la venta
                try (CallableStatement cs = (CallableStatement) getInsertPS(conn, venta)) {
                    cs.execute();
                    setId(venta, cs.getInt(4));
                }

                // Registrar los detalles
                registrarDetalles(conn, venta);

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar venta", e);
        }
    }

    private void registrarDetalles(Connection conn, Venta venta) throws SQLException {
        String sp = "{CALL sp_registrar_detalle_venta(?, ?, ?, ?)}";
        try (CallableStatement cs = conn.prepareCall(sp)) {
            for (DetalleVenta detalle : venta.getDetalles()) {
                cs.setInt(1, venta.getId());
                cs.setInt(2, detalle.getProducto().getId());
                cs.setInt(3, detalle.getCantidad());
                cs.setDouble(4, detalle.getPrecioUnitario());
                cs.execute();
            }
        }
    }

    @Override
    public void actualizarEstado(Integer ventaId, Venta.EstadoVenta estado) {
        Venta venta = new Venta();
        venta.setId(ventaId);
        venta.setEstado(estado);
        actualizar(venta);
    }

    @Override
    public List<Venta> listarPorCliente(Integer clienteId) {
        throw new UnsupportedOperationException("Metodo aun no implementado 'listarPorCliente'");
    }

    @Override
    public List<Venta> listarPorEmpleado(Integer empleadoId) {
        throw new UnsupportedOperationException("Metodo aun no implementado 'listarPorEmpleado'");
    }

    @Override
    public List<Venta> listarPorFecha(Date inicio, Date fin) {
        throw new UnsupportedOperationException("Metodo aun no implementado 'listarPorFecha'");
    }

    @Override
    public List<Venta> listarPorEstado(EstadoVenta estado) {
        throw new UnsupportedOperationException("Metodo aun no implementado 'listarPorEstado'");
    }
}
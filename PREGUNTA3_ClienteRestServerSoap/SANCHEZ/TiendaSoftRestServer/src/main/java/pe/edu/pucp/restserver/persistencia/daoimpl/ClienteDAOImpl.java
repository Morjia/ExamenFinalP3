package pe.edu.pucp.restserver.persistencia.daoimpl;

import pe.edu.pucp.restserver.persistencia.dao.ClienteDAO;
import java.sql.*;
import pe.edu.pucp.dbmanager.BaseDAOImpl;
import pe.edu.pucp.dbmanager.DBManager;
import pe.edu.pucp.dominio.Cliente;
import pe.edu.pucp.dominio.Persona;

public class ClienteDAOImpl extends BaseDAOImpl<Cliente> implements ClienteDAO {
    
    @Override
    protected PreparedStatement getInsertPS(Connection conn, Cliente cliente) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO clientes(id_cliente, direccion_envio, ciudad) VALUES(?, ?, ?)"
        );
        ps.setInt(1, cliente.getId());
        ps.setString(2, cliente.getDireccionEnvio());
        ps.setString(3, cliente.getCiudad());
        return ps;
    }

    @Override
    protected PreparedStatement getUpdatePS(Connection conn, Cliente cliente) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "UPDATE clientes SET direccion_envio=?, ciudad=? WHERE id_cliente=?"
        );
        ps.setString(1, cliente.getDireccionEnvio());
        ps.setString(2, cliente.getCiudad());
        ps.setInt(3, cliente.getId());
        return ps;
    }

    @Override
    protected PreparedStatement getDeletePS(Connection conn, Integer id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "UPDATE personas SET estado=0 WHERE id=?"
        );
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement getSelectByIdPS(Connection conn, Integer id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "SELECT p.id, p.nombre, p.apellido, p.dni, p.fecha_registro, p.estado, " +
            "c.direccion_envio, c.ciudad, c.ultima_compra " +
            "FROM personas p INNER JOIN clientes c ON p.id = c.id_cliente " +
            "WHERE p.id=? AND p.estado=1"
        );
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement getSelectAllPS(Connection conn) throws SQLException {
        return conn.prepareStatement(
            "SELECT p.id, p.nombre, p.apellido, p.dni, p.fecha_registro, p.estado, " +
            "c.direccion_envio, c.ciudad, c.ultima_compra " +
            "FROM personas p INNER JOIN clientes c ON p.id = c.id_cliente " +
            "WHERE p.estado=1"
        );
    }

    @Override
    protected Cliente createFromResultSet(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setApellido(rs.getString("apellido"));
        cliente.setDni(rs.getString("dni"));
        cliente.setFechaRegistro(rs.getTimestamp("fecha_registro"));
        cliente.setEstado(rs.getBoolean("estado"));
        cliente.setDireccionEnvio(rs.getString("direccion_envio"));
        cliente.setCiudad(rs.getString("ciudad"));
        cliente.setUltimaCompra(rs.getTimestamp("ultima_compra"));
        return cliente;
    }
    
    @Override
    protected void setId(Cliente cliente, Integer id) {
        cliente.setId(id);
    }
    
    protected PreparedStatement getInsertPSPersona(Connection conn, Persona persona) throws SQLException {
        String query = "INSERT INTO personas(nombre, apellido, dni) VALUES(?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, persona.getNombre());
        ps.setString(2, persona.getApellido());
        ps.setString(3, persona.getDni());
        return ps;
    }    
    
    @Override
    public void agregar(Cliente cliente) {
        try (Connection conn = DBManager.getInstance().obtenerConexion()) {
            conn.setAutoCommit(false);
            try {
                try (PreparedStatement ps = getInsertPSPersona(conn, cliente)) {
                   ps.executeUpdate();
                   try (ResultSet rs = ps.getGeneratedKeys()) {
                       if (rs.next()) {
                            cliente.setId(rs.getInt(1));
                       }
                   }
                }
                 
                try (PreparedStatement ps = getInsertPS(conn, cliente)) {
                    ps.executeUpdate();
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al agregar cliente", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error de conexión al agregar cliente", e);
        }
    }
    
    protected PreparedStatement getUpdatePSPersona(Connection conn, Persona persona) throws SQLException {
        String query = "UPDATE personas SET nombre=?, apellido=?, dni=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, persona.getNombre());
        ps.setString(2, persona.getApellido());
        ps.setString(3, persona.getDni());
        ps.setInt(4, persona.getId());
        return ps;
    }

    @Override
    public void actualizar(Cliente cliente) {
        try (Connection conn = DBManager.getInstance().obtenerConexion()) {
            conn.setAutoCommit(false);
            try {
                try (PreparedStatement ps = getUpdatePSPersona(conn, cliente)) {
                      ps.executeUpdate();
                }

                try (PreparedStatement ps = getUpdatePS(conn, cliente)) {
                    ps.executeUpdate();
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al actualizar cliente", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error de conexión al actualizar cliente", e);
        }
    }
}

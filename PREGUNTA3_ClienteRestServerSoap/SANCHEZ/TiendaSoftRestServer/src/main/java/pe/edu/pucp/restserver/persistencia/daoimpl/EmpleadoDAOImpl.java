package pe.edu.pucp.restserver.persistencia.daoimpl;

import pe.edu.pucp.restserver.persistencia.dao.EmpleadoDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.dbmanager.BaseDAOImpl;
import pe.edu.pucp.dbmanager.DBManager;
import pe.edu.pucp.dominio.Empleado;
import pe.edu.pucp.dominio.Persona;

public class EmpleadoDAOImpl extends BaseDAOImpl<Empleado> implements EmpleadoDAO {
    
    public EmpleadoDAOImpl() {
    }

    @Override
    protected PreparedStatement getInsertPS(Connection conn, Empleado empleado) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO empleados(id_empleado, cargo, salario, departamento, supervisor_id) VALUES(?, ?, ?, ?, ?)"
        );
        ps.setInt(1, empleado.getId());
        ps.setString(2, empleado.getCargo());
        ps.setDouble(3, empleado.getSalario());
        ps.setString(4, empleado.getDepartamento());
        if (empleado.getSupervisor() != null) {
            ps.setInt(5, empleado.getSupervisor().getId());
        } else {
            ps.setNull(5, Types.INTEGER);
        }
        return ps;
    }

    @Override
    protected PreparedStatement getUpdatePS(Connection conn, Empleado empleado) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "UPDATE empleados SET cargo=?, salario=?, departamento=?, supervisor_id=? WHERE id_empleado=?"
        );
        ps.setString(1, empleado.getCargo());
        ps.setDouble(2, empleado.getSalario());
        ps.setString(3, empleado.getDepartamento());
        if (empleado.getSupervisor() != null) {
            ps.setInt(4, empleado.getSupervisor().getId());
        } else {
            ps.setNull(4, Types.INTEGER);
        }
        ps.setInt(5, empleado.getId());
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
            "e.cargo, e.salario, e.departamento, e.supervisor_id " +
            "FROM personas p INNER JOIN empleados e ON p.id = e.id_empleado " +
            "WHERE p.id=? AND p.estado=1"
        );
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement getSelectAllPS(Connection conn) throws SQLException {
        return conn.prepareStatement(
            "SELECT p.id, p.nombre, p.apellido, p.dni, p.fecha_registro, p.estado, " +
            "e.cargo, e.salario, e.departamento, e.supervisor_id " +
            "FROM personas p INNER JOIN empleados e ON p.id = e.id_empleado " +
            "WHERE p.estado=1"
        );
    }

    @Override
    protected Empleado createFromResultSet(ResultSet rs) throws SQLException {
        Empleado empleado = new Empleado();
        empleado.setId(rs.getInt("id"));
        empleado.setNombre(rs.getString("nombre"));
        empleado.setApellido(rs.getString("apellido"));
        empleado.setDni(rs.getString("dni"));
        empleado.setFechaRegistro(rs.getTimestamp("fecha_registro"));
        empleado.setEstado(rs.getBoolean("estado"));
        empleado.setCargo(rs.getString("cargo"));
        empleado.setSalario(rs.getDouble("salario"));
        empleado.setDepartamento(rs.getString("departamento"));
        
        int supervisorId = rs.getInt("supervisor_id");
        if (!rs.wasNull() && supervisorId != empleado.getId()) {
            empleado.setSupervisor(obtener(supervisorId));
        }
        
        return empleado;
    }

    @Override
    protected void setId(Empleado empleado, Integer id) {
        empleado.setId(id);
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
    public void agregar(Empleado empleado) {
        try (Connection conn = DBManager.getInstance().obtenerConexion()) {
            conn.setAutoCommit(false);
            try {
                try (PreparedStatement ps = getInsertPSPersona(conn, empleado)) {
                   ps.executeUpdate();
                   try (ResultSet rs = ps.getGeneratedKeys()) {
                       if (rs.next()) {
                            empleado.setId(rs.getInt(1));
                       }
                   }
                }

                try (PreparedStatement ps = getInsertPS(conn, empleado)) {
                    ps.executeUpdate();
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error al agregar empleado", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error de conexión al agregar empleado", e);
        }
    }

    @Override
    public List<Empleado> listarPorDepartamento(String departamento) {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT p.id, p.nombre, p.apellido, p.dni, p.fecha_registro, p.estado, " +
                    "e.cargo, e.salario, e.departamento, e.supervisor_id " +
                    "FROM personas p INNER JOIN empleados e ON p.id = e.id_empleado " +
                    "WHERE p.estado=1 AND e.departamento=?";
        
        try (Connection conn = DBManager.getInstance().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, departamento);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    empleados.add(createFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar empleados por departamento", e);
        }
        return empleados;
    }

    @Override
    public List<Empleado> listarPorSupervisor(Integer idSupervisor) {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT p.id, p.nombre, p.apellido, p.dni, p.fecha_registro, p.estado, " +
                    "e.cargo, e.salario, e.departamento, e.supervisor_id " +
                    "FROM personas p INNER JOIN empleados e ON p.id = e.id_empleado " +
                    "WHERE p.estado=1 AND e.supervisor_id=?";
        
        try (Connection conn = DBManager.getInstance().obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idSupervisor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    empleados.add(createFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar empleados por supervisor", e);
        }
        return empleados;
    }
}

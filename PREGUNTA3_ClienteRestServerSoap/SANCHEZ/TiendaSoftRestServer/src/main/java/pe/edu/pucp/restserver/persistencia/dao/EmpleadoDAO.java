package pe.edu.pucp.restserver.persistencia.dao;

import java.util.List;
import pe.edu.pucp.dbmanager.BaseDAO;
import pe.edu.pucp.dominio.Empleado;

public interface EmpleadoDAO extends BaseDAO<Empleado> {
    List<Empleado> listarPorDepartamento(String departamento);
    List<Empleado> listarPorSupervisor(Integer idSupervisor);
}

package pe.edu.pucp.restserver.persistencia.dao;
import java.util.List;
import java.util.Date;
import pe.edu.pucp.dbmanager.BaseDAO;
import pe.edu.pucp.dominio.Venta;

public interface VentaDAO extends BaseDAO<Venta> {
    List<Venta> listarPorCliente(Integer clienteId);
    List<Venta> listarPorEmpleado(Integer empleadoId);
    List<Venta> listarPorFecha(Date inicio, Date fin);
    List<Venta> listarPorEstado(Venta.EstadoVenta estado);
    void actualizarEstado(Integer ventaId, Venta.EstadoVenta estado);
}
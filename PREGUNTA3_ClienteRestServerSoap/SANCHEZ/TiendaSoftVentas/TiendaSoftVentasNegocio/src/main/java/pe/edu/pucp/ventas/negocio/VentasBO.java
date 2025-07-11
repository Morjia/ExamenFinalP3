package pe.edu.pucp.ventas.negocio;

import java.util.Date;
import java.util.List;
import pe.edu.pucp.dominio.DetalleVenta;
import pe.edu.pucp.dominio.Producto;
import pe.edu.pucp.dominio.Venta;
import pe.edu.pucp.ventas.restclient.ClienteClient;
import pe.edu.pucp.ventas.restclient.EmpleadoClient;
import pe.edu.pucp.ventas.restclient.ProductoClient;
import pe.edu.pucp.ventas.restclient.VentaClient;

public class VentasBO {

    private final VentaClient ventaDAO;
    private final ClienteClient clienteDAO;
    private final EmpleadoClient empleadoDAO;
    private final ProductoClient productoDAO;
    
    public VentasBO() {
        this.ventaDAO = new VentaClient();
        this.clienteDAO = new ClienteClient();
        this.productoDAO = new ProductoClient();
        this.empleadoDAO = new EmpleadoClient();
    }
    
    public void registrarVenta(Venta venta) throws Exception {
        // Validar que exista el cliente
        if (clienteDAO.obtener(venta.getCliente().getId()) == null) {
            throw new Exception("El cliente no existe");
        }
        
        // Validar que exista el empleado
        if (empleadoDAO.obtener(venta.getEmpleado().getId()) == null) {
            throw new Exception("El empleado no existe");
        }
        
        // Validar stock de productos
        for (DetalleVenta detalle : venta.getDetalles()) {
            Producto producto = productoDAO.obtener(detalle.getProducto().getId());
            if (producto == null) {
                throw new Exception("Producto no encontrado: " + detalle.getProducto().getId());
            }
            if (producto.getStock() < detalle.getCantidad()) {
                throw new Exception("Stock insuficiente para el producto: " + producto.getNombre());
            }
        }
        
        // Registrar la venta
        ventaDAO.agregar(venta);
        
        // Actualizar stock de productos lo realiza el procedimiento almacenado en la base de datos
        // por lo que no es necesario hacerlo aquí.
        /*for (DetalleVenta detalle : venta.getDetalles()) {
            Producto producto = productoDAO.obtener(detalle.getProducto().getId());
            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoDAO.actualizar(producto);
        }*/
    }
    
    public void actualizarVenta(Venta venta) throws Exception {
        if (ventaDAO.obtener(venta.getId()) == null) {
            throw new Exception("La venta no existe");
        }
        
        // Validaciones de negocio
        if (venta.getTotal() <= 0) {
            throw new Exception("El total de la venta debe ser mayor a 0");
        }
        
        ventaDAO.actualizar(venta);
    }
    
    public void eliminarVenta(int idVenta) throws Exception {
        Venta venta = ventaDAO.obtener(idVenta);
        if (venta == null) {
            throw new Exception("La venta no existe");
        }
        if (venta.getEstado() == Venta.EstadoVenta.Completada) {
            throw new Exception("No se puede eliminar una venta completada");
        }
        // Cambiar el estado a Cancelada en lugar de eliminarla físicamente
        ventaDAO.actualizarEstado(idVenta, Venta.EstadoVenta.Cancelada);
    }
    
    public Venta obtenerVenta(int idVenta) throws Exception {
        Venta venta = ventaDAO.obtener(idVenta);
        if (venta == null) {
            throw new Exception("Venta no encontrada");
        }
        return venta;
    }
    
    public List<Venta> listarVentas() throws Exception {
        return ventaDAO.listarTodos();
    }
    
    public List<Venta> buscarVentasPorCliente(Integer clienteId) throws Exception {
        return ventaDAO.listarPorCliente(clienteId);
    }
    
    public List<Venta> buscarVentasPorEmpleado(Integer empleadoId) throws Exception {
        return ventaDAO.listarPorEmpleado(empleadoId);
    }
    
    public List<Venta> buscarVentasPorFecha(Date inicio, Date fin) throws Exception {
        if (inicio == null || fin == null) {
            throw new Exception("Las fechas no pueden ser nulas");
        }
        if (inicio.after(fin)) {
            throw new Exception("La fecha de inicio no puede ser posterior a la fecha fin");
        }
        return ventaDAO.listarPorFecha(inicio, fin);
    }
    
    public List<Venta> buscarVentasPorEstado(Venta.EstadoVenta estado) throws Exception {
        if (estado == null) {
            throw new Exception("El estado no puede ser nulo");
        }
        return ventaDAO.listarPorEstado(estado);
    }
    
    public void actualizarEstadoVenta(Integer ventaId, Venta.EstadoVenta estado) throws Exception {
        if (estado == null) {
            throw new Exception("El estado no puede ser nulo");
        }
        Venta venta = ventaDAO.obtener(ventaId);
        if (venta == null) {
            throw new Exception("La venta no existe");
        }
        if (venta.getEstado() == Venta.EstadoVenta.Cancelada) {
            throw new Exception("No se puede modificar una venta cancelada");
        }
        
        ventaDAO.actualizarEstado(ventaId, estado);
    }
}
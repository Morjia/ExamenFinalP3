package pe.edu.pucp.ventas.negocio;

import java.util.List;
import pe.edu.pucp.dominio.Producto;
import pe.edu.pucp.ventas.restclient.ProductoClient;

public class ProductosBO {
   private final ProductoClient productoDAO;
    
    public ProductosBO() {
        this.productoDAO = new ProductoClient();
    }
    
    public void registrarProducto(Producto producto) throws Exception {
        // Validaciones de negocio
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del producto no puede estar vacío");
        }
        if (producto.getPrecio() <= 0) {
            throw new Exception("El precio debe ser mayor a 0");
        }
        if (producto.getStock() < 0) {
            throw new Exception("El stock no puede ser negativo");
        }
        
        producto.setEstado(Producto.EstadoProducto.Activo);
        productoDAO.agregar(producto);
    }
    
    public void actualizarProducto(Producto producto) throws Exception {
        // Validar que el producto exista
        if (productoDAO.obtener(producto.getId()) == null) {
            throw new Exception("El producto no existe");
        }
        
        // Validaciones de negocio
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del producto no puede estar vacío");
        }
        if (producto.getPrecio() <= 0) {
            throw new Exception("El precio debe ser mayor a 0");
        }
        if (producto.getStock() < 0) {
            throw new Exception("El stock no puede ser negativo");
        }
        
        productoDAO.actualizar(producto);
    }
    
    public void eliminarProducto(int idProducto) throws Exception {
        Producto producto = productoDAO.obtener(idProducto);
        if (producto == null) {
            throw new Exception("El producto no existe");
        }
        
        // En lugar de eliminar físicamente, cambiamos el estado a Inactivo
        producto.setEstado(Producto.EstadoProducto.Inactivo);
        productoDAO.actualizar(producto);
    }
    
    public Producto obtenerProducto(int idProducto) throws Exception {
        Producto producto = productoDAO.obtener(idProducto);
        if (producto == null) {
            throw new Exception("Producto no encontrado");
        }
        return producto;
    }
    
    public List<Producto> listarProductos() throws Exception {
        return productoDAO.listarTodos();
    }
    
    public List<Producto> buscarProductosPorNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre de búsqueda no puede estar vacío");
        }
        return productoDAO.buscarPorNombre(nombre.trim());
    }
    
    public List<Producto> listarProductosPorEstado(Producto.EstadoProducto estado) throws Exception {
        if (estado == null) {
            throw new Exception("El estado no puede ser nulo");
        }
        return productoDAO.listarPorEstado(estado);
    }
    
    public void actualizarStock(Integer idProducto, int cantidad) throws Exception {
        Producto producto = productoDAO.obtener(idProducto);
        if (producto == null) {
            throw new Exception("El producto no existe");
        }
        
        // Validar que el nuevo stock no sea negativo
        int nuevoStock = producto.getStock() + cantidad;
        if (nuevoStock < 0) {
            throw new Exception("No hay suficiente stock disponible");
        }
        
        productoDAO.actualizarStock(idProducto, cantidad);
    }
}
package pe.edu.pucp.ventas.ws;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.edu.pucp.dominio.Producto;
import pe.edu.pucp.ventas.negocio.ProductosBO;


@WebService(serviceName = "ProductoWS", targetNamespace = "pe.edu.pucp.tiendasoft")
public class ProductoWS {
    
    private ProductosBO productoBO;
    
    public ProductoWS(){
        productoBO = new ProductosBO();
    }    
    
    @WebMethod(operationName = "obtenerProducto")
    public Producto obtenerProducto(int idProducto) {
        try {
            return productoBO.obtenerProducto(idProducto);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener producto: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarProductos")
    public List<Producto> listarProductos() {
        try {
            return productoBO.listarProductos();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar productos: " + ex.getMessage());
        }
    }    
    
}

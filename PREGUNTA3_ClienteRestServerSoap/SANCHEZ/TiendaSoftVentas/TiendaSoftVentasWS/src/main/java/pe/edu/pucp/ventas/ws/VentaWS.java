package pe.edu.pucp.ventas.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.edu.pucp.dominio.Venta;
import pe.edu.pucp.ventas.negocio.VentasBO;

@WebService(serviceName = "VentaWS", targetNamespace = "pe.edu.pucp.tiendasoft")
public class VentaWS {
    private final VentasBO ventasBO;
    
    public VentaWS(){
        ventasBO = new VentasBO();
    }
    
    @WebMethod(operationName = "listarVentas")
    public List<Venta> listarVentas() {
        try {
            return ventasBO.listarVentas();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar ventas: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "obtenerVenta") 
    public Venta obtenerVenta(@WebParam(name = "idVenta") int idVenta) {
        try {
            return ventasBO.obtenerVenta(idVenta);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener venta: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "actualizarEstadoVenta")
    public void actualizarEstadoVenta(
            @WebParam(name = "ventaId") int ventaId, 
            @WebParam(name = "estado") Venta.EstadoVenta estado) {
        try {
            ventasBO.actualizarEstadoVenta(ventaId, estado);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar estado de venta: " + ex.getMessage());
        }
    }

    @WebMethod(operationName = "registrarVenta")
    public void registrarVenta(@WebParam(name = "venta") Venta venta) {
        try {
            ventasBO.registrarVenta(venta);
        } catch (Exception ex) {
            throw new WebServiceException("Error al registrar venta: " + ex.getMessage());
        }
    }
}


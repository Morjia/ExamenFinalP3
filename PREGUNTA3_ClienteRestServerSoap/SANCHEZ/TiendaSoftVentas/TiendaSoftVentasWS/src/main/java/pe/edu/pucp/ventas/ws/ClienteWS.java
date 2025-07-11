package pe.edu.pucp.ventas.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.edu.pucp.dominio.Cliente;
import pe.edu.pucp.ventas.negocio.ClientesBO;

@WebService(serviceName = "ClienteWS", targetNamespace = "pe.edu.pucp.tiendasoft")
public class ClienteWS {
    private final ClientesBO clienteBO;
    
    public ClienteWS(){
        clienteBO = new ClientesBO();
    }
    
    @WebMethod(operationName = "listarClientes")
    public List<Cliente> listarClientes() {
        try {
            return clienteBO.listarClientes();
        } catch (Exception ex) {
            throw new WebServiceException("Error al lista clientes "+ex.getMessage()) ;
        }
    }

    @WebMethod(operationName = "obtenerCliente")
    public Cliente obtenerCliente(int idCliente) {
        try {
            return clienteBO.obtenerCliente(idCliente);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener cliente: " + ex.getMessage());
        }
    }
}
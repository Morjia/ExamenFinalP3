package pe.edu.pucp.ventas.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.edu.pucp.dominio.Empleado;
import pe.edu.pucp.ventas.negocio.EmpleadosBO;

@WebService(serviceName = "EmpleadoWS", targetNamespace = "pe.edu.pucp.tiendasoft")
public class EmpleadoWS {
    
    private EmpleadosBO empleadoBO;
    
    public EmpleadoWS(){
        empleadoBO = new EmpleadosBO();
    }
    
    @WebMethod(operationName = "listarEmpleados")
    public List<Empleado> listarEmpleados() {
        try {
            return empleadoBO.listarEmpleados();
        } catch (Exception ex) {              
            throw new WebServiceException("Error al lista empleado "+ex.getMessage()) ;
        }
    }

    @WebMethod(operationName = "eliminarEmpleado")
    public void eliminarEmpleado(int idEmpleado) {
        try {
            empleadoBO.eliminarEmpleado(idEmpleado);
        } catch (Exception ex) {              
            throw new WebServiceException("Error al eliminar empleado: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "obtenerEmpleado")
    public Empleado obtenerEmpleado(int idEmpleado) {
        try {
            return empleadoBO.obtenerEmpleado(idEmpleado);
        } catch (Exception ex) {              
            throw new WebServiceException("Error al eliminar empleado: " + ex.getMessage());
        }
    }
}

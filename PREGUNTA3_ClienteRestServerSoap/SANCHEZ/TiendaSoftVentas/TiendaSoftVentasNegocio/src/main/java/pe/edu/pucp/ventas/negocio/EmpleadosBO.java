package pe.edu.pucp.ventas.negocio;

import java.util.List;
import pe.edu.pucp.dominio.Empleado;
import pe.edu.pucp.ventas.restclient.EmpleadoClient;


public class EmpleadosBO {
   
    private final EmpleadoClient empleadoDAO;
    
    public EmpleadosBO() {
        this.empleadoDAO = new EmpleadoClient();
    }
    
    public void registrarEmpleado(Empleado empleado) throws Exception {
        // Validaciones de negocio
        if (empleado.getDni() == null || empleado.getDni().trim().length() != 8) {
            throw new Exception("El DNI debe tener 8 dígitos");
        }
        if (empleado.getSalario() <= 0) {
            throw new Exception("El salario debe ser mayor a 0");
        }
        if (empleado.getCargo() == null || empleado.getCargo().trim().isEmpty()) {
            throw new Exception("El cargo no puede estar vacío");
        }
        
        empleadoDAO.agregar(empleado);
    }
    
    public void actualizarEmpleado(Empleado empleado) throws Exception {
        if (empleadoDAO.obtener(empleado.getId()) == null) {
            throw new Exception("El empleado no existe");
        }
        
        // Validaciones de negocio
        if (empleado.getSalario() <= 0) {
            throw new Exception("El salario debe ser mayor a 0");
        }
        
        empleadoDAO.actualizar(empleado);
    }
    
    public void eliminarEmpleado(int idEmpleado) throws Exception {
        Empleado empleado = empleadoDAO.obtener(idEmpleado);
        if (empleado == null) {
            throw new Exception("El empleado no existe");
        }
        empleadoDAO.eliminar(idEmpleado);
    }
    
    public Empleado obtenerEmpleado(int idEmpleado) throws Exception {
        Empleado empleado = empleadoDAO.obtener(idEmpleado);
        if (empleado == null) {
            throw new Exception("Empleado no encontrado");
        }
        return empleado;
    }
    
    public List<Empleado> listarEmpleados() throws Exception {
        return empleadoDAO.listarTodos();
    }
}

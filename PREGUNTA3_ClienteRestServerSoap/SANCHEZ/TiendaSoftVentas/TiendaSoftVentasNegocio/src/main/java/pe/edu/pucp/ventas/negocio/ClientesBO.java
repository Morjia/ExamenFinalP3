package pe.edu.pucp.ventas.negocio;

import java.util.List;
import pe.edu.pucp.dominio.Cliente;
import pe.edu.pucp.ventas.restclient.ClienteClient;

public class ClientesBO {
    
    private final ClienteClient clienteDAO;
    
    public ClientesBO() {
        this.clienteDAO = new ClienteClient();
    }
    
    public void registrarCliente(Cliente cliente) throws Exception {
        // Validaciones de negocio
        if (cliente.getDni() == null || cliente.getDni().trim().length() != 8) {
            throw new Exception("El DNI debe tener 8 dígitos");
        }
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre no puede estar vacío");
        }
        if (cliente.getApellido() == null || cliente.getApellido().trim().isEmpty()) {
            throw new Exception("El apellido no puede estar vacío");
        }
        if (cliente.getDireccionEnvio() == null || cliente.getDireccionEnvio().trim().isEmpty()) {
            throw new Exception("La dirección de envío no puede estar vacía");
        }
        
        // Si pasa todas las validaciones, registra el cliente
        clienteDAO.agregar(cliente);
    }
    
    public void actualizarCliente(Cliente cliente) throws Exception {
        // Validar que el cliente exista
        if (clienteDAO.obtener(cliente.getId()) == null) {
            throw new Exception("El cliente no existe");
        }
        
        // Realizar las mismas validaciones que en registro
        if (cliente.getDni() == null || cliente.getDni().trim().length() != 8) {
            throw new Exception("El DNI debe tener 8 dígitos");
        }
        // ... otras validaciones ...
        
        clienteDAO.actualizar(cliente);
    }
    
    public void eliminarCliente(int idCliente) throws Exception {
        Cliente cliente = clienteDAO.obtener(idCliente);
        if (cliente == null) {
            throw new Exception("El cliente no existe");
        }
        clienteDAO.eliminar(idCliente);
    }
    
    public Cliente obtenerCliente(int idCliente) throws Exception {
        Cliente cliente = clienteDAO.obtener(idCliente);
        if (cliente == null) {
            throw new Exception("Cliente no encontrado");
        }
        return cliente;
    }
    
    public List<Cliente> listarClientes() throws Exception {
        return clienteDAO.listarTodos();
    }
}

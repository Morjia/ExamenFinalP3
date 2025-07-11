package pe.edu.pucp.ventas.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.net.http.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import pe.edu.pucp.dominio.Empleado;

public class EmpleadoClient {
    
    private static final String BASE_URL = "http://localhost:8080/TiendaSoftRestServer/api/empleadors";
    private final HttpClient client;
    private final ObjectMapper mapper;
    
    public EmpleadoClient() {
        client = HttpClient.newHttpClient();
        mapper = new ObjectMapper();
    }
    
    public Empleado obtener(int id)  {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + id))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404) {
                return null;
            }       
            return mapper.readValue(response.body(), Empleado.class);
        } catch (IOException | InterruptedException ex) {
            System.getLogger(EmpleadoClient.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

    public void agregar(Empleado empleado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void actualizar(Empleado empleado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void eliminar(int idEmpleado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Empleado> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

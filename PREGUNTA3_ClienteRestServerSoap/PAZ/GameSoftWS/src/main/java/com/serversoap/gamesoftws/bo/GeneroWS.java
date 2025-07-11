package com.serversoap.gamesoftws.bo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.serversoap.gamesoftws.base.RestClient;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.io.IOException;
import java.util.ArrayList;
import pe.edu.pucp.gamesoft.model.Genero;

@WebService(serviceName = "GeneroWS")
public class GeneroWS {
    
    private static final String BASE_URL = "http://localhost:8080/GameSoftWR/webresources/GeneroWR";

    
    @WebMethod(operationName = "listarTodosGenero")
    public ArrayList<Genero> listarTodosGenero() {
        try {
            return RestClient.get(BASE_URL + "/listarTodos",new TypeReference<ArrayList<Genero>>(){});
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error al listar empleados: " + ex.getMessage());
            return null;
        }
    }
}

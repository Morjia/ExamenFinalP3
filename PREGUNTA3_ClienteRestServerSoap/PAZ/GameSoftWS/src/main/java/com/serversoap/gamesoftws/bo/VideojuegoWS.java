package com.serversoap.gamesoftws.bo;

import com.serversoap.gamesoftws.base.RestClient;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import java.io.IOException;
import pe.edu.pucp.gamesoft.model.Videojuego;

@WebService(serviceName = "VideojuegoWS")
public class VideojuegoWS {
    
    private static final String BASE_URL = "http://localhost:8080/GameSoftWR/webresources/VideojuegoWR";
    
    @WebMethod(operationName = "insertarVideojuego")
    public int insertarVideojuego(Videojuego videojuego) {
        try {
            return RestClient.post(BASE_URL + "/insertar",videojuego);
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error al listar empleados: " + ex.getMessage());
            return 0;
        }
    }
}

package com.serversoap.gamesoftbusinessclienterest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class RestClient<T> {
    private final String baseUrl;
    private final String recurso;
    private final Class<T> dtoClass;
    private final TypeReference<List<T>> listTypeRef;
    
    private final ObjectMapper mapper = new ObjectMapper();
    
    private HttpClient client;
    private HttpRequest request;
    private HttpResponse<String> response;
    
    //HTTP

    public RestClient(String baseUrl, String recurso, Class<T> dtoClass, TypeReference<List<T>> listTypeRef) {
        this.baseUrl = baseUrl;
        this.recurso = recurso;
        this.dtoClass = dtoClass;
        this.listTypeRef = listTypeRef;
    }
    
    private void crearHttpClient() {
        this.client = HttpClient.newHttpClient();
    }
    
    private void cerrarHttpClient() {
        client = null;
    }
    
    private void crearRequest(String metodo, String json, Integer id) {
        String url = baseUrl + "/" + recurso;
        if (id != null) url += "/" + id;

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url));

        switch (metodo) {
            case "GET" -> builder.GET();
            case "POST" -> builder
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json));
            case "PUT" -> builder
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json));
            case "DELETE" -> builder.DELETE();
        }

        this.request = builder.build();
    }
    
    private void enviarRequest() throws IOException, InterruptedException {
        this.response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    //JSON
    private String serializar(T dto) throws JsonProcessingException {
        return mapper.writeValueAsString(dto);
    }
    
    private List<T> deserializarLista() throws JsonProcessingException {
        return mapper.readValue(response.body(), listTypeRef);
    }

    private T deserializar() throws JsonProcessingException {
        return mapper.readValue(response.body(), dtoClass);
    }
}

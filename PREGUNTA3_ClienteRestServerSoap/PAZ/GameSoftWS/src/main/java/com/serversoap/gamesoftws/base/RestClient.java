package com.serversoap.gamesoftws.base;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;

public class RestClient {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    // ----------------------
    // MÉTODOS HTTP PÚBLICOS
    // ----------------------

    public static <T> T get(String url, Class<T> responseType) throws IOException, InterruptedException {
        HttpRequest request = buildGetRequest(url);
        return sendAndParse(request, responseType);
    }

    public static <T> T get(String url, TypeReference<T> typeRef) throws IOException, InterruptedException {
        HttpRequest request = buildGetRequest(url);
        return sendAndParse(request, typeRef);
    }

    public static <T> int post(String url, T body) throws IOException, InterruptedException {
        HttpRequest request = buildJsonRequest(url, "POST", body);
        return sendAndParseInt(request);
    }

    public static <T> int put(String url, T body) throws IOException, InterruptedException {
        HttpRequest request = buildJsonRequest(url, "PUT", body);
        return sendAndParseInt(request);
    }

    public static int delete(String url) throws IOException, InterruptedException {
        HttpRequest request = buildDeleteRequest(url);
        return sendAndParseInt(request);
    }

    // --------------------------
    // MÉTODOS AUXILIARES PRIVADOS
    // --------------------------

    private static HttpRequest buildGetRequest(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }

    private static HttpRequest buildDeleteRequest(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
    }

    private static <T> HttpRequest buildJsonRequest(String url, String method, T body) throws IOException {
        String json = mapper.writeValueAsString(body);
        HttpRequest.BodyPublisher publisher = HttpRequest.BodyPublishers.ofString(json);

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json");

        if ("POST".equalsIgnoreCase(method)) {
            builder.POST(publisher);
        } else if ("PUT".equalsIgnoreCase(method)) {
            builder.PUT(publisher);
        }

        return builder.build();
    }
    
    //Envía una solicitud HTTP y devuelve el cuerpo de la respuesta como String.
    private static String sendRequest(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    
    // --------------------------
    // ENVÍO Y PARSEO DE RESPUESTA
    // --------------------------

    // Parsea JSON a objeto (clase concreta)
    private static <T> T sendAndParse(HttpRequest request, Class<T> responseType) throws IOException, InterruptedException {
        String responseBody = sendRequest(request);
        return mapper.readValue(responseBody, responseType);
    }

    // Parsea JSON a tipo genérico (ej. lista)
    private static <T> T sendAndParse(HttpRequest request, TypeReference<T> typeRef) throws IOException, InterruptedException {
        String responseBody = sendRequest(request);
        return mapper.readValue(responseBody, typeRef);
    }

    // Parsea respuesta como entero
    private static int sendAndParseInt(HttpRequest request) throws IOException, InterruptedException {
        String responseBody = sendRequest(request);
        return Integer.parseInt(responseBody);
    }
}


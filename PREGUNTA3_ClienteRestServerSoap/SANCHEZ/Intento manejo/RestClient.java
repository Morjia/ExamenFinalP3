using namespace std;

package com.serversoap.gamesoftws.base;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.List;
import java.util.Map;

public class RestClient {

    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(java.time.Duration.ofSeconds(10))
            .build();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static class RestResponse<T> {
        public int statusCode;
        public Map<String, List<String>> headers;
        public T body;
    }

    public static <T> RestResponse<T> get(String url, Class<T> responseType) throws IOException, InterruptedException {
        HttpRequest request = buildGetRequest(url);
        return sendAndParse(request, responseType);
    }

    public static <T> RestResponse<T> get(String url, TypeReference<T> typeRef) throws IOException, InterruptedException {
        HttpRequest request = buildGetRequest(url);
        return sendAndParse(request, typeRef);
    }

    public static <T> RestResponse<Void> post(String url, T body) throws IOException, InterruptedException {
        HttpRequest request = buildJsonRequest(url, "POST", body);
        return send(request);
    }

    public static <T> RestResponse<Void> put(String url, T body) throws IOException, InterruptedException {
        HttpRequest request = buildJsonRequest(url, "PUT", body);
        return send(request);
    }

    public static RestResponse<Void> delete(String url) throws IOException, InterruptedException {
        HttpRequest request = buildDeleteRequest(url);
        return send(request);
    }

    private static HttpRequest buildGetRequest(String url) {
        return HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
    }

    private static HttpRequest buildDeleteRequest(String url) {
        return HttpRequest.newBuilder().uri(URI.create(url)).DELETE().build();
    }

    private static <T> HttpRequest buildJsonRequest(String url, String method, T body) throws IOException {
        String json = mapper.writeValueAsString(body);
        HttpRequest.BodyPublisher publisher = HttpRequest.BodyPublishers.ofString(json);

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json");

        if (method.equalsIgnoreCase("POST")) {
            builder.POST(publisher);
        } else if (method.equalsIgnoreCase("PUT")) {
            builder.PUT(publisher);
        }
        return builder.build();
    }

    private static <T> RestResponse<T> sendAndParse(HttpRequest request, Class<T> responseType) throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        RestResponse<T> res = new RestResponse<>();
        res.statusCode = response.statusCode();
        res.headers = response.headers().map();

        if (res.statusCode >= 200 && res.statusCode < 300) {
            res.body = mapper.readValue(response.body(), responseType);
        } else {
            res.body = null;
        }
        return res;
    }

    private static <T> RestResponse<T> sendAndParse(HttpRequest request, TypeReference<T> typeRef) throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        RestResponse<T> res = new RestResponse<>();
        res.statusCode = response.statusCode();
        res.headers = response.headers().map();

        if (res.statusCode >= 200 && res.statusCode < 300) {
            res.body = mapper.readValue(response.body(), typeRef);
        } else {
            res.body = null;
        }
        return res;
    }

    private static RestResponse<Void> send(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        RestResponse<Void> res = new RestResponse<>();
        res.statusCode = response.statusCode();
        res.headers = response.headers().map();
        res.body = null;
        return res;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.transitsoft.bo.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import pe.com.transitsoft.modelo.Conductor;
/**
 *
 * @author Yessica
 */

public class ConductorBO {

    private String url;
    private ObjectMapper mapper;
    private HttpClient client;
    private HttpRequest request;
    private HttpResponse<String> response;

    public ConductorBO() {
        this.url = "http://localhost:8080/TransitSoftBusinessServidor/resources/ConductorWR";
        this.mapper = new ObjectMapper();
        this.client = null;
        this.request = null;
        this.response = null;
    }

    private void crearHttpClient() {
        this.client = HttpClient.newHttpClient();
    }

    private void cerrarHttpClient() {
        this.client.close();
    }

    private void crearHttpRequestPOST(String jsonRequest) {
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();
    }

    private void crearHttpRequestGET() {
        Integer productoId = null;
        this.crearHttpRequestGET(productoId);
    }

    private void crearHttpRequestGET(Integer productoId) {
        String urlGET = this.url;
        if (productoId != null) {
            urlGET = urlGET.concat("/" + productoId);
        }

        this.request = HttpRequest.newBuilder()
                .uri(URI.create(urlGET))
                .GET()
                .build();
    }

    private void crearHttpRequestPUT(String jsonRequest) {
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();
    }
    
    private void crearHttpRequestDELETE(Integer productoId) {
        String urlGET = this.url;
        if (productoId != null) {
            urlGET = urlGET.concat("/" + productoId);
        }

        this.request = HttpRequest.newBuilder()
                .uri(URI.create(urlGET))
                .DELETE()
                .build();
    }

    private void enviarRequest() throws IOException, InterruptedException {
        this.response = this.client.send(this.request, HttpResponse.BodyHandlers.ofString());
    }

    private Conductor crearDTO(Integer conductorId, String paterno, String materno,String nombre, String numLicencia, 
            String tipoLicenciaId, Integer puntos) {
        Conductor productosDTO = new Conductor();
        productosDTO.setConductorId(conductorId);
        productosDTO.setPaterno(paterno);
        productosDTO.setMaterno(materno);
        productosDTO.setNombres(nombre);
        productosDTO.setNumLicencia(numLicencia);
        productosDTO.setTipoLicenciaId(tipoLicenciaId);
        productosDTO.setPuntosAcumulados(puntos);
        return productosDTO;
    }

    private String serializar(Conductor productosDTO) throws JsonProcessingException {
        String jsonRequest = this.mapper.writeValueAsString(productosDTO);
        return jsonRequest;
    }

    private Conductor deserializar(Class<Conductor> clase) throws JsonProcessingException {
        Conductor produtosDTORespuesta = mapper.readValue(this.response.body(), clase);
        return produtosDTORespuesta;
    }

    private ArrayList<Conductor> deserializarListaDTO(TypeReference<ArrayList<Conductor>> typeReference) throws JsonProcessingException {
        String json = this.response.body();
        ArrayList<Conductor> listaProductos = this.mapper.readValue(json, typeReference);
        return listaProductos;
    }

    public Integer insertar(String paterno, String materno,String nombre, String numLicencia, 
            String tipoLicenciaId, Integer puntos) throws JsonProcessingException, IOException, InterruptedException {

        Conductor productosDTO = this.crearDTO(null,  paterno,  materno, nombre,  numLicencia, 
             tipoLicenciaId,  puntos);
        String jsonRequest = this.serializar(productosDTO);
        this.crearHttpClient();
        this.crearHttpRequestPOST(jsonRequest);
        this.enviarRequest();
        Conductor produtosDTORespuesta = this.deserializar(Conductor.class);
        this.cerrarHttpClient();

        if (response.statusCode() == Response.Status.CREATED.getStatusCode()) {
            return produtosDTORespuesta.getConductorId();
        }
        return 0;
    }

    public Conductor obtenerPorId(Integer productoId) throws IOException, InterruptedException {
        this.crearHttpClient();
        this.crearHttpRequestGET(productoId);
        this.enviarRequest();
        this.cerrarHttpClient();
        Conductor productoDTORespuesta = this.deserializar(Conductor.class);
        return productoDTORespuesta;
    }

    public ArrayList<Conductor> listarTodos() throws IOException, InterruptedException {
        this.crearHttpClient();
        this.crearHttpRequestGET();
        this.enviarRequest();
        this.cerrarHttpClient();
        ArrayList<Conductor> listaProductos = this.deserializarListaDTO(new TypeReference<ArrayList<ProductosDTO>>() {
        });
        return listaProductos;
    }

    public Integer modificar(Integer productoId, String paterno, String materno,String nombre, String numLicencia, 
            String tipoLicenciaId, Integer puntos) throws JsonProcessingException, IOException, InterruptedException {
        Conductor productosDTO = this.crearDTO(productoId, paterno,  materno, nombre,  numLicencia, 
             tipoLicenciaId,  puntos);
        String jsonRequest = this.serializar(productosDTO);
        this.crearHttpClient();
        this.crearHttpRequestPUT(jsonRequest);
        this.enviarRequest();
        this.cerrarHttpClient();

        if (this.response.statusCode() == Response.Status.OK.getStatusCode()) {
            return productosDTO.getConductorId();
        }
        return 0;
    }

    public Integer eliminar(Integer productoId) throws IOException, InterruptedException {
        this.crearHttpClient();
        this.crearHttpRequestDELETE(productoId);
        this.enviarRequest();
        this.cerrarHttpClient();
        
        if (this.response.statusCode() == Response.Status.NO_CONTENT.getStatusCode()) {
            return productoId;
        }
        return 0;
    }    

}

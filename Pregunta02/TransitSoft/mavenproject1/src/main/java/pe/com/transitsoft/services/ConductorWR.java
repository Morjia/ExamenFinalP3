/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.transitsoft.services;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import pe.com.transitsoft.BOImpl.ConductorBOImpl;
import pe.com.transitsoft.modelo.Conductor;

/**
 *
 * @author Yessica
 */
@Path("ConductorWR")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConductorWR {
    private ConductorBOImpl conductorBO;
    
    public ConductorWR(){
        this.conductorBO= new ConductorBOImpl();
    }
    @GET
    public ArrayList<Conductor> listarTodos() {
        return this.conductorBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Response obtenerPorId(@PathParam("id") Integer productoId) {
        Conductor producto = this.conductorBO.obtenerPorId(productoId);
        if (producto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(producto).build();
    }

    @POST
    public Response insertar(Conductor productosDTO) {
        productosDTO = this.conductorBO.insertar(productosDTO);
        return Response.status(Response.Status.CREATED).entity(productosDTO).build();
    }

    @PUT
    public Response modificar(Conductor productosDTO) {
        Integer respuesta = this.conductorBO.modificar(productosDTO);
        if (respuesta > 0) {
            return Response.ok(productosDTO).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") Integer productoId) {
        Integer respuesta = this.conductorBO.eliminar(productoId);
        if (respuesta > 0) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

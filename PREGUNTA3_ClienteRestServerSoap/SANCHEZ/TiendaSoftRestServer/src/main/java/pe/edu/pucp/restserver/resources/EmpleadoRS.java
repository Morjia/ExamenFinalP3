package pe.edu.pucp.restserver.resources;

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
import java.util.List;
import pe.edu.pucp.dominio.Empleado;
import pe.edu.pucp.restserver.persistencia.dao.EmpleadoDAO;
import pe.edu.pucp.restserver.persistencia.daoimpl.EmpleadoDAOImpl;

@Path("empleadors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmpleadoRS {
    private final EmpleadoDAO empleadoDAO;
    
    public EmpleadoRS(){
        empleadoDAO = new EmpleadoDAOImpl();
    }
    
    @GET
    public Response listarEmpleados(){
        try{
            List<Empleado> resultado = empleadoDAO.listarTodos();
            return Response.ok(resultado).build();
        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al listar empleados")
                    .build();
        }
    }
    
    @GET
    @Path("{id}")
    public Response obtenerEmpleado(@PathParam("id") int id_empleado){
        try{
            Empleado empleado = empleadoDAO.obtener(id_empleado);
            if(empleado == null)
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Empleado no encontrado (bica es poder)").build();
            return Response.ok(empleado).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno (trica es conocimiento)").build();
        }
    }

    @POST
    public Response agregarEmpleado(Empleado empleado) {
        try {
            empleadoDAO.agregar(empleado);
            return Response.status(Response.Status.CREATED)
                    .entity(empleado)
                    .build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al agregar el empleado")
                    .build();
        }
    }

    @PUT
    @Path("{id}")
    public Response actualizarEmpleado(@PathParam("id") int id_empleado, Empleado empleado) {
        try {
            if (empleadoDAO.obtener(id_empleado) == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Empleado no encontrado")
                        .build();
            }
            empleado.setId(id_empleado);
            empleadoDAO.actualizar(empleado);
            return Response.ok(empleado).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al actualizar el empleado")
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response eliminarEmpleado(@PathParam("id") int id_empleado) {
        try {
            if (empleadoDAO.obtener(id_empleado) == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Empleado no encontrado")
                        .build();
            }
            empleadoDAO.eliminar(id_empleado);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al eliminar el empleado")
                    .build();
        }
    }

    @GET
    @Path("departamento/{departamento}")
    public Response listarPorDepartamento(@PathParam("departamento") String departamento) {
        try {
            List<Empleado> resultado = empleadoDAO.listarPorDepartamento(departamento);
            return Response.ok(resultado).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al listar empleados por departamento")
                    .build();
        }
    }

    @GET
    @Path("supervisor/{idSupervisor}")
    public Response listarPorSupervisor(@PathParam("idSupervisor") int idSupervisor) {
        try {
            List<Empleado> resultado = empleadoDAO.listarPorSupervisor(idSupervisor);
            return Response.ok(resultado).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al listar empleados por supervisor")
                    .build();
        }
    }
}

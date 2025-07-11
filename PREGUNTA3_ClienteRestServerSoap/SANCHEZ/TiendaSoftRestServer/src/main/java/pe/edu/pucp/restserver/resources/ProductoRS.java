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
import jakarta.ws.rs.QueryParam;
import java.util.List;
import pe.edu.pucp.dominio.Producto;
import pe.edu.pucp.restserver.persistencia.dao.ProductoDAO;
import pe.edu.pucp.restserver.persistencia.daoimpl.ProductoDAOImpl;

@Path("productors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductoRS {
    private final ProductoDAO productoDAO;
    
    public ProductoRS(){
        productoDAO = new ProductoDAOImpl();
    }
    
    @GET
    public Response listarProductos(){
        try{
            List<Producto> resultado = productoDAO.listarTodos();
            return Response.ok(resultado).build();
        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al listar productos")
                    .build();
        }
    }
    
    
    @GET
    @Path("{id}")
    public Response getProducto(@PathParam("id")int id_producto){
        try{
            Producto producto = productoDAO.obtener(id_producto);
            return Response.ok(producto).build();
        }catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener producto")
                    .build();
        }
    }

    @POST
    public Response agregarProducto(Producto producto) {
        try {
            productoDAO.agregar(producto);
            return Response.status(Response.Status.CREATED)
                    .entity(producto)
                    .build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al agregar el producto")
                    .build();
        }
    }

    @PUT
    @Path("{id}")
    public Response actualizarProducto(@PathParam("id") int id_producto, Producto producto) {
        try {
            if (productoDAO.obtener(id_producto) == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Producto no encontrado")
                        .build();
            }
            producto.setId(id_producto);
            productoDAO.actualizar(producto);
            return Response.ok(producto).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al actualizar el producto")
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response eliminarProducto(@PathParam("id") int id_producto) {
        try {
            if (productoDAO.obtener(id_producto) == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Producto no encontrado")
                        .build();
            }
            productoDAO.eliminar(id_producto);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al eliminar el producto")
                    .build();
        }
    }

    @GET
    @Path("buscar")
    public Response buscarPorNombre(@QueryParam("nombre") String nombre) {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("El nombre de búsqueda no puede estar vacío")
                        .build();
            }
            List<Producto> resultado = productoDAO.buscarPorNombre(nombre);
            return Response.ok(resultado).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al buscar productos por nombre")
                    .build();
        }
    }

    @GET
    @Path("estado/{estado}")
    public Response listarPorEstado(@PathParam("estado") String estado) {
        try {
            Producto.EstadoProducto estadoProducto = Producto.EstadoProducto.valueOf(estado.toUpperCase());
            List<Producto> resultado = productoDAO.listarPorEstado(estadoProducto);
            return Response.ok(resultado).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Estado de producto no válido")
                    .build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al listar productos por estado")
                    .build();
        }
    }

    @PUT
    @Path("stock")
    public Response actualizarStock(
            @QueryParam("id") Integer id,
            @QueryParam("cantidad") int cantidad) {
        try {
            if (productoDAO.obtener(id) == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Producto no encontrado")
                        .build();
            }
            productoDAO.actualizarStock(id, cantidad);
            return Response.ok()
                    .entity("Stock actualizado correctamente")
                    .build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al actualizar el stock del producto")
                    .build();
        }
    }
}

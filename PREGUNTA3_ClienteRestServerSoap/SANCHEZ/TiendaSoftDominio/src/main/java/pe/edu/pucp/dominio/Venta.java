package pe.edu.pucp.dominio;


import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    private Integer id;
    private Cliente cliente;
    private Empleado empleado;
    private Date fechaVenta;
    private double total;
    private EstadoVenta estado;
    private List<DetalleVenta> detalles;

    public enum EstadoVenta {
        Pendiente, Completada, Cancelada
    }

    public Venta() {
        detalles = new ArrayList<>();
        estado = EstadoVenta.Pendiente;
    }

    public Venta(Cliente cliente, Empleado empleado) {
        this.cliente = cliente;
        this.empleado = empleado;
        this.fechaVenta = new Date(); // Fecha actual
        this.total = 0.0;
        this.estado = EstadoVenta.Pendiente;
        detalles = new ArrayList<>();
    }

    public Venta(Cliente cliente, Empleado empleado, Date fechaVenta, double total, EstadoVenta estado) {
        this.cliente = cliente;
        this.empleado = empleado;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.estado = estado;
        detalles = new ArrayList<>();
    }

    // Completar todos los Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public EstadoVenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoVenta estado) {
        this.estado = estado;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    public void agregarDetalle(DetalleVenta detalle) {
        detalles.add(detalle);
        total += detalle.getSubtotal(); // Actualiza el total al agregar un detalle
    }

    public void eliminarDetalle(DetalleVenta detalle) {
        detalles.remove(detalle);
        total -= detalle.getSubtotal(); // Actualiza el total al eliminar un detalle
    }

    public void limpiarDetalles() {
        detalles.clear();
        total = 0.0; // Reinicia el total al limpiar los detalles
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", empleado=" + empleado +
                ", fechaVenta=" + fechaVenta +
                ", total=" + total +
                ", estado=" + estado +
                ", detalles=" + detalles +
                '}';
    }

}
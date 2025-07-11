package pe.edu.pucp.dominio;


public class Producto {
    private Integer id;
    private String nombre;
    private Double precio;
    private int stock;
    private EstadoProducto estado;
    
    public enum EstadoProducto {
        Activo, Inactivo, Descontinuado
    }
    
    public Producto() {}
    
    public Producto(String nombre, Double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.estado = EstadoProducto.Activo;
    }
    
    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    
    public EstadoProducto getEstado() { return estado; }
    public void setEstado(EstadoProducto estado) { this.estado = estado; }
    
    @Override
    public String toString() {
        return "Producto{" + 
               "id=" + id + 
               ", nombre=" + nombre + 
               ", precio=" + precio + 
               ", stock=" + stock + 
               ", estado=" + estado + '}';
    }
}

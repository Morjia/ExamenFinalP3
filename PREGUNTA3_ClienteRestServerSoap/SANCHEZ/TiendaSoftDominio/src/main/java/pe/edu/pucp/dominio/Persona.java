package pe.edu.pucp.dominio;

import java.util.Date;

public class Persona {
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fechaRegistro;
    private boolean estado;
    
    public Persona() {}
    
    public Persona(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.estado = true;
    }
    
    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    
    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    
    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }
    
    @Override
    public String toString() {
        return "id=" + id + 
               ", nombre=" + nombre + 
               ", apellido=" + apellido + 
               ", dni=" + dni + 
               ", fechaRegistro=" + fechaRegistro + 
               ", estado=" + estado;
    }
}
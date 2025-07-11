package pe.edu.pucp.dominio;

import java.util.Date;

public class Cliente extends Persona {
    private String direccionEnvio;
    private String ciudad;
    private Date ultimaCompra;
    
    public Cliente() {
        super();
    }
    
    public Cliente(String nombre, String apellido, String dni, String direccionEnvio, String ciudad) {
        super(nombre, apellido, dni);
        this.direccionEnvio = direccionEnvio;
        this.ciudad = ciudad;
    }
    
    public String getDireccionEnvio() {
        return direccionEnvio;
    }
    
    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }
    
    public String getCiudad() {
        return ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public Date getUltimaCompra() {
        return ultimaCompra;
    }
    
    public void setUltimaCompra(Date ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }

    @Override
    public String toString() {
        return "Cliente{" + super.toString() + 
               ", direccionEnvio=" + direccionEnvio + 
               ", ciudad=" + ciudad + 
               ", ultimaCompra=" + ultimaCompra + '}';
    }
}
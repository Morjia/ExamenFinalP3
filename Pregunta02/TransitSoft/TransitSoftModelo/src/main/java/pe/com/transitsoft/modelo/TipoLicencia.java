/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.transitsoft.modelo;

/**
 *
 * @author Yessica
 */
public class TipoLicencia {
    private int id_tipo_licencia;
    private String nombre;
    private String descripcion;
    
    public TipoLicencia(){
    }

    public TipoLicencia(int id_tipo_licencia, String nombre, String descripcion){
        this.id_tipo_licencia=id_tipo_licencia;
        this.nombre=nombre;
        this.descripcion=descripcion;
    }

    public int getId_tipo_licencia() {
        return id_tipo_licencia;
    }

    public void setId_tipo_licencia(int id_tipo_licencia) {
        this.id_tipo_licencia = id_tipo_licencia;
    }

    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDescripcion() {
        return descripcion;
    }

 
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}

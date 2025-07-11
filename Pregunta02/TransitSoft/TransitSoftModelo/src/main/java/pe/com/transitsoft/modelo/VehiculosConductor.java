/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.transitsoft.modelo;

import java.util.Date;

/**
 *
 * @author Yessica
 */
public class VehiculosConductor {
    private Vehiculo vehiculo;
    private Conductor conductor;
    private Date fecha_adquisicion;

    public VehiculosConductor(){
    }
    
    public VehiculosConductor(Vehiculo vehiculo, Conductor conductor, Date fecha){
        this.vehiculo=vehiculo;
        this.conductor=conductor;
        this.fecha_adquisicion=fecha;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }


    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

 
    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }


    public Date getFecha_adquisicion() {
        return fecha_adquisicion;
    }


    public void setFecha_adquisicion(Date fecha_adquisicion) {
        this.fecha_adquisicion = fecha_adquisicion;
    }
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.transitsoft.modelo;

/**
 *
 * @author Yessica
 */
public class Vehiculo {
    private int id_vehiculo;
    private String placa;
    private String marca;
    private String modelo;
    private Integer anho;

    public Vehiculo() {
    }

    public Vehiculo(Integer vehiculoId, String placa, String marca, String modelo, Integer anho) {
        this.id_vehiculo = vehiculoId;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anho = anho;
    }

    public Integer getVehiculoId() {
        return id_vehiculo;
    }

    public void setVehiculoId(Integer vehiculoId) {
        this.id_vehiculo = vehiculoId;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnho() {
        return anho;
    }

    public void setAnho(Integer anho) {
        this.anho = anho;
    }

    
}

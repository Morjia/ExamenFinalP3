package pe.edu.pucp.gamesoft.model;

import jakarta.json.bind.annotation.JsonbTypeAdapter;
import java.util.Date;

public class Videojuego {
    private int idVideojuego;
    private Genero genero;
    private char categoria;
    private String nombre;
    @JsonbTypeAdapter(DateAdapter.class)
    private Date fechaLanzamiento;
    private double precioAproxMercado;
    private int numeroJugadoresMC;

    public Videojuego(int idVideojuego, Genero genero, char categoria, String nombre, Date fechaLanzamiento, double precioAproxMercado, int numeroJugadoresMC) {
        this.idVideojuego = idVideojuego;
        this.genero = genero;
        this.categoria = categoria;
        this.nombre = nombre;
        this.fechaLanzamiento = fechaLanzamiento;
        this.precioAproxMercado = precioAproxMercado;
        this.numeroJugadoresMC = numeroJugadoresMC;
    }

    public int getIdVideojuego() {
        return idVideojuego;
    }
    
    public void setIdVideojuego(int idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public char getCategoria() {
        return categoria;
    }

    public void setCategoria(char categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public double getPrecioAproxMercado() {
        return precioAproxMercado;
    }

    public void setPrecioAproxMercado(double precioAproxMercado) {
        this.precioAproxMercado = precioAproxMercado;
    }

    public int getNumeroJugadoresMC() {
        return numeroJugadoresMC;
    }

    public void setNumeroJugadoresMC(int numeroJugadoresMC) {
        this.numeroJugadoresMC = numeroJugadoresMC;
    }

    
}

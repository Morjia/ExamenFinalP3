/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.transitsoft.daoimpl;

import java.util.ArrayList;
import pe.com.transitsoft.dao.VehiculoDAO;
import pe.com.transitsoft.modelo.Vehiculo;
import pe.com.transitsoft.daoimpl.util.Columna;
import pe.com.transitsoft.daoimpl.util.Tipo_Dato;
/**
 *
 * @author Yessica
 */

public class VehiculoDAOImpl extends DAOImplBase implements VehiculoDAO {
    private Vehiculo vehiculo;

    public VehiculoDAOImpl() {
        super("EX1_VEHICULOS");
        // La PK es auto-generada
        this.retornarLlavePrimaria = true;
        this.vehiculo = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        // Columna(nombre, tipo, esPK, esAutoIncremental)
        this.listaColumnas.add(new Columna("VEHICULO_ID", Tipo_Dato.ENTERO, true,  true));
        this.listaColumnas.add(new Columna("PLACA",       Tipo_Dato.CADENA,  false, false));
        this.listaColumnas.add(new Columna("MARCA",       Tipo_Dato.CADENA,  false, false));
        this.listaColumnas.add(new Columna("MODELO",      Tipo_Dato.CADENA,  false, false));
        this.listaColumnas.add(new Columna("ANHO",        Tipo_Dato.ENTERO,  false, false));
    }

    @Override
    public Integer insertar(Vehiculo dto) {
        this.vehiculo = dto;
        return super.insertar();
    }

    @Override
    public Integer modificar(Vehiculo dto) {
        this.vehiculo = dto;
        return super.modificar();
    }

    @Override
    public Integer eliminar(Vehiculo dto) {
        this.vehiculo = dto;
        return super.eliminar();
    }

    @Override
    public Vehiculo obtenerPorId(Integer id) {
        this.vehiculo = new Vehiculo();
        this.vehiculo.setVehiculoId(id);
        super.obtenerPorId();
        return this.vehiculo;
    }

    @Override
    public ArrayList<Vehiculo> listarTodos() {
        return (ArrayList<Vehiculo>) super.listarTodos();
    }
}


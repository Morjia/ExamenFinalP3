package pe.com.transitsoft.dao;

import java.util.ArrayList;

public interface ICrud<T> {
    public Integer insertar(T modelo);
    
    public Integer modificar(T modelo);
    
    public Integer eliminar(T modelo);
    
    public T obtenerPorId(Integer idModelo);
    
    public ArrayList<T> listarTodos();
    
}

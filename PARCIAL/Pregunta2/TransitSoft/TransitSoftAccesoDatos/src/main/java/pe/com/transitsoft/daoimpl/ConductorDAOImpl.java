package pe.com.transitsoft.daoimpl;

import pe.com.transitsoft.daoimplBase.DAOImplBase;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;
import pe.com.transitsoft.dao.IConductorDAO;
import pe.com.transitsoft.modelo.Gravedad;


public class ConductorDAOImpl extends DAOImplBase implements IConductorDAO{

    public ConductorDAOImpl() {
        super("EX1_CONDUCTORES"); // O el nombre real de la tabla
    }

    @Override
    protected void configurarListaDeColumnas() {
        // Si no usas columnas, deja vacío
        this.listaColumnas.clear();
    }
    
    @Override
    public int obtenerPuntos(int idConductor, Gravedad gravedad) {
        String sql = "{CALL preg2_obtenerPuntosConductor(?,?,?)}";
        Consumer<CallableStatement> parametros = stmt -> {
            try{
                stmt.setInt(1, idConductor);
                stmt.setString(2, gravedad.name());
            }catch (SQLException ex) {
                System.err.println("Error al incluir parámetro en listarPorNombre: " + ex.getMessage());
            }
        };
        
        Object resultado = ejecutarProcedimientoConParametroOut(sql, parametros, java.sql.Types.INTEGER, 3);
        return (int) resultado;
    }
}
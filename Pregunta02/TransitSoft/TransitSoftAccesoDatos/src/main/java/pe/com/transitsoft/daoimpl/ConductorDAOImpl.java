package pe.com.transitsoft.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.com.transitsoft.dao.IConductorDAO;
import pe.com.transitsoft.modelo.Conductor;
import pe.com.transitsoft.modelo.Gravedad;
import pe.com.transitsoft.daoimpl.DAOImplBase;
import pe.com.transitsoft.daoimpl.util.Columna;
import pe.com.transitsoft.daoimpl.util.Tipo_Dato;

/**
 *
 * @author eric
 */
public class ConductorDAOImpl extends DAOImplBase implements IConductorDAO {
    
    private Conductor conductor;
    
    public ConductorDAOImpl(){
        super("EX1_CONDUCTORES");
        this.retornarLlavePrimaria=true;
        this.conductor=null;
    }
    
    @Override
    public int obtenerPuntos(int idConductor, Gravedad gravedad) {
        int totalPuntos=0;
        try{
            this.abrirConexion();
            this.statement = configurarComandoObtenerPuntos(conexion, idConductor, gravedad);
            
            ejecutarConsultaEnBD();
            if(resultSet.next())
                totalPuntos=resultSet.getInt("TOTAL_PUNTOS");
            
        }catch(SQLException ex){
            System.err.println("Error en obtener puntos : " + ex);
            rollbackSeguro();
        }finally{
            this.cerrarConexionSegura();
        }
        return totalPuntos;
    }
    
    private CallableStatement configurarComandoObtenerPuntos(
            Connection conn, int id, Gravedad gravedad) throws SQLException {
        String sql =
        "SELECT COALESCE(SUM(i.PUNTOS),0) AS TOTAL_PUNTOS " +
        "FROM EX1_REGISTRO_INFRACCIONES r " +
        " JOIN EX1_INFRACCIONES i " +
        "   ON r.INFRACCION_ID = i.INFRACCION_ID " +
        "WHERE r.CONDUCTOR_ID = ? " +
        "  AND i.GRAVEDAD = ?";
        
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        cs.setString(2, gravedad.name());
        return cs;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("CONDUCTOR_ID", Tipo_Dato.ENTERO, true, true));
        this.listaColumnas.add(new Columna("PATERNO", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("MATERNO", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("NUM_LICENCIA", Tipo_Dato.CADENA, false, false));
        this.listaColumnas.add(new Columna("TIPO_LICENCIA", Tipo_Dato.ENTERO, false, false));
        this.listaColumnas.add(new Columna("PUNTOS_ACUMULADOS", Tipo_Dato.ENTERO, false, false));
    }
    @Override
    public Integer insertar(Conductor conductor) {
        this.conductor = conductor;
        return super.insertar();
    }

    @Override
    public Integer modificar(Conductor conductor) {
        this.conductor = conductor;
        return super.modificar();
    }

    @Override
    public Integer eliminar(Conductor conductor) {
        this.conductor = conductor;
        return super.eliminar();
    }

    @Override
    public Conductor obtenerPorId(Integer idConductor) {
        this.conductor= new Conductor();
        this.conductor.setConductorId(idConductor);
        super.obtenerPorId();
        return this.conductor;
    }
    
    @Override
    public ArrayList<Conductor> listarTodos() {
        return (ArrayList<Conductor>) super.listarTodos();
    }


}
package pe.com.transitsoft.config;

import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import pe.com.transitsoft.config.db.DBManager;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
    
    @org.junit.jupiter.api.Test
    public void testGetInstance() {
        System.out.println("getInstance");                
        DBManager dBManager = DBManager.getInstance();
        assertNotNull(dBManager);
    }

    @org.junit.jupiter.api.Test
    public void testGetConnection() {
        System.out.println("getConnection");                
        DBManager dBManager = DBManager.getInstance();
        Connection conexion = dBManager.getConnection();
        assertNotNull(conexion);
    }
}

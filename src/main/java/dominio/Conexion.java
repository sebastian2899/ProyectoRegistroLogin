package dominio;

import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {
    
    private static BasicDataSource dataSoruce;
    private static final String JBDC_URL  = "jdbc:mysql://localhost/persona?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "admin";
    
    public static DataSource getDataSource(){
        if (dataSoruce==null) {
            dataSoruce = new BasicDataSource();
            dataSoruce.setUrl(JBDC_URL);
            dataSoruce.setUsername(JDBC_USER);
            dataSoruce.setPassword(JDBC_PASS);
            dataSoruce.setInitialSize(10);
            
        }
        return dataSoruce;
    }
    
    public static Connection getConexion() throws SQLException{
        return getDataSource().getConnection();
    }
    
    public static void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
    }
    public static void close(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
    }
    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
    }
    
}

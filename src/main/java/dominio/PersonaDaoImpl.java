package dominio;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaDaoImpl implements validar {

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM persona WHERE nombre=? AND email=?";
    private static final String SQL_INSERT = "INSERT INTO persona (nombre,email) VALUES (?,?)";

    @Override
    public int validar(Persona persona) {
        int r = 0;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);

            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getEmail());
            rs = stmt.executeQuery();

            while (rs.next()) {
                r = +1;
                persona.setNombre(rs.getString("nombre"));
                persona.setEmail(rs.getString("email"));
            }

            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return 0;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

    }

    @Override
    public void insertar(Persona persona) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getEmail());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }

    }

}

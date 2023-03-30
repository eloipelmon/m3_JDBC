package monlau.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.*;
import monlau.model.Products;

/**
 *
 * @author user
 */
public class ProductoDAOImpl implements ProductoDAO {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/m3springboot?useSSL=false";
    static final String DB_USR = "root";
    static final String DB_PWD = "";

    /**
     * Load the driver class
     */
    private void registerDriver() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            //System.out.println("ERROR: failed to load MySQL JDBC Driver");
            System.err.println("ERROR: failed to load MySQL JDBC Driver"); // imprime en la salida standar de error
            ex.printStackTrace();
        }
    }

    public void insert(Products product) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexión
            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
            Statement stmt = conn.createStatement();
            // enviar el comando insert
            stmt.executeUpdate("insert into products values ("
                    + product.getId() + ",'"
                    + product.getName() + "',"
                    + product.getPrice() + ");");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void update(Products product) {

    }

    public void delete(Products product) {

    }

    public Products read(Integer id) {
        Connection conn = null;
        Products prod = null;
        try {
            registerDriver();
            // abrir la conexión
            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
            // consulta select (selecciona el producto con id especificado)
            try ( PreparedStatement ps = conn.prepareStatement("select * from products where id = ?")) {
                //indicar el id que buscamos
                ps.setInt(1, id);
                //ejecutar la consulta
                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // obtener cada una de las columnas y mapearlas a la clase Product
                        prod = new Products(id, rs.getString("name"), rs.getDouble("price"));
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return prod;
    }

}

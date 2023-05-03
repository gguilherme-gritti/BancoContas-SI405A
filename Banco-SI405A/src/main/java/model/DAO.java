package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author g247468
 */
public abstract class DAO {

    public static final String DBURL = "jdbc:sqlite:veterinaria-v3.db";
    private static Connection con;
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Connect to SQLite
    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(DBURL);
                if (con != null) {
                    DatabaseMetaData meta = con.getMetaData();
                }
            } catch (SQLException e) {
                System.err.println("Exception: " + e.getMessage());
            }
        }
        return con;
    }

    protected ResultSet getResultSet(String query) {
        Statement s;
        ResultSet rs = null;
        try {
            s = (Statement) con.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return rs;
    }

    protected int executeUpdate(PreparedStatement queryStatement) throws SQLException {
        int update;
        update = queryStatement.executeUpdate();
        return update;
    }

    protected int lastId(String tableName, String primaryKey) {
        Statement s;
        int lastId = -1;
        try {
            s = (Statement) con.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(" + primaryKey + ") AS id FROM " + tableName);
            if (rs.next()) {
                lastId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return lastId;
    }

    public static void terminar() {
        try {
            (DAO.getConnection()).close();
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    protected final boolean createTable() {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Cliente( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n"
                    + "cpf VARCHAR, \n"
                    + "data_nascimento VARCHAR); \n");
            executeUpdate(stmt);

            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Conta( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "id_cliente INTEGER, \n"
                    + "data_abertura VARCHAR, \n"
                    + "saldo FLOAT, \n"
                    + "limite FLOAT, \n"
                    + "limite_credito FLOAT, \n"
                    + "tipo CHAR, \n"
                    + "data_nascimento VARCHAR); \n");
            executeUpdate(stmt);
            
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Movimentacao( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "id_conta INTEGER, \n"
                    + "id_conta_transf INTEGER, \n"
                    + "tipo CHAR, \n"
                    + "valor FLOAT); \n");
            executeUpdate(stmt);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}

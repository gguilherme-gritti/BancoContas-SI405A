package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.DAO.getConnection;

/**
 *
 * @author GuilhermeGoulartGrit
 */
public class ContaDAO extends DAO {

    private static ContaDAO instance;

    private ContaDAO() {
        getConnection();
        createTable();
    }

    public static ContaDAO getInstance() {
        return (instance == null ? (instance = new ContaDAO()) : instance);
    }

    public Conta create(
            int id_cliente,
            String data_abertura,
            float saldo,
            float limite,
            float limite_credito,
            String tipo,
            int data_aniv
    ) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO Conta (id_cliente, data_abertura, saldo, limite, limite_credito, "
                    + "tipo, data_aniv) VALUES (?,?,?,?,?,?,?)");
            stmt.setInt(1, id_cliente);
            stmt.setString(2, data_abertura);
            stmt.setFloat(3, saldo);
            stmt.setFloat(4, limite);
            stmt.setFloat(5, limite_credito);
            stmt.setString(6, tipo);
            stmt.setInt(7, data_aniv);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("Conta", "id"));
    }

    public boolean isLastEmpty() {
        Conta lastAccount = this.retrieveById(lastId("Conta", "id"));
        if (lastAccount != null) {
            return lastAccount.getData_abertura().isBlank();
        }
        return false;
    }

    private Conta buildObject(ResultSet rs) {
        Conta conta = null;
        try {
            conta = new Conta(
                    rs.getInt("id"),
                    rs.getInt("id_cliente"),
                    rs.getString("data_abertura"),
                    rs.getFloat("saldo"),
                    rs.getFloat("limite"),
                    rs.getFloat("limite_credito"),
                    rs.getString("tipo"),
                    rs.getInt("data_aniv")
            );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return conta;
    }

    public List retrieve(String query) {
        List<Conta> contas = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                contas.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return contas;
    }

    public List retrieveAll() {
        return this.retrieve("SELECT * FROM Conta");
    }

    public List retrieveLast() {
        return this.retrieve("SELECT * FROM Conta WHERE id = " + lastId("Conta", "id"));
    }

    public Conta retrieveById(int id) {
        List<Conta> contas = this.retrieve("SELECT * FROM Conta WHERE id = " + id);
        return (contas.isEmpty() ? null : contas.get(0));
    }
    
    public List retrieveByClienteId(int id){
        return this.retrieve("SELECT * FROM Conta WHERE id_cliente = " + id);
    }

    public void update(Conta conta) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE Conta SET saldo=?, limite=?, limite_credito=?"
                    + " WHERE id=?");
            stmt.setFloat(1, conta.getSaldo());
            stmt.setFloat(2, conta.getLimite());
            stmt.setFloat(3, conta.getLimite_credito());
            stmt.setInt(4, conta.getId());

            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void delete(Conta conta) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM Conta WHERE id = ?");
            stmt.setInt(1, conta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

}

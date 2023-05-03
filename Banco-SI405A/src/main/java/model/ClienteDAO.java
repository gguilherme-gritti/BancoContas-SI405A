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
public class ClienteDAO extends DAO {
    private static ClienteDAO instance;

    private ClienteDAO() {
        getConnection();
        createTable();
    }

    public static ClienteDAO getInstance() {
        return (instance == null ? (instance = new ClienteDAO()) : instance);
    }

    public Cliente create(
        String nome,
        String cpf,
        String data_nascimento
    ) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO Cliente (nome, cpf, data_nascimento) VALUES (?,?,?)");
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, data_nascimento);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("Cliente", "id"));
    }

    public boolean isLastEmpty() {
        Cliente lastClient = this.retrieveById(lastId("Cliente", "id_cli"));
        if (lastClient != null) {
            return lastClient.getNome().isBlank();
        }
        return false;
    }

    private Cliente buildObject(ResultSet rs) {
        Cliente cliente = null;
        try {
            cliente = new Cliente(
                    rs.getInt("id"), 
                    rs.getString("nome"), 
                    rs.getString("cpf"), 
                    rs.getString("data_nascimento")
            );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return cliente;
    }

    public List retrieve(String query) {
        List<Cliente> clientes = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                clientes.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return clientes;
    }

    public List retrieveAll() {
        return this.retrieve("SELECT * FROM Cliente");
    }

    public List retrieveLast() {
        return this.retrieve("SELECT * FROM Cliente WHERE id = " + lastId("Cliente", "id"));
    }

    public Cliente retrieveById(int id) {
        List<Cliente> clientes = this.retrieve("SELECT * FROM Cliente WHERE id = " + id);
        return (clientes.isEmpty() ? null : clientes.get(0));
    }

    public List retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM Cliente WHERE nome LIKE '%" + nome + "%'");
    }

    public void update(Cliente cliente) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE Cliente SET nome=?, cpf=?, data_nascimento=?"
                    + " WHERE id=?");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getData_nascimento());
            stmt.setInt(4, cliente.getId());
            
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void delete(Cliente cliente) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM Cliente WHERE id_cli = ?");
            stmt.setInt(1, cliente.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}

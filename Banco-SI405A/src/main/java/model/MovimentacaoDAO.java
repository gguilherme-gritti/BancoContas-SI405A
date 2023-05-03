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
public class MovimentacaoDAO extends DAO {

    private static MovimentacaoDAO instance;

    private MovimentacaoDAO() {
        getConnection();
        createTable();
    }

    public static MovimentacaoDAO getInstance() {
        return (instance == null ? (instance = new MovimentacaoDAO()) : instance);
    }

    public Movimentacao create(
            int id_conta,
            String tipo,
            float valor,
            int id_conta_transf
    ) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO Movimentacao (id_conta, tipo, valor, id_conta_transf) VALUES (?,?,?,?)");
            stmt.setInt(1, id_conta);
            stmt.setString(2, tipo);
            stmt.setFloat(3, valor);
            stmt.setInt(4, id_conta_transf);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(MovimentacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("Movimentacao", "id"));
    }

    public boolean isLastEmpty() {
        Movimentacao lastMov = this.retrieveById(lastId("Movimentacao", "id"));
        if (lastMov != null) {
            return lastMov.getTipo().isBlank();
        }
        return false;
    }

    private Movimentacao buildObject(ResultSet rs) {
        Movimentacao movimentacao = null;
        try {
            movimentacao = new Movimentacao(
                    rs.getInt("id"),
                    rs.getInt("id_conta"),
                    rs.getString("tipo"),
                    rs.getFloat("valor"),
                    rs.getInt("id_conta_transf")
            );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return movimentacao;
    }

    public List retrieve(String query) {
        List<Movimentacao> movimentacoes = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                movimentacoes.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return movimentacoes;
    }

    public List retrieveAll() {
        return this.retrieve("SELECT * FROM Movimentacao");
    }

    public List retrieveLast() {
        return this.retrieve("SELECT * FROM Movimentacao WHERE id = " + lastId("Movimentacao", "id"));
    }

    public Movimentacao retrieveById(int id) {
        List<Movimentacao> movimentacoes = this.retrieve("SELECT * FROM Movimentacao WHERE id = " + id);
        return (movimentacoes.isEmpty() ? null : movimentacoes.get(0));
    }

    public void update(Cliente cliente) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE Movimentacao SET nome=?, cpf=?, data_nascimento=?"
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

    public void delete(Movimentacao movimentacao) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM Movimentacao WHERE id_cli = ?");
            stmt.setInt(1, movimentacao.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}

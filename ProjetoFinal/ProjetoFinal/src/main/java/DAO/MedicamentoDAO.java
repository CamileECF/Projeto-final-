package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Medicamento;
import util.DBConnection;

public class MedicamentoDAO {

    // CREATE
    public void criarMedicamento(Medicamento med) {
        String sql = "INSERT INTO Medicamento (nome, descricao, preco, quantidadeEstoque) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, med.getNome());
            stmt.setString(2, med.getDescricao());
            stmt.setDouble(3, med.getPreco());
            stmt.setInt(4, med.getQuantidadeEstoque());
            stmt.executeUpdate();

            // Recuperar o ID gerado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                med.setId(rs.getInt(1)); // Define o ID no objeto original
            }
            
            System.out.println("Medicamento cadastrado com sucesso no banco!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar medicamento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // READ - Listar todos
    public List<Medicamento> listarMedicamentos() {
        List<Medicamento> lista = new ArrayList<>();
        // Note que o ID no seu modelo é 'int id', mas a coluna no banco pode ser 'idMedicamento'
        // Vou assumir que a coluna no banco é 'idMedicamento'
        String sql = "SELECT idMedicamento, nome, descricao, preco, quantidadeEstoque FROM Medicamento";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Usa o construtor do modelo para criar o objeto
                Medicamento med = new Medicamento(
                    rs.getInt("idMedicamento"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidadeEstoque")
                );
                lista.add(med);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar medicamentos: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
    
    // READ - Buscar por ID
    public Medicamento buscarMedicamentoPorId(int id) {
        String sql = "SELECT * FROM Medicamento WHERE idMedicamento = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                 return new Medicamento(
                    rs.getInt("idMedicamento"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidadeEstoque")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar medicamento: " + e.getMessage());
            e.printStackTrace();
        }
        return null; // Retorna null se não encontrar
    }

    // UPDATE
    public void atualizarMedicamento(Medicamento med) {
        String sql = "UPDATE Medicamento SET nome = ?, descricao = ?, preco = ?, quantidadeEstoque = ? WHERE idMedicamento = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, med.getNome());
            stmt.setString(2, med.getDescricao());
            stmt.setDouble(3, med.getPreco());
            stmt.setInt(4, med.getQuantidadeEstoque());
            stmt.setInt(5, med.getId()); // ID vai no WHERE
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Medicamento atualizado com sucesso!");
            } else {
                System.out.println("Medicamento não encontrado para atualização (ID: " + med.getId() + ")");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar medicamento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // DELETE
    public void excluirMedicamento(int id) {
        String sql = "DELETE FROM Medicamento WHERE idMedicamento = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Medicamento removido com sucesso!");
            } else {
                System.out.println("Medicamento não encontrado para remoção (ID: " + id + ")");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir medicamento: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
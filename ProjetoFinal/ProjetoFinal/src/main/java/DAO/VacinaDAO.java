package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Vacina;
import util.DBConnection;

public class VacinaDAO {

    // Criar Vacina
    public void criarVacina(Vacina vacina) {
        String sql = "INSERT INTO Vacina (nome, fabricante, validadeMeses) VALUES (?, ?, ?)";
        
        // Adicionado try-with-resources para gerenciar a conexão
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, vacina.getNome());
            stmt.setString(2, vacina.getFabricante());
            stmt.setInt(3, vacina.getPeriodoValidadeEmMeses());
            stmt.executeUpdate();

            // Recuperar o ID gerado e definir no objeto
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                vacina.setId(rs.getLong(1)); // Assumindo que Vacina.setId() aceita long
            }
            
            System.out.println("Vacina cadastrada com sucesso no banco!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar vacina: " + e.getMessage());
            e.printStackTrace(); // Adicionado para depuração
        }
    }

    // READ - Buscar vacina pelo ID
    public Vacina buscarVacinaPorId(int id) {
        String sql = "SELECT * FROM Vacina WHERE idVacina = ?";
        
        // Adicionado try-with-resources
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Vacina v = new Vacina();
                // Assumindo que seu Vacina.id é 'long' como no Pet
                v.setId(rs.getLong("idVacina")); 
                v.setNome(rs.getString("nome"));
                v.setFabricante(rs.getString("fabricante"));
                v.setPeriodoValidadeEmMeses(rs.getInt("validadeMeses"));
                return v;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar vacina: " + e.getMessage());
            e.printStackTrace(); // Adicionado para depuração
        }
        return null;
    }

    // READ - Listar todas as vacinas
    public List<Vacina> listarVacinas() {
        List<Vacina> lista = new ArrayList<>();
        String sql = "SELECT * FROM Vacina";

        // Adicionado try-with-resources
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Vacina v = new Vacina();
                v.setId(rs.getLong("idVacina"));// Assumindo 'long'
                v.setNome(rs.getString("nome"));
                v.setFabricante(rs.getString("fabricante"));
                v.setPeriodoValidadeEmMeses(rs.getInt("validadeMeses"));
                lista.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar vacinas: " + e.getMessage());
            e.printStackTrace(); // Adicionado para depuração
        }

        return lista;
    }

    // UPDATE
    public void atualizarVacina(Vacina vacina) {
        String sql = "UPDATE Vacina SET nome = ?, fabricante = ?, validadeMeses = ? WHERE idVacina = ?";
        
        // Adicionado try-with-resources
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, vacina.getNome());
            stmt.setString(2, vacina.getFabricante());
            stmt.setInt(3, vacina.getPeriodoValidadeEmMeses());
            stmt.setLong(4, vacina.getId()); // MUDANÇA: Assumindo 'long'
            stmt.executeUpdate();
            
            System.out.println("Vacina atualizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar vacina: " + e.getMessage());
            e.printStackTrace(); // Adicionado para depuração
        }
    }

    // DELETE
    public void excluirVacina(long id) { // MUDANÇA: Assumindo 'long'
        String sql = "DELETE FROM Vacina WHERE idVacina = ?";
        
        //  Adicionado try-with-resources
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id); // MUDANÇA: Assumindo 'long'
            stmt.executeUpdate();
            
            System.out.println("Vacina excluída com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir vacina: " + e.getMessage());
            e.printStackTrace(); // Adicionado para depuração
        }
    }
}
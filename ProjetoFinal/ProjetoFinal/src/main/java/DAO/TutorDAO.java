package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Tutor;
import util.DBConnection;

public class TutorDAO {

    // Criar tutor
    public void criarTutor(Tutor tutor) {
        String sqlUsuario = "INSERT INTO Usuario (senha, data_cadastro) VALUES (?, ?)";
        String sqlTutor = "INSERT INTO Tutor (idTutor, nome, cpf, email, telefone) VALUES (?, ?, ?, ?, ?)";
        
        Connection conn = null;
        
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // Inserir em Usuario
            PreparedStatement psUsuario = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
            psUsuario.setString(1, tutor.getSenha()); 
            psUsuario.setDate(2, Date.valueOf(tutor.getData_cadastro()));
            psUsuario.executeUpdate();

            // Obter o ID gerado
            ResultSet rs = psUsuario.getGeneratedKeys();
            int idGerado = -1;
            if (rs.next()) {
                idGerado = rs.getInt(1);
                tutor.setIdUsuario(idGerado); 
            } else {
                throw new SQLException("Falha ao criar usuário, nenhum ID obtido.");
            }

            // Inserir em Tutor
            PreparedStatement psTutor = conn.prepareStatement(sqlTutor);
            psTutor.setInt(1, idGerado);
            psTutor.setString(2, tutor.getNome());
            psTutor.setString(3, tutor.getCpf());
            psTutor.setString(4, tutor.getEmail());
            psTutor.setString(5, tutor.getTelefone());
            psTutor.executeUpdate();

            conn.commit();
            System.out.println("Tutor cadastrado com sucesso no banco!");

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Erro ao cadastrar tutor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // READ - Buscar por ID
    public Tutor buscarTutorPorId(int idUsuario) {
        // Verificar os nomes exatos das colunas
        String sql = "SELECT u.idUsuario, u.senha, u.data_cadastro, t.nome, t.cpf, t.email, t.telefone " +
                     "FROM Usuario u JOIN Tutor t ON u.idUsuario = t.idTutor " +
                     "WHERE u.idUsuario = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return extrairTutorDoResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar tutor: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // READ - Listar todos
    public List<Tutor> listarTutores() {
        List<Tutor> lista = new ArrayList<>();
        // Verificar os nomes exatos das colunas
        String sql = "SELECT u.idUsuario, u.senha, u.data_cadastro, t.nome, t.cpf, t.email, t.telefone " +
                     "FROM Usuario u JOIN Tutor t ON u.idUsuario = t.idTutor";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                lista.add(extrairTutorDoResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar tutores: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // UPDATE
    public void atualizarTutor(Tutor tutor) {
        String sqlUsuario = "UPDATE Usuario SET senha = ?, data_cadastro = ? WHERE idUsuario = ?";
        String sqlTutor = "UPDATE Tutor SET nome = ?, cpf = ?, email = ?, telefone = ? WHERE idTutor = ?";
        
        Connection conn = null;
        
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // Atualizar Usuario
            PreparedStatement psUsuario = conn.prepareStatement(sqlUsuario);
            psUsuario.setString(1, tutor.getSenha());
            psUsuario.setDate(2, Date.valueOf(tutor.getData_cadastro()));
            psUsuario.setInt(3, tutor.getIdUsuario());
            psUsuario.executeUpdate();

            // Atualizar Tutor
            PreparedStatement psTutor = conn.prepareStatement(sqlTutor);
            psTutor.setString(1, tutor.getNome());
            psTutor.setString(2, tutor.getCpf());
            psTutor.setString(3, tutor.getEmail());
            psTutor.setString(4, tutor.getTelefone());
            psTutor.setInt(5, tutor.getIdUsuario());
            psTutor.executeUpdate();
            
            conn.commit();
            System.out.println("Tutor atualizado com sucesso!");
            
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Erro ao atualizar tutor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // DELETE
    public void excluirTutor(int idUsuario) {
        String sqlTutor = "DELETE FROM Tutor WHERE idTutor = ?";
        String sqlUsuario = "DELETE FROM Usuario WHERE idUsuario = ?";
        
        Connection conn = null;
        
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // Excluir de Tutor primeiro
            PreparedStatement psTutor = conn.prepareStatement(sqlTutor);
            psTutor.setInt(1, idUsuario);
            psTutor.executeUpdate();

            // Excluir de Usuario
            PreparedStatement psUsuario = conn.prepareStatement(sqlUsuario);
            psUsuario.setInt(1, idUsuario);
            psUsuario.executeUpdate();

            conn.commit();
            System.out.println("Tutor excluído com sucesso!");
            
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Erro ao excluir tutor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método helper
    private Tutor extrairTutorDoResultSet(ResultSet rs) throws SQLException {
        int idUsuario = rs.getInt("idUsuario");
        String senha = rs.getString("senha");
        String dataCadastro = rs.getDate("data_cadastro").toString();
        String nome = rs.getString("nome");
        String cpf = rs.getString("cpf");
        String email = rs.getString("email");
        String telefone = rs.getString("telefone");

        return new Tutor(idUsuario, senha, dataCadastro, nome, cpf, email, telefone);
    }
}
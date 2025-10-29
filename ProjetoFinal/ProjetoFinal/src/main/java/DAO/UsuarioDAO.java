package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import util.DBConnection;

public class UsuarioDAO {

    // Criar Usuario
    public void criarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (senha, data_cadastro) VALUES (?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, usuario.getSenha());
            stmt.setDate(2, Date.valueOf(usuario.getData_cadastro()));
            stmt.executeUpdate();

            // Recuperar o ID gerado e definir no objeto
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt(1));
            }
            
            System.out.println("Usuário cadastrado com sucesso no banco!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // READ - Buscar usuário pelo ID
    public Usuario buscarUsuarioPorId(int id) {
        String sql = "SELECT * FROM Usuario WHERE idUsuario = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario(
                    rs.getInt("idUsuario"),
                    rs.getString("senha"),
                    rs.getDate("data_cadastro").toString()
                );
                return u;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // READ - Listar todos os usuários
    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new Usuario(
                    rs.getInt("idUsuario"),
                    rs.getString("senha"),
                    rs.getDate("data_cadastro").toString()
                );
                lista.add(u);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    // UPDATE
    public void atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE Usuario SET senha = ?, data_cadastro = ? WHERE idUsuario = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getSenha());
            stmt.setDate(2, Date.valueOf(usuario.getData_cadastro()));
            stmt.setInt(3, usuario.getIdUsuario());
            stmt.executeUpdate();
            
            System.out.println("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // DELETE
    public void excluirUsuario(int id) {
        String sql = "DELETE FROM Usuario WHERE idUsuario = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
            System.out.println("Usuário excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
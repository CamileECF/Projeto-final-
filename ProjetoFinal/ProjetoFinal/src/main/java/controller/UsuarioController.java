package controller;

import java.util.List;

import DAO.UsuarioDAO;
import model.Usuario;

public class UsuarioController {

    // O Controller tem uma instância do DAO
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        // O Controller cria seu próprio DAO
        this.usuarioDAO = new UsuarioDAO();
    }

    // CREATE
    public void cadastrarUsuario(Usuario usuario) {
        // Delega o trabalho para o DAO, que salvará no banco
        usuarioDAO.criarUsuario(usuario);
        // A mensagem de sucesso já é impressa pelo DAO
    }

    // READ
    public void listarUsuarios() {
        // Busca a lista do BANCO DE DADOS através do DAO
        List<Usuario> listaUsuarios = usuarioDAO.listarUsuarios();
        
        System.out.println("\n=== LISTA DE USUÁRIOS (do Banco de Dados) ===");
        if (listaUsuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            // Itera sobre a lista vinda do banco
            for (Usuario u : listaUsuarios) {
                // Imprime os dados formatados, incluindo o ID
                System.out.println("ID: " + u.getIdUsuario() + 
                                   " | Senha: " + u.getSenha() +
                                   " | Data Cadastro: " + u.getData_cadastro());
            }
        }
    }

    // READ - Buscar por ID
    public Usuario buscarUsuarioPorId(int id) {
        return usuarioDAO.buscarUsuarioPorId(id);
    }

    // UPDATE
    // Recebe o objeto Usuario completo, que já deve conter o ID
    public void atualizarUsuario(Usuario usuarioAtualizado) {
        // A View é responsável por colocar o ID no objeto antes de passá-lo para cá
        usuarioDAO.atualizarUsuario(usuarioAtualizado);
    }

    // DELETE
    // Recebe o ID (int), não o índice
    public void removerUsuario(int id) {
        usuarioDAO.excluirUsuario(id);
    }
}
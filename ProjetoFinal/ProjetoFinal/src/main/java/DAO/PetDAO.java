package DAO;

import model.Pet;
import model.Tutor;
import util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    //Criação do Tutor DAO
    private TutorDAO tutorDAO; 

    public PetDAO() {
        this.tutorDAO = new TutorDAO(); // Instanciando o DAO necessário
    }

    public void criarPet(Pet pet) {
        String sql = "INSERT INTO Pet (nome, especie, raca, dataNascimento, sexo, idTutor) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, pet.getNome());
            ps.setString(2, pet.getEspecie());
            ps.setString(3, pet.getRaca());
            ps.setDate(4, Date.valueOf(pet.getDataNascimento()));
            ps.setString(5, pet.getSexo());
            
            
            // Assumindo que o ID do Tutor vem de Usuario.getIdUsuario()
            ps.setInt(6, pet.getTutor().getIdUsuario()); 

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
               // Usando setLong() para corresponder ao tipo 'long' do Pet.id
                pet.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pet buscarPetPorId(long idPet) {
        String sql = "SELECT * FROM Pet WHERE idPet = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, idPet);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Pet pet = new Pet();
                //  Usando setLong() e o nome correto da coluna
                pet.setId(rs.getLong("idPet"));
                pet.setNome(rs.getString("nome"));
                pet.setEspecie(rs.getString("especie"));
                pet.setRaca(rs.getString("raca"));
                pet.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                pet.setSexo(rs.getString("sexo"));

                // Lógica correta para carregar o objeto Tutor
                int idTutor = rs.getInt("idTutor");
                Tutor tutor = this.tutorDAO.buscarTutorPorId(idTutor); 
                pet.setTutor(tutor);

                return pet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pet> listarPets() {
        List<Pet> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pet";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Pet pet = new Pet();
                // método (setId) e o tipo (getLong)
                pet.setId(rs.getLong("idPet"));
                pet.setNome(rs.getString("nome"));
                pet.setEspecie(rs.getString("especie"));
                pet.setRaca(rs.getString("raca"));
                pet.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                pet.setSexo(rs.getString("sexo"));

                // Lógica correta para carregar o objeto Tutor
                int idTutor = rs.getInt("idTutor");
                Tutor tutor = this.tutorDAO.buscarTutorPorId(idTutor); // Exemplo de como deveria ser
                pet.setTutor(tutor);
                
                lista.add(pet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void atualizarPet(Pet pet) {
        String sql = "UPDATE Pet SET nome=?, especie=?, raca=?, dataNascimento=?, sexo=?, idTutor=? WHERE idPet=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, pet.getNome());
            ps.setString(2, pet.getEspecie());
            ps.setString(3, pet.getRaca());
            ps.setDate(4, Date.valueOf(pet.getDataNascimento()));
            ps.setString(5, pet.getSexo());
            
            // ID de dentro do objeto Tutor
            ps.setInt(6, pet.getTutor().getIdUsuario()); 
            
            //nome do método (getId) e o tipo (setLong)
            ps.setLong(7, pet.getId());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirPet(long idPet) {
        String sql = "DELETE FROM Pet WHERE idPet = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            // Usando setLong()
            ps.setLong(1, idPet);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
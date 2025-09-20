package model;

public class Usuario {
    private int idUsuario; 
    private String senha;
    private String data_cadastro; 
    
// Construtor com todos os dados
public Usuario(String senha, String data_cadastro){
        this.senha=senha;
        this.data_cadastro=data_cadastro;
    }
   
public void mostrarInfo() {
    System.out.println("Id Usuario: "+getIdUsuario()+
            "\nSenha: "+getSenha()+
            "\nData de cadastro: "+getData_cadastro());
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the data_cadastro
     */
    public String getData_cadastro() {
        return data_cadastro;
    }

}

   

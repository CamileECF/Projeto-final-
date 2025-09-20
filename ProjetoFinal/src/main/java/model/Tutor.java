package model;

public class Tutor extends Usuario {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    // Construtor com todos os dados
    public Tutor(int idUsuario,String senha, String data_cadastro, String nome, String cpf, String email, String telefone) {
        super(idUsuario,senha, data_cadastro); 
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }
    @Override
    public void mostrarInfo(){
        super.mostrarInfo();
        System.out.println("Nome: "+getNome()+
                "\nCPF: "+getCpf()+
                "\nEmail: "+getEmail()+
                "\nTelefone: "+getTelefone());
        
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    }


package model;

/**
 *
 * @author GuilhermeGoulartGrit
 */
public class Cliente {

    private int id;
    private String nome;
    private String cpf;
    private String data_nascimento;

    public Cliente(
        int id,
        String nome,
        String cpf,
        String data_nascimento
    ) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
    
}

package model;

/**
 *
 * @author GuilhermeGoulartGrit
 */
public class Movimentacao {
    
    private int id;
    private int id_conta;
    private String tipo;
    private float valor;
    private int id_conta_transf;

    public Movimentacao(int id, int id_conta, String tipo, float valor, int id_conta_transf) {
        this.id = id;
        this.id_conta = id_conta;
        this.tipo = tipo;
        this.valor = valor;
        this.id_conta_transf = id_conta_transf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_conta() {
        return id_conta;
    }

    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getId_conta_transf() {
        return id_conta_transf;
    }

    public void setId_conta_transf(int id_conta_transf) {
        this.id_conta_transf = id_conta_transf;
    }
    
    
   
}

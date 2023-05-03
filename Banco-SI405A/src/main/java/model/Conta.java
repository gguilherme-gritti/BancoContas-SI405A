package model;

/**
 *
 * @author GuilhermeGoulartGrit
 */
public class Conta {
    
    private int id;
    private int id_cliente;
    private String data_abertura;
    private float saldo;
    private float limite;
    private float limite_credito;
    private String tipo;
    private int data_aniv;

    public Conta(
        int id, 
        int id_cliente, 
        String data_abertura, 
        float saldo, 
        float limite, 
        float limite_credito, 
        String tipo, 
        int data_aniv
    ) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.data_abertura = data_abertura;
        this.saldo = saldo;
        this.limite = limite;
        this.limite_credito = limite_credito;
        this.tipo = tipo;
        this.data_aniv = data_aniv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getData_abertura() {
        return data_abertura;
    }

    public void setData_abertura(String data_abertura) {
        this.data_abertura = data_abertura;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    public float getLimite_credito() {
        return limite_credito;
    }

    public void setLimite_credito(float limite_credito) {
        this.limite_credito = limite_credito;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getData_aniv() {
        return data_aniv;
    }

    public void setData_aniv(int data_aniv) {
        this.data_aniv = data_aniv;
    }
    
    
}

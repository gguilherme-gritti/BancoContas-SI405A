/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JTextField;
import model.Cliente;
import model.ClienteDAO;
import model.Conta;
import model.ContaDAO;
import model.MovimentacaoDAO;

/**
 *
 * @author GuilhermeGoulartGrit
 */
public class Controller {

    //Objects
    private static Cliente selectedCliente = null;
    private static Conta selectedTransferConta = null;
    private static Conta selectedConta = null;

    //GUI controlleds
    private static JTextField tfCliente = null;
    private static JTextField tfConta = null;
    private static JTextField tfSaldo = null;
    private static JTextField tfTipo = null;
    private static JTextField tfLimite = null;
    private static JTextField tfCredito = null;
    
    private static JTextField tfTransferConta = null;

    public static List getAllClients() {
        return ClienteDAO.getInstance().retrieveAll();
    }
    
    public static List getAllContas() {
        return ContaDAO.getInstance().retrieveAll();
    }
    
    public static List getAllMovimentacoes() {
        return MovimentacaoDAO.getInstance().retrieveAll();
    }
    
    public static List getContasByCliente(){
        return ContaDAO.getInstance().retrieveByClienteId(selectedCliente.getId());
    }
    
    public static List getMovimentacoesByConta(){
        return MovimentacaoDAO.getInstance().retrieveByContaId(selectedConta.getId());
    }

    public static void addCliente(String nome, String cpf, String data_nascimento) {
        ClienteDAO.getInstance().create(nome, cpf, data_nascimento);
    }
    
    public static void addConta( float saldo, float limite, float limite_credito, String tipo, int data_aniv){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String data_abertura = sdf.format(new Date());
        
        ContaDAO.getInstance().create(selectedCliente.getId(), data_abertura, saldo, limite, limite_credito, tipo, data_aniv);
    }
    
    public static void addMovimentacao(int id_conta, String tipo, float valor, int id_conta_transf) {
        MovimentacaoDAO.getInstance().create(id_conta, tipo, valor, id_conta_transf);
    }
    
    public static void updateConta(Conta conta){
        ContaDAO.getInstance().update(conta);
    }

    public static void setSelectedCliente(Cliente cliente) {
        selectedCliente = cliente;
        tfCliente.setText(cliente.getNome());
    }
    
    
    public static void setSelectedTransferConta(Conta conta) {
        selectedTransferConta = conta;
        tfTransferConta.setText(String.valueOf(conta.getId()));
    }

    public static void setSelectedConta(Conta conta) {
        selectedConta = conta;
        tfConta.setText(String.valueOf(conta.getId()));
        tfSaldo.setText(String.valueOf(conta.getSaldo()));
        tfLimite.setText(String.valueOf(conta.getLimite()));
        tfCredito.setText(String.valueOf(conta.getLimite_credito()));
        tfTipo.setText(conta.getTipo());
    }

    public static void setSelectedHeaders(
            JTextField cliente,
            JTextField conta,
            JTextField saldo,
            JTextField tipo,
            JTextField limite,
            JTextField credito
    ) {
        tfCliente = cliente;
        tfConta = conta;
        tfSaldo = saldo;
        tfTipo = tipo;
        tfLimite = limite;
        tfCredito = credito;
    }
    
    public static void setSelectedTransfer(JTextField contaTransferir) {
        tfTransferConta = contaTransferir;
    }
    
    public static Conta getSelectedTransferConta(){
        return selectedTransferConta;
    }
    
    public static Conta getSelectedConta() {
        return selectedConta;
    }
    
    public static Cliente getSelectedCliente(){
        return selectedCliente;
    }

}

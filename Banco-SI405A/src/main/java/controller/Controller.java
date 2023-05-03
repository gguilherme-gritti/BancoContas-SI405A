/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.ClienteDAO;

/**
 *
 * @author GuilhermeGoulartGrit
 */
public class Controller {

    public static List getAllClients() {
        return ClienteDAO.getInstance().retrieveAll();
    }

    public static void addCliente(String nome, String cpf, String data_nascimento) {
        ClienteDAO.getInstance().create(nome, cpf, data_nascimento);
    }

}

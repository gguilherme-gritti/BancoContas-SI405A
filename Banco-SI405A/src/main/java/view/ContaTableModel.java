/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.List;
import model.Conta;
import model.ContaDAO;

/**
 *
 * @author GuilhermeGoulartGrit
 */
public class ContaTableModel extends GenericTableModel {

    public ContaTableModel(List vDados) {
        super(vDados, new String[]{"Numero", "Data Abertura", "Saldo", "Limite", "Limite Credito", "Tipo", "Aniv"});
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
            default:
                return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Conta conta = (Conta) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return conta.getId();
            case 1:
                return conta.getData_abertura();
            case 2:
                return conta.getSaldo();
            case 3:
                return conta.getLimite();
            case 4:
                return conta.getLimite_credito();
            case 5:
                return conta.getTipo();
            case 6:
                return conta.getData_aniv();
            default:
                return "";
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Conta conta = (Conta) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                break;
            case 1:
                conta.setData_abertura((String) aValue);
                break;
            case 2:
                conta.setSaldo((float) aValue);
                break;
            case 3:
                conta.setLimite((float) aValue);
                break;
            case 4:
                conta.setLimite_credito((float) aValue);
                break;
            case 5:
                conta.setTipo((String) aValue);
                break;
            case 6:
                conta.setData_aniv((int) aValue);
                break;
            default:
                break;
        }

        ContaDAO.getInstance().update(conta);

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

}

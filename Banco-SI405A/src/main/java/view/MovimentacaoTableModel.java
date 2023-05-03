/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.List;
import model.Movimentacao;

/**
 *
 * @author GuilhermeGoulartGrit
 */
public class MovimentacaoTableModel extends GenericTableModel{
    public MovimentacaoTableModel(List vDados) {
        super(vDados, new String[]{"Tipo", "Valor", "Conta Transf"});
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
            default:
                return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movimentacao movimentacao = (Movimentacao) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return movimentacao.getTipo();
            case 1:
                return movimentacao.getValor();
            case 2:
                return movimentacao.getId_conta_transf();
            default:
                return "";
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}

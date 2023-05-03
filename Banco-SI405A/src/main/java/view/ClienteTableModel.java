package view;

import java.util.List;
import model.Cliente;
import model.ClienteDAO;

/**
 *
 * @author GuilhermeGoulartGrit
 */
public class ClienteTableModel extends GenericTableModel {

    public ClienteTableModel(List vDados) {
        super(vDados, new String[]{"Nome", "CPF", "Data Nascimento"});
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
        Cliente cliente = (Cliente) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return cliente.getNome();
            case 1:
                return cliente.getCpf();
            case 2:
                return cliente.getData_nascimento();
            default:
                return "";
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Cliente cliente = (Cliente) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                cliente.setNome((String) aValue);
                break;
            case 1:
                cliente.setCpf((String) aValue);
                break;
            case 2:
                cliente.setData_nascimento((String) aValue);
                break;
            default:
                break;
        }

        ClienteDAO.getInstance().update(cliente);

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}

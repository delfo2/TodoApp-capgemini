package util;

import javax.swing.table.AbstractTableModel;

import model.Tasks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TaskTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private String[] columns = {"Nome", "Descrição", "Prazo", "Concluída?", "Editar", "Excluir"};
	private List<Tasks> tasks = new ArrayList<Tasks>();

	@Override
	public int getRowCount() {
		return tasks.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;
    }
	
	@Override
    public Class<?> getColumnClass(int columnIndex) {
		if(tasks.isEmpty()) { 
			return Object.class;
		}
		return this.getValueAt(0, columnIndex).getClass();
    }
	
	@Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		tasks.get(rowIndex).setComplete((boolean) aValue);
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
			case 0:
				return tasks.get(rowIndex).getName();
			case 1:
				return tasks.get(rowIndex).getDescription();
			case 2:
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
				return dateFormat.format(tasks.get(rowIndex).getDeadLine());
			case 3:
				return tasks.get(rowIndex).isComplete();
			case 4:
				return "";
			case 5:
				return "";
			default:
				return "Dados não encontrados.";
		}
	}

	public List<Tasks> getTasks() {
		return tasks;
	}

	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}

	public String[] getColumns() {
		return columns;
	}

}

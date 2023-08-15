package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import java.awt.Cursor;
import javax.swing.JTable;

import controller.projectController;
import controller.taskController;
import model.Project;
import model.Tasks;
import util.SwitchVisible;
import util.TableRenderFactory;
import util.TableRenderFactoryButton;
import util.TaskTableModel;

public class MainScreen extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1627198260492105003L;
	
	private ProjectDialogScreen projectScreen;
	private TaskDialogScreen taskScreen = new TaskDialogScreen();
	
	private JPanel contentPane;
	private JLabel projetos_panel_p;
	private JLabel tarefas_panel_p;
	
	private projectController pController;
	private taskController tController;
	
	private DefaultListModel<String> projectModel;
	private int[] projectIdList;
	private int selectedProjectId = 0;
	private JList<String> projetos_space_list;
	
	private JPanel tarefas_space;
	private TaskTableModel taskModel;
	private JPanel projetos_space;
	private boolean hasTasks = false;
	
	private JTable tarefas_space_table;
	private JScrollPane tarefas_space_scrollPane;
	private JPanel tarefas_space_card;
	private int[] taskIdList;
	
	private Color backgroundColor = new Color(81, 43, 129);

	public MainScreen() {
		init();
		controllersInit();
		modelsInit();
	}
	
	private void init() {
		setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 489);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(53, 21, 93));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel header = new JPanel();
		header.setBackground(new Color(140, 171, 255));
		header.setBounds(0, 0, 768, 81);
		contentPane.add(header);
		header.setLayout(null);
		
		JLabel header_h1 = new JLabel("Todo App");
		header_h1.setForeground(new Color(0, 0, 1));
		header_h1.setFont(new Font("Eras Bold ITC", Font.BOLD, 44));
		header_h1.setBounds(81, 14, 677, 41);
		header.add(header_h1);
		header_h1.addMouseListener(this);
		
		JLabel header_p = new JLabel("tarefas e obigações");
		header_p.setForeground(new Color(0, 0, 1));
		header_p.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));
		header_p.setBounds(91, 56, 634, 14);
		header.add(header_p);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/view/leilao (1).png")));
		lblNewLabel.setBounds(10, 11, 71, 67);
		header.add(lblNewLabel);
		
		JPanel projetos = new JPanel();
		projetos.setBackground(new Color(68, 119, 206));
		projetos.setBounds(10, 89, 125, 25);
		contentPane.add(projetos);
		projetos.setLayout(null);
		
		JLabel projetos_h2 = new JLabel("Projetos");
		projetos_h2.setForeground(new Color(0, 0, 1));
		projetos_h2.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));
		projetos_h2.setBounds(5, 0, 56, 25);
		projetos.add(projetos_h2);
		
		JPanel projetos_panel = new JPanel();
		projetos_panel.setLayout(null);
		projetos_panel.setBackground(new Color(140, 171, 255));
		projetos_panel.setBounds(69, 0, 56, 25);
		projetos.add(projetos_panel);
		
		projetos_panel_p = new JLabel("+");
		projetos_panel_p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		projetos_panel_p.setForeground(new Color(0, 0, 1));
		projetos_panel_p.setVerticalAlignment(SwingConstants.CENTER);
		projetos_panel_p.setHorizontalAlignment(SwingConstants.CENTER);
		projetos_panel_p.setFont(new Font("Eras Bold ITC", Font.PLAIN, 24));
		projetos_panel_p.setBounds(0, 0, 56, 25);
		projetos_panel.add(projetos_panel_p);
		projetos_panel_p.addMouseListener(this);
		
		JPanel tarefas = new JPanel();
		tarefas.setBackground(new Color(68, 119, 206));
		tarefas.setBounds(145, 89, 613, 25);
		contentPane.add(tarefas);
		tarefas.setLayout(null);
		
		JLabel tarefas_h2 = new JLabel("Tarefas");
		tarefas_h2.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));
		tarefas_h2.setBackground(new Color(68, 119, 206));
		tarefas_h2.setBounds(5, 0, 502, 25);
		tarefas_h2.setForeground(new Color(0, 0, 1));
		tarefas.add(tarefas_h2);
		
		JPanel tarefas_panel = new JPanel();
		tarefas_panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tarefas_panel.setBackground(new Color(140, 171, 255));
		tarefas_panel.setBounds(512, 0, 101, 25);
		tarefas.add(tarefas_panel);
		tarefas_panel.setLayout(null);
		
		tarefas_panel_p = new JLabel("+");
		tarefas_panel_p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tarefas_panel_p.setForeground(new Color(0, 0, 1));
		tarefas_panel_p.setFont(new Font("Eras Bold ITC", Font.PLAIN, 24));
		tarefas_panel_p.setHorizontalAlignment(SwingConstants.CENTER);
		tarefas_panel_p.setVerticalAlignment(SwingConstants.CENTER);
		tarefas_panel_p.setBounds(0, 0, 101, 25);
		tarefas_panel_p.addMouseListener(this);
		tarefas_panel.add(tarefas_panel_p);
		
		projetos_space = new JPanel();
		projetos_space.setBackground(new Color(81, 43, 129));
		projetos_space.setBounds(10, 125, 125, 314);
		contentPane.add(projetos_space);
		projetos_space.setLayout(null);
		
		projetos_space_list = new JList<String>();
		projetos_space_list.setFixedCellWidth(500);
		projetos_space_list.setFixedCellHeight(25);
		projetos_space_list.setFocusable(false);
		projetos_space_list.setSelectionForeground(new Color(0, 0, 1));
		projetos_space_list.setFocusTraversalKeysEnabled(false);
		projetos_space_list.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		projetos_space_list.setSelectionBackground(new Color(152, 116, 205));
		projetos_space_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		projetos_space_list.setForeground(new Color(255, 255, 255));
		projetos_space_list.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
		projetos_space_list.setBackground(new Color(81, 43, 129));
		projetos_space_list.setBounds(0, 0, 125, 314);
		projetos_space.add(projetos_space_list);
		projetos_space_list.addMouseListener(this);
		
		tarefas_space = new JPanel();
		tarefas_space.setBackground(new Color(81, 43, 129));
		tarefas_space.setBounds(145, 125, 613, 314);
		contentPane.add(tarefas_space);
		tarefas_space.setLayout(null);
		
		tarefas_space_card = new JPanel();
		tarefas_space_card.setVisible(true);
		
		tarefas_space_table = new JTable();
		tarefas_space_table.setShowGrid(false);
		tarefas_space_table.setBackground(new Color(128, 74, 193));
		tarefas_space_table.setSelectionBackground(new Color(189, 162, 223));
		tarefas_space_table.setShowVerticalLines(false);
		tarefas_space_table.setShowHorizontalLines(false);
		tarefas_space_table.setOpaque(false);
		tarefas_space_table.setForeground(new Color(0, 0, 1));
		tarefas_space_table.setFont(new Font("Eras Demi ITC", Font.PLAIN, 12));
		tarefas_space_table.setBounds(0, 0, 613, 180);
		tarefas_space_table.getTableHeader().setBackground(backgroundColor);
		tarefas_space_table.getTableHeader().setForeground(new Color(255,255,255));
		tarefas_space_table.addMouseListener(this);
		
		tarefas_space_card.setOpaque(false);
		tarefas_space_card.setFocusable(false);
		tarefas_space_card.setBounds(0, 0, 613, 314);
		tarefas_space.add(tarefas_space_card);
		tarefas_space_card.setLayout(null);
		
		JLabel tarefas_space_fantasma = new JLabel("");
		tarefas_space_fantasma.setHorizontalAlignment(SwingConstants.CENTER);
		tarefas_space_fantasma.setVisible(true);
		tarefas_space_fantasma.setIcon(new ImageIcon(MainScreen.class.getResource("/view/fantasma.png")));
		tarefas_space_fantasma.setBounds(0, 0, 613, 40);
		tarefas_space_card.add(tarefas_space_fantasma);
		
		JLabel tarefas_space_nothingHere = new JLabel("Nada aqui.");
		tarefas_space_nothingHere.setVisible(true);
		tarefas_space_nothingHere.setHorizontalAlignment(SwingConstants.CENTER);
		tarefas_space_nothingHere.setForeground(new Color(0, 0, 1));
		tarefas_space_nothingHere.setFont(new Font("Eras Demi ITC", Font.PLAIN, 20));
		tarefas_space_nothingHere.setBounds(0, 42, 613, 19);
		tarefas_space_card.add(tarefas_space_nothingHere);
		
		JLabel tarefas_space_clickHere = new JLabel("Clique em + para adicionar uma tarefa.");
		tarefas_space_clickHere.setVisible(true);
		tarefas_space_clickHere.setHorizontalAlignment(SwingConstants.CENTER);
		tarefas_space_clickHere.setForeground(new Color(0, 0, 1));
		tarefas_space_clickHere.setFont(new Font("Eras Light ITC", Font.PLAIN, 14));
		tarefas_space_clickHere.setBounds(0, 72, 603, 17);
		tarefas_space_card.add(tarefas_space_clickHere);

		contentPane.repaint();
	}

	private void scrollGenerator() {
		if(tarefas_space_scrollPane != null) {
			tarefas_space.remove(tarefas_space_scrollPane);			
		}
		tarefas_space_scrollPane = new JScrollPane(tarefas_space_table);
		tarefas_space_scrollPane.setOpaque(false);
		tarefas_space_scrollPane.setLocation(0, 0);
		tarefas_space_scrollPane.setBorder(BorderFactory.createEmptyBorder());
		tarefas_space_scrollPane.getViewport().setBackground(backgroundColor);
		tarefas_space_scrollPane.setSize(613, 314);
		tarefas_space.add(tarefas_space_scrollPane);
	}

	private void controllersInit() {
		pController = new projectController();
		tController = new taskController();
	}
	
	private void modelsInit() {
		projectModel = new DefaultListModel<String>();
		projectUpdate();
		projetos_space_list.setSelectedIndex(0);
		updateProjectIdSelected();
		
		taskModel = new TaskTableModel();
		tasksUpdate(selectedProjectId);
		tarefas_space_table.setModel(taskModel);
		
		tarefas_space_table.getColumnModel()
			.getColumn(2).setCellRenderer(new TableRenderFactory());
		tarefas_space_table.getColumnModel()
			.getColumn(4).setCellRenderer(new TableRenderFactoryButton("update"));
		tarefas_space_table.getColumnModel()
			.getColumn(5).setCellRenderer(new TableRenderFactoryButton("delete"));
		tarefas_space_table.repaint();
	}
	
	private void tasksUpdate(int id) {
		List<Tasks> tasks = tController.getAll(id);
		hasTasks = !tasks.isEmpty();
		taskIdList = new int[tasks.size()];
		
		for(int i = 0; i < tasks.size(); i++) {
			taskIdList[i] = tasks.get(i).getId();
		}
		
		taskModel.setTasks(tasks);
		scrollGenerator();
		switchTaskView(hasTasks);
	}
	
	private void projectUpdate() {
		List<Project> projects = pController.getAll();
		projectModel.clear();
		projectIdList = new int[projects.size()]; 
		
		for (int i = 0; i < projects.size(); i++) {
			projectModel.add(i, projects.get(i).getName());
			projectIdList[i] = projects.get(i).getId();
		}
		
		projetos_space_list.setModel(projectModel); 
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	private void showProjectDialog() {
		projectScreen = new ProjectDialogScreen();
		projectScreen.addWindowListener((WindowListener) new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				projectUpdate();
			}
		});
	}
	
	private void showTaskDialog() {
		taskScreen.init(selectedProjectId);
		addWindowListenerToTaskScreen();
	}
	private void showTaskUpdateDialog(Tasks task) {
		taskScreen.updateTask(selectedProjectId, task);
		addWindowListenerToTaskScreen();
	}
	
	private void addWindowListenerToTaskScreen() {
		taskScreen.addWindowListener((WindowListener) new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				tasksUpdate(selectedProjectId);
			}
		});
	}
	
	private void taskListAction(MouseEvent e) {
		int rowIndex = tarefas_space_table.rowAtPoint(e.getPoint());
		int columnIndex = tarefas_space_table.columnAtPoint(e.getPoint());
		Tasks task;

		switch(columnIndex) {
			case 3:
				task = taskModel.getTasks().get(rowIndex);
				task.setId(taskIdList[rowIndex]);
				tController.update(task);
				break;
			case 4:
				task = taskModel.getTasks().get(rowIndex);
				task.setId(taskIdList[rowIndex]);
				showTaskUpdateDialog(task);
				break;
			case 5:
				task = taskModel.getTasks().get(rowIndex);
				task.setId(taskIdList[rowIndex]);
				tController.removeById(task.getId());
				tasksUpdate(selectedProjectId);
				break;
			default:
				break;
		}
	}
	
	private void switchTaskView(boolean hasTask) {
		if(hasTask) {
			SwitchVisible.change(tarefas_space_table, hasTask);
			SwitchVisible.change(tarefas_space_scrollPane, hasTask);
			SwitchVisible.change(tarefas_space_card, !hasTask);
		} else {
			SwitchVisible.change(tarefas_space_table, hasTask);
			SwitchVisible.change(tarefas_space_scrollPane, hasTask);
			SwitchVisible.change(tarefas_space_card, !hasTask);
		}
	}

	private void updateProjectIdSelected() {
		int index = projetos_space_list.getSelectedIndex() > 0 
			? index = projetos_space_list.getSelectedIndex()
			: 0;
		projetos_space_list.setSelectedIndex(index);
		selectedProjectId = projectIdList[index];
	}
	
	private void projectListAction(MouseEvent e) {
		updateProjectIdSelected();
		tasksUpdate(selectedProjectId);
		switchTaskView(hasTasks);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource() == projetos_panel_p) {
			showProjectDialog();
		}
		else if(e.getSource() == tarefas_panel_p) {
			showTaskDialog();
		}
		else if(e.getSource() == tarefas_space_table) {
			taskListAction(e);
		}
		else if(e.getSource() == projetos_space_list) {
			projectListAction(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

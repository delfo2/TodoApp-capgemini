package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.taskController;
import model.Tasks;
import util.DataConverter;

import javax.swing.JFormattedTextField;

public class TaskDialogScreen extends JFrame implements MouseListener {

	private taskController tController = new taskController();
	
	private JPanel contentPane;
	private JLabel header_btn;
	private JTextField form_nomeTarefa_textField;
	private JTextArea form_descricaoTarefa_textField;
	private JFormattedTextField tarefas_prazoTarefa_textField;
	private JTextArea tarefas_panel_p;
	
	private JLabel header_h1;
	
	private int idProject;
	private boolean isUpdateTask = false;
	private int atualTask;

	public TaskDialogScreen() {
	}
	
	public void init(int idProject) {
		this.idProject = idProject;
		
		setBackground(new Color(0, 0, 1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 450, 600);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(53, 23, 91));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel header = new JPanel();
		header.setBackground(new Color(68, 119, 206));
		header.setBounds(0, 0, 434, 65);
		contentPane.add(header);
		header.setLayout(null);
		
		header_h1 = new JLabel("Nova Tarefa");
		header_h1.setForeground(new Color(0, 0, 1));
		header_h1.setFont(new Font("Eras Demi ITC", Font.BOLD, 25));
		header_h1.setBounds(10, 11, 284, 43);
		header.add(header_h1);
		
		header_btn = new JLabel("");
		header_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		header_btn.setIcon(new ImageIcon(ProjectDialogScreen.class.getResource("/view/simbolo-correto.png")));
		header_btn.setForeground(new Color(0, 0, 1));
		header_btn.setFont(new Font("Eras Demi ITC", Font.BOLD, 38));
		header_btn.setBounds(386, 11, 38, 43);
		header_btn.addMouseListener(this);
		header.add(header_btn);
		
		JPanel form = new JPanel();
		form.setBackground(new Color(81, 43, 129));
		form.setBounds(10, 76, 414, 460);
		contentPane.add(form);
		form.setLayout(null);
		
		JLabel form_nomeTarefa = new JLabel("Nome da tarefa:");
		form_nomeTarefa.setForeground(new Color(255, 255, 255));
		form_nomeTarefa.setFont(new Font("Eras Demi ITC", Font.PLAIN, 15));
		form_nomeTarefa.setBounds(10, 11, 394, 24);
		form.add(form_nomeTarefa);
		
		form_nomeTarefa_textField = new JTextField();
		form_nomeTarefa_textField.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));
		form_nomeTarefa_textField.setBorder(null);
		form_nomeTarefa_textField.setBounds(10, 46, 394, 24);
		form.add(form_nomeTarefa_textField);
		form_nomeTarefa_textField.setColumns(10);
		
		JLabel form_descricaoTarefa = new JLabel("Descrição da tarefa:");
		form_descricaoTarefa.setForeground(Color.WHITE);
		form_descricaoTarefa.setFont(new Font("Eras Demi ITC", Font.PLAIN, 15));
		form_descricaoTarefa.setBounds(10, 81, 394, 24);
		form.add(form_descricaoTarefa);
		
		form_descricaoTarefa_textField = new JTextArea();
		form_descricaoTarefa_textField.setFont(new Font("Eras Demi ITC", Font.PLAIN, 13));
		form_descricaoTarefa_textField.setBounds(10, 116, 394, 104);
		addTabTraversalListener(form_descricaoTarefa_textField);
		
		form.add(form_descricaoTarefa_textField);
		
		JLabel form_prazoTarefa = new JLabel("Prazo da tarefa:");
		form_prazoTarefa.setForeground(Color.WHITE);
		form_prazoTarefa.setFont(new Font("Eras Demi ITC", Font.PLAIN, 15));
		form_prazoTarefa.setBounds(10, 231, 394, 24);
		form.add(form_prazoTarefa);
		
		try {
			tarefas_prazoTarefa_textField = new JFormattedTextField(maskCreator());
		} catch (ParseException e) {
			throw new RuntimeException("wasnt possible create textField with mask");
		}
		tarefas_prazoTarefa_textField.setColumns(10);
		tarefas_prazoTarefa_textField.setBorder(null);
		tarefas_prazoTarefa_textField.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));
		tarefas_prazoTarefa_textField.setBounds(10, 266, 394, 20);

		form.add(tarefas_prazoTarefa_textField);
		
		JLabel form_notasTarefa = new JLabel("Notas (opcional):");
		form_notasTarefa.setForeground(Color.WHITE);
		form_notasTarefa.setFont(new Font("Eras Demi ITC", Font.PLAIN, 15));
		form_notasTarefa.setBounds(10, 301, 394, 24);
		form.add(form_notasTarefa);
		
		tarefas_panel_p = new JTextArea();
		tarefas_panel_p.setFont(new Font("Eras Demi ITC", Font.PLAIN, 13));
		tarefas_panel_p.setBounds(10, 336, 394, 104);
		addTabTraversalListener(tarefas_panel_p);
		form.add(tarefas_panel_p);
		header.repaint();
		form.repaint();
	}

	public void updateTask(int idProject, Tasks task) {
		init(idProject);
		this.isUpdateTask = true;
		
		header_h1.setText("Atualizar tarefa");
		
		form_nomeTarefa_textField.setText(task.getName());;
		form_descricaoTarefa_textField.setText(task.getDescription());
		tarefas_panel_p.setText(task.getNotes());
		tarefas_prazoTarefa_textField.setText(DataConverter.Parser(task.getDeadLine()));
		this.atualTask = task.getId();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	private Tasks consumeInputUser() {
		Tasks task = null;
		try {
			task = new Tasks(
				idProject,
				form_nomeTarefa_textField.getText(),
				form_descricaoTarefa_textField.getText(),
				tarefas_panel_p.getText(),
				DataConverter.Converter(tarefas_prazoTarefa_textField.getText())
			);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, e);
		}
		return task;
	}

	private void addTabTraversalListener(JTextArea textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    e.consume(); // Consume the Tab key event
                    if (e.isShiftDown()) {
                        textField.transferFocusBackward(); // Shift+Tab goes to the previous field
                    } else {
                        textField.transferFocus(); // Tab goes to the next field
                    }
                }
            }
        });
    }
	
	private MaskFormatter maskCreator() throws ParseException {
		MaskFormatter mask = null;
        try {
            mask = new MaskFormatter("##/##/####"); // Define a mask for "dd/mm/yyyy"
            mask.setPlaceholderCharacter('_'); // Use '_' as a placeholder for empty positions
        } catch (ParseException e) {
            throw new ParseException(e.getMessage(), 194);
        }
        return mask;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource() == header_btn) {
			try {	
				if(form_nomeTarefa_textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "O campo Nome é obrigatório.");
				} 
				else if (tarefas_prazoTarefa_textField.getText().equals("__/__/____")) {
					JOptionPane.showMessageDialog(contentPane, "O campo Data Limite é obrigatório.");
				} else {

					//happy path
					if(!this.isUpdateTask) {
						tController.save(consumeInputUser());
					} else {
						Tasks task = consumeInputUser();
						task.setId(this.atualTask);
						tController.update(task);
					}
					JOptionPane.showMessageDialog(contentPane, "Projeto Salvo.");
					this.dispose();
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(contentPane, e2);
			}
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

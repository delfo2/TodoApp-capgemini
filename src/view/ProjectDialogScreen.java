package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.projectController;
import model.Project;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class ProjectDialogScreen extends JFrame implements MouseListener {

	private JPanel contentPane;
	private JTextField form_nomeProjeto_textField;
	private JTextArea form_descricaoProjeto_textField;
	private projectController pController = new projectController();;

	public ProjectDialogScreen() {
		init();
	}
	
	private void init() {
		setBackground(new Color(0, 0, 1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 375);
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
		
		JLabel header_h1 = new JLabel("Novo Projeto");
		header_h1.setForeground(new Color(0, 0, 1));
		header_h1.setFont(new Font("Eras Demi ITC", Font.BOLD, 25));
		header_h1.setBounds(10, 11, 284, 43);
		header.add(header_h1);
		
		JLabel header_btn = new JLabel("");
		header_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		header_btn.setIcon(new ImageIcon(ProjectDialogScreen.class.getResource("/view/simbolo-correto.png")));
		header_btn.setForeground(new Color(0, 0, 1));
		header_btn.setFont(new Font("Eras Demi ITC", Font.BOLD, 38));
		header_btn.setBounds(386, 11, 38, 43);
		header.add(header_btn);
		header_btn.addMouseListener(this);
		
		JPanel form = new JPanel();
		form.setBackground(new Color(81, 43, 129));
		form.setBounds(10, 76, 414, 249);
		contentPane.add(form);
		form.setLayout(null);
		
		JLabel form_nomeProjeto = new JLabel("Nome do projeto:");
		form_nomeProjeto.setForeground(new Color(255, 255, 255));
		form_nomeProjeto.setFont(new Font("Eras Demi ITC", Font.PLAIN, 15));
		form_nomeProjeto.setBounds(10, 11, 394, 24);
		form.add(form_nomeProjeto);
		
		form_nomeProjeto_textField = new JTextField();
		form_nomeProjeto_textField.setBorder(null);
		form_nomeProjeto_textField.setBounds(10, 46, 394, 24);
		form.add(form_nomeProjeto_textField);
		form_nomeProjeto_textField.setColumns(10);
		
		JLabel form_descricaoProjeto = new JLabel("Descrição do projeto:");
		form_descricaoProjeto.setForeground(Color.WHITE);
		form_descricaoProjeto.setFont(new Font("Eras Demi ITC", Font.PLAIN, 15));
		form_descricaoProjeto.setBounds(10, 81, 394, 24);
		form.add(form_descricaoProjeto);
		
		form_descricaoProjeto_textField = new JTextArea();
		form_descricaoProjeto_textField.setBounds(10, 116, 394, 104);
		form.add(form_descricaoProjeto_textField);
		header.repaint();
		form.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		try {
			if(!form_nomeProjeto_textField.getText().equals("")) {				
				Project Project = new Project(
					form_nomeProjeto_textField.getText(),
					form_descricaoProjeto_textField.getText()
				);
				pController.save(Project);
				JOptionPane.showMessageDialog(contentPane, "Projeto Salvo.");
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(contentPane, "Preencha o campo Nome.");
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(contentPane, e2);
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

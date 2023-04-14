package edu.java.project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class mainFrame {

	private JFrame frame;
	private JLabel lblLogin;
	private JButton btnNewButton_1;
	private JButton btnNewButton;
	private JLabel lblId;
	private JLabel lblPassword;
	private JTextField textId;
	private JTextField textPassword;
	private JLabel lblProject;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame window = new mainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblId = new JLabel("ID");
		lblId.setBounds(54, 299, 57, 15);
		frame.getContentPane().add(lblId);
		
		lblPassword = new JLabel("PWD");
		lblPassword.setBounds(54, 354, 57, 15);
		frame.getContentPane().add(lblPassword);
		
		btnNewButton = new JButton("New button");
		btnNewButton.setBounds(55, 459, 97, 23);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(221, 469, 97, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		lblLogin = new JLabel("Sign in");
		lblLogin.setBounds(178, 256, 57, 15);
		frame.getContentPane().add(lblLogin);
		
		textId = new JTextField();
		textId.setBounds(169, 296, 116, 21);
		frame.getContentPane().add(textId);
		textId.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setBounds(178, 351, 116, 21);
		frame.getContentPane().add(textPassword);
		textPassword.setColumns(10);
		
		lblProject = new JLabel("New label");
		lblProject.setBounds(178, 87, 57, 15);
		frame.getContentPane().add(lblProject);
	}
}

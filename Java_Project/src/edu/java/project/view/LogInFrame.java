package edu.java.project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import edu.java.project.controller.JoinDaoImpl;

//import edu.java.project.view.MainProgramFrame;

public class LogInFrame extends JFrame {
	JoinDaoImpl dao = new JoinDaoImpl();
//	MainProgramFrame main = new MainProgramFrame();

	private JFrame frame;
	private JButton btnSignUp;
	private JButton btnSignIn;
	private JLabel lblId;
	private JLabel lblPassword;
	public JTextField textId;
	private JTextField textPassword;
	private JLabel lblProject;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInFrame window = new LogInFrame();
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
	public LogInFrame() {
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
		
		btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logIn();
				
			}
		});
		btnSignIn.setBounds(63, 469, 97, 23);
		frame.getContentPane().add(btnSignIn);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Register();
			}
		});
		btnSignUp.setBounds(221, 469, 97, 23);
		frame.getContentPane().add(btnSignUp);
		
		textId = new JTextField();
		textId.setBounds(169, 296, 116, 21);
		frame.getContentPane().add(textId);
		textId.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setBounds(169, 351, 116, 21);
		frame.getContentPane().add(textPassword);
		textPassword.setColumns(10);
		
		lblProject = new JLabel("New label");
		lblProject.setBounds(178, 87, 57, 15);
		frame.getContentPane().add(lblProject);
	}



	protected void logIn() {
		String id = textId.getText();
		String password = textPassword.getText();
		
		try {
			if (dao.login(id, password)) {
				frame.setVisible(false);
				// TODO main 프레임 띄움
				Main();
				
			} else {
				JOptionPane.showMessageDialog(frame, "다시 입력하세요");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	protected void Main() {
		MainProgramFrame app = new MainProgramFrame();
		app.frame.setVisible(true);
	}
	
	protected void Register() {
		RegisterFrame app = new RegisterFrame();
		app.setVisible(true);
		
	}
	
	
}

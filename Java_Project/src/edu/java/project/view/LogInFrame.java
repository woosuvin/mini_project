package edu.java.project.view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import edu.java.project.controller.JoinDaoImpl;
import java.awt.Font;
import javax.swing.SwingConstants;

//import edu.java.project.view.MainProgramFrame;

public class LogInFrame extends JFrame {
//	MainProgramFrame main = new MainProgramFrame();

	private JFrame frame;
	private JButton btnSignUp;
	private JButton btnSignIn;
	private JLabel lblId;
	private JLabel lblPassword;
	public JTextField textId;
	private JPasswordField textPassword;
	private JLabel lblProject;
	
	private final JoinDaoImpl dao = JoinDaoImpl.getInstance();

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
		setTitle("Sign In");
		
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Color color = new Color(248, 248, 255);
		frame.getContentPane().setBackground(color);
		
		lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("D2Coding", Font.BOLD, 15));
		lblId.setBounds(126, 289, 100, 30);
		frame.getContentPane().add(lblId);
		
		lblPassword = new JLabel("PWD");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("D2Coding", Font.BOLD, 15));
		lblPassword.setBounds(126, 348, 100, 30);
		frame.getContentPane().add(lblPassword);
		
		btnSignIn = new JButton("Sign In");
		btnSignIn.setFont(new Font("D2Coding", Font.BOLD, 15));
		btnSignIn.setBorderPainted(false); // 버튼 외곽선 없앰
		btnSignIn.setContentAreaFilled(false);
		btnSignIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logIn();
				
			}
		});
		btnSignIn.setBounds(126, 426, 100, 30);
		frame.getContentPane().add(btnSignIn);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.setFont(new Font("D2Coding", Font.BOLD, 15));
		btnSignUp.setBorderPainted(false); // 버튼 외곽선 없앰
		btnSignUp.setContentAreaFilled(false); // 버튼 안 색 채우기 없앰
		btnSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Register();
			}
		});
		btnSignUp.setBounds(338, 426, 100, 30);
		frame.getContentPane().add(btnSignUp);
		
		textId = new JTextField();
		textId.setBounds(238, 290, 200, 30);
		frame.getContentPane().add(textId);
		textId.setColumns(10);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(238, 349, 200, 30);
		frame.getContentPane().add(textPassword);
		textPassword.setColumns(10);
		textPassword.setEchoChar('♥');
		
		lblProject = new JLabel("Memo Archive");
		lblProject.setFont(new Font("D2Coding", Font.BOLD, 28));
		lblProject.setForeground(new Color(112,128,144));
		lblProject.setHorizontalAlignment(SwingConstants.CENTER);
		lblProject.setBounds(12, 100, 562, 51);
		frame.getContentPane().add(lblProject);
		
	}



	protected void logIn() {
		String id = textId.getText();
		String password = textPassword.getText();
		
		try {
			if (dao.login(id, password)) {
				frame.setVisible(false);
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

package edu.java.project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.java.project.controller.JoinDaoImpl;
import edu.java.project.model.Join;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterFrame extends JFrame {
	JoinDaoImpl dao = new JoinDaoImpl();

	private JPanel contentPane;
	private JTextField textNewId;
	private JTextField textnewPWD;
	private JTextField textNewEmail;
	private JTextField textNewName;
	private JLabel lblNewId;
	private JLabel lblNewPWD;
	private JLabel lblNewEmail;
	private JButton btnSignUp;
	private JButton btnCancel;
	private JLabel lblNewName;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewId = new JLabel("ID");
		lblNewId.setBounds(88, 105, 50, 15);
		contentPane.add(lblNewId);
		
		lblNewPWD = new JLabel("PWD");
		lblNewPWD.setBounds(88, 156, 50, 15);
		contentPane.add(lblNewPWD);
		
		lblNewEmail = new JLabel("Email");
		lblNewEmail.setBounds(88, 215, 50, 15);
		contentPane.add(lblNewEmail);
		
		textNewId = new JTextField();
		textNewId.setBounds(244, 102, 96, 21);
		contentPane.add(textNewId);
		textNewId.setColumns(10);
		
		textnewPWD = new JTextField();
		textnewPWD.setBounds(244, 153, 96, 21);
		contentPane.add(textnewPWD);
		textnewPWD.setColumns(10);
		
		textNewEmail = new JTextField();
		textNewEmail.setBounds(244, 212, 96, 21);
		contentPane.add(textNewEmail);
		textNewEmail.setColumns(10);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signUp();
				
			}
		});
		btnSignUp.setBounds(106, 320, 91, 23);
		contentPane.add(btnSignUp);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		btnCancel.setBounds(317, 320, 91, 23);
		contentPane.add(btnCancel);
		
		lblNewName = new JLabel("Name");
		lblNewName.setBounds(88, 41, 50, 15);
		contentPane.add(lblNewName);
		
		textNewName = new JTextField();
		textNewName.setBounds(244, 38, 96, 21);
		contentPane.add(textNewName);
		textNewName.setColumns(10);
	}



	protected void signUp() {
		String name = textNewName.getText();
		String id = textNewId.getText();
		String password = textnewPWD.getText();
		String email = textNewEmail.getText();
		
		Join newUser = new Join(name, id, password, email);
		
		int result = dao.userRegister(newUser);
		if (result != 1) {
			JOptionPane.showMessageDialog(frame, "회원가입 실패");
			return;
		}
		JOptionPane.showMessageDialog(frame, "회원가입이 완료되었습니다");
		
		MainProgramFrame app = new MainProgramFrame();
		app.setVisible(true);
		this.frame.dispose();
	}
	
	protected void cancel() {
		dispose();
	}
}

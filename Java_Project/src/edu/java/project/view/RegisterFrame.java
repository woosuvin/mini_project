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

import edu.java.project.view.MainProgramFrame;

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
	private JLabel lblIdMsg;
	private JLabel lblEmailMsg;
	
	
	

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
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewId = new JLabel("ID");
		lblNewId.setBounds(12, 116, 50, 15);
		contentPane.add(lblNewId);
		
		lblNewPWD = new JLabel("PWD");
		lblNewPWD.setBounds(12, 167, 50, 15);
		contentPane.add(lblNewPWD);
		
		lblNewEmail = new JLabel("Email");
		lblNewEmail.setBounds(12, 226, 50, 15);
		contentPane.add(lblNewEmail);
		
		textNewId = new JTextField();
		textNewId.setBounds(130, 116, 96, 21);
		contentPane.add(textNewId);
		textNewId.setColumns(10);
		
		textnewPWD = new JTextField();
		textnewPWD.setBounds(130, 167, 96, 21);
		contentPane.add(textnewPWD);
		textnewPWD.setColumns(10);
		
		textNewEmail = new JTextField();
		textNewEmail.setBounds(130, 226, 96, 21);
		contentPane.add(textNewEmail);
		textNewEmail.setColumns(10);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signUp();
				
			}
		});
		btnSignUp.setBounds(30, 331, 91, 23);
		contentPane.add(btnSignUp);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		btnCancel.setBounds(241, 331, 91, 23);
		contentPane.add(btnCancel);
		
		lblNewName = new JLabel("Name");
		lblNewName.setBounds(12, 52, 50, 15);
		contentPane.add(lblNewName);
		
		textNewName = new JTextField();
		textNewName.setBounds(130, 52, 96, 21);
		contentPane.add(textNewName);
		textNewName.setColumns(10);
		
		JButton btnIdCheck = new JButton("중복 확인");
		btnIdCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnIdCheck();
			}
		});
		btnIdCheck.setBounds(264, 116, 91, 23);
		contentPane.add(btnIdCheck);
		
		JButton btnEmailCheck = new JButton("중복 확인");
		btnEmailCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEmailCheck();
			}
		});
		btnEmailCheck.setBounds(264, 225, 91, 23);
		contentPane.add(btnEmailCheck);
		
		lblIdMsg = new JLabel("");
		lblIdMsg.setBounds(264, 149, 143, 15);
		contentPane.add(lblIdMsg);
		
		lblEmailMsg = new JLabel("");
		lblEmailMsg.setBounds(264, 255, 143, 15);
		contentPane.add(lblEmailMsg);
	}







	protected void signUp() {
		String name = textNewName.getText();
		String id = textNewId.getText();
		String password = textnewPWD.getText();
		String email = textNewEmail.getText();
		
		Join newUser = new Join(name, id, password, email);
		
		System.out.println(newUser);
		
		int result = dao.userRegister(newUser);
		if (result != 1) {
			JOptionPane.showMessageDialog(frame, "회원가입 실패");
			return;
		}
		JOptionPane.showMessageDialog(frame, "회원가입이 완료되었습니다");
		
//		MainProgramFrame app = new MainProgramFrame();
//		app.setVisible(true);
		// TODO 로그인 프레임 띄움
		dispose();
	}
	
	protected void cancel() {
		dispose();
	}
	
	
	
	protected void btnIdCheck() {
		try {
			if (dao.checkId(textNewId.getText())) {
				lblIdMsg.setText("중복되는 ID 입니다.");
				textNewId.setText("");
			} else {
				lblIdMsg.setText("사용 가능한 ID 입니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void btnEmailCheck() {
		try {
			if (dao.checkEmail(textNewEmail.getText())) {
				lblEmailMsg.setText("중복되는 Email 입니다.");
				textNewId.setText("");
			} else {
				lblEmailMsg.setText("사용 가능한 Email 입니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

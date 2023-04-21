package edu.java.project.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

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
import javax.swing.SwingConstants;

public class RegisterFrame extends JFrame {

	private final JoinDaoImpl dao = JoinDaoImpl.getInstance();
	
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
	private JButton btnIdCheck;
	private JButton btnEmailCheck;
	
	private boolean checkIdResult = false;
	private boolean checkEmailResult = false;
	

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
		setTitle("Sign Up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Color color = new Color(248, 248, 255);
		contentPane.setBackground(color);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewId = new JLabel("ID");
		lblNewId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewId.setFont(new Font("D2Coding", Font.BOLD, 15));
		lblNewId.setBounds(60, 150, 100, 30);
		contentPane.add(lblNewId);
		
		lblNewPWD = new JLabel("PWD");
		lblNewPWD.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewPWD.setFont(new Font("D2Coding", Font.BOLD, 15));
		lblNewPWD.setBounds(60, 250, 100, 30);
		contentPane.add(lblNewPWD);
		
		lblNewEmail = new JLabel("Email");
		lblNewEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewEmail.setFont(new Font("D2Coding", Font.BOLD, 15));
		lblNewEmail.setBounds(60, 350, 100, 30);
		contentPane.add(lblNewEmail);
		
		textNewId = new JTextField();
		textNewId.setBounds(220, 150, 160, 30);
		contentPane.add(textNewId);
		textNewId.setColumns(10);
		
		textnewPWD = new JTextField();
		textnewPWD.setBounds(220, 250, 160, 30);
		contentPane.add(textnewPWD);
		textnewPWD.setColumns(10);
		
		textNewEmail = new JTextField();
		textNewEmail.setBounds(220, 350, 160, 30);
		contentPane.add(textNewEmail);
		textNewEmail.setColumns(10);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.setBorderPainted(false);
		btnSignUp.setContentAreaFilled(false);
		btnSignUp.setFont(new Font("D2Coding", Font.BOLD, 15));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signUp();
			}
		});
		btnSignUp.setBounds(95, 487, 100, 30);
		contentPane.add(btnSignUp);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBorderPainted(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setFont(new Font("D2Coding", Font.BOLD, 15));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		btnCancel.setBounds(372, 487, 100, 30);
		contentPane.add(btnCancel);
		
		lblNewName = new JLabel("Name");
		lblNewName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewName.setFont(new Font("D2Coding", Font.BOLD, 15));
		lblNewName.setBounds(60, 60, 100, 30);
		contentPane.add(lblNewName);
		
		textNewName = new JTextField();
		textNewName.setBounds(220, 60, 160, 30);
		contentPane.add(textNewName);
		textNewName.setColumns(10);
		
		btnIdCheck = new JButton("중복 확인");
		btnIdCheck.setBorderPainted(false);
		btnIdCheck.setContentAreaFilled(false);
		btnIdCheck.setFont(new Font("D2Coding", Font.BOLD, 13));
		btnIdCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnIdCheck();
			}
		});
		btnIdCheck.setBounds(409, 153, 100, 25);
		contentPane.add(btnIdCheck);
		
		btnEmailCheck = new JButton("중복 확인");
		btnEmailCheck.setBorderPainted(false);
		btnEmailCheck.setContentAreaFilled(false);
		btnEmailCheck.setFont(new Font("D2Coding", Font.BOLD, 13));
		btnEmailCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEmailCheck();
			}
		});
		btnEmailCheck.setBounds(409, 353, 100, 25);
		contentPane.add(btnEmailCheck);
		
		lblIdMsg = new JLabel("");
		lblIdMsg.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdMsg.setFont(new Font("D2Coding", Font.PLAIN, 12));
		lblIdMsg.setBounds(309, 189, 200, 20);
		contentPane.add(lblIdMsg);
		
		lblEmailMsg = new JLabel("");
		lblEmailMsg.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmailMsg.setFont(new Font("D2Coding", Font.PLAIN, 12));
		lblEmailMsg.setBounds(309, 390, 200, 20);
		contentPane.add(lblEmailMsg);
	}







	protected void signUp() {
		Join newUser = null;
		String name = textNewName.getText();
		String id = textNewId.getText();
		String password = textnewPWD.getText();
		String email = textNewEmail.getText();
		if (name.equals("") || id.equals("") || password.equals("") || email.equals("")) {
			JOptionPane.showMessageDialog(contentPane, "항목을 입력하세요");
			return;
		}
		if (checkIdResult == false ||checkEmailResult == false) {
			JOptionPane.showMessageDialog(contentPane, "중복 확인을 하세요");
			return;
		}
		newUser = new Join(name, id, password, email);
		
		System.out.println(newUser);
		
		int result = dao.userRegister(newUser);
		if (result != 1) {
			JOptionPane.showMessageDialog(contentPane, "회원가입 실패");
			return;
		}
		JOptionPane.showMessageDialog(contentPane, "회원가입이 완료되었습니다");
		dispose();
	}
	
	protected void cancel() {
		int confirm = JOptionPane.showConfirmDialog(contentPane, "가입을 취소하시겠습니까?", "Cencel", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			dispose();
		}
	}
	
	
	
	protected boolean btnIdCheck() {
		try {
			if (textNewId.getText().equals("")) {
				lblIdMsg.setText("입력하세요.");
			} else if (dao.checkId(textNewId.getText())) { // 사용 가능
				lblIdMsg.setText("사용 가능한 ID 입니다.");
				checkIdResult = true;
			} else if (!dao.checkId(textNewId.getText())) {
				lblIdMsg.setText("중복되는 ID 입니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkIdResult;
	}

	
	protected boolean btnEmailCheck() {
		try {
			if (textNewEmail.getText().equals("")) {
				lblEmailMsg.setText("입력하세요.");
			} else if (dao.checkEmail(textNewEmail.getText())) { // 사용 가능
				lblEmailMsg.setText("사용 가능한 Email 입니다.");
				checkEmailResult = true;
			} else if (!dao.checkEmail(textNewEmail.getText())){
				lblEmailMsg.setText("중복되는 Email 입니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkEmailResult;
	}
	 
	
}

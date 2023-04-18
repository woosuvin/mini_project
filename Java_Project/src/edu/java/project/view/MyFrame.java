package edu.java.project.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.java.project.controller.ContentDaoImpl;
import edu.java.project.model.Content;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyFrame extends JFrame {

	private final ContentDaoImpl dao = ContentDaoImpl.getInstance();
	private JPanel contentPane;
	private JLabel lblDate;
	private JLabel lblTitle;
	private JLabel lblCategory;
	private JTextField textDate;
	private JTextField textTitle;
	private JTextField textCategory;
	private JTextField textComment;
	private Component parent;
	MainProgramFrame app = new MainProgramFrame();
	private int no;
	
	/**
	 * Launch the application.
	 */
	public static void showMyFrame(Component parent, int no, MainProgramFrame app) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame();
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
	public MyFrame(Component parent, int no, MainProgramFrame app) {
		this.parent = parent; // jframe의 좌표를 알고난 후에 initialize 생성해야됨.
		this.no = no;
		this.app = app;
		initialize();
		readContent();
	}
	
	public void readContent() {
		Content content = dao.read(no);
		textCategory.setText(content.getContent());
		textTitle.setText(content.getTitle());
		textComment.setText(content.getContent());
		textDate.setText(content.getModifiedTime().toString());
	}
	
	public MyFrame() {
		initialize();
		
	}
	
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 현재 창만 닫음
		
		int x = 100;
		int y = 100;
		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblDate = new JLabel("Date");
		lblDate.setBounds(12, 38, 50, 15);
		contentPane.add(lblDate);
		
		lblTitle = new JLabel("Title");
		lblTitle.setBounds(12, 80, 50, 15);
		contentPane.add(lblTitle);
		
		lblCategory = new JLabel("Category");
		lblCategory.setBounds(12, 132, 50, 15);
		contentPane.add(lblCategory);
		
		JLabel lblComment = new JLabel("Comment");
		lblComment.setBounds(12, 192, 50, 15);
		contentPane.add(lblComment);
		
		textDate = new JTextField();
//		textDate.setText(dao.read(no).getModifiedTime().toString()); // TODO값 지정
		textDate.setEnabled(false); // 고정
		textDate.setBounds(112, 35, 224, 21);
		contentPane.add(textDate);
		textDate.setColumns(10);
		
		textTitle = new JTextField();
//		textTitle.setText(dao.read(no).getTitle()); // 값 지정
		textTitle.setEnabled(false); // 고정
		textTitle.setColumns(10);
		textTitle.setBounds(112, 77, 224, 21);
		contentPane.add(textTitle);
		
		textCategory = new JTextField();
//		textCategory.setText(dao.read(no).getCtgr()); // 값 지정
		textCategory.setEnabled(false); // 고정
		textCategory.setColumns(10);
		textCategory.setBounds(112, 129, 224, 21);
		contentPane.add(textCategory);
		
		textComment = new JTextField();
//		textComment.setText(dao.read(no).getContent()); // 값 지정
		textComment.setEnabled(false); // 고정
		textComment.setColumns(10);
		textComment.setBounds(112, 189, 224, 245);
		contentPane.add(textComment);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 MyFrame.this.dispose();
			}
		});
		btnClose.setBounds(172, 530, 91, 23);
		contentPane.add(btnClose);
	}
	
	

}

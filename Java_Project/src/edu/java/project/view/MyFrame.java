package edu.java.project.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import edu.java.project.controller.ContentDaoImpl;
import edu.java.project.model.Content;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class MyFrame extends JFrame {
	
	private final ContentDaoImpl dao = ContentDaoImpl.getInstance();
	private JPanel contentPane;
	private JLabel lblDate;
	private JLabel lblTitle;
	private JLabel lblCategory;
	private JTextField textDate;
	private JTextField textTitle;
	private JTextField textCategory;
	private JTextArea textComment;
	private Component parent;
	MainProgramFrame app = new MainProgramFrame();
	private int no; // 선택한 행 숫자 저장
	private JLabel lblComment;
	private JButton btnClose;
	
	private List<Content> contentList;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	
	/**
	 * Launch the application.
	 */
	public static void showMyFrame(Component parent, int no, MainProgramFrame app) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame(parent, no, app);
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
		textCategory.setText(content.getCtgr());
		textTitle.setText(content.getTitle());
		textComment.setText(content.getContent());
		textDate.setText(content.getModifiedTime().format(DateTimeFormatter.ofPattern("yyyy. MM. dd")));
	}
	
	
	public void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 현재 창만 닫음
		
		int x = 100;
		int y = 100;
		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		
		setBounds(x + 600, y, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Color color = new Color(248, 248, 255);
		contentPane.setBackground(color);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblDate.setBounds(15, 40, 100, 30);
		contentPane.add(lblDate);
		
		lblTitle = new JLabel("Title");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblTitle.setBounds(15, 80, 100, 30);
		contentPane.add(lblTitle);
		
		lblCategory = new JLabel("Category");
		lblCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategory.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblCategory.setBounds(15, 120, 100, 30);
		contentPane.add(lblCategory);
		
		lblComment = new JLabel("Comment");
		lblComment.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComment.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblComment.setBounds(15, 160, 100, 30);
		contentPane.add(lblComment);
		
		textDate = new JTextField();
		textDate.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textDate.setDisabledTextColor(Color.black);
		textDate.setEnabled(false); // 고정
		textDate.setBounds(160, 40, 380, 30);
		contentPane.add(textDate);
		textDate.setColumns(10);
		textDate.setBorder(BorderFactory.createEmptyBorder());
		
		
		textTitle = new JTextField();
		textTitle.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textTitle.setDisabledTextColor(Color.black);
		textTitle.setEnabled(false); // 고정
		textTitle.setColumns(10);
		textTitle.setBounds(160, 80, 380, 30);
		contentPane.add(textTitle);
		textTitle.setBorder(BorderFactory.createEmptyBorder());
		
		
		textCategory = new JTextField();
		textCategory.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textCategory.setDisabledTextColor(Color.black);
		textCategory.setEnabled(false); // 고정
		textCategory.setColumns(10);
		textCategory.setBounds(160, 120, 380, 30);
		contentPane.add(textCategory);
		textCategory.setBorder(BorderFactory.createEmptyBorder());
		
		
		btnClose = new JButton("Close");
		btnClose.setBorderPainted(false);
		btnClose.setContentAreaFilled(false);
		btnClose.setFont(new Font("D2Coding", Font.BOLD, 13));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 MyFrame.this.dispose();
			}
		});
		btnClose.setBounds(240, 510, 100, 25);
		contentPane.add(btnClose);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(160, 160, 380, 328);
		contentPane.add(scrollPane);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		textComment = new JTextArea();
		scrollPane.setViewportView(textComment);
		textComment.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textComment.setDisabledTextColor(Color.black);
		textComment.setEnabled(false); // 고정
		textComment.setColumns(10);
		textComment.setLineWrap(true);
		textComment.setWrapStyleWord(true);
		
	}

}

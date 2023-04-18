package edu.java.project.view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.java.project.model.Content;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;

import edu.java.project.model.Content;
import edu.java.project.controller.ContentDaoImpl;

public class CreateFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textTitle;
	private JTextField textComment;
	private JLabel lblCategory;
	private JRadioButton btnMovie;
	private JRadioButton btnTv;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblTitle;
	private JLabel lblComment;
	private JButton btnSave;
	private JButton btnCancel;
	private JRadioButton btnBook;
	
	private final ContentDaoImpl dao = ContentDaoImpl.getInstance();
	private Component parent; // 부모 컴포넌트(JFrame)를 저장하기 위한 필드
	MainProgramFrame app = new MainProgramFrame(); // notifyContentCreated 메서드를 가지고 있는 객체
//	ContentDaoImpl dao = new ContentDaoImpl();
	private LogInFrame logApp;

	/**
	 * Launch the application.
	 */
	public static void showContactCreateFrame(Component parent, MainProgramFrame app) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateFrame frame = new CreateFrame(parent, app);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
//	public static void showContactCreateFrame() {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CreateFrame frame = new CreateFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public CreateFrame(Component parent, MainProgramFrame app) {
		this.parent = parent;
		this.app = app;
		initialize();
	}
	
	public CreateFrame() {
		initialize();
	}
	
	public void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 닫기 x 버튼의 기본 동작을 현재 창만 닫기(Dispose)로 설정.
		
		// JFrame이 화면에 보이는 좌표
		int x = 100;
		int y = 100;
		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		setBounds(x, y, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCategory = new JLabel("Category");
		lblCategory.setBounds(12, 47, 50, 15);
		contentPane.add(lblCategory);
		
		btnMovie = new JRadioButton("MOVIE");
		btnMovie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleRadioButtonClick(e);
			}
		});
		buttonGroup.add(btnMovie);
		btnMovie.setBounds(82, 43, 113, 23);
		contentPane.add(btnMovie);
		
		btnTv = new JRadioButton("TV");
		btnTv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleRadioButtonClick(e);
			}
		});
		buttonGroup.add(btnTv);
		btnTv.setBounds(199, 43, 113, 23);
		contentPane.add(btnTv);
		
		btnBook = new JRadioButton("BOOK");
		btnBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleRadioButtonClick(e);
			}
		});
		buttonGroup.add(btnBook);
		btnBook.setBounds(316, 43, 113, 23);
		contentPane.add(btnBook);
		
		lblTitle = new JLabel("Title");
		lblTitle.setBounds(12, 95, 50, 15);
		contentPane.add(lblTitle);
		
		textTitle = new JTextField();
		textTitle.setBounds(82, 92, 347, 21);
		contentPane.add(textTitle);
		textTitle.setColumns(10);
		
		lblComment = new JLabel("Comment");
		lblComment.setBounds(12, 143, 50, 15);
		contentPane.add(lblComment);
		
		textComment = new JTextField();
		textComment.setBounds(82, 140, 347, 141);
		contentPane.add(textComment);
		textComment.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewContent();
			}
		});
		btnSave.setBounds(61, 530, 91, 23);
		contentPane.add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelCreateContent();
			}
		});
		btnCancel.setBounds(256, 530, 91, 23);
		contentPane.add(btnCancel);
	}
	
	protected void cancelCreateContent() {
		int confrim = JOptionPane.showConfirmDialog(parent, "작성을 취소하시겠습니까?", "Cancel", JOptionPane.YES_NO_OPTION);
		if (confrim == JOptionPane.YES_OPTION) {
			dispose();
		}
	}

	protected void handleRadioButtonClick(ActionEvent event) {
		JRadioButton btn = (JRadioButton)event.getSource(); // 클릭 이벤트가 발생한 이벤트 소스(라디오버튼)을 찾음
		String btnText = btn.getText(); // 라디오버튼의 텍스트를 찾음
		boolean selected = btn.isSelected(); // 라디오버튼의 체크 여부 확인
	}

	protected void createNewContent() {
		String category = null;
		if (btnMovie.isSelected()) {
			category = "MOVIE";
		} else if (btnTv.isSelected()) {
			category = "TV";
		} else if (btnBook.isSelected()) {
			category = "BOOK";
		}
		String title = textTitle.getText();
		String comment = textComment.getText();
		LocalDateTime date = LocalDateTime.now();
		Content content = new Content(0, category, title, comment, date, date);
		dao.create(content);
		app.notifyContentCreated();
		dispose();
	}
}

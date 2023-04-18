package edu.java.project.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.java.project.controller.ContentDaoImpl;
import edu.java.project.model.Content;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;

public class UpdateFrame extends JFrame {
	
	private final ContentDaoImpl dao = ContentDaoImpl.getInstance();
	private JPanel contentPane;
	private JTextField textComment;
	private JLabel lblCategory;
	private JTextField textTitle;
	private JLabel lblTitle;
	private JLabel lblComment;

	private Component parent; // 부모 컴포넌트(JFrame)를 저장하기 위한 필드
    MainProgramFrame app = new MainProgramFrame();
    private int no; // 업데이트할 연락처의 no를 저장하기 위한 필드
    private JTextField textCategory;
    
	
	/**
	 * Launch the application.
	 */
    public static void showUpdateFrame(Component parent, int no, MainProgramFrame app) {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateFrame frame = new UpdateFrame(parent, no, app);
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
	public UpdateFrame(Component parent, int no, MainProgramFrame app) {
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
	}
	
	public UpdateFrame() {
		initialize();
	}
	
//	private String selectedCategory() {
//		Content content = dao.read(no);
//		String category = content.getCtgr();
//		return category;
//	}

	
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
		
		lblCategory = new JLabel("Category");
		lblCategory.setBounds(12, 47, 50, 15);
		contentPane.add(lblCategory);
		
		textTitle = new JTextField();
		textTitle.setColumns(10);
		textTitle.setBounds(82, 92, 347, 21);
		contentPane.add(textTitle);
		
		lblTitle = new JLabel("Title");
		lblTitle.setBounds(12, 95, 50, 15);
		contentPane.add(lblTitle);
		
		lblComment = new JLabel("Comment");
		lblComment.setBounds(12, 143, 50, 15);
		contentPane.add(lblComment);
		
		textComment = new JTextField();
		textComment.setColumns(10);
		textComment.setBounds(82, 140, 347, 141);
		contentPane.add(textComment);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateContent();
			}
		});
		btnEdit.setBounds(61, 530, 91, 23);
		contentPane.add(btnEdit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeUpdateFrame();
			}
		});
		btnCancel.setBounds(256, 530, 91, 23);
		contentPane.add(btnCancel);
		
		textCategory = new JTextField();
		textCategory.setText(dao.read(no).getCtgr());
		textCategory.setEnabled(false);
		textCategory.setBounds(82, 44, 232, 21);
		contentPane.add(textCategory);
		textCategory.setColumns(10);
	}

	protected void closeUpdateFrame() {
		int confrim = JOptionPane.showConfirmDialog(parent, "작성을 취소하시겠습니까?", "Cancel", JOptionPane.YES_NO_OPTION);
		if (confrim == JOptionPane.YES_OPTION) {
			dispose();
		}		
	}
	
	
	protected void updateContent() {
//		int row 
		
		String ctgr = textCategory.getText();
		String title = textTitle.getText();
		String comment = textComment.getText();
		LocalDateTime date = LocalDateTime.now();
		Content content = new Content(no, ctgr, title, comment, date, date);
		
		int confrim = JOptionPane.showConfirmDialog(parent, "수정사항을 저장하시겠습니까?", "Edit", JOptionPane.YES_NO_OPTION);
		if (confrim == JOptionPane.YES_OPTION) {
			dao.update(content); // 리스트 수정 후 파일 업로드
			app.notifyContentUpdated(); // 메인 프레임에 있는 테이블을 갱신
			dispose();
		}
		
	}
}

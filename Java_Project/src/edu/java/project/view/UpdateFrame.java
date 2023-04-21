package edu.java.project.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import edu.java.project.controller.ContentDaoImpl;
import edu.java.project.model.Content;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class UpdateFrame extends JFrame {
	
	private final ContentDaoImpl dao = ContentDaoImpl.getInstance();
	private JPanel contentPane;
	private JTextArea textComment;
	private JLabel lblCategory;
	private JTextField textTitle;
	private JLabel lblTitle;
	private JLabel lblComment;

	private Component parent; // 부모 컴포넌트(JFrame)를 저장하기 위한 필드
    MainProgramFrame app = new MainProgramFrame();
    private int no; // 업데이트할 연락처의 no를 저장하기 위한 필드
    private JTextField textCategory;
    private JButton btnEdit;
    private JButton btnCancel;
    private int limit = 500; // 글자수
    private JLabel lblLimitChar;
    private JScrollPane scrollPane;
	
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
		textCategory.setText(content.getCtgr());
		textTitle.setText(content.getTitle());
		textComment.setText(content.getContent());
	}
	
	public UpdateFrame() {
		initialize();
	}
	
	
	public void initialize() {
		setTitle("Edit");
		
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
		
		lblCategory = new JLabel("Category");
		lblCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategory.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblCategory.setBounds(15, 60, 100, 30);
		contentPane.add(lblCategory);
		
		textTitle = new JTextField();
		textTitle.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textTitle.setDisabledTextColor(Color.black);
		textTitle.setColumns(10);
		textTitle.setBounds(160, 100, 380, 30);
		contentPane.add(textTitle);
		textTitle.setBorder(BorderFactory.createEmptyBorder());
		
		lblTitle = new JLabel("Title");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblTitle.setBounds(15, 100, 100, 30);
		contentPane.add(lblTitle);
		
		lblComment = new JLabel("Comment");
		lblComment.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComment.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblComment.setBounds(15, 140, 100, 30);
		contentPane.add(lblComment);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBorderPainted(false);
		btnEdit.setContentAreaFilled(false);
		btnEdit.setFont(new Font("D2Coding", Font.BOLD, 13));
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateContent();
			}
		});
		btnEdit.setBounds(160, 500, 100, 25);
		contentPane.add(btnEdit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBorderPainted(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setFont(new Font("D2Coding", Font.BOLD, 13));
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeUpdateFrame();
			}
		});
		btnCancel.setBounds(320, 500, 100, 25);
		contentPane.add(btnCancel);
		
		textCategory = new JTextField();
		textCategory.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textCategory.setDisabledTextColor(Color.black);
		textCategory.setText(dao.read(no).getCtgr());
		textCategory.setEnabled(false);
		textCategory.setBounds(160, 60, 380, 30);
		contentPane.add(textCategory);
		textCategory.setColumns(10);
		textCategory.setBorder(BorderFactory.createEmptyBorder());
		
		lblLimitChar = new JLabel("");
		lblLimitChar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLimitChar.setFont(new Font("굴림", Font.PLAIN, 11));
		lblLimitChar.setBounds(440, 480, 100, 15);
		contentPane.add(lblLimitChar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(160, 140, 380, 340);
		contentPane.add(scrollPane);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		textComment = new JTextArea();
		scrollPane.setViewportView(textComment);
		textComment.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textComment.setDisabledTextColor(Color.black);
		textComment.setColumns(10);
		textComment.setLineWrap(true);
		textComment.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateLimitLabel();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateLimitLabel();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				updateLimitLabel();
			}
		});
		
		// DocumentFilter 추가
        ((javax.swing.text.AbstractDocument) textComment.getDocument())
            .setDocumentFilter(new DocumentFilter() {
                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                        throws BadLocationException {
                    int newLength = fb.getDocument().getLength() - length + text.length();
                    if (newLength <= limit) {
                        super.replace(fb, offset, length, text, attrs);
                        updateLimitLabel();
                    }
                }
            });
		
		
	}

	protected void updateLimitLabel() {
		int count = textComment.getText().length();
		if (count > limit) {
			try {
				textComment.setText(textComment.getText(0, limit));
			} catch (BadLocationException e) {
                e.printStackTrace();
            } 
			count = limit;
		}
		lblLimitChar.setText(count + "/" + limit);
	
}

	protected void closeUpdateFrame() {
		int confrim = JOptionPane.showConfirmDialog(contentPane, "작성을 취소하시겠습니까?", "Cancel", JOptionPane.YES_NO_OPTION);
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
		
		int confrim = JOptionPane.showConfirmDialog(contentPane, "수정사항을 저장하시겠습니까?", "Edit", JOptionPane.YES_NO_OPTION);
		if (confrim == JOptionPane.YES_OPTION) {
			dispose();
			dao.update(content); // 리스트 수정 후 파일 업로드
			app.notifyContentUpdated(); // 메인 프레임에 있는 테이블을 갱신
		}
		
	}
}

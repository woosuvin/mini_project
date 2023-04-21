package edu.java.project.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import edu.java.project.model.Content;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;

import edu.java.project.model.Content;
import edu.java.project.controller.ContentDaoImpl;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class CreateFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textTitle;
	private JTextArea textComment;
	private JLabel lblCategory;
	private JRadioButton btnMovie;
	private JRadioButton btnTv;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblTitle;
	private JLabel lblComment;
	private JButton btnSave;
	private JButton btnCancel;
	private JRadioButton btnBook;
	private boolean selectedRadioBtn = false;
	private int limit = 500; // 글자수
	
	private final ContentDaoImpl dao = ContentDaoImpl.getInstance();
	private Component parent; // 부모 컴포넌트(JFrame)를 저장하기 위한 필드
	MainProgramFrame app = new MainProgramFrame(); // notifyContentCreated 메서드를 가지고 있는 객체
	private LogInFrame logApp;
	
	JFileChooser fileChooser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
	private JLabel lblLimitChar;
	private JScrollPane scrollPane;
	
	
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
		setTitle("New");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 닫기 x 버튼의 기본 동작을 현재 창만 닫기(Dispose)로 설정.
		
		// JFrame이 화면에 보이는 좌표
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
		
		btnMovie = new JRadioButton("MOVIE");
		btnMovie.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnMovie.setContentAreaFilled(false); // 색 채우기 없앰
		btnMovie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleRadioButtonClick(e);
			}
		});
		buttonGroup.add(btnMovie);
		btnMovie.setBounds(160, 60, 100, 30);
		contentPane.add(btnMovie);
		
		btnTv = new JRadioButton("TV");
		btnTv.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnTv.setContentAreaFilled(false); // 색 채우기 없앰
		btnTv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleRadioButtonClick(e);
			}
		});
		buttonGroup.add(btnTv);
		btnTv.setBounds(264, 60, 100, 30);
		contentPane.add(btnTv);
		
		btnBook = new JRadioButton("BOOK");
		btnBook.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnBook.setContentAreaFilled(false); // 색 채우기 없앰
		btnBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleRadioButtonClick(e);
			}
		});
		buttonGroup.add(btnBook);
		btnBook.setBounds(368, 60, 100, 30);
		contentPane.add(btnBook);
		
		lblTitle = new JLabel("Title");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblTitle.setBounds(15, 100, 100, 30);
		contentPane.add(lblTitle);
		
		textTitle = new JTextField();
		textTitle.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textTitle.setDisabledTextColor(Color.black);
		textTitle.setBounds(160, 101, 381, 30);
		contentPane.add(textTitle);
		textTitle.setColumns(10);
		textTitle.setBorder(BorderFactory.createEmptyBorder());
		
		lblComment = new JLabel("Comment");
		lblComment.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComment.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblComment.setBounds(15, 140, 100, 30);
		contentPane.add(lblComment);
		
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("D2Coding", Font.BOLD, 13));
		btnSave.setBorderPainted(false); // 외곽선 없앰
		btnSave.setContentAreaFilled(false);
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewContent();
			}
		});
		btnSave.setBounds(160, 500, 100, 25);
		contentPane.add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBorderPainted(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setFont(new Font("D2Coding", Font.BOLD, 13));
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelCreateContent();
			}
		});
		btnCancel.setBounds(320, 500, 100, 25);
		contentPane.add(btnCancel);
		
		lblLimitChar = new JLabel("");
		lblLimitChar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLimitChar.setFont(new Font("굴림", Font.PLAIN, 11));
		lblLimitChar.setBounds(440, 480, 100, 15);
		contentPane.add(lblLimitChar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(160, 141, 381, 340);
		contentPane.add(scrollPane);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		textComment = new JTextArea();
		scrollPane.setViewportView(textComment);
		textComment.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		        	textComment.append("\n"); // 또는 textArea.append(System.lineSeparator());
		            e.consume();
		        }
		    }
		});
		textComment.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textComment.setDisabledTextColor(Color.black);
		textComment.setColumns(10);
		textComment.setLineWrap(true);
		textComment.setWrapStyleWord(true);
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

	protected void cancelCreateContent() {
		int confrim = JOptionPane.showConfirmDialog(contentPane, "작성을 취소하시겠습니까?", "Cancel", JOptionPane.YES_NO_OPTION);
		if (confrim == JOptionPane.YES_OPTION) {
			dispose();
		}
	}

	protected void handleRadioButtonClick(ActionEvent event) {
		JRadioButton btn = (JRadioButton)event.getSource(); // 클릭 이벤트가 발생한 이벤트 소스(라디오버튼)을 찾음
		String btnText = btn.getText(); // 라디오버튼의 텍스트를 찾음
		boolean selectedRadioBtn = btn.isSelected(); // 라디오버튼의 체크 여부 확인
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
		if (selectedRadioBtn = false) {
			JOptionPane.showMessageDialog(contentPane, "내용을 입력하세요");
			return;
		}
		if (title.isEmpty() || comment.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "내용을 입력하세요");
			return;
		}
		
		Content content = new Content(0, category, title, comment, date, date);
		dao.create(content);
		app.notifyContentCreated();
		dispose();
	}
}

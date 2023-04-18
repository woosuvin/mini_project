package edu.java.project.view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;

import edu.java.project.controller.ContentDaoImpl;
import edu.java.project.model.Content;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainProgramFrame extends JFrame {
	private static final String[] COLUMN_NAMES = {"category", "title", "date"}; // 테이블 컬럼 이름
	
	public JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnNewContent;
	private JButton btnSearch;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnSignOut;
	
	private DefaultTableModel model; // 테이블의 데이터, 컬럼 이름 등을 관리하는 객체.
	private final ContentDaoImpl dao = ContentDaoImpl.getInstance(); // 컨텐츠 정보 관리 객체(Controller)
	private List<Content> contentList; // DB 테이블에서 검색한 결과를 저장할 리스트.
	private JLabel lblUserName;
//	LogInFrame logApp;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainProgramFrame window = new MainProgramFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void notifyContentCreated() { // CreateFrame에서 새 컨텐츠 저장을 성공했을 때 호출할 메서드
		resetTableModel();
		JOptionPane.showMessageDialog(frame, "save");
	}
	
	public void notifyContentUpdated() { // ModifiedFrame에서 컨텐츠 업데이트를 성공했을 때 호출할 메서드
		resetTableModel();
		JOptionPane.showMessageDialog(frame, "edit");
	}
	
	private void resetTableModel() { // JTable을 새로 그림
		model = new DefaultTableModel(null, COLUMN_NAMES); // 데이터가 비워진 모델을 새로 생성하고
		loadContentData(); // 파일에 저장된 데이터를 다시 읽고 테이블 모델에 추가.
		table.setModel(model); // 새롭게 만들어진 테이블 모델을 테이블에 세팅
	}
	
	/**
	 * Create the application.
	 */
	public MainProgramFrame() {
		initialize();
	}
	
	private void loadContentData() {
		contentList = dao.read();
		for (Content x : contentList) {
			Object[] row = {x.getCtgr(), x.getTitle(), x.getModifiedTime()};
			model.addRow(row);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 135, 412, 418);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
//		table.setEnabled(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickShowMyFrame(e);
			}
		});
		
		resetTableModel();
		
		scrollPane.setViewportView(table);
		
		btnNewContent = new JButton("New");
		btnNewContent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showCreateFrame();
			}
		});
		btnNewContent.setBounds(12, 10, 91, 23);
		frame.getContentPane().add(btnNewContent);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchContent();
			}
		});
		btnSearch.setBounds(12, 43, 91, 23);
		frame.getContentPane().add(btnSearch);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showUpdateFrame();
			}
		});
		btnEdit.setBounds(115, 10, 91, 23);
		frame.getContentPane().add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteContent();
			}
		});
		btnDelete.setBounds(115, 43, 91, 23);
		frame.getContentPane().add(btnDelete);
		
		btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeMainFrame();
			}
		});
		btnSignOut.setBounds(333, 10, 91, 23);
		frame.getContentPane().add(btnSignOut);
		
		lblUserName = new JLabel("");
		lblUserName.setBounds(333, 51, 91, 15);
		frame.getContentPane().add(lblUserName);
	}

	// TODO
	protected void clickShowMyFrame(MouseEvent event) {
		int row = table.getSelectedRow();
		if (row < 0) {
			return;
		}
		if (event.getClickCount() == 2) {
			int column = table.getSelectedColumn();
			TableModel tm = (DefaultTableModel)table.getModel();
			Object value = tm.getValueAt(row, column);
			System.out.println(value.toString());
			int number = contentList.get(row).getNo();
			MyFrame.showMyFrame(frame, number, MainProgramFrame.this);
		}
//		Object value = table.gedtValueAt(row, column);
		int number = contentList.get(row).getNo();
		MyFrame.showMyFrame(frame, number, MainProgramFrame.this);
	}


	protected void searchContent() {
		String keyword = JOptionPane.showInputDialog(frame, "Search");
		System.out.println("keyword : " + keyword);
		
		if (keyword == null) {
			JOptionPane.showMessageDialog(frame, "검색어를 입력하세요.");
			return;
		}
		contentList = dao.read(keyword); // 검색결과
		model = new DefaultTableModel(null, COLUMN_NAMES);
		for (Content x : contentList) {
			Object[] row = {x.getCtgr(), x.getTitle(), x.getModifiedTime()};
			model.addRow(row);
		}
		table.setModel(model);
		MyFrame.showMyFrame(btnDelete, DO_NOTHING_ON_CLOSE, null);
	}

	protected void showUpdateFrame() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(frame, "선택하세요", "Delete", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int number = contentList.get(row).getNo();
		UpdateFrame.showUpdateFrame(frame, number, MainProgramFrame.this);
	}

	protected void deleteContent() {
		int row = table.getSelectedRow(); // 인덱스 선택
		if (row == -1) {
			JOptionPane.showMessageDialog(frame, "선택하세요", "Delete", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int confirm = JOptionPane.showConfirmDialog(frame, "삭제하시겠습니까?", "Delete", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.NO_OPTION) {
			return;
		}
		int number = contentList.get(row).getNo();
		dao.delete(number);
		model.removeRow(row);
		
		JOptionPane.showMessageDialog(frame, "삭제되었습니다");
	}
	
	
	protected void closeMainFrame() {
		int confirm = JOptionPane.showConfirmDialog(frame, "로그아웃 후 창이 닫힙니다", "Sign Out", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			frame.dispose();
		}
	}

	protected void showCreateFrame() {
		CreateFrame cApp = new CreateFrame();
		cApp.setVisible(true);
	}

}

package edu.java.project.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import edu.java.project.controller.ContentDaoImpl;
import edu.java.project.controller.JoinDaoImpl;
import edu.java.project.model.Content;
import edu.java.project.model.Join;

import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class MainProgramFrame extends JFrame {
	private static final String[] COLUMN_NAMES = {"category", "title", "date"}; // 테이블 컬럼 이름
	
	DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 셀 내용 가운데 정렬
	
	Join join = new Join();
	
	public JFrame frame;
	public JTable table;
	private JScrollPane scrollPane;
	private JButton btnNewContent;
	private JButton btnSearch;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnSignOut;
	
	private DefaultTableModel model; // 테이블의 데이터, 컬럼 이름 등을 관리하는 객체.
	private final ContentDaoImpl dao = ContentDaoImpl.getInstance(); // 컨텐츠 정보 관리 객체(Controller)
	private final JoinDaoImpl jdao = JoinDaoImpl.getInstance();
	private List<Content> contentList; // DB 테이블에서 검색한 결과를 저장할 리스트.
	private JLabel lblUserName;
	LogInFrame logApp = new LogInFrame();
	private JButton btnShowAll;
	
	private String userId; // 선택한 행
	
	
	
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
		model = createEmptyTableModel(); // 데이터가 비워진 모델을 새로 생성하고
		loadContentData(); // 파일에 저장된 데이터를 다시 읽고 테이블 모델에 추가.
		table.setModel(model); // 새롭게 만들어진 테이블 모델을 테이블에 세팅
		tableSetting();
	}
	
	/**
	 * Create the application.
	 */
	public MainProgramFrame() {
		initialize();
		loadContentData(); // DB에 저장된 데이터 로딩(테이블 초기화)
		
	}
	
	
	private DefaultTableModel createEmptyTableModel() {
		return new DefaultTableModel(null, COLUMN_NAMES) {
			@Override
			public boolean isCellEditable(int row, int column) { // 더블클릭 했을 때 edit 되는거 false로 만듦
				return false;
			}
		};
	}
	
	private void loadContentData() {
		contentList = dao.read();
		for (Content x : contentList) {
			Object[] row = {x.getCtgr(), x.getTitle(), x.getModifiedTime().format(DateTimeFormatter.ofPattern("yyyy. MM. dd"))};
			model.addRow(row);
		}
	}

	public void tableSetting() { // 테이블 변경 값 메서드
		table.setRowHeight(30); // 행 높이 변경
		table.getTableHeader().setReorderingAllowed(false); // 컬럼 헤더 이동 못하게
		table.getColumn("category").setPreferredWidth(100); // 컬럼 너비 지정
		table.getColumn("title").setPreferredWidth(312);
		table.getColumn("date").setPreferredWidth(150);
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(1).setCellRenderer(dtcr);
		tcm.getColumn(2).setCellRenderer(dtcr);
	}
	
//	public void readUserInfo() {
//		Join userInfo = jdao.userInfo(userId);
//		userId = logApp.textId.getText();
//		
//		
//	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() { // TODO
		
		setTitle("메인화면");
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Color color = new Color(248, 248, 255);
		frame.getContentPane().setBackground(color);
		
		scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setBounds(12, 78, 562, 475);
		frame.getContentPane().add(scrollPane);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		table = new JTable();
		table.setFont(new Font("D2Coding", Font.PLAIN, 15));
		model = createEmptyTableModel();
		table.setModel(model);
		scrollPane.setViewportView(table);
		table.setShowGrid(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickShowMyFrame(e);
			}
		});
		Color headerColor = new Color(230,230,250);
		table.getTableHeader().setBackground(headerColor); // 테이블 헤더 색 변경
		tableSetting();
		table.setAutoCreateRowSorter(true); 
		
		
		
		btnNewContent = new JButton("New");
		btnNewContent.setBorderPainted(false);
		btnNewContent.setContentAreaFilled(false);
		btnNewContent.setFont(new Font("D2Coding", Font.BOLD, 13));
		btnNewContent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateFrame.showContactCreateFrame(frame, MainProgramFrame.this);
			}
		});
		btnNewContent.setBounds(12, 10, 75, 25);
		frame.getContentPane().add(btnNewContent);
		
		btnSearch = new JButton("Search");
		btnSearch.setBorderPainted(false);
		btnSearch.setContentAreaFilled(false);
		btnSearch.setFont(new Font("D2Coding", Font.BOLD, 13));
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchContent();
			}
		});
		btnSearch.setBounds(12, 43, 75, 25);
		frame.getContentPane().add(btnSearch);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBorderPainted(false);
		btnEdit.setContentAreaFilled(false);
		btnEdit.setFont(new Font("D2Coding", Font.BOLD, 13));
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showUpdateFrame();
			}
		});
		btnEdit.setBounds(99, 43, 75, 25);
		frame.getContentPane().add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBorderPainted(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setFont(new Font("D2Coding", Font.BOLD, 13));
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteContent();
			}
		});
		btnDelete.setBounds(186, 43, 75, 25);
		frame.getContentPane().add(btnDelete);
		
		btnSignOut = new JButton("Sign Out");
		btnSignOut.setBorderPainted(false);
		btnSignOut.setContentAreaFilled(false);
		btnSignOut.setFont(new Font("D2Coding", Font.BOLD, 13));
		btnSignOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeMainFrame();
			}
		});
		btnSignOut.setBounds(474, 9, 100, 25);
		frame.getContentPane().add(btnSignOut);
		
		lblUserName = new JLabel(); // TODO
		lblUserName.setBounds(483, 53, 91, 15);
		frame.getContentPane().add(lblUserName);
		
		btnShowAll = new JButton("All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetTableModel();
			}
		});
		btnShowAll.setFont(new Font("D2Coding", Font.BOLD, 13));
		btnShowAll.setContentAreaFilled(false);
		btnShowAll.setBorderPainted(false);
		btnShowAll.setBounds(99, 10, 75, 25);
		frame.getContentPane().add(btnShowAll);
	}


	protected void clickShowMyFrame(MouseEvent event) {
		int row = table.getSelectedRow();
		if (event.getClickCount() == 2) {
			int number = contentList.get(row).getNo();
			MyFrame.showMyFrame(frame, number, MainProgramFrame.this);
		}
	}


	protected void searchContent() {
//		String keyword = JOptionPane.showInputDialog(frame, "Search the title");
		String keyword = JOptionPane.showInputDialog(frame, "Search the title", "Search", JOptionPane.DEFAULT_OPTION);
		System.out.println("keyword : " + keyword);
		
		if (keyword == null) {
			JOptionPane.showMessageDialog(frame, "검색어를 입력하세요.");
			return;
		}
		contentList = dao.read(keyword); // 검색결과
		model = createEmptyTableModel();
		for (Content x : contentList) {
			Object[] row = {x.getCtgr(), x.getTitle(), x.getModifiedTime().format(DateTimeFormatter.ofPattern("yyyy. MM. dd"))};
			model.addRow(row);
		}
		table.setModel(model);
		tableSetting();
		
	}

	protected void showUpdateFrame() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(frame, "선택하세요", "Edit", JOptionPane.INFORMATION_MESSAGE);
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
			int number = contentList.get(row).getNo();
			dao.delete(number);
			model.removeRow(row);
			JOptionPane.showMessageDialog(frame, "삭제되었습니다");
			return;
		}
	}
	
	
	protected void closeMainFrame() {
		int confirm = JOptionPane.showConfirmDialog(frame, "로그아웃 후 창이 닫힙니다", "Sign Out", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			frame.dispose();
		}
	}


}

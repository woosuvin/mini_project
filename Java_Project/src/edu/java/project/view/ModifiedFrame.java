package edu.java.project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class ModifiedFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textTitle;
	private JTextField textComment;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifiedFrame frame = new ModifiedFrame();
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
	public ModifiedFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(0, 0, 436, 563);
		contentPane.add(contentPane_1);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(12, 47, 50, 15);
		contentPane_1.add(lblCategory);
		
		JRadioButton btnMovie = new JRadioButton("MOVIE");
		buttonGroup.add(btnMovie);
		btnMovie.setBounds(82, 43, 113, 23);
		contentPane_1.add(btnMovie);
		
		JRadioButton btnTv = new JRadioButton("TV");
		buttonGroup.add(btnTv);
		btnTv.setBounds(199, 43, 113, 23);
		contentPane_1.add(btnTv);
		
		JRadioButton btnBook = new JRadioButton("BOOK");
		buttonGroup.add(btnBook);
		btnBook.setBounds(316, 43, 113, 23);
		contentPane_1.add(btnBook);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(12, 95, 50, 15);
		contentPane_1.add(lblTitle);
		
		textTitle = new JTextField();
		textTitle.setColumns(10);
		textTitle.setBounds(82, 92, 347, 21);
		contentPane_1.add(textTitle);
		
		JLabel lblComment = new JLabel("Comment");
		lblComment.setBounds(12, 143, 50, 15);
		contentPane_1.add(lblComment);
		
		textComment = new JTextField();
		textComment.setColumns(10);
		textComment.setBounds(82, 140, 347, 141);
		contentPane_1.add(textComment);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setBounds(61, 530, 91, 23);
		contentPane_1.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		buttonGroup.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(256, 530, 91, 23);
		contentPane_1.add(btnCancel);
	}
}

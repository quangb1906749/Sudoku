package sudoku;

import java.awt.*;

import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class MainMenu extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnStartGame;
	private JButton btnCreateMatrix;
	private JButton btnInFo;
	private JButton btnExit;
	private JPanel contentPane_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setTitle("Sudoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnTitile = new JPanel();
		contentPane.add(pnTitile, BorderLayout.NORTH);
		
		JLabel lblS = new JLabel("S");
		lblS.setForeground(Color.BLUE);
		lblS.setFont(new Font("Times New Roman", Font.BOLD, 35));
		pnTitile.add(lblS);
		
		JLabel lblU = new JLabel("U");
		lblU.setForeground(Color.RED);
		lblU.setFont(new Font("Times New Roman", Font.BOLD, 35));
		pnTitile.add(lblU);
		
		JLabel lblD = new JLabel("D");
		lblD.setForeground(Color.ORANGE);
		lblD.setFont(new Font("Times New Roman", Font.BOLD, 35));
		pnTitile.add(lblD);
		
		JLabel lblO = new JLabel("O");
		lblO.setForeground(Color.BLUE);
		lblO.setFont(new Font("Times New Roman", Font.BOLD, 35));
		pnTitile.add(lblO);
		
		JLabel lblK = new JLabel("K");
		lblK.setForeground(new Color(51, 204, 0));
		lblK.setFont(new Font("Times New Roman", Font.BOLD, 35));
		pnTitile.add(lblK);
		
		JLabel lblU_1 = new JLabel("U");
		lblU_1.setForeground(Color.RED);
		lblU_1.setFont(new Font("Times New Roman", Font.BOLD, 35));
		pnTitile.add(lblU_1);
		
		contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPane_1, BorderLayout.CENTER);
		
		btnStartGame = new JButton("Start Game");
		btnStartGame.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnStartGame.setBounds(206, 73, 255, 68);
		btnStartGame.addActionListener(this);
		contentPane_1.add(btnStartGame);
		
		btnCreateMatrix = new JButton("Create New Matrix");
		btnCreateMatrix.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnCreateMatrix.setBounds(206, 172, 255, 63);
		btnCreateMatrix.addActionListener(this);
		contentPane_1.add(btnCreateMatrix);
		
		btnInFo = new JButton("Game's Information");
		btnInFo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnInFo.setBounds(206, 260, 255, 68);
		btnInFo.addActionListener(this);
		contentPane_1.add(btnInFo);
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnExit.setBounds(206, 357, 255, 67);
		btnExit.addActionListener(this);
		contentPane_1.add(btnExit);
		
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(btnStartGame.getText())) {
			Sudoku sudoku = new Sudoku();
			sudoku.setVisible(true);
			this.dispose();
		}else if (e.getActionCommand().equals(btnCreateMatrix.getText())) {
			CreateMatrix sudoku2 = new CreateMatrix();
			sudoku2.setVisible(true);
			this.dispose();
		}else if (e.getActionCommand().equals(btnInFo.getText())) {
			JOptionPane.showMessageDialog(contentPane_1, "Author's Information:\nNguyen Thanh Quang\nMSSV: B1906749", "Game's Information", JOptionPane.INFORMATION_MESSAGE);
		}else if (e.getActionCommand().equals(btnExit.getText())) {
			this.dispose();
		}
	}
}

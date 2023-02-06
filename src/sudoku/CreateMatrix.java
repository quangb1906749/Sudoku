package sudoku;

import java.awt.*;

import javax.swing.border.EmptyBorder;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;


public class CreateMatrix extends JFrame implements ActionListener, KeyListener {

	private JPanel contentPane;
	
	ArrayList<String> S = new ArrayList<String>();
	
	String str;
	int I, J;
	File file = new File("Matrix.txt");
	int an[] = {2, 35, 52};
	int [][]b = new int[9][9];
	private JPanel matrixPanel;
	private JButton btnOpenFile;
	private JButton btnNewMatrix;
	private JPanel panel;
	private JButton[][] bt;
	private JButton btnSave;
	private JButton btnMainMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateMatrix frame = new CreateMatrix();
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
	public CreateMatrix() {
		setTitle("Sudoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()) {
				S.add(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(matrixPanel, "An error occourred.", "Error", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		btnNewMatrix = new JButton("New Matrix");
		btnNewMatrix.addActionListener(this);
		panel.add(btnNewMatrix);
		
		btnOpenFile = new JButton("Open File");
		btnOpenFile.addActionListener(this);
		panel.add(btnOpenFile);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(this);
		panel.add(btnSave);
		
		btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(this);
		panel.add(btnMainMenu);
		
		
		matrixPanel = new JPanel();
		contentPane.add(matrixPanel, BorderLayout.CENTER);
		matrixPanel.setLayout(new GridLayout(9,9));
		
		bt = new JButton[9][9];
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++) {
				bt[i][j] = new JButton();
				bt[i][j].addActionListener(this);
				bt[i][j].addKeyListener(this);
				bt[i][j].setActionCommand(i + " " + j);
				bt[i][j].setBackground(Color.white);
				bt[i][j].setFont(new Font("UTM Micra", 1, 30));
				bt[i][j].setForeground(Color.black);
				matrixPanel.add(bt[i][j]);
			}
		
		for (int i = 0; i < 9; i += 3)
			for (int j = 0; j < 9; j += 3) {
				bt[i][j].setBorder(BorderFactory.createMatteBorder(3,3,1,1, Color.black));
				bt[i][j + 2].setBorder(BorderFactory.createMatteBorder(3,1,1,3, Color.black));
				bt[i + 2][j + 2].setBorder(BorderFactory.createMatteBorder(1,1,3,3, Color.black));
				bt[i + 2][j].setBorder(BorderFactory.createMatteBorder(1,3,3,1, Color.black));
				bt[i][j + 1].setBorder(BorderFactory.createMatteBorder(3,1,1,1, Color.black));
				bt[i + 1][j + 2].setBorder(BorderFactory.createMatteBorder(1,1,1,3, Color.black));
				bt[i + 2][j + 1].setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.black));
				bt[i + 1][j].setBorder(BorderFactory.createMatteBorder(1,3,1,1, Color.black));
				bt[i + 1][j + 1].setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
			}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
					bt[i][j].setBackground(Color.white);
		if (e.getActionCommand().equals(btnNewMatrix.getText())) {
			for (int i = 0; i < 9; i++)
	        	for (int j = 0; j < 9; j++) {
	        		bt[i][j].setText("");
	        		b[i][j] = 0;
	        	}
		}else if (e.getActionCommand().equals(btnOpenFile.getText())) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = fileChooser.getSelectedFile();
			    readNewMatrix(selectedFile);
			}
						
		}else if (e.getActionCommand().equals(btnSave.getText())) {	
			boolean testSave = true;
			for (int i = 0; i < 9; i++)
	        	for (int j = 0; j < 9; j++) {
	        		if(b[i][j] == 0 || !isValidPlacementInMatrix(b, b[i][j], i, j)) {
	        			testSave = false;
	        		}
	        	}
			if(testSave) {
				saveFile();
			}
			else {
				JOptionPane.showMessageDialog(matrixPanel, "Ma trận không hợp lệ. Lưu không thành công.", "Báo lỗi!", JOptionPane.INFORMATION_MESSAGE);
			}
		}else if (e.getActionCommand().equals(btnMainMenu.getText())) {
			MainMenu frame = new MainMenu();
			frame.setVisible(true);
			this.dispose();
		} else {
			String s = e.getActionCommand();
			int k = s.indexOf(32);
			int i = Integer.parseInt(s.substring(0, k));
			int j = Integer.parseInt(s.substring(k + 1, s.length()));
			I = i;
			J = j;
			if (b[I][J] > 0) {
				for (i = 0; i < 9; i++)
					for (j = 0; j < 9; j++)
						if (b[i][j] == b[I][J])
							bt[i][j].setBackground(Color.GRAY);
			}
		}
	}
	
	public void saveFile() {
		try (PrintWriter pw = new PrintWriter(file)){
			String stringMatrix = new String();
			for (int i = 0; i < 9; i++)
	        	for (int j = 0; j < 9; j++)
	        		stringMatrix = stringMatrix + b[i][j];
			S.add(stringMatrix);
			for(String i: S) {
				pw.println(i);
			}
			JOptionPane.showMessageDialog(matrixPanel, "Đã lưu.", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(matrixPanel, "Lưu không thành công.", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void readNewMatrix(File file) {
		String matrix = new String();
		try(Scanner sc = new Scanner(file)) {
				matrix = sc.nextLine();
				for (int i = 0; i < 9; i++)
		        	for (int j = 0; j < 9; j++) {
		        		b[i][j] = matrix.charAt(i * 9 + j) - 48;
		        		bt[i][j].setText(b[i][j]+"");
		        	}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(matrixPanel, "Không thể mở File.", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int v = e.getKeyCode();
		if ((v >= 49 && v <= 57) || (v >= 97 && v <= 105)) {
			if (v >= 49 && v <= 57)
				v -= 48;
			if (v >= 97 && v <= 105)
				v -= 96;
			bt[I][J].setText(v + "");
			if (isValidPlacement(b, v, I, J)) {
				bt[I][J].setForeground(Color.black);
			}
			else {
				bt[I][J].setForeground(Color.RED);
			}
			b[I][J] = v;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean isNumberInRow(int[][] board, int number, int row) {
		for(int i = 0; i < 9; i++) {
			if (board[row][i] == number) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isNumberInColumn(int[][] board, int number, int column) {
		for(int i = 0; i < 9; i++) {
			if (board[i][column] == number) {
				return true;
			}
		}
		return false;
	}

	private boolean isNumberInBox(int[][] board, int number, int row, int column) {
		int localBoxRow = row - row % 3;
		int localBoxColumn = column - column % 3;
		for(int i = localBoxRow; i < localBoxRow + 3; i++)
			for(int j = localBoxColumn; j < localBoxColumn + 3; j++) {
				if(board[i][j] == number) {
					return true;
				}
			}
		return false;
	}
	
	private boolean isValidPlacement(int[][] board, int number, int row, int column) {		
		return !isNumberInRow(board, number, row) &&
				!isNumberInColumn(board, number, column) &&
				!isNumberInBox(board, number, row, column);
	}
	
	private boolean isValidPlacementInMatrix(int[][] board, int number, int row, int column) {		

		for(int i = 0; i < 9; i++) {
			if ((board[row][i] == number && i != column)||(board[i][column] == number && i != row)) {
				return false;
			}
		}
		int localBoxRow = row - row % 3;
		int localBoxColumn = column - column % 3;
		for(int i = localBoxRow; i < localBoxRow + 3; i++)
			for(int j = localBoxColumn; j < localBoxColumn + 3; j++) {
				if(board[i][j] == number && i != row && j != column) {
					return false;
				}
			}
		return true;
	}
}

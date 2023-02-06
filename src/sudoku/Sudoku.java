package sudoku;

import java.awt.*;

import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Sudoku extends JFrame implements ActionListener, KeyListener {

	private JPanel contentPane;
	
	ArrayList<String> S = new ArrayList<String>();
	
	String str;
	int I, J;
	int an[] = {18, 35, 52};
	File file = new File("Matrix.txt");
	int [][]a = new int[9][9];
	int [][]aa = new int[9][9];
	int x[] = new int[81];
	int y[] = new int[81];
	int temp[] = new int[81];
	private JPanel matrixPanel;
	private JComboBox cbxLevel;
	private JButton btnSudokuSolver;
	private JButton btnNewGame;
	private JPanel panel;
	private JButton[][] bt;
	private JButton btnReset;
	private JButton btnSelectFile;
	private JButton btnMainMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sudoku frame = new Sudoku();
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
	public Sudoku() {
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
		
		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(this);
		panel.add(btnNewGame);
		
		btnSelectFile = new JButton("Select File");
		btnSelectFile.addActionListener(this);
		panel.add(btnSelectFile);
		
		cbxLevel = new JComboBox();
		cbxLevel.addItem("Dễ");
		cbxLevel.addItem("Trung bình");
		cbxLevel.addItem("Khó");
		cbxLevel.setSelectedIndex(0);
		panel.add(cbxLevel);
		
		btnSudokuSolver = new JButton("Sudoku Solver");
		btnSudokuSolver.addActionListener(this);
		panel.add(btnSudokuSolver);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(this);
		panel.add(btnReset);
		
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
		try {
			creatMatrix();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void creatMatrix() throws FileNotFoundException {
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++) {
				bt[i][j].setText("");
				bt[i][j].setForeground(Color.black);
				bt[i][j].setBackground(Color.white);
			}
		if(S.size()==1)
			str = S.get(0);
		else
			str = S.get((int)((S.size() - 1) * Math.random()) + 1);
        int N = 0;
        for (int i = 0; i < 9; i++)
        	for (int j = 0; j < 9; j++)
        		a[i][j] = str.charAt(i * 9 + j) - 48;
        
        for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j ++) {
				x[N] = i;
				y[N] = j;
				temp[N++] = (int) (10000 * Math.random());
			}
		for (int i = 0; i < N - 1; i ++)
			for (int j = i + 1; j < N; j++)
				if (temp[i] > temp[j]) {
					int t = x[i];
					x[i] = x[j];
					x[j] = t;
					
					t = y[i];
					y[i] = y[j];
					y[j] = t;
					
					t = temp[i];
					temp[i] = temp[j];
					temp[j] = t;
				}
		for (int i = 0; i < an[cbxLevel.getSelectedIndex()]; i++) {
			a[x[i]][y[i]] = 0;
		}
		for (int i = 0; i < 9; i++)
        	for (int j = 0; j < 9; j++)
        		if (a[i][j] > 0)
        			bt[i][j].setText(a[i][j] + "");
		for (int i = 0; i < 9; i++)
        	for (int j = 0; j < 9; j++)
        		aa[i][j] = a[i][j];
    }

	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
					bt[i][j].setBackground(Color.white);
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(btnNewGame.getText())) {
			try {
				creatMatrix();
			} catch (FileNotFoundException er) {
				// TODO Auto-generated catch block
				er.printStackTrace();
			}
		}else if (e.getActionCommand().equals(btnSelectFile.getText())) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(contentPane);
			if (result == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = fileChooser.getSelectedFile();
			    try {
			    	S.clear();
			    	S = new ArrayList<String>();
					Scanner sc = new Scanner(selectedFile);
					while(sc.hasNextLine()) {
						S.add(sc.nextLine());
					}
					sc.close();
				} catch (FileNotFoundException er) {
					JOptionPane.showMessageDialog(matrixPanel, "An error occourred.", "Error", JOptionPane.INFORMATION_MESSAGE);
					er.printStackTrace();
				}
			}
		}else if (e.getActionCommand().equals(btnSudokuSolver.getText())) {
			//TODO
			if (solveBoard(a)) {
				for (int i = 0; i < 9; i++)
		        	for (int j = 0; j < 9; j++)
		        		if(aa[i][j] == 0 ) {
		        			bt[i][j].setText(a[i][j] + "");
		        			bt[i][j].setForeground(Color.BLUE);
		        		}
				JOptionPane.showMessageDialog(matrixPanel, "Giải thành công!", "Thành công!", JOptionPane.INFORMATION_MESSAGE);
				}
			else {
				JOptionPane.showMessageDialog(matrixPanel, "Không thể giải!", "Báo lỗi!", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}else if (e.getActionCommand().equals(btnReset.getText())) {	
			for(int i = 0; i < 9; i++)
				for(int j = 0; j < 9; j++) {
					bt[i][j].setText("");
					bt[i][j].setForeground(Color.black);
					bt[i][j].setBackground(Color.white);
				}
			for (int i = 0; i < 9; i++)
	        	for (int j = 0; j < 9; j++) {
	        		a[i][j] = aa[i][j];
	        		if (a[i][j] > 0)
	        			bt[i][j].setText(a[i][j] + "");
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
			if (a[I][J] > 0) {
				for (i = 0; i < 9; i++)
					for (j = 0; j < 9; j++)
						if (a[i][j] == a[I][J])
							bt[i][j].setBackground(Color.GRAY);
			}
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
			if (aa[I][J] == 0) {
				bt[I][J].setText(v + "");
				if (isValidPlacement(a, v, I, J)) {
					a[I][J] = v;
					bt[I][J].setForeground(Color.BLUE);
					boolean check = true;
					for (int i = 0; i <  9; i++)
						for (int j = 0; j < 9; j++) {
							if(a[i][j]==0)
								check = false;
						}
					if (check) {
						JOptionPane.showMessageDialog(matrixPanel, "Bạn đã chiến thắng", "Chúc mừng!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					bt[I][J].setForeground(Color.RED);
				}
			}
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
	
	private boolean isValidPlacement(int[][] board, int number, int row, int column) { //Chỉ sử dụng trước khi điền số cần kiểm tra vào ma trận
		return !isNumberInRow(board, number, row) &&
				!isNumberInColumn(board, number, column) &&
				!isNumberInBox(board, number, row, column);
	}
	
	private boolean solveBoard(int[][] board) {
		for(int row = 0; row < 9; row++) {
			for(int column = 0; column < 9; column++) {
				if (board[row][column] == 0) {
					for(int numberToTry = 1; numberToTry <= 9; numberToTry++) {
						if(isValidPlacement(board, numberToTry, row, column)) {
							board[row][column] = numberToTry;
							
							if(solveBoard(board)) {
								return true;
							}
							else {
								board[row][column] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

}

import java.util.Scanner;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*; //for saving, PrintWriter
import javax.imageio.ImageIO; //set icon image
import java.util.ArrayList;

public class GUI extends JFrame{
	
	//red will always move 1st
	boolean redTurn = true;
	boolean blueTurn = false;
	
	//counter used for image transformation and flipping image each turn
	int turnCounter = 0;
	int redTurnCounter = 0;
	int blueTurnCounter = 0;
	
	Board gameBoard = new Board();
	//making copy so that original setup of board remain the same
	String[][] boardCopy = gameBoard.getBoard();
	
	//making button based on number of tile
	JButton[][] button = new JButton[gameBoard.getRow()][gameBoard.getColumn()]; 
	
	//intial and destination tile of clicked piece
	String initialTile = null;
	String destinationTile = null;
	
	//for checking legal move method
	boolean legalMove;
	boolean legal;
	
	//available move get from each piece class
	List<int[]> allowMoveList = new ArrayList<int[]>();
	
	// copies of string and row column of clicked button incase illegal move
	String initialTileCopy;
	int initialRowCopy;
	int initialColumnCopy;
	
	
	public GUI(){
		super("Webale Chess");
		
		JMenuBar bar = new JMenuBar();
		setJMenuBar (bar);
		JMenu menu = new JMenu("Menu");
		bar.add(menu);
		
		JMenuItem save = new JMenuItem("Save Game");
		JMenuItem load = new JMenuItem("Load Game");
		save.addActionListener(new SaveActionListener());
		load.addActionListener(new LoadActionListner());
		menu.add(save);
		menu.add(load);
		
		
		
		JPanel panel = new JPanel(new GridLayout(gameBoard.getRow(),gameBoard.getColumn()));
		//making buttons based on row and column
		for (int i=0; i<gameBoard.getRow(); i++){
			for (int j=0; j<gameBoard.getColumn(); j++){
				button[i][j] = new JButton();
				panel.add(button[i][j]);
				setIconBoard(button,i,j,boardCopy);
				ButtonActions actions = new ButtonActions(i,j, button, boardCopy);
			}	
		}
		add(panel);
		
		
		setSize(900,700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// This class is the core  class that handles action performed when clicking piece and moving pieces
	// Almost all methods are called by this class when a button is clicked
	// It acts as a BIG action Listener to each button
	class ButtonActions  {
		public ButtonActions(int row, int column, JButton[][] button, String[][] boardCopy){
			button[row][column].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e){
					String saved = boardCopy[row][column];
					//will go through here when the is no clicked piece yet
					if(initialTile == null && saved.startsWith(getTurn(redTurn))){
						initialTileCopy = saved;
						initialRowCopy = row;
						initialColumnCopy = column;
						initialTile = saved;
						try{
							getInitialPiece(row, column, getTurn(redTurn), boardCopy);
						}
						catch(Exception f){
							System.out.println(f);
						}
						boardCopy[row][column] = " -  ";
						destinationTile = null;
					}
					//after clicked a piece, will go here to check the move logic of that piece
					else if(initialTile != null && destinationTile == null){
						destinationTile = saved;
						legal = checkLegalMoves(row, column, initialTile);
						//System.out.println(legal);  put this for error checking if needed
						//if a move is legal, move here
						if (legal == true){
							setDefaultColorSquare();
							boardCopy[row][column] = initialTile;
							removeInitiaTileIcon(button);
							setIconBoard(button, row, column, boardCopy);
							gameBoard.flipBoard(boardCopy);
							turnCounter++;
							
							if(initialTile.startsWith("r")){
								redTurnCounter++;
								redPieceTransform(redTurnCounter);
							}
							else if(initialTile.startsWith("b")){
								blueTurnCounter++;
								bluePieceTransform(blueTurnCounter);
							}
							
							setIconFlip(button, boardCopy);
							
							if (redTurn == true){
								redTurn = false;
								blueTurn = true;
							}
							else{
								redTurn = true;
								blueTurn = false;
							}
							initialTile = null;
							endStatus(destinationTile);
						}
						//if move not legal, reset all the variables as of that no piece were clicked yet
						else{
							boardCopy[initialRowCopy][initialColumnCopy] = initialTileCopy;
							setDefaultColorSquare();

							if (redTurn == true){
								redTurn = true;
								blueTurn = false;
							}
							else{
								redTurn = false;
								blueTurn = true;
							}
							initialTile = null;
						}
					}
					//if an empty tile is cliked, go here
					else{
						initialTile = null;
						destinationTile = null;
					}
					
				}
			});
		}	
	}
	//ActionListner class for saving game, record the board string and turn counter in a txt file
	//Irfan
	private class SaveActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			try{
				PrintWriter saveGame = new PrintWriter("savegame.txt");
				String turn = getTurn(redTurn);
				saveGame.print(turn);
				if(turnCounter < 10){
					Integer.toString(turnCounter);
					saveGame.print("0" + turnCounter);
				}else{
					saveGame.print(turnCounter);
				}
				
				saveGame.print(redTurnCounter);
				saveGame.print(blueTurnCounter);
				for(int i=0; i<8; i++){
					for(int j=0; j<7; j++){
						saveGame.print(boardCopy[i][j]);
					}
				}
				System.out.println("Game succesfully saved");
				saveGame.close();
			}catch(Exception f) {
				f.printStackTrace();  //to print which line of code is problem
				System.out.println("Unable to save game");
			}
		}
	}
	//Action listener for load menu, it will read the txt file and print out the board based on the txt
	//it will also replace the existing counter to the saved counter 
	//Irfan
	private class LoadActionListner implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			try{
				int begin = 5;  //index to start and end read from file
				int end = 9;
				Scanner scan = new Scanner(new File("savegame.txt"));
				String word = scan.useDelimiter("\\A").next();
				if (word.startsWith("r")){
					redTurn = true;
					blueTurn = false;
				}
				else if (word.startsWith("b")){
					blueTurn = true;
					redTurn = false;
				}
				turnCounter = Integer.parseInt(word.substring(1,3));
				redTurnCounter = Integer.parseInt(word.substring(3,4));
				blueTurnCounter = Integer.parseInt(word.substring(4,5));
				for (int i=0; i <8; i++){
					for (int j=0; j<7; j++){
						boardCopy[i][j] = word.substring(begin,end);
						begin += 4;
						end += 4;
					}
				}
				scan.close();
				setIconFlip(button, boardCopy);
				System.out.println("Game succesfully loaded");
			}catch(IOException g){
				System.out.println("Something went wrong in loading game");
			}
		}
	}
	
	//get the current turn 
	//Hakeen
	public String getTurn(boolean redTurn){
		String turn;
		if (redTurn == true)
			turn = "r";
		else
			turn = "b";
		
		return turn;
	}
	
	//check to see if triangle and pluss of red piece should transform after each move
	//Farisha
	public void redPieceTransform(int redTurnCounter){
		if(redTurnCounter >= 2 && redTurnCounter%2 == 0){
			for (int i=0; i<gameBoard.getRow(); i++){
				for (int j=0; j<gameBoard.getColumn(); j++){
					if(boardCopy[i][j].equals("rt1 ")){
						boardCopy[i][j] = "rp1 ";
					}
					else if(boardCopy[i][j].equals("rt2 ")){
						boardCopy[i][j] = "rp2 ";
					}
					else if(boardCopy[i][j].equals("rp1 ")){
						boardCopy[i][j] = "rt1 ";
					}
					else if(boardCopy[i][j].equals("rp2 ")){
						boardCopy[i][j] = "rt2 ";
					}
				}
			}
		}
	}
	//check to see if triangle and pluss of red piece should transform after each move
	//Farisha
	public void bluePieceTransform(int blueTurnCounter){
		if(blueTurnCounter >= 2 && blueTurnCounter%2 == 0){
			for (int i=0; i<gameBoard.getRow(); i++){
				for (int j=0; j<gameBoard.getColumn(); j++){
					if(boardCopy[i][j].equals("bt1 ")){
						boardCopy[i][j] = "bp1 ";
					}
					else if(boardCopy[i][j].equals("bt2 ")){
						boardCopy[i][j] = "bp2 ";
					}
					else if(boardCopy[i][j].equals("bp1 ")){
						boardCopy[i][j] = "bt1 ";
					}
					else if(boardCopy[i][j].equals("bp2 ")){
						boardCopy[i][j] = "bt2 ";
					}
				}
			}
		}
	}
	
	//method to scale Image of pieces
	//Zaim
	public ImageIcon loadImage(String source){
		Image image = new ImageIcon(this.getClass().getResource(source)).getImage();
		Image scaledImage = image.getScaledInstance(90,90, Image.SCALE_SMOOTH);
		return new ImageIcon(scaledImage);
	}
	
	//method to set the Image of each button based on what string(piece) is on that location
	//Zaim
	public void setIconBoard(JButton[][] button, int row, int column, String[][] boardCopy){
		try{
			if(boardCopy[row][column].startsWith("ba")){
				button[row][column].setIcon(loadImage("Images/BArrow.png"));
			}
			else if(boardCopy[row][column].startsWith("bp")){
				button[row][column].setIcon(loadImage("Images/BPlus.png"));
			}
			else if(boardCopy[row][column].startsWith("bt")){
				button[row][column].setIcon(loadImage("Images/BTriangle.png"));
			}
			else if(boardCopy[row][column].startsWith("bs")){
				button[row][column].setIcon(loadImage("Images/BSun.png"));
			}
			else if(boardCopy[row][column].startsWith("bc")){
				button[row][column].setIcon(loadImage("Images/BChevron.png"));
			}
			else if(boardCopy[row][column].startsWith("ra")){
				button[row][column].setIcon(loadImage("Images/RArrow.png"));
			}
			else if(boardCopy[row][column].startsWith("rp")){
				button[row][column].setIcon(loadImage("Images/RPlus.png"));
			}
			else if(boardCopy[row][column].startsWith("rt")){
				button[row][column].setIcon(loadImage("Images/RTriangle.png"));
			}
			else if(boardCopy[row][column].startsWith("rs")){
				button[row][column].setIcon(loadImage("Images/RSun.png"));
			}
			else if(boardCopy[row][column].startsWith("rc")){
				button[row][column].setIcon(loadImage("Images/RChevron.png"));
			}
		}catch(Exception k){
			System.out.println(k);
		}
	}
	//method to set image after a move have been made, after the board has flip
	//Hakeem
	public void setIconFlip(JButton[][] button, String[][] boardCopy){
		try{
			for (int i=0; i <gameBoard.getRow(); i++){
				for (int j=0; j<gameBoard.getColumn(); j++){
					if(boardCopy[i][j].startsWith(" - ")){
						button[i][j].setIcon(null);
					}
					else if(boardCopy[i][j].startsWith("ba") && (turnCounter%2 == 0) && turnCounter>0){
						button[i][j].setIcon(loadImage("Images/BArrow.png"));
					}
					else if(boardCopy[i][j].startsWith("ba") && (turnCounter%2 != 0)&& turnCounter>0){
						button[i][j].setIcon(loadImage("Images/BArrowFlip.png"));
					}
					else if(boardCopy[i][j].startsWith("bp")){
						button[i][j].setIcon(loadImage("Images/BPlus.png"));
					}
					else if(boardCopy[i][j].startsWith("bt") && (turnCounter%2 == 0)&& turnCounter>0){
						button[i][j].setIcon(loadImage("Images/BTriangle.png"));
					}
					else if(boardCopy[i][j].startsWith("bt") && (turnCounter%2 != 0)&& turnCounter>0){
						button[i][j].setIcon(loadImage("Images/BTriangleFlip.png"));
					}
					else if(boardCopy[i][j].startsWith("bs")){
						button[i][j].setIcon(loadImage("Images/BSun.png"));
					}
					else if(boardCopy[i][j].startsWith("bc")&& (turnCounter%2 == 0)&& turnCounter>0){
						button[i][j].setIcon(loadImage("Images/BChevron.png"));
					}
					else if(boardCopy[i][j].startsWith("bc")&& (turnCounter%2 != 0)&& turnCounter>0){
						button[i][j].setIcon(loadImage("Images/BChevronFlip.png"));
					}
					else if(boardCopy[i][j].startsWith("ra") && (turnCounter%2 == 0)&& turnCounter>0){
						button[i][j].setIcon(loadImage("Images/RArrow.png"));
					}
					else if(boardCopy[i][j].startsWith("ra") && (turnCounter%2 != 0)&& turnCounter>0){
						button[i][j].setIcon(loadImage("Images/RArrowFlip.png"));
					}
					else if(boardCopy[i][j].startsWith("rp")){
						button[i][j].setIcon(loadImage("Images/RPlus.png"));
					}
					else if(boardCopy[i][j].startsWith("rt") && (turnCounter%2 == 0)&& turnCounter>0){
						button[i][j].setIcon(loadImage("Images/RTriangle.png"));
					}
					else if(boardCopy[i][j].startsWith("rt") && (turnCounter%2 != 0)&& turnCounter>0){
						button[i][j].setIcon(loadImage("Images/RTriangleFlip.png"));
					}
					else if(boardCopy[i][j].startsWith("rs")){
						button[i][j].setIcon(loadImage("Images/RSun.png"));
					}
					else if(boardCopy[i][j].startsWith("rc")&& (turnCounter%2 == 0)&& turnCounter>0){
						button[i][j].setIcon(loadImage("Images/RChevron.png"));
					}
					else if(boardCopy[i][j].startsWith("rc")&& (turnCounter%2 != 0)&& turnCounter>0){
						button[i][j].setIcon(loadImage("Images/RChevronFlip.png"));
					}
				}
			}
		}catch(Exception k){
			System.out.println(k);
		}
	}
	
	//get the information of clicked button, if it is a piece, initialize the piece and get its possible movment
	//it will also highlight the possible moves
	//This is the part we use startergy design pattern 
	//Hakeem
	public void getInitialPiece(int row, int column, String turn, String[][] boardCopy){
		if(initialTile.startsWith("ra") || initialTile.startsWith("ba")) {
			Context context = new Context(new Arrow());
			List<int[]> getMove = context.runPieceMove(row,column, getTurn(redTurn), boardCopy);
			allowMoveList = getMove;
			setHighlightSquare(allowMoveList);
		}
		else if(initialTile.startsWith("rs") || initialTile.startsWith("bs")){
			Context context = new Context(new Sun());
			List<int[]> getMove = context.runPieceMove(row,column, getTurn(redTurn), boardCopy);
			allowMoveList = getMove;
			setHighlightSquare(allowMoveList);
		}
		else if(initialTile.startsWith("rp") || initialTile.startsWith("bp")) {
			Context context = new Context(new Plus());
			List<int[]> getMove = context.runPieceMove(row,column, getTurn(redTurn), boardCopy);
			allowMoveList = getMove;
			setHighlightSquare(allowMoveList);
		}
		else if(initialTile.startsWith("rc") || initialTile.startsWith("bc")) {
			Context context = new Context(new Chevron());
			List<int[]> getMove = context.runPieceMove(row,column, getTurn(redTurn), boardCopy);
			allowMoveList = getMove;
			setHighlightSquare(allowMoveList);
		}
		else if(initialTile.startsWith("rt") || initialTile.startsWith("bt")) {
			Context context = new Context(new Triangle());
			List<int[]> getMove = context.runPieceMove(row,column, getTurn(redTurn), boardCopy);
			allowMoveList = getMove;
			setHighlightSquare(allowMoveList);
		}
	}
	
	//to check the clicked destination tile for a piece is legal
	//Zaim
	public boolean checkLegalMoves(int row, int column, String initialTile){
		if(initialTile.startsWith("r") || initialTile.startsWith("b")){
			List<int[]> allowMoveCopy = allowMoveList;
			for(int i=0; i<allowMoveCopy.size(); i++){
				int x = allowMoveCopy.get(i)[0];  {  }
				int y = allowMoveCopy.get(i)[1];
				
				if((y == column) && (x == row)){
					legalMove = true;
					break;
				} 
				else{
					legalMove = false;
				}
			}
		}
		return legalMove;
	}
	
	//remove icon of original piece location after it has move
	//Hakeem
	public void removeInitiaTileIcon(JButton[][] button){
		for (int i=0; i <gameBoard.getRow(); i++){
			for (int j=0; j<gameBoard.getColumn(); j++){
				if (boardCopy[i][j] == " -  "){
					button[i][j].setIcon(null);
				} 
			}
		}
	}
	//highlight the coordinate of possible move in green
	//Hakeem
	public void setHighlightSquare(List<int[]> availableMove){
		for (int i=0; i<availableMove.size(); i++){
			button[availableMove.get(i)[0]][availableMove.get(i)[1]].setBackground(Color.green);
		}
	}
	//remove highlighted square after the piece is no longer clicked, or a move have been made
	//Puteri
	public void setDefaultColorSquare(){
			for (int i=0; i<gameBoard.getRow(); i++){
				for (int j=0; j<gameBoard.getColumn(); j++){
					button[i][j].setBackground(null);
				}
			}
	}
	

	//method to check if the game has ended
	//Irfan
	public void endStatus(String destinationTile){
		String endMessage = " ";
		boolean gameEnd = false;
		
		if(destinationTile.startsWith("rs")){
			gameEnd = true;
			endMessage = "Blue win! Do you like to play a new game?"; 
		}
		else if (destinationTile.startsWith("bs")){
			gameEnd = true;
			endMessage = "Red win! Do you like to play a new game?"; 
		}
		
		if(gameEnd == true){
			JFrame endFrame = new JFrame();
			Object[] startNewGame = {"Yes" , "No"};
			int k = JOptionPane.showOptionDialog(endFrame, endMessage, "Game has ended", JOptionPane.YES_NO_OPTION, 
			JOptionPane.QUESTION_MESSAGE, null, startNewGame, startNewGame[0]);
			if (k == JOptionPane.YES_OPTION){
				setVisible(false);
				new GUI();
			}
			else if(k == JOptionPane.YES_OPTION){
				System.exit(0);
			}
		}
	}
	
	
}



	
	
	
	
	

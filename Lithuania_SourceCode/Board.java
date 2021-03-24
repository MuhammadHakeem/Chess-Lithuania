//Class to create Board and flip board method after each turn
public class Board{
	private int row = 8;
	private int column = 7;
	private String[][] board;
	
	
	public Board(){
		createBoard(row, column);
	}
	
	
	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return column;
	}
	
	public String[][] getBoard(){
		return board;
	}

	
	public String[][] createBoard(int row, int column){
		board = new String[][]{
			{ "bp1 ", "bt1 ", "bc1 ", "bs  ", "bc2 ", "bt2 ", "bp2 "},
            { "ba1 ", " -  ", "ba2 ", " -  ", "ba3 ", " -  ", "ba4 "},
            { " -  ", " -  ", " -  ", " -  ", " -  ", " -  ", " -  "},
            { " -  ", " -  ", " -  ", " -  ", " -  ", " -  ", " -  "},
            { " -  ", " -  ", " -  ", " -  ", " -  ", " -  ", " -  "},
			{ " -  ", " -  ", " -  ", " -  ", " -  ", " -  ", " -  "},
			{ "ra1 ", " -  ", "ra2 ", " -  ", "ra3 ", " -  ", "ra4 "},
            { "rp1 ", "rt1 ", "rc1 ", "rs  ", "rc2 ", "rt2 ", "rp2 "}};
		return board;
	}
	
	//method to flip the board
	//Farisha 
	public void flipBoard(String[][] board){
		for (int i=0; i<4; i++){
			for (int j=0; j<7; j++){
				String temporary = board[i][j];
				board[i][j] = board[7-i][6-j];
				board[7-i][6-j] = temporary;
			}
		}
	}
	
	
		
	
}
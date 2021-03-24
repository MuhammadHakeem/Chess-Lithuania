import java.util.*;
//this class is to implement Startegy Design Pattern.
//all the pieces method of piceMove will be run by this class
public class Context{
	private Piece piece;
	
	public Context(Piece p){
		piece = p;
	}
	
	public List<int[]> runPieceMove(int row, int column, String turn, String[][] boardCopy){
		return piece.pieceMove(row,column,turn,boardCopy);
	}
	
	
}
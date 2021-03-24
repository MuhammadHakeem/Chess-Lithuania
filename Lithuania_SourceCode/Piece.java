import java.util.*;
//Making interfece Piece
public interface Piece{
	public List<int[]> pieceMove(int row, int column, String turn, String[][] boardCopy);
}


import java.util.*;

public class Chevron implements Piece{
	@Override
    public List<int[]> pieceMove(int row, int column, String turn, String[][] boardCopy) {
        List<int[]> possMove = new ArrayList<int[]>();
        //make a list of all possible moves of chevron no matter if theres a piece on that tile or not
        int[][] moveSlot ={{row-2,column-1},
									  {row-2,column+1},
									  {row-1,column-2},
									  {row-1,column+2},
									  {row+1,column-2},
									  {row+1,column+2},
									  {row+2,column-1},
									  {row+2,column+1}};
		
        for(int a=0; a<moveSlot.length; a++){
            if(turn=="b") {
                int x = moveSlot[a][0];
                int y = moveSlot[a][1];
                try {
                    if(boardCopy[Math.abs(x)][Math.abs(y)].startsWith("b")){ //if there is same colour piece there, dont add
                        continue;
                    }
                }catch (Exception e){}
                if (moveSlot[a][0] < 0 || moveSlot[a][0] > 7 || //check if it the possible move is a valid location in the board
					moveSlot[a][1] < 0 || moveSlot[a][1] > 6) {
                    continue;
                }else {
                    possMove.add(moveSlot[a]);
                }
				
            }
            if(turn=="r") {
                int x = moveSlot[a][0];
                int y = moveSlot[a][1];
                try {
                    if(boardCopy[Math.abs(x)][Math.abs(y)].startsWith("r")){
                        continue;
                    }
                }catch (Exception e){}
                if (moveSlot[a][0] < 0 || moveSlot[a][0] > 7 ||
					moveSlot[a][1] < 0 || moveSlot[a][1] > 6) {
                    continue;
                } else {
                    possMove.add(moveSlot[a]);
                }
				
            }
        }
        return possMove;
    }
}
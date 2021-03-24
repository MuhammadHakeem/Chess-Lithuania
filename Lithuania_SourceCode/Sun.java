

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sun implements Piece{
	@Override
	public List<int[]> pieceMove(int row, int column, String turn, String[][] boardCopy){
		List<int[]> possMove = new ArrayList<int[]>();
		
		//possible movement coordinates
        int[][] moveSlot ={{row-1,column-1},
									 {row-1,column},
									 {row-1,column+1},
									 {row,column-1},
									 {row,column+1},
									 {row+1,column-1},
									 {row+1,column},
									 {row+1,column+1}};
		
		for(int a=0; a<moveSlot.length; a++) {
		if (turn == "b") {
                int x = moveSlot[a][0];
                int y = moveSlot[a][1];
                try {
                    if (boardCopy[Math.abs(x)][Math.abs(y)].startsWith("b")) { //if same piece there, location can not be added
                        continue;
                    }
                } catch (Exception e) {
                }
                if (moveSlot[a][0] < 0 || moveSlot[a][0] > 7 || 
					moveSlot[a][1] < 0 || moveSlot[a][1] > 6) {  //checking for borders
						continue;
                }else {
                    possMove.add(moveSlot[a]);
                }
					
			
            }
            if (turn == "r") {
                int x = moveSlot[a][0];
                int y = moveSlot[a][1];
                try {
                    if (boardCopy[Math.abs(x)][Math.abs(y)].startsWith("r")) {
                        continue;
                    }
                } catch (Exception e) {
                }
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
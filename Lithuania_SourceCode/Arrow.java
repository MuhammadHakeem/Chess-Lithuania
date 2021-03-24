//***********************************************
//This class is mainly about the possible movement
// of Arrow Pieces on the chess board. 
//*********************************************
import java.util.*;

public class Arrow implements Piece{
    @Override
    public List<int[]> pieceMove(int row, int column, String turn, String[][] boardCopy){
        
        List<int[]> possMove = new ArrayList<int[]>(); //coordinate of legal moves.
        
        int[][] moveSlot = //store probability available coordinate
        {
            {row-1,column},
            {row-2,column},
            {row+1,column}, 
            {row+2,column}
        };
        if(row == 0){//to move opposite direction after reaching other edge
			possMove.add(moveSlot[2]);
			possMove.add(moveSlot[3]);
		}
        else{
			if(turn == "r"){
				for(int i = 0; i<2; i++){
					int x = moveSlot[i][0];
					int y = moveSlot[i][1];
            
					possMove.add(moveSlot[i]);//add all available coordinate
            
				try{
					if(boardCopy[x][y].startsWith("r"))//remove possible move if meet "r" at the same coordinate
					{
						possMove.remove(moveSlot[i]);
						break;
					}
					else if(boardCopy[x][y].startsWith("b"))//remove +1 possible move coordinate if possible move meet "b"
					{
						possMove.remove(moveSlot[i+1]);       
						break;
					}
				}catch(Exception e){}   
            
				if(moveSlot[i][0] < 0 || moveSlot[i][0] > 7)//remove all the coordinate that out of bounds
					{
						possMove.remove(moveSlot[i]);
					}
					else if(moveSlot[i][1] < 0 || moveSlot[i][1] > 6)
					{
						possMove.remove(moveSlot[i]);
					}
				}
				int[][] dummy = {{9,9}};    //dummy value so that theres always 1 available move on list
				possMove.add(dummy[0]);
			}
        
			if(turn == "b"){
				for(int i = 0; i<2; i++){
					int x = moveSlot[i][0];
					int y = moveSlot[i][1];
					possMove.add(moveSlot[i]);
            
				try{
					if(boardCopy[x][y].startsWith("b"))//remove possible move if meet "r" at the same coordinate
					{
						possMove.remove(moveSlot[i]);
						break;
					}
					else if(boardCopy[x][y].startsWith("r"))//remove +1 possible move coordinate if possible move meet "b"
					{
						possMove.remove(moveSlot[i+1]);       
						break;
					}
					}catch(Exception e){}   
            
					if(moveSlot[i][0] < 0 || moveSlot[i][0] > 7)//remove all the coordinate that out of bounds
					{
						possMove.remove(moveSlot[i]);
					}
					else if(moveSlot[i][1] < 0 || moveSlot[i][1] > 6)
					{
						possMove.remove(moveSlot[i]);
					}
				}
				int[][] dummy = {{9,9}};    //dummy value so that theres always 1 available move on list
				possMove.add(dummy[0]);
			}
		}
        return possMove;
    }   
}
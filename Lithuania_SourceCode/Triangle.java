// this class is for triangle's move


import java.util.*;

public class Triangle implements Piece
{
	public List<int[]> pieceMove(int row, int column, String turn, String[][] boardCopy)
	{
        List<int[]> possMove = new ArrayList<>(); //store coordinate of possible move
		
		// depends on current position 
		// declare all possible move
		int[][] moveSlot = 
		{
			{row-1,column+1}, //up right for(int i = 0; i < 6; i++)
			{row-2,column+2},
			{row-3,column+3},
			{row-4,column+4},
			{row-5,column+5},
			{row-6,column+6},

			{row-1,column-1}, // up left for(int i = 6; i < 12; i++)
			{row-2,column-2},
			{row-3,column-3},
			{row-4,column-4},
			{row-5,column-5},
			{row-6,column-6},

			{row+1,column-1}, //down left for(int i = 12; i < 18; i++)
			{row+2,column-2},
			{row+3,column-3},
			{row+4,column-4},
			{row+5,column-5},
			{row+6,column-6},

			{row+1,column+1}, //down right for(int i = 18; i < 24; i++)
			{row+2,column+2},
			{row+3,column+3},
			{row+4,column+4},
			{row+5,column+5},
			{row+6,column+6},
		};
		

		if(turn == "r") //red turn
		{
			for(int i = 0; i < 6; i++) //up right
			{
				int x = moveSlot[i][0]; //row value
				int y = moveSlot[i][1]; //col value

				possMove.add(moveSlot[i]); // add into list

				try
				{
					if(boardCopy[x][y].startsWith("r")) //if possible move has red piece
					{
						possMove.remove(moveSlot[i]);
						break;
					}
					else if(boardCopy[x][y].startsWith("b")) //if possible move has blue piece and remove the leftover
					{
						possMove.remove(moveSlot[i+1]);
						break;
					}
				}			
				catch(Exception e){}				

				if(moveSlot[i][0] < 0 || moveSlot[i][0] > 7) //check if possible move is in the board 
				{
					possMove.remove(moveSlot[i]);
				}
				else if(moveSlot[i][1] < 0 || moveSlot[i][1] > 6)
				{
					possMove.remove(moveSlot[i]);
				}
			}
			
			for(int i = 6; i < 12; i++) // up left
			{
				int x = moveSlot[i][0];
				int y = moveSlot[i][1];

				possMove.add(moveSlot[i]);

				try
				{
					if(boardCopy[x][y].startsWith("r"))
					{
						possMove.remove(moveSlot[i]);
						break;
					}
					else if(boardCopy[x][y].startsWith("b"))
					{
						possMove.remove(moveSlot[i+1]);
						break;
					}
				}			
				catch(Exception ex){}				

				if(moveSlot[i][0] < 0 || moveSlot[i][0] > 7)
				{
					possMove.remove(moveSlot[i]);
				}
				else if(moveSlot[i][1] < 0 || moveSlot[i][1] > 6)
				{
					possMove.remove(moveSlot[i]);
				}
			}
			
			for(int i = 12; i < 18; i++) //down left
			{
				int x = moveSlot[i][0];
				int y = moveSlot[i][1];

				possMove.add(moveSlot[i]);

				try
				{
					if(boardCopy[x][y].startsWith("r"))
					{
						possMove.remove(moveSlot[i]);
						break;
					}
					else if(boardCopy[x][y].startsWith("b"))
					{
						possMove.remove(moveSlot[i+1]);
						break;
					}
				}			
				catch(Exception ex){}				

				if(moveSlot[i][0] < 0 || moveSlot[i][0] > 7)
				{
					possMove.remove(moveSlot[i]);
				}
				else if(moveSlot[i][1] < 0 || moveSlot[i][1] > 6)
				{
					possMove.remove(moveSlot[i]);
				}				
			}
			
			for(int i = 18; i < 24; i++) //down right
			{
				int x = moveSlot[i][0];
				int y = moveSlot[i][1];

				possMove.add(moveSlot[i]);

				try
				{
					if(boardCopy[x][y].startsWith("r"))
					{
						possMove.remove(moveSlot[i]);
						break;
					}
					else if(boardCopy[x][y].startsWith("b"))
					{
						possMove.remove(moveSlot[i+1]);
						break;
					}
				}			
				catch(Exception ex){}				

				if(moveSlot[i][0] < 0 || moveSlot[i][0] > 7)
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
		else if(turn == "b") //blue turn
		{
			for(int i = 0; i < 6; i++) //up right
			{
				int x = moveSlot[i][0];
				int y = moveSlot[i][1];

				possMove.add(moveSlot[i]);

				try
				{
					if(boardCopy[x][y].startsWith("b"))
					{
						possMove.remove(moveSlot[i]);
						break;
					}
					else if(boardCopy[x][y].startsWith("r"))
					{
						possMove.remove(moveSlot[i+1]);
						break;
					}
				}			
				catch(Exception e){}				

				if(moveSlot[i][0] < 0 || moveSlot[i][0] > 7)
				{
					possMove.remove(moveSlot[i]);
				}
				else if(moveSlot[i][1] < 0 || moveSlot[i][1] > 6)
				{
					possMove.remove(moveSlot[i]);
				}
			}
			
			for(int i = 6; i < 12; i++) //up left
			{
				int x = moveSlot[i][0];
				int y = moveSlot[i][1];

				possMove.add(moveSlot[i]);

				try
				{
					if(boardCopy[x][y].startsWith("b"))
					{
						possMove.remove(moveSlot[i]);
						break;
					}
					else if(boardCopy[x][y].startsWith("r"))
					{
						possMove.remove(moveSlot[i+1]);
						break;
					}
				}			
				catch(Exception e){}				

				if(moveSlot[i][0] < 0 || moveSlot[i][0] > 7)
				{
					possMove.remove(moveSlot[i]);
				}
				else if(moveSlot[i][1] < 0 || moveSlot[i][1] > 6)
				{
					possMove.remove(moveSlot[i]);
				}
			}
			
			for(int i = 12; i < 18; i++) //down left
			{
				int x = moveSlot[i][0];
				int y = moveSlot[i][1];

				possMove.add(moveSlot[i]);

				try
				{
					if(boardCopy[x][y].startsWith("b"))
					{
						possMove.remove(moveSlot[i]);
						break;
					}
					else if(boardCopy[x][y].startsWith("r"))
					{
						possMove.remove(moveSlot[i+1]);
						break;
					}
				}			
				catch(Exception e){}				

				if(moveSlot[i][0] < 0 || moveSlot[i][0] > 7)
				{
					possMove.remove(moveSlot[i]);
				}
				else if(moveSlot[i][1] < 0 || moveSlot[i][1] > 6)
				{
					possMove.remove(moveSlot[i]);
				}				
			}
			
			for(int i = 18; i < 24; i++) //down right
			{
				int x = moveSlot[i][0];
				int y = moveSlot[i][1];

				possMove.add(moveSlot[i]);

				try
				{
					if(boardCopy[x][y].startsWith("b"))
					{
						possMove.remove(moveSlot[i]);
						break;
					}
					else if(boardCopy[x][y].startsWith("r"))
					{
						possMove.remove(moveSlot[i+1]);
						break;
					}
				}			
				catch(Exception e){}				

				if(moveSlot[i][0] < 0 || moveSlot[i][0] > 7)
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
		return possMove;
    }		
}
import java.util.*;

public class Plus implements Piece{
	@Override
	public List<int[]> pieceMove(int row, int column, String turn, String[][] boardCopy)
	{
        List<int[]> possMove = new ArrayList<>();//coordinate of legal move
		
		
		int[][] moveSlot =//store probability available coordinate
		{
			{row-1,column},//coordinate upwards (int i = 0; i < 7; i++)
			{row-2,column},
			{row-3,column},
			{row-4,column},
			{row-5,column},
			{row-6,column},
			{row-7,column},

			{row+1,column},//coordinate downwards for(int i = 7; i < 14; i++)
			{row+2,column},
			{row+3,column},
			{row+4,column},
			{row+5,column},
			{row+6,column},
			{row+7,column},

			{row,column-1},//coordinate to the left for(int i = 14; i < 20; i++)
			{row,column-2},
			{row,column-3},
			{row,column-4},
			{row,column-5},
			{row,column-6},

			{row,column+1},//coordinate to the right for(int i = 20; i < 26; i++)
			{row,column+2},
			{row,column+3},
			{row,column+4},
			{row,column+5},
			{row,column+6},

		};
		

		if(turn == "r")
		{
			for(int i = 0; i < 7; i++)//upwards           
			{
				int x = moveSlot[i][0];
				int y = moveSlot[i][1];

				possMove.add(moveSlot[i]);//add all available coordinate

				try
				{
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
				}			
				catch(Exception e){}				

				if(moveSlot[i][0] < 0 || moveSlot[i][0] > 7)//remove all the coordinate that out of bounds
				{
					possMove.remove(moveSlot[i]);
				}
				else if(moveSlot[i][1] < 0 || moveSlot[i][1] > 6)
				{
					possMove.remove(moveSlot[i]);
				}
				
				
			}
			
			for(int i = 7; i < 14; i++) //downwards
			{
				int x = moveSlot[i][0];
				int y = moveSlot[i][1];

				possMove.add(moveSlot[i]);//add all available coordinate

				try
				{
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
				}			
				catch(Exception ex){}				

				if(moveSlot[i][0] < 0 || moveSlot[i][0] > 7)//remove all the coordinate that out of bounds
				{
					possMove.remove(moveSlot[i]);
				}
				else if(moveSlot[i][1] < 0 || moveSlot[i][1] > 6)
				{
					possMove.remove(moveSlot[i]);
				}
			}
			for(int i = 14; i < 20; i++)//to the left
			{
				int x = moveSlot[i][0];
				int y = moveSlot[i][1];

				possMove.add(moveSlot[i]);//add all available coordinate

				try
				{
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
				}			
				catch(Exception ex){}				

				if(moveSlot[i][0] < 0 || moveSlot[i][0] > 7)//remove all the coordinate that out of bounds
				{
					possMove.remove(moveSlot[i]);
				}
				else if(moveSlot[i][1] < 0 || moveSlot[i][1] > 6)
				{
					possMove.remove(moveSlot[i]);
				}				
			}
			for(int i = 20; i < 26; i++)//to the right
			{
				int x = moveSlot[i][0];
				int y = moveSlot[i][1];

				possMove.add(moveSlot[i]);//add all available coordinate

				try
				{
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
				}			
				catch(Exception ex){}				

				if(moveSlot[i][0] < 0 || moveSlot[i][0] > 7)//remove all the coordinate that out of bounds
				{
					possMove.remove(moveSlot[i]);
				}
				else if(moveSlot[i][1] < 0 || moveSlot[i][1] > 6)
				{
					possMove.remove(moveSlot[i]);
				}				
			}
			int[][] dummy = {{9,9}};
			possMove.add(dummy[0]);	//dummy value so that theres always 1 available move on list
		}
		if(turn == "b")
		{
			for(int i = 0; i < 7; i++)//upwards
			{
				int x = moveSlot[i][0];
				int y = moveSlot[i][1];

				possMove.add(moveSlot[i]);//add all available coordinate

				try
				{
					if(boardCopy[x][y].startsWith("b"))//remove possible move if meet "b" at the same coordinate
					{
						possMove.remove(moveSlot[i]);
						break;
					}
					else if(boardCopy[x][y].startsWith("r"))//remove +1 possible move coordinate if possible move meet "r"
					{
						possMove.remove(moveSlot[i+1]);
						break;
					}
				}			
				catch(Exception e){}				

				if(moveSlot[i][0] < 0 || moveSlot[i][0] > 7)//remove all the coordinate that out of bounds
				{
					possMove.remove(moveSlot[i]);
				}
				else if(moveSlot[i][1] < 0 || moveSlot[i][1] > 6)
				{
					possMove.remove(moveSlot[i]);
				}
			}
			for(int i = 7; i < 14; i++)//downwards
			{
				int x = moveSlot[i][0];
				int y = moveSlot[i][1];

				possMove.add(moveSlot[i]);//add all available coordinate

				try
				{
					if(boardCopy[x][y].startsWith("b"))//remove possible move if meet "b" at the same coordinate
					{
						possMove.remove(moveSlot[i]);
						break;
					}
					else if(boardCopy[x][y].startsWith("r"))//remove +1 possible move coordinate if possible move meet "b"
					{
						possMove.remove(moveSlot[i+1]);
						break;
					}
				}			
				catch(Exception e){}				

				if(moveSlot[i][0] < 0 || moveSlot[i][0] > 7)//remove all the coordinate that out of bounds
				{
					possMove.remove(moveSlot[i]);
				}
				else if(moveSlot[i][1] < 0 || moveSlot[i][1] > 6)
				{
					possMove.remove(moveSlot[i]);
				}
			}
			for(int i = 14; i < 20; i++)//to the left
			{
				int x = moveSlot[i][0];
				int y = moveSlot[i][1];

				possMove.add(moveSlot[i]);//add all available coordinate

				try
				{
					if(boardCopy[x][y].startsWith("b"))//remove possible move if meet "b" at the same coordinate
					{
						possMove.remove(moveSlot[i]);
						break;
					}
					else if(boardCopy[x][y].startsWith("r"))//remove +1 possible move coordinate if possible move meet "b"
					{
						possMove.remove(moveSlot[i+1]);
						break;
					}
				}			
				catch(Exception e){}				

				if(moveSlot[i][0] < 0 || moveSlot[i][0] > 7)//remove all the coordinate that out of bounds
				{
					possMove.remove(moveSlot[i]);
				}
				else if(moveSlot[i][1] < 0 || moveSlot[i][1] > 6)
				{
					possMove.remove(moveSlot[i]);
				}				
			}
			for(int i = 20; i < 26; i++) //to the right
			{
				int x = moveSlot[i][0];
				int y = moveSlot[i][1];

				possMove.add(moveSlot[i]);//add all available coordinate

				try
				{
					if(boardCopy[x][y].startsWith("b"))//remove possible move if meet "b" at the same coordinate
					{
						possMove.remove(moveSlot[i]);
						break;
					}
					else if(boardCopy[x][y].startsWith("r"))//remove +1 possible move coordinate if possible move meet "b"
					{
						possMove.remove(moveSlot[i+1]);
						break;
					}
				}			
				catch(Exception e){}				

				if(moveSlot[i][0] < 0 || moveSlot[i][0] > 7)//remove all the coordinate that out of bounds
				{
					possMove.remove(moveSlot[i]);
				}
				else if(moveSlot[i][1] < 0 || moveSlot[i][1] > 6)
				{
					possMove.remove(moveSlot[i]);
				}				
			}
			int[][] dummy = {{9,9}};
			possMove.add(dummy[0]);  //dummy value so that theres always 1 available move on list
		}
		
        return possMove;
    }
}
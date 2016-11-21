
public class Board {
	
	private int board[][];
	public final int width = 7;
	public final int height = 6;
	
	public void printBoard(){
		System.out.println();
		for(int i =0 ; i < this.board.length; i++){
			int row[] = this.board[i];
			for(int j = 0; j < row.length; j++){
				System.out.print(row[j] + ", ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public int[][] getBoard(){
		int [][] myInt = new int[this.board.length][];
		for(int i = 0; i < this.board.length; i++)
		{
		  int[] aMatrix = this.board[i];
		  int   aLength = aMatrix.length;
		  myInt[i] = new int[aLength];
		  System.arraycopy(aMatrix, 0, myInt[i], 0, aLength);
		}
		
		return myInt;
	}
	
	public void initBoard(){
		for(int i =0 ; i < this.board.length; i++){
			int row[] = this.board[i];
			for(int j = 0; j < row.length; j++){
				row[j] = 0;
			}
		}
	}
	
	public Board(){
		board = new int[6][7];
		this.initBoard();
		this.printBoard();
	}
	
	public Board(Board b){
		board = new int[6][7];
		this.board = b.getBoard().clone();
	}
	
	public boolean addChip(int player, int column){
		if(column < 0 || column >= 7){
			return false;
		}
		for(int i = (this.board.length - 1); i >= 0; i--){
			if(board[i][column] == 0){
				board[i][column] = player;
				return true;
			}
		}
		return false;
	}
	
	public boolean gameFinished(){
		int length = this.board.length;
		int wide = this.board[0].length;
		for (int i=length-1; i>=0;i--){
			for(int j=wide-1;j>=0;j--){
				if(this.board[i][j]==0){
					return false;
				}
			}
		}
		return true;
	}
	
	
	public int getCurrentScore(){
		int[][] Board = this.getBoard();
		int length = Board.length;
		int wide = Board[0].length;
		int cpu = 100;
		int player = -100;
		int auxP=0;
		int auxC=0;
		for (int i=length-1; i>=0;i--){
			for(int j=wide-1;j>=0;j--){
			if(Board[i][j]==1){
				auxP++;
				auxC=0;
			}else if (Board[i][j]==2){
				auxC++;
				auxP=0;
			}else{
				auxC=0;
				auxP=0;
			}
			if(auxC==4){
				System.out.println("gano cpu en ");
				return cpu;
			}
			if(auxP==4){
				return player;
			}
			}
			auxC=0;
			auxP=0;
		}
		auxC=0;
		auxP=0;
		for(int j=wide-1;j>=0;j--){
			for (int i=length-1; i>=0;i--){
				if(Board[i][j]==1){
					auxP++;
					auxC=0;
				}else if (Board[i][j]==2){
					auxC++;
					auxP=0;
				}else{
					auxC=0;
					auxP=0;
				}
				if(auxC==4){
					return cpu;
				}
				if(auxP==4){
					return player;
				}
				}
			auxC=0;
			auxP=0;
				
			}
		
		auxC=0;
		auxP=0;
		for(int i=0;i<wide-1;i++){
	        for(int j=0;j<length-1;j++){
	        	
	        	int j3=j+3;
	        	int i3= i+3;
	        	
	        	if(i3<wide-1 && j3 <length-1){
	        	
	        	
	            if(Board[i][j] == 1 && Board[i][j]==Board[i+1][j+1] && Board[i][j]==Board[i+2][j+2] && Board[i][j]==Board[i+3][j+3])
	            	return player;
	            else if(Board[i][j] == 2 && Board[i][j]==Board[i+1][j+1] && Board[i][j]==Board[i+2][j+2] && Board[i][j]==Board[i+3][j+3])
	            	return cpu;
	        	}
	        }
	        
		}
	    //checks left diagonal win
		for(int i=0;i<wide-1;i++){
	        for(int j=0;j<length-1;j++){
	        	int j3=j-3;
	        	int i3= i+3;
	        	
	        	if(i3<wide-1 && j3 >0){
	        	
	            if(Board[i][j] == 1 && Board[i][j]==Board[i+1][j-1] && Board[i][j]==Board[i+2][j-2] && Board[i][j]==Board[i+3][j-3])
	            	return player;
	            else if(Board[i][j] == 2 && Board[i][j]==Board[i+1][j-1] && Board[i][j]==Board[i+2][j-2] && Board[i][j]==Board[i+3][j-3])
	            	return cpu;
	        	}
	        } 	
		}
		return 0;
	}
}

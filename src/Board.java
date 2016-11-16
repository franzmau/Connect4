
public class Board {
	
	private int board[][];
	
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
		return this.board;
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
	
	public boolean addChip(int player, int column){
		for(int i = (this.board.length - 1); i >= 0; i--){
			if(board[i][column] == 0){
				board[i][column] = player;
				return true;
			}
		}
		return false;
	}
	
	public int gameFinished(){
		int[][] Board = this.board;
		int length = Board.length;
		int wide = Board[0].length;
		int cpu = 2;
		int player = 1;
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
				return cpu;
			}
			if(auxP==4){
				return player;
			}
			}
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
				
			}
		
		/*auxC=0;
		auxP=0;
		for(int i=0;i<wide-3;i++){
	        for(int j=0;j<length-3;j++){
	        	
	            if(Board[i][j] == 1 && Board[i][j]==Board[i+1][j+1] && Board[i][j]==Board[i+2][j+2] && Board[i][j]==Board[i+3][j+3])
	            	return player;
	            else if(Board[i][j] == 2 && Board[i][j]==Board[i+1][j+1] && Board[i][j]==Board[i+2][j+2] && Board[i][j]==Board[i+3][j+3])
	            	return cpu;
	        	}
	        
		}
	    //checks left diagonal win
		for(int i=0;i<wide-3;i++){
	        for(int j=4;j<length-4;j++){
	            if(Board[i][j] == 1 && Board[i][j]==Board[i+1][j-1] && Board[i][j]==Board[i+2][j-2] && Board[i][j]==Board[i+3][j-3])
	            	return player;
	            else if(Board[i][j] == 2 && Board[i][j]==Board[i+1][j-1] && Board[i][j]==Board[i+2][j-2] && Board[i][j]==Board[i+3][j-3])
	            	return cpu;
	        	
	        } 	
		}*/
		
		
		
		return 0;
	}
}

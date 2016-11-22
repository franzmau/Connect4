
public class Board {
	
	private int board[][];
	public final int width = 7;
	public final int height = 6;
	public int score = 1000;
	
	public void printBoard(){
		System.out.println();
		for(int i =0 ; i < this.height; i++){
			int row[] = this.board[i];
			for(int j = 0; j < this.width; j++){
				System.out.print(row[j] + ", ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public int[][] getBoard(){
		int [][] myInt = new int[this.height][];
		for(int i = 0; i < this.height; i++)
		{
		  int[] aMatrix = this.board[i];
		  int   aLength = aMatrix.length;
		  myInt[i] = new int[aLength];
		  System.arraycopy(aMatrix, 0, myInt[i], 0, aLength);
		}
		
		return myInt;
	}
	
	public void initBoard(){
		for(int i =0 ; i < this.height; i++){
			int row[] = this.board[i];
			for(int j = 0; j < this.width; j++){
				row[j] = 0;
			}
		}
	}
	
	public Board(){
		board = new int[this.height][this.width];
		this.initBoard();
		this.printBoard();
	}
	
	public Board(Board b){
		board = new int[6][7];
		this.board = b.getBoard().clone();
	}
	
	public boolean addChip(int player, int column){
		if(column < 0 || column >= this.width){
			return false;
		}
		for(int i = (this.height - 1); i >= 0; i--){
			if(board[i][column] == 0){
				board[i][column] = player;
				return true;
			}
		}
		return false;
	}
	
	public boolean isFull(){
		for (int i=this.height-1; i>=0;i--){
			for(int j=this.width-1;j>=0;j--){
				if(this.board[i][j]==0){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isFinished(int depth, int score) {
	    if (depth == 0 || score == this.score || score == -this.score || this.isFull()) {
	        return true;
	    }
	    return false;
	}
	
	public int getCurrentScore(){
		int[][] Board = this.getBoard();
		int length = this.height;
		int wide = this.width;
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
	            else if(Board[i][j] == 2 && Board[i][j]==Board[i+1][j+1] && Board[i][j]==Board[i+2][j+2] && Board[i][j]==Board[i+3][j+3]){
	            	System.out.println("entro");
	            	return cpu;
	            }
	        	}
	        }
	        
		}
	    //checks left diagonal win
		for(int i=0;i<wide-1;i++){
	        for(int j=0;j<length-1;j++){
	        	int j3=j-3;
	        	int i3= i+3;
	        	int ja3=j+3;
	        	int ia3=i-3;
	        	if(i3<wide-1 && j3 >0 ){
	        	
	            if(Board[i][j] == 1 && Board[i][j]==Board[i+1][j-1] && Board[i][j]==Board[i+2][j-2] && Board[i][j]==Board[i+3][j-3])
	            	return player;
	            else if(Board[i][j] == 2 && Board[i][j]==Board[i+1][j-1] && Board[i][j]==Board[i+2][j-2] && Board[i][j]==Board[i+3][j-3])
	            	return cpu;
	            
	          
	            
	        	}
	        	
	        	if(ia3>0 &&ja3<length-1){
	        		  if(Board[i][j] == 1 && Board[i][j]==Board[i-1][j+1] && Board[i-1][j+1]==Board[i-2][j+2] && Board[i][j]==Board[i-3][j+3])
	  	            	return player;
	  	            else if(Board[i][j] == 2 && Board[i][j]==Board[i-1][j+1] && Board[i-1][j+1]==Board[i-2][j+2] && Board[i][j]==Board[i-3][j+3])
	  	            	return cpu;
	        		
	        	}
	        	
	        	
	        	
	     
	        } 	
		}
		return 0;
	}
	
	public int scorePosition (int row, int column, int delta_y, int delta_x) {
	    int human_points = 0;
	    int computer_points = 0;
	    int[][] board = this.getBoard();

	    for (int i = 0; i < 4; i++) {
	        if (board[row][column] == 1) {
	            human_points++; 
	        } else if (board[row][column] == 2) {
	            computer_points++; 
	        }

	        row += delta_y;
	        column += delta_x;
	    }

	    if (human_points == 4) {
	        return -this.score;
	    } else if (computer_points == 4) {
	        return this.score;
	    } else {
	        return computer_points;
	    }
	}
	
	
	public int getScore() {
	    int points = 0;

	    int vertical_points = 0;
	    int horizontal_points = 0;
	    int diagonal_points1 = 0;
	    int diagonal_points2 = 0;


	    for (int row = 0; row < this.height - 3; row++) {
	        for (int column = 0; column < this.width; column++) {
	            int score = this.scorePosition(row, column, 1, 0);
	            if (score == this.score){
	            	return this.score;
	            }
	            if (score == -this.score){
	            	return -this.score;
	            }
	            vertical_points += score;
	        }            
	    }

	    for (int row = 0; row < this.height; row++) {
	        for (int column = 0; column < this.width - 3; column++) {
	            int score = this.scorePosition(row, column, 0, 1);   
	            if (score == this.score){
	            	return this.score;
	            }
	            if (score == -this.score){
	            	return -this.score;
	            }
	            horizontal_points += score;
	        } 
	    }

	    for (int row = 0; row < this.height - 3; row++) {
	        for (int column = 0; column < this.width - 3; column++) {
	            int score = this.scorePosition(row, column, 1, 1);
	            if (score == this.score){
	            	return this.score;
	            }
	            if (score == -this.score){
	            	return -this.score;
	            }
	        }            
	    }

	    for (int row = 3; row < this.height; row++) {
	        for (int column = 0; column < this.width - 4; column++) {
	            int score = this.scorePosition(row, column, -1, +1);
	            if (score == this.score){
	            	return this.score;
	            }
	            if (score == -this.score){
	            	return -this.score;
	            }
	            diagonal_points2 += score;
	        }

	    }

	    points = horizontal_points + vertical_points + diagonal_points1 + diagonal_points2;
	    return points;
	}
}

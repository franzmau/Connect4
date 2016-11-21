import java.util.Scanner;

public class Player {
	private int score;
	private boolean isHuman;
	private Scanner reader;
	public final int id;

	
	public Player(boolean isHuman, int id){
		this.reader = new Scanner(System.in);
		this.score = 0;
		this.isHuman = isHuman;
		this.id = id;
	}
	
	
	public int getMove(Board board){
		if(isHuman){
			return reader.nextInt();

		}else{
			return getMaxMove(board, 6)[0];
		}
	}
	
	private int [] getMaxMove(Board board, int depth){
		int [] output = new int [2];
		int score = board.getCurrentScore();
		if(board.gameFinished() || depth <= 0){
			output[0] = 0;
			output[1] = score;
			return output;
		}
		
		output[0] = 0;
		output[1] = -9999;
		
		
		for(int i = 0; i < 7 ; i++){
			Board b = new Board(board);
			
			if(b.addChip(2, i)){
				int [] next_move = getMinMove(b, depth - 1); // Recursive calling

	            // Evaluate new move
				if (output[0] == 0 || next_move[1] > output[1]) {
		            output[0] = i;
		            output[1] = next_move[1];
		         }
	            
			}
		}
		
		return output;
	}
	
	private int [] getMinMove(Board board, int depth){
		int [] output = new int [2];
		int score = board.getCurrentScore();
		if(board.gameFinished() || depth <= 0){
			output[0] = 0;
			output[1] = score;
			return output;
		}
		
		output[0] = 0;
		output[1] = 9999;
		
		
		for(int i = 0; i < 7 ; i++){
			Board b = new Board(board);
			
			if(b.addChip(1, i)){
				int [] next_move = getMaxMove(b, depth - 1); // Recursive calling

	            // Evaluate new move
				if (output[0] == 0 || next_move[1] < output[1]) {
		            output[0] = i;
		            output[1] = next_move[1];
		         }
	            
			}
		}
		
		return output;
	}
	
}

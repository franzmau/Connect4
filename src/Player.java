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
			return getComputedMove(board, true, 5)[0];
		}
	}
	
	private int [] getComputedMove(Board board, Boolean isPlaying, int depth){
		int [] output = new int [2];
		int offset = isPlaying ? depth : -depth;
		score = board.getCurrentScore() + offset;
		if(board.gameFinished() || depth <= 0){
			output[0] = 0;
			output[1] = score;
			return output;
		}
		
		if(isPlaying){
			output[0] = 0;
			output[1] = -9999;
		}else{
			output[0] = 0;
			output[1] = 9999;
		}
		
		int player = isPlaying ? 1: 2;
		
		for(int i = 0; i < 7 ; i++){
			Board b = new Board(board);
			
			if(b.addChip(player, i)){
				int [] next_move = getComputedMove(b, !isPlaying, depth - 1); // Recursive calling

	            // Evaluate new move
				if(isPlaying){
					if (output[0] == 0 || next_move[1] > output[1]) {
		            	output[0] = i;
		            	output[1] = next_move[1];
		            }
				}else{
					if (output[0] == 0 || next_move[1] < output[1]) {
		            	output[0] = i;
		            	output[1] = next_move[1];
		            }
				}
	            
			}
		}
		
		return output;
	}
	
}

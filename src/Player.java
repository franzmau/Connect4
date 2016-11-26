import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Scanner;
import javax.comm.*;

public class Player{


	public Player(boolean isHuman, int id){
		this.reader = new Scanner(System.in);
		this.score = 0;
		this.isHuman = isHuman;
		this.id = id;
	}


	public int getMove(Board board){
		System.out.println("Getting move...");
		if(isHuman){
		    return this.reader.nextInt();
		}else{
			iterations = 0;
			int[] output =  minMax(board,true, this.MAX_DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE);
			/*System.out.println("Iterations: "+ this.iterations);
			System.out.println("Column: "+  output[0]);
			System.out.println("Score: "+ output[1]);*/
			return output [0];
		}
	}

	private int[] minMax(Board board, Boolean isPlaying, int depth  ,int alpha, int beta) {
		int [] output = new int [2];
		int score = board.getScore();
		int currentPlayer =  isPlaying? 2 : 1;

		if(board.isFinished(depth, score)){
			output[0] = 0;
			output[1] = score;
			return output;
		}

		output[0] = -1;
		output[1] = -9999;

		for(int i = 0; i < 7; i++){
			Board b = new Board(board);
			if(b.addChip(currentPlayer, i)){
				this.iterations ++;

				if(isPlaying){
					int [] next_move = minMax(b, !isPlaying, depth - 1, alpha,beta);
					if (output[0] == -1 || next_move[1] > output[1]) {
			            output[0] = i;
			            output[1] = next_move[1];
			            alpha = next_move[1];
			         }
				}else{
					int [] next_move = minMax(b, !isPlaying, depth - 1, alpha,beta);

					if (output[0] == -1 || next_move[1] < output[1]) {
			            output[0] = i;
			            output[1] = next_move[1];
			            beta = next_move[1];
			         }
				}
			}
			if (alpha >= beta) break;
		}
		return output;
	}

}

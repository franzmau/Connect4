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
			return getComputedMove(board);
		}
	}
	
	private int getComputedMove(Board board){
		if(board.gameFinished() != 0){
			return 0;
		}
		return 0;
	}
	
}

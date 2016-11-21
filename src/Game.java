import java.util.Scanner;

public class Game {
	
	public static final int HUMAN_VS_HUMAN = 0;
	public static final int HUMAN_VS_PC = 1;
	
	private boolean playing = false;
	private boolean isPlayer1Turn = true;
	
	private Board board;
	Player p1;
	Player p2;
	
	public Game(int mode){
		this.board = new Board();
		if(mode ==  HUMAN_VS_HUMAN){
			p1 = new Player(true,1);
			p2 = new Player(true,2);
		}else{
			p1 = new Player(true,1);
			p2 = new Player(false,2);
		}
	}
	
	public void StartGame(){
		
		playing = true;
		this.board.initBoard();
		
		while(playing){
			if(isPlayer1Turn){
				System.out.println("P1");
				while(!board.addChip(p1.id, p1.getMove(board)));
			}else{
				System.out.println("P2");
				while(!board.addChip(p2.id, p2.getMove(board)));
			}
			isPlayer1Turn = !isPlayer1Turn;
			board.printBoard();
			if(this.board.gameFinished() ){
				this.playing = false;
				System.out.println("YA alguien gano");
			}
			if( Math.abs(this.board.getCurrentScore()) >= (100)){
				this.playing = false;
				System.out.println("score ya es mayor a 100");
			}
			
			board.printBoard();
		}
		System.out.println("GAME FINISHED");
		
	}
	
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Scanner;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;

public class Game {


	private boolean playing = false;
	private boolean isPlayer1Turn = true;

	private Board board;
	Player p1;
	Player p2;

	public Game(i){
		this.board = new Board();

		p1 = new Player(true,1);
		p2 = new Player(false,2);

	}

	public void preFillBoard(){
		//this.board.addChip(1, 0);
		//this.board.addChip(2, 3);
	}

	public void sendSerial(char move){
		Process p = null;
		try {
			String toSend = "python ./master.py "+move;
			System.out.println(toSend);
			p = Runtime.getRuntime().exec(toSend);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			System.out.println("Exit with status: " + p.waitFor());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    BufferedReader reader =
		         new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String output = "";
		    String line = "";
		    try {
				while ((line = reader.readLine())!= null) {
					output+= line + "\n";
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.out.println(output);
	}

	public void StartGame(){

		playing = true;
		this.board.initBoard();
		preFillBoard();
		this.board.printBoard();
		while(playing){
			int toMove = -1;
			if(isPlayer1Turn){
				System.out.println("P1");
				while(!board.addChip(p1.id, toMove)){
					toMove = p1.getMove(board);
				};
			}else{
				System.out.println("P2");
				toMove = p2.getMove(board);
				while(!board.addChip(p2.id, toMove)){
					toMove = p2.getMove(board);
				};
			}
			if(toMove >= 0){
				sendSerial((char) (toMove+'0'));
				try {
				    Thread.sleep(5000);                 //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			}
			isPlayer1Turn = !isPlayer1Turn;
			board.printBoard();
			System.out.println("Score: " + this.board.getCurrentScore());
			if(this.board.getCurrentScore() != 0 ){
				this.playing = false;
				
			}
			if( Math.abs(this.board.getCurrentScore()) >= (100)){
				this.playing = false;
				
			}

			board.printBoard();
		}
		System.out.println("GAME FINISHED");

	}

}

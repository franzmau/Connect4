import java.util.ArrayList;
import java.util.Scanner;


public class main {
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		game Game= new game();
		Game.start_game();
		int person;
		Computer computer=new Computer();
		int Board[][];
		int B[][]=new int [6][7];
		
		
		Board=new int[6][7];
		for (int i=0; i< 6;i++){
			for(int j=0;j<7;j++){
				Board[i][j]=0;
				B[i][j]=Board[i][j];
				
			}
		}
		
		
		Board[4][3]=2;
		Board[0][3]=2;
		Board[2][3]=2;
		Board[1][3]=1;
		Board[5][3]=2;
		Board[3][3]=1;
		Board[5][4]=2;
		
		Board[4][2]=2;
		Board[0][2]=2;
		Board[2][2]=2;
		Board[1][2]=1;
		Board[5][2]=2;
		Board[3][2]=1;
		System.out.print("\n");System.out.print("\n");
		for (int i=0; i< 6;i++){
			for(int j=0;j<7;j++){
				System.out.print("("+Board[i][j]+")");
				
			}
			
			System.out.print("\n");
		}
		System.out.print("\n");
		System.out.print("\n");
		System.out.println("\n Empieza");
		
		
		computer.setPosx(2);
		computer.setPosx(2);
		computer.setPosx(2);
		computer.setPosx(2);
		computer.setPosx(2);
		computer.setPosx(2);
		computer.setPosx(3);
		computer.setPosx(3);
		computer.setPosx(3);
		computer.setPosx(3);
		computer.setPosx(3);
		computer.setPosx(3);
		int x=computer.poner(Board);
		
		
		
		//System.out.print("El mejor lugar para ponerlo es"+x );
		
		/*
		Scanner sc = new Scanner(System.in);
	    while(Game.game_finished()==0){
	    	do{
	    	System.out.print("muevele\n");
	    	person= sc.nextInt();
	    	
	    	}while(!Game.movePerson(person));
	    	
	    	Game.print();
	    	
	    }
		*/
		
			
	}

}
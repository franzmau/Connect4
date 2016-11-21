
public class game2 {

	int Board[][];
	final int length=6;
	final int wide= 7;
	final int cpu=2;
	final int player=1;
	int posx [];
	
	public game2(){
		Board= new int [length][wide];
		posx=new int[7];
	}
	public void start_game(){
		for (int i=0; i< length;i++){
			for(int j=0;j<wide;j++){
				Board[i][j]=0;
				//System.out.print("[0] ");
				
			}
			posx[i]=length-1;
			//System.out.print("\n");
		}
		Board[5][3]=2;
		this.setPosx(3);
		Board[5][1]=2;
		this.setPosx(1);
		
	
	}
	public boolean movePerson(int x){
		if(this.play_player(x)){
			Board[this.getPosx(x)][x]=1;
			setPosx(x);
			
				return true;
			
		}
		System.out.print("Invalid movement\n");
		return false;
	}
	
	public void print(){
		for (int i=0; i< length;i++){
			for(int j=0;j<wide;j++){
				System.out.print(" ["+Board[i][j]+"] ");
				
			}
			
			System.out.print("\n");
		}
	}
	public boolean play_player(int x){
		if(x<wide && x>=0){
			if(this.getPosx(x)>-1){
				
				return true;
			}
			return false;
		}
		return false;
		
	}
	public int getPosx(int i){
		return posx[i];
	}
	public void setPosx(int i){
		posx[i]-=1;
	}
	
	public int game_finished(){
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
		auxC=0;
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
	        for(int j=0;j<length-3;j++){
	        	
	            if(Board[i][j] == 1 && Board[i][j]==Board[i+1][j-1] && Board[i][j]==Board[i+2][j-2] && Board[i][j]==Board[i+3][j-3])
	            	return player;
	            else if(Board[i][j] == 2 && Board[i][j]==Board[i+1][j-1] && Board[i][j]==Board[i+2][j-2] && Board[i][j]==Board[i+3][j-3])
	            	return cpu;
	        	
	        } 	
		}
		
		
		
		return 0;
	}
}

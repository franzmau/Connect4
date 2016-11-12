import java.util.ArrayList;


public class Computer {
	int Board[][];
	final int length=6;
	final int wide= 7;
	final int cpu=2;
	final int player=1;
	int posx [];
	final int depth =4;
	
	public Computer(){
		Board= new int [length][wide];
		posx=new int[wide];
		for (int i=0; i< wide;i++){
			posx[i]=length-1;
		}
	
	}
	
	public int game_finished(int Board[][]){
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
		return 0;
	}

	
	
	public void print(int Boarda[][]){
		for (int i=0; i< length;i++){
			for(int j=0;j<wide;j++){
				//System.out.print("("+i + ","+j+")");
				System.out.print("["+Boarda[i][j]+"] ");
				
			}
			
			System.out.print("\n");
		}
		System.out.print("\n");System.out.print("\n");
	}

	public int[][] clone(int Board[][]){
		int old [][]=new int[length][wide];
		for(int i=0; i<Board.length; i++)
			  for(int j=0; j<Board[i].length; j++){
				  old[i][j]=Board[i][j]; 
			  }
		return old;
		
	}
	public  int poner (int Bo[][]){
		
		int boardaux[][]=new int[length][wide];
		int Real[][]=new int[length][wide];
		int zero=-300;
		int one=-300;
		int two=-300;
		int four=-300;
		int five=-300;
		int six=-300;
		Real=this.clone(Bo);
		
		
		if(this.play_player(0)){
		boardaux=this.insertBoard(Real, 0, 1);
			if(boardaux!=null){
				print(boardaux);
			}
			zero=compute(boardaux,0);
		}
		
		Real=this.clone(Bo);
		
		
		if(this.play_player(1)){
			boardaux=this.insertBoard(Real, 1, 1);
			if(boardaux!=null){
				print(boardaux);
			}
			one= compute(boardaux,0);
		}
		
		
		
		
		Real=this.clone(Bo);
		if(this.play_player(2))
		boardaux=this.insertBoard(Real, 2, 1);
		if(boardaux!=null){
			//print(boardaux);
			}
		//int two=compute(boardaux,0);
		Real=this.clone(Bo);
		boardaux=this.insertBoard(Real, 3, 1);
		if(boardaux!=null){
			//print(boardaux);
			}
		//int three=compute(boardaux,0);
		Real=this.clone(Bo);
		boardaux=this.insertBoard(Real, 4, 1);
		if(boardaux!=null){
			//print(boardaux);
			}
		//int four=compute(boardaux,0);
		Real=this.clone(Bo);
		boardaux=this.insertBoard(Real, 5, 1);
		if(boardaux!=null){
			//print(boardaux);
			}
		//int five=compute(boardaux,0);
		Real=this.clone(Bo);
		boardaux=this.insertBoard(Real, 6, 1);
		if(boardaux!=null){
			//print(boardaux);
			}
		//int six=compute(boardaux,0);
		
		System.out.print(" "+ one );
		/*
		System.out.print(one +" "+ two+ " "+ " "+three+" "+ four+"  "+five +" "+" "+six+ "");
		
		if(zero>=one && zero>=two && zero>=three&&zero>=four && zero>=five){
			return 0;
		}
		if(one>=zero && one>=two && one>=three&&one>=four && one>=five){
			return 1;
		}
		if(two>=zero && two>=one && two>=three&&two>=four && two>=five){
			return 2;
		}
		if(three>=zero && three>=two && three>=one&&three>=four && three>=five){
			return 3;
		}
		if(four>=zero && four>=two && four>=one&&four>=three && four>=five){
			return 4;
		}
		if(five>=zero && five>=two && five>=one&&five>=four && five>=three){
			return 5;
		}
		
		*/
		return -1;
	}
	
	
	public int compute(int Board[][],int depth){
			if(Board!=null){
		 if(this.game_finished(Board)==1){
			System.out.println("se acabo gano regresare " +(int)(-1* (Math.abs(depth-this.depth)) ) );
			
			return ((int)(-1* (Math.abs(depth-this.depth)) )  );
		 }
		else if(this.game_finished(Board)==2){
			
			System.out.println("depth vale"+depth+" se acabo gane"+(int)((Math.abs(this.depth-depth))) );
			return ((int)((Math.abs(this.depth-depth)))) ;
		}
			}
		if(depth>=this.depth){
			
				return 0;
			
			}
			
		
			depth+=1;
			System.out.println("estoy en el nivel"+depth);
			ArrayList<Integer> best = new ArrayList<Integer>();
			
			int cero=compute(insertBoard(Board,0,depth),depth);
			if(cero!=-25){
				best.add(cero);
				
			}
			
			
			int uno=compute(insertBoard(Board,1,depth),depth);
			if(uno!=-25){
				best.add(uno);
			}
			int dos=compute(insertBoard(Board,2,depth),depth);
			if(dos!=-25){
				best.add(dos);
			}
			int tres=compute(insertBoard(Board,3,depth),depth);
			if(tres!=-25){
				best.add(tres);
			}
			int cuatro=compute(insertBoard(Board,4,depth),depth);
			if(cuatro!=-25){
				best.add(cuatro);
			}
			int cinco=compute(insertBoard(Board,5,depth),depth);
			if(cinco!=25){
				best.add(cinco);
			}
			int seis=compute(insertBoard(Board,6,depth),depth);
			if(seis!=25){
				best.add(seis);
			}
		
			
			return big(best);
		}
		
		
	public int big(ArrayList<Integer>list){
		int bigg =-25;
		for(int i:list){
			//System.out.print("valor"+ i);
			if(i>bigg)
				bigg=i;
		}
		//System.out.println("\n mandare "+bigg);
		return bigg;
	}
	
	public int biggest(int a, int b, int c , int d, int e, int f ,int g ){
		//System.out.println("\nrecibi un "+a+","+b+" ,"+c+" ,"+d+" ,"+" ,"+e+" ,"+f+" ,"+" ,"+g);
		return Math.max(a, Math.max(b, Math.max(c, Math.max(d, Math.max(e, Math.max(f, g))))));
	}
	
	
	
	public int[][] insertBoard(int Board[][],int pos,int depth){
		if(Board!=null){
		if(Board[0][pos]==0){
			if(this.play_player(pos)){
				if(depth%2==0){
				Board[this.getPosx(pos)][pos]=1;
				setPosx(pos);
				}else{
					Board[this.getPosx(pos)][pos]=2;
					setPosx(pos);
				}			
				
			}
			
			
		}else{
			//System.out.println("regresare null");
		  Board=null;
		}
		}
		
		
		return Board;
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
	
}






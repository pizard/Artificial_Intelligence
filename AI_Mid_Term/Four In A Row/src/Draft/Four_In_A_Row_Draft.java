package Draft;

import java.io.IOException;
import java.util.Scanner;


// 초안입니다.
public class Four_In_A_Row_Draft {

	public static void main (String args[]) throws IOException{
	Scanner in = new Scanner(System.in);
	System.out.println("Player X, what is your name?");
	String playerX = in.next();
	System.out.println("Player Y, what is your name?");
	String playerY = in.next();
	
	int winner;
	char board[][] = new char[7][6];
	int c_board[][] = new int[7][6];
	
	Board_Reset(board);		// board를 초기화해주는 부분입니다.
	C_Board_Reset(c_board); // c_board를 초기화 해주는 부분입니다. Reference 1 의 점수를 초기 점수로 정하였습니다.
	int i=0;
	print(board);
	
	do{
		char playerX_pieces = 'X';
		char playerY_pieces = 'O';
		int row;
		boolean placed;
		String name = (i%2==0)?playerX:playerY;
		
		
		if(i%2 ==1){
		System.out.print(name+" please select a row:");
		row = in.nextInt();
		placed = place(board,i,(row-1));
		while(!placed){
			
			if(row>6 || row<0){
				System.out.println("row is invalid. \n select row:");
			}else{
				System.out.print("row "+ row +" is full.\n select row:");
			}
			row = in.nextInt();
			placed = place(board,i,(row-1));
		}
		}else{
			row = evaluate(board, c_board, playerX_pieces, playerY_pieces);
			placed = place(board,i,(row-1));
			
		}
			
		
		
		
		winner = findFour(board);
		print(board);
		
		i++;
		
	}while(!full(board) && winner==-1);
	
	if(winner==0){
		System.out.println(playerX+" You Won!!");
	}else if(winner==1){
		System.out.println(playerY+" You Won!!");
	}else{
		System.out.println("Sorry, no one won.");
	}
    
}

// 연속된 4개의 Pieces를 확인하는 부분입니다.
private static int findFour(char board[][]){
	for(int i=0;i<7;i++){
		for(int j=0;j<6;j++){
			
			boolean r=true,u=true,ur=true,ul=true;
			for(int k=0;k<4;k++){
			
				if(j+k<6){
					u&=board[i][j]==board[i][j+k];
				}else{
					u=false;
				}
				
				if(i+k<7){
					r&=board[i][j]==board[i+k][j];
				}else{
					r=false;
				}
				
				if(i+k<7 && j+k<6){
					ur&=board[i][j]==board[i+k][j+k];
				}else{
					ur=false;
				}
				
				if(i+k<7 && j-k>=0){
					ul&=board[i][j]==board[i+k][j-k];
				}else{
					ul=false;
				}
			}
			
			if((r || u || ul || ur) && (board[i][j]=='X' || board[i][j]=='O')){
				
				return (board[i][j]=='X')?0:1;
			}
		}
	}
	return -1;
	
}

private static boolean place(char board[][], int turn, int row){

	if(row>6 || row<0){
		return false;
	}
	
	if(full(board)){
		return false;
	}
	int i=0;
	
	boolean placed = false;
	
	while(!placed && i<6){
		if(board[row][i]!='X' && board[row][i]!='O'){
			board[row][i] = (turn%2==0)?'X':'O';
			return true;
		}else{
			i++;
		}
	}
	return false;
}



// board가 가득찼는지 확인해 주는 부분입니다. 
private static boolean full(char board[][]){
	for(int i=0;i<7;i++){
		for(int j=0;j<6;j++){
			if(board[i][j]!='X' && board[i][j]!='O') return false;
		}
	}
	
	return true;
}


// board를 출력하는 부분입니다.
private static void print(char board[][]){
	System.out.println("┌───┬───┬───┬───┬───┬───┬───┐");
	System.out.println("├ " + board[0][5] + " ┼ "+ board[1][5] + " ┼ " + board[2][5]+ " ┼ " + board[3][5]+ " ┼ " + board[4][5]+ " ┼ " + board[5][5] + " ┼ " + board[6][5] + " ┤ ");
	System.out.println("├───┼───┼───┼───┼───┼───┼───┤");
	System.out.println("├ " + board[0][4] + " ┼ "+ board[1][4] + " ┼ " + board[2][4]+ " ┼ " + board[3][4]+ " ┼ " + board[4][4]+ " ┼ " + board[5][4] + " ┼ " + board[6][4] + " ┤ ");
	System.out.println("├───┼───┼───┼───┼───┼───┼───┤");
	System.out.println("├ " + board[0][3] + " ┼ "+ board[1][3] + " ┼ " + board[2][3]+ " ┼ " + board[3][3]+ " ┼ " + board[4][3]+ " ┼ " + board[5][3] + " ┼ " + board[6][3] + " ┤ ");
	System.out.println("├───┼───┼───┼───┼───┼───┼───┤");
	System.out.println("├ " + board[0][2] + " ┼ "+ board[1][2] + " ┼ " + board[2][2]+ " ┼ " + board[3][2]+ " ┼ " + board[4][2]+ " ┼ " + board[5][2] + " ┼ " + board[6][2] + " ┤ ");
	System.out.println("├───┼───┼───┼───┼───┼───┼───┤");
	System.out.println("├ " + board[0][1] + " ┼ "+ board[1][1] + " ┼ " + board[2][1]+ " ┼ " + board[3][1]+ " ┼ " + board[4][1]+ " ┼ " + board[5][1] + " ┼ " + board[6][1] + " ┤ ");
	System.out.println("├───┼───┼───┼───┼───┼───┼───┤");
	System.out.println("├ " + board[0][0] + " ┼ "+ board[1][0] + " ┼ " + board[2][0]+ " ┼ " + board[3][0]+ " ┼ " + board[4][0]+ " ┼ " + board[5][0] + " ┼ " + board[6][0] + " ┤ ");
	System.out.println("└───┴───┴───┴───┴───┴───┴───┘");

}//print

// 보드를 리셋하는 부분입니다.
private static void Board_Reset(char board[][]){
	int i,j;
	for(i=0;i<7;i++)
		for(j=0;j<6;j++)
			board[i][j] = ' ';
}


private static void C_Board_Reset(int c_board[][]){
	c_board[0][5]=3;c_board[1][5]=4;c_board[2][5]=5;c_board[3][5]=7;c_board[4][5]=5;c_board[5][5]=4;c_board[6][5]=3;
	c_board[0][4]=4;c_board[1][4]=6;c_board[2][4]=8;c_board[3][4]=10;c_board[4][4]=8;c_board[5][4]=6;c_board[6][4]=4;
	c_board[0][3]=5;c_board[1][3]=8;c_board[2][3]=11;c_board[3][3]=13;c_board[4][3]=11;c_board[5][3]=8;c_board[6][3]=5;
	c_board[0][2]=5;c_board[1][2]=8;c_board[2][2]=11;c_board[3][2]=13;c_board[4][2]=11;c_board[5][2]=8;c_board[6][2]=5;
	c_board[0][1]=4;c_board[1][1]=6;c_board[2][1]=8;c_board[3][1]=10;c_board[4][1]=8;c_board[5][1]=6;c_board[6][1]=4;
	c_board[0][0]=3;c_board[1][0]=4;c_board[2][0]=5;c_board[3][0]=7;c_board[4][0]=5;c_board[5][0]=4;c_board[6][0]=3;	
}

// board에서 각 column의 놓을 위치를 결정하고 이 c_board[col][row]를 비교하여 가장 높은 column을 출력하여 놓을곳을 결정합니다.
private static int max_check(char[][]board, int[][] c_board){
	int row_max[] = new int[7];
	int col, i=0;
	int stop;
	for(col = 0;col < 7;col++){
		i=0;
		stop = 0;
		while(board[col][i] != ' ' && stop == 0){
			i++;
			if(i == 6){
				i = 5;
				c_board[col][row_max[col]] = -1;
				stop = 1;
			}
			row_max[col] = i;	
		}
	}
	int a_col = 0;
	int a_row = 0;
	for(col = 0;col < 7;col++){
		
			if(	c_board[a_col][a_row] < c_board[col][row_max[col]]){
				a_col = col;
				a_row = row_max[col];
			}
	}				
	
	return a_col;
}


// 각 좌표의 평가함수를 만든 부분입니다.
private static int evaluate(char[][] board, int[][] c_board, char player1, char player2)
{

	C_Board_Reset(c_board); // 평가함수 리셋!!
	int v = 1;
	int d = 2;
	int h = 3;
	int twoIn = 10;
	int threeIn = 1000;

	for (int row=0;row<6;row++)
	{
		for (int col = 0;col<4;col++)
		{
			if (board[col][row] == player1 &&
				board[col][row] == board[col+1][row] &&
				board[col+2][row] == ' ' &&
				board[col+3][row] == ' ')
				{
					c_board[col+2][row] += twoIn*h;
					c_board[col+3][row] += twoIn*h;
					
				}
			else if (board[col][row] == player1 &&
				board[col+2][row] == player1 &&
				board[col+1][row] == ' ' &&
				board[col+3][row] == ' ')
				{
					c_board[col+1][row] += twoIn*h;
					c_board[col+3][row] += twoIn*h;
				}
			else if (board[col][row] == player1 &&
				board[col+3][row] == player1 &&
				board[col+1][row] == ' ' &&
				board[col+2][row] == ' ')
				{
					c_board[col+1][row] += twoIn*h;
					c_board[col+2][row] += twoIn*h;
				}
			else if (board[col][row] == ' '&&
				board[col+1][row] == player1 &&
				board[col+2][row] == player1 &&
				board[col+3][row] == ' ')
				{
					c_board[col][row] += twoIn*h;
					c_board[col+3][row] += twoIn*h;
				
				}
			else if (board[col][row] == ' ' &&
				board[col+1][row] == player1 &&
				board[col+2][row] == ' ' &&
				board[col+3][row] == player1)
				{
					c_board[col+1][row] += twoIn*h;
					c_board[col+1][row] += twoIn*h;
				}
			else if (board[col][row] == ' ' &&
				board[col][row] == board[col+1][row] &&
				board[col+2][row] == player1 &&
				board[col+3][row] == player1)
				{
					c_board[col][row] += twoIn*h;
					c_board[col+1][row] += twoIn*h;
				}
		}
	}
	
	for (int row=0; row<3;row++)		
	{
		for (int col = 0;col<4;col++)
		{
			if (board[col][row] == player1 &&
				board[col][row] == board[col+1][row+1] &&
				board[col+2][row+2] == ' ' &&
				board[col+3][row+3] == ' ')
				{
					c_board[col+2][row+2] += twoIn*d;
					c_board[col+3][row+3] += twoIn*d;
				}
			else if (board[col][row] == player1 &&
				board[col+1][row+1] == ' ' &&
				board[col+2][row+2] == ' ' &&
				board[col][row] == board[col+3][row+3])
				{
					c_board[col+1][row+1] += twoIn*d;
					c_board[col+2][row+2] += twoIn*d;
				}
			else if (board[col][row] == ' ' &&
				board[col+1][row+1] == ' ' &&
				board[col+2][row+2] == player1 &&
				board[col+3][row+3] == player1)
				{
					c_board[col][row] += twoIn*d;
					c_board[col+1][row+1] += twoIn*d;
				}
			else if (board[col][row] == ' ' &&
				board[col+1][row+1] == player1 &&
				board[col][row] == board[col+2][row+2] &&
				board[col+1][row+1] == board[col+3][row+3])
				{
					c_board[col][row] += twoIn*d;
					c_board[col+2][row+2] += twoIn*d;
				}
			else if (board[col][row] == player1 &&
				board[col+1][row+1] == ' ' &&
				board[col][row] == board[col+2][row+2] &&
				board[col+1][row+1] == board[col+3][row+3])
				{
					c_board[col+1][row+1] += twoIn*d;
					c_board[col+3][row+3] += twoIn*d;
				}
			else if (board[col][row] == ' ' &&
				board[col+1][row+1] == player1 &&
				board[col+1][row+1] == board[col+2][row+2] &&
				board[col][row] == board[col+3][row+3])
				{
					c_board[col][row] += twoIn*d;
					c_board[col+3][row+3] += twoIn*d;
				}
		}
	}
	
	for (int row= 0;row<3;row++)
	{
		for (int col = 0;col<4;col++)
		{
			if (board[col][row] == player1 &&
				board[col][row] == board[col+1][row+1] &&
				board[col+2][row+2] == ' ' &&
				board[col+3][row+3] == ' ')
				{
					c_board[col+2][row+2] += twoIn*d;
					c_board[col+3][row+3] += twoIn*d;
				}
			else if (board[col][row] == player1 &&
				board[col+1][row+1] == ' ' &&
				board[col+2][row+2] == ' ' &&
				board[col][row] == board[col+3][row+3])
				{
					c_board[col+1][row+1] += twoIn*d;
					c_board[col+2][row+2] += twoIn*d;
				}
			else if (board[col][row] == ' ' &&
				board[col+1][row+1] == ' ' &&
				board[col+2][row+2] == player1 &&
				board[col+3][row+3] == player1)
				{
					c_board[col][row] += twoIn*d;
					c_board[col+1][row+1] += twoIn*d;
				}
			else if (board[col][row] == ' ' &&
				board[col+1][row+1] == player1 &&
				board[col][row] == board[col+2][row+2] &&
				board[col+1][row+1] == board[col+3][row+3])
				{
					c_board[col][row] += twoIn*d;
					c_board[col+2][row+2] += twoIn*d;
				}
			else if (board[col][row] == player1 &&
				board[col+1][row+1] == ' ' &&
				board[col][row] == board[col+2][row+2] &&
				board[col+1][row+1] == board[col+3][row+3])
				{
					c_board[col+1][row+1] += twoIn*d;
					c_board[col+3][row+3] += twoIn*d;
				}
			else if (board[col][row] == ' ' &&
				board[col+1][row+1] == player1 &&
				board[col+1][row+1] == board[col+2][row+2] &&
				board[col][row] == board[col+3][row+3])
				{
					c_board[col][row] += twoIn*2*d;
					c_board[col+3][row+3] += twoIn*2*d;
				}
		}
	}
	//Check for horizontal 3-in-a-row.
	for (int row = 0 ; row<6 ;row++)
	{
		for (int col = 0;col<4;col++)
		{
			//(xx' 'x)
			if (board[col][row] != ' ' &&
				board[col][row] == board[col+1][row] &&
				board[col+2][row] == ' ' &&
				board[col][row] == board[col+3][row])
				{
					c_board[col+2][row] += threeIn*h;
					
				}
			//(x' 'xx)
			else if (board[col][row] != ' ' &&
				board[col+1][row] == ' ' &&
				board[col][row] == board[col+2][row] &&
				board[col][row] == board[col+3][row])
				{
					c_board[col+1][row] += threeIn*h;
				}
			//(' 'xxx)
			else if (board[col][row] == ' ' &&
				board[col+1][row] != ' ' &&
				board[col+1][row] == board[col+2][row] &&
				board[col+1][row] == board[col+3][row])
				{
					c_board[col][row] += threeIn*h;
				}
			//(xxx' ')
			else if (board[col][row] != ' ' &&
				board[col][row] == board[col+1][row] &&
				board[col][row] == board[col+2][row] &&
				board[col+3][row] == ' ')
				{
					c_board[col+3][row] += threeIn*h;
				}
		}
	}

	for (int row=0;row<3;row++)
	{
		for (int col = 0;col<7;col++)
		{
			if (board[col][row] != ' ' &&
				board[col][row] == board[col][row+1] &&
				board[col][row] == board[col][row+2] &&
				board[col][row+3] == ' ')
				{
					c_board[col][row+3] += threeIn*v;
				}
		}
	}


	for (int row=0;row<3;row++)
	{
		for (int col = 0;col<4;col++)
		{
			if (board[col][row] != ' ' &&
				board[col][row] == board[col+1][row+1] &&
				board[col][row] == board[col+2][row+2] &&
				board[col+3][row+3] == ' ')
				{
					c_board[col+3][row+3] += threeIn*d;
				}
			else if (board[col][row] != ' ' &&
				board[col][row] == board[col+1][row+1] &&
				board[col+2][row+2] == ' ' &&
				board[col][row] == board[col+3][row+3])
				{
					c_board[col+2][row+2] += threeIn*d;
				}
			else if (board[col][row] != ' ' &&
				board[col+1][row+1] == ' ' &&
				board[col][row] == board[col+2][row+2] &&
				board[col][row] == board[col+3][row+3])
				{
					c_board[col+1][row+1] += threeIn*d;
				}
			else if (board[col][row] == ' ' &&
				board[col+1][row+1] != ' ' &&
				board[col+1][row+1] == board[col+2][row+2] &&
				board[col+1][row+1] == board[col+3][row+3])
				{
					c_board[col][row] += threeIn*d;
				}
		}
	}
	
	for (int row= 0;row<3;row++)
	{
		for (int col = 0;col<4;col++)
		{
			if (board[col][row] == ' ' &&
				board[col+1][row+1] != ' ' &&
				board[col+1][row+1] == board[col+2][row+2] &&
				board[col+1][row+1] == board[col+3][row+3])
				{
					c_board[col][row] += threeIn*d;
				}
			else if (board[col][row] != ' ' &&
				board[col+1][row+1] == ' ' &&
				board[col][row] == board[col+2][row+2] &&
				board[col][row] == board[col+3][row+3])
				{
					c_board[col+1][row+1] += threeIn*d;
				}
			else if (board[col][row] != ' ' &&
				board[col][row] == board[col+1][row+1] &&
				board[col+2][row+2] == ' ' &&
				board[col][row] == board[col+3][row+3])
				{
					c_board[col+2][row+2] += threeIn*d;
				}
			else if (board[col][row] != ' ' &&
				board[col][row] == board[col+1][row+1] &&
				board[col][row] == board[col+2][row+2] &&
				board[col+3][row+3] == ' ')
				{
					c_board[col+3][row+3] += threeIn*d;
				}
		}
	}

	
	for (int row=0;row<2;row++)
		{
			for (int col = 0;col<3;col++)
			{
				if (board[col][row] == ' ' &&
					board[col+1][row+1] != ' ' &&
					board[col+2][row+2] != ' ' &&
					board[col+3][row+3] != ' ' &&
					board[col+4][row+4] == ' ')
					{
						c_board[col][row] += 2* threeIn*d;
						c_board[col+4][row+4] += 2* threeIn*d;
					}
			}
		}
	
	int result;
	result = max_check(board,c_board);
	
	System.out.println("컴퓨터가 선택한 col: " + (result+1));
	return result+1;
}

}
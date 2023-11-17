package is.game;

import is.utils.MyVector;

public class BoardManager
{
    private static BoardManager Instance;
    private char[][] board;
    public final static int N = 3;

    public enum Player{
        X,
        O;

        public char getChar() {
            return this == Player.X ? 'X' : 'O';
        }
    }//Player

    private BoardManager(){
        board = new char[N][N];
        for( int i = 0; i<N; i++)
            for( int j=0; j<N; j++)
                board[i][j] = '.';
    }

    public static BoardManager getInstance(){
        if( Instance == null)
            Instance = new BoardManager();
        return Instance;
    }

    public void setMove(MyVector move, Player to_move){
        board[move.x][move.y] = to_move.getChar();
    }//setMove

    public void clearCell(MyVector move){
        board[move.x][move.y] = '.';
    }//clearCell

    public boolean isCellOccupiedBy(int x, int y, Player p){
        if( x<0 || x>=N || y<0 || y>=N) return false;
        return board[x][y] == p.getChar();
    }//checkCell

    public boolean feasibleMove(int x, int y){
        return board[x][y] == '.';
    }//feasibleCell

    public void display(){
        for( int i = 0; i<N; i++){
            for( int j=0; j<N; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }//display
}//BoardManager

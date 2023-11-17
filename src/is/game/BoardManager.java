package is.game;

import is.utils.MyVector;

public class BoardManager
{
    private static BoardManager instance;
    private char[][] board;
    public final static int N = 3;

    public enum Player{
        X,
        O;

        public static char getChar(Player to_move) {
            return to_move == Player.X ? 'X' : 'O';
        }
    }//Player

    private BoardManager(){
        board = new char[N][N];
        for( int i = 0; i<N; i++)
            for( int j=0; j<N; j++)
                board[i][j] = '.';
    }

    public static BoardManager getInstance(){
        if( instance == null)
            instance = new BoardManager();
        return instance;
    }

    public void setMove(MyVector move, Player to_move){
        board[move.x][move.y] = Player.getChar(to_move);
    }//setMove

    public void clearCell(MyVector move){
        board[move.x][move.y] = '.';
    }//clearCell

    public boolean checkCell(int x, int y, Player p){
        return board[x][y] == Player.getChar(p);
    }//checkCell

    public boolean isBoardFull(){
        for( int i = 0; i<N; i++)
            for( int j=0; j<N; j++)
                if( board[i][j] == '.' )
                    return false;
        return true;
    }//isBoardFull

    public void display(){
        for( int i = 0; i<N; i++){
            for( int j=0; j<N; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }//display
}//BoardManager

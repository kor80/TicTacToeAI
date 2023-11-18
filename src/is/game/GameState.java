package is.game;

import is.utils.MyVector;
import java.util.LinkedList;

public class GameState
{
    private final BoardManager.Player toMove;
    private final float utility;
    private LinkedList<MyVector> moves;
    private char[][] board;

    public GameState(BoardManager.Player toMove, float utility, LinkedList<MyVector> moves, char[][] board){
        this.toMove = toMove;
        this.utility = utility;
        this.moves = moves;
        this.board = board;
    }

    public LinkedList<MyVector> getMoves(){
        return new LinkedList<>(moves);
    }//getMoves

    public BoardManager.Player getPlayer(){ return toMove; }

    public float getUtility(){ return utility; }

    public void displayBoard(){
        for( int i = 0; i<Game.N; i++){
            for( int j=0; j<Game.N; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }//display

    public char[][] getBoard(){
        char[][] res = new char[Game.N][Game.N];
        for( int i=0; i<Game.N; i++)
            for( int j=0; j<Game.N; j++)
                res[i][j] = this.board[i][j];

        return res;
    }//getBoard
}//is.game.GameState

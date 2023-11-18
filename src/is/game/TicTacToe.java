package is.game;

import is.player.AlphaBetaPlayer;
import is.player.MinMaxPlayer;
import is.player.Player;
import is.utils.MyVector;

import java.util.LinkedList;

public class TicTacToe extends Game
{
    public TicTacToe( BoardManager.Player initialPlayer ){
        LinkedList<MyVector> moves = new LinkedList<>();
        for( int i = 0; i<N; i++)
            for( int j=0; j<N; j++)
                moves.addLast(new MyVector(i,j));

        char[][] board = new char[N][N];
        for( int i = 0; i<N; i++)
            for( int j=0; j<N; j++)
                board[i][j] = '.';

        initial = new GameState(initialPlayer,0,moves, board);
    }

    @Override
    public LinkedList<MyVector> actions(GameState state) {
        return state.getMoves();
    }//actions

    @Override
    public GameState result(GameState state, MyVector move) {
        BoardManager.Player playerMoving = state.getPlayer();

        BoardManager.Player nextPlayer;
        if( playerMoving == BoardManager.Player.X) nextPlayer = BoardManager.Player.O;
        else nextPlayer = BoardManager.Player.X;

        char[][] newBoard = state.getBoard();
        newBoard[move.x][move.y] = playerMoving.getChar();

        LinkedList<MyVector> newMoves = state.getMoves();
        newMoves.remove(move);

        return new GameState(nextPlayer,computeUtility(move,state,playerMoving),newMoves,newBoard);
    }//result

    @Override
    public float utility(GameState state, BoardManager.Player to_move) {
        float u = state.getUtility();
        return to_move == BoardManager.Player.X ? u : -u;
    }//utility

    private float computeUtility(MyVector move, GameState state, BoardManager.Player to_move){
        if( NInRow(move,state,to_move,new MyVector(0,1)) || NInRow(move,state,to_move,new MyVector(1,0)) ||
                NInRow(move,state,to_move,new MyVector(1,-1)) || NInRow(move,state,to_move,new MyVector(1,1)) ){
            if( to_move == BoardManager.Player.X ) return 1;
            return -1;
        }
        else return 0;
    }//computeUtility

    private boolean NInRow(MyVector move, GameState state, BoardManager.Player player, MyVector deltaXY){
        int n = 0;

        int x = move.x;
        int y = move.y;
        while( state.getBoard()[x][y] == player.getChar() ){
            n += 1;
            x += deltaXY.x;
            y += deltaXY.y;
        }

        x = move.x;
        y = move.y;
        while( state.getBoard()[x][y] == player.getChar() ){
            n += 1;
            x -= deltaXY.x;
            y -= deltaXY.y;
        }

        n -= 1;
        return n >= N;
    }//NInRow

    @Override
    public boolean terminalTest(GameState state) {
        return state.getUtility() != 0 || state.getMoves().isEmpty();
    }//terminalTest

    @Override
    public BoardManager.Player toMove(GameState state) {
        return state.getPlayer();
    }//toMove

    public static void main(String[] args) {
        TicTacToe ttt = new TicTacToe(BoardManager.Player.X);
        Player p1 = new AlphaBetaPlayer(ttt);
        Player p2 = new AlphaBetaPlayer(ttt);
        System.out.println(ttt.play(p1,p2));
    }//main
}//Game

package is.game;

import is.command.ActionCommand;
import is.command.Handler;
import is.command.HistoryCommandHandler;
import is.utils.MyVector;

import java.util.LinkedList;

public class TicTacToe extends Game
{
    private LinkedList<MyVector> moves;
    private int N;
    private Handler histCmdHandler;

    public TicTacToe( GameState initial ){
        super(initial);
        N = BoardManager.N;
        histCmdHandler = new HistoryCommandHandler();

        moves = new LinkedList<>();
        for( int i = 0; i<N; i++)
            for( int j=0; j<N; i++)
                moves.addLast(new MyVector(i,j));
    }

    @Override
    public LinkedList<MyVector> actions(GameState state) {
        return state.getMoves();
    }//actions

    @Override
    public GameState result(GameState state, MyVector move) {
        BoardManager.Player playerMoving = state.getPlayer();
        histCmdHandler.handle(new ActionCommand(move,state.getPlayer()));
        //From now on the internal state (board) is changed

        BoardManager.Player nextPlayer;
        if( playerMoving == BoardManager.Player.X) nextPlayer = BoardManager.Player.O;
        else nextPlayer = BoardManager.Player.X;

        moves.remove(move);
        return new GameState(nextPlayer,computeUtility(move,nextPlayer),moves);
    }//result

    @Override
    public float utility(GameState state, BoardManager.Player to_move) {
        float u = state.getUtility();
        return to_move == BoardManager.Player.X ? u : -u;
    }//utility

    private float computeUtility(MyVector move, BoardManager.Player to_move){
        if( NInRow(move,to_move,new MyVector(0,1)) || NInRow(move,to_move,new MyVector(1,0)) ||
                NInRow(move,to_move,new MyVector(1,-1)) || NInRow(move,to_move,new MyVector(1,1)) ){
            if( to_move == BoardManager.Player.X ) return 1;
            return -1;
        }
        else return 0;
    }//computeUtility

    private boolean NInRow(MyVector move, BoardManager.Player player, MyVector deltaXY){
        int n = 0;
        int x = move.x;
        int y = move.y;
        while( BoardManager.getInstance().checkCell(x,y,player) ){
            n += 1;
            x += deltaXY.x;
            y += deltaXY.y;
        }

        x = move.x;
        y = move.y;
        while( BoardManager.getInstance().checkCell(x,y,player) ){
            n += 1;
            x -= deltaXY.x;
            y -= deltaXY.y;
        }

        n -= 1;
        return n >= N;
    }//NInRow

    @Override
    public boolean terminalTest() {
        return BoardManager.getInstance().isBoardFull();
    }//terminalTest

    @Override
    public BoardManager.Player toMove(GameState state) {
        return state.getPlayer();
    }//toMove
}//Game

package is.game;

import is.command.ActionCommand;
import is.command.HistoryCommandHandler;
import is.player.AlphaBetaPlayer;
import is.player.Player;
import is.utils.MyVector;

import java.util.LinkedList;

public class TicTacToe extends Game
{
    private final int N;

    public TicTacToe( BoardManager.Player initialPlayer ){
        N = BoardManager.N;
        histCmdHandler = new HistoryCommandHandler();

        LinkedList<MyVector> moves = new LinkedList<>();
        for( int i = 0; i<N; i++)
            for( int j=0; j<N; j++)
                moves.addLast(new MyVector(i,j));

        initial = new GameState(initialPlayer,0,moves);
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

        LinkedList<MyVector> newMoves = state.getMoves();
        newMoves.remove(move);
        return new GameState(nextPlayer,computeUtility(move,playerMoving),newMoves);
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
        while( BoardManager.getInstance().isCellOccupiedBy(x,y,player) ){
            n += 1;
            x += deltaXY.x;
            y += deltaXY.y;
        }

        x = move.x;
        y = move.y;
        while( BoardManager.getInstance().isCellOccupiedBy(x,y,player) ){
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

    private HistoryCommandHandler getHandler(){ return histCmdHandler; }

    // we set a fixed depth limit "d" so that CUTOFF-TEST(state, depth) returns true for all depth greater than
    // some fixed depth d. (It must also return true for all terminal states, just as TERMINAL-TEST
    public boolean cutoff_test(GameState state, int d) {
        int depth = 0;
        return depth > d || terminalTest(state);
    }

    // evaluation function TODO
    public float eval(GameState state, BoardManager.Player player) {
        float h_value = (float) 0.0;
        BoardManager board = BoardManager.getInstance();
        if(board.isCellOccupiedBy(N, N, player)) {
            return h_value;
        }
         return h_value;      
    }

    public static void main(String[] args) {
        TicTacToe ttt = new TicTacToe(BoardManager.Player.X);
        Player p1 = new AlphaBetaPlayer(ttt, ttt.getHandler());
        Player p2 = new AlphaBetaPlayer(ttt, ttt.getHandler());
        System.out.println(ttt.play(p1,p2));
    }//main
}//Game

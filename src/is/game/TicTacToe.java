package is.game;

import is.command.ActionCommand;
import is.command.HistoryCommandHandler;
import is.player.AlphaBetaCutoffPlayer;
import is.player.AlphaBetaPlayer;
import is.player.AIPlayer;
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
    public float utility(GameState state, BoardManager.Player player) {
        float u = state.getUtility();
        return player == BoardManager.Player.X ? u : -u;
    }//utility

    private float computeUtility(MyVector move, BoardManager.Player to_move){
        if( NInRow(move,to_move,new MyVector(0,1)) >= N || NInRow(move,to_move,new MyVector(1,0)) >= N ||
                NInRow(move,to_move,new MyVector(1,-1)) >= N || NInRow(move,to_move,new MyVector(1,1)) >= N){
            if( to_move == BoardManager.Player.X ) return 1;
            return -1;
        }
        else return 0;
    }//computeUtility

    private int NInRow(MyVector move, BoardManager.Player player, MyVector deltaXY){
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
        return n;
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

    @Override
    public float eval(GameState state) {
        float utility = state.getUtility();
        if( utility != 0 ) return utility;


        BoardManager.Player player = state.getPlayer();
        BoardManager.Player opposite;
        if( player == BoardManager.Player.X) opposite = BoardManager.Player.O;
        else opposite = BoardManager.Player.X;

        // Counting Lines
        for( int i=-1; i<2; i++){
            int countCurrPlayer = NInRow(new MyVector(0,0), player, new MyVector(1,i));
            int countOppositePlayer = NInRow(new MyVector(0,0), opposite, new MyVector(1,i));
            if( countCurrPlayer == 2 && countOppositePlayer == 0 ) utility += 1;
            else if( countOppositePlayer == 2 && countCurrPlayer == 0 ) utility -= 1;
        }

        int countCurrPlayer = NInRow(new MyVector(0,0), player, new MyVector(0,1));
        int countOppositePlayer = NInRow(new MyVector(0,0), opposite, new MyVector(0,1));
        if( countCurrPlayer == 2 && countOppositePlayer == 0 ) utility += 1;
        else if( countOppositePlayer == 2 && countCurrPlayer == 0 ) utility -= 1;

        // Center Control
        if( BoardManager.getInstance().isCellOccupiedBy(1,1,player) ) utility += 0.5f;
        else if( BoardManager.getInstance().isCellOccupiedBy(1,1,opposite) ) utility -= 0.5f;

        return utility;
    }//eval

    public static void main(String[] args) {
        TicTacToe ttt = new TicTacToe(BoardManager.Player.X);
        AIPlayer p1 = new AlphaBetaPlayer(ttt, ttt.getHandler());
        AIPlayer p2 = new AlphaBetaCutoffPlayer(ttt, ttt.getHandler(),3);
        System.out.println(ttt.play(p1,p2));
    }//main
}//Game

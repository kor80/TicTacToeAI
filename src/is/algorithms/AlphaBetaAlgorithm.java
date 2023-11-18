package is.algorithms;

import is.command.HistoryCommandHandler;
import is.game.Game;
import is.game.GameState;
import is.utils.MyVector;

public class AlphaBetaAlgorithm extends AbstractAlgorithms{

    public AlphaBetaAlgorithm(Game game, HistoryCommandHandler handler) {
        super(game, handler);
    }

    public MyVector searchForBestAction(GameState state ){
        player = state.getPlayer();
        float alpha = Integer.MIN_VALUE;
        float beta = Integer.MAX_VALUE;
        MyVector bestAction = null;
        float v;

        for( MyVector action : game.actions(state) ){
            v = minValue(game.result(state,action), alpha, beta);
            handler.undo();
            if( v > alpha ){
                alpha = v;
                bestAction = action;
            }
        }
        return bestAction;
    }//alphaBetaSearch

    protected float maxValue( GameState state, float alpha, float beta ){
        if( game.terminalTest(state) )
            return game.utility(state,player);

        float v = Integer.MIN_VALUE;
        for( MyVector action : game.actions(state) ){
            v = Math.max(v, minValue(game.result(state,action), alpha, beta) );
            handler.undo();
            if( v >= beta ) return v;
            alpha = Math.max(alpha, v);
        }
        return v;
    }//maxValue

    private float minValue(GameState state, float alpha, float beta){
        if(game.terminalTest(state) )
            return game.utility(state, player);

        float v = Integer.MAX_VALUE;
        for( MyVector action : game.actions(state) ){
            v = Math.min(v, maxValue(game.result(state,action), alpha, beta) );
            handler.undo();
            if( v <= alpha ) return v;
            beta = Math.min(beta,v);
        }
        return v;
    }//minValue
}//AlphaBetaAlgorithm

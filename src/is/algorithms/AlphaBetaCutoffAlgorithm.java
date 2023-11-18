package is.algorithms;

import is.command.HistoryCommandHandler;
import is.game.Game;
import is.game.GameState;
import is.utils.MyVector;

public class AlphaBetaCutoffAlgorithm extends AbstractAlgorithms{
    private final int D;

    public AlphaBetaCutoffAlgorithm(Game game, HistoryCommandHandler handler, int maxDepth) {
        super(game, handler);
        D = maxDepth;
    }

    public MyVector searchForBestAction(GameState state ){
        player = state.getPlayer();
        float alpha = Integer.MIN_VALUE;
        float beta = Integer.MAX_VALUE;
        MyVector bestAction = null;
        float v;

        for( MyVector action : game.actions(state) ){
            v = minValue(game.result(state,action), alpha, beta, 1);
            handler.undo();
            if( v > alpha ){
                alpha = v;
                bestAction = action;
            }
        }
        System.out.println(alpha);
        return bestAction;
    }//alphaBetaSearch

    private float maxValue(GameState state, float alpha, float beta, int depth ){
        if( cutoff_test(state, depth) )
            return game.eval(state);

        float v = Integer.MIN_VALUE;
        for( MyVector action : game.actions(state) ){
            v = Math.max(v, minValue(game.result(state,action), alpha, beta, depth + 1) );
            handler.undo();
            if( v >= beta ) return v;
            alpha = Math.max(alpha, v);
        }
        return v;
    }//maxValue

    private float minValue(GameState state, float alpha, float beta, int depth){
        if( cutoff_test(state, depth) )
            return game.eval(state);

        float v = Integer.MAX_VALUE;
        for( MyVector action : game.actions(state) ){
            v = Math.min(v, maxValue(game.result(state,action), alpha, beta, depth+1) );
            handler.undo();
            if( v <= alpha ) return v;
            beta = Math.min(beta,v);
        }
        return v;
    }//minValue

    // we set a fixed depth limit "d" so that CUTOFF-TEST(state, depth) returns true for all depth greater than
    // some fixed depth d. (It must also return true for all terminal states, just as TERMINAL-TEST
    private boolean cutoff_test(GameState state, int currentDepth) {
        return currentDepth > D || game.terminalTest(state);
    }//currentDepth
}//AlphaBetaCutoffAlgorithm

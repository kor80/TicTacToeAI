package is.algorithms;

import is.command.HistoryCommandHandler;
import is.game.BoardManager;
import is.game.Game;
import is.game.GameState;
import is.utils.MyVector;

import java.util.LinkedList;

public class Algorithms
{
    private final Game game;
    private BoardManager.Player player = null;
    private final HistoryCommandHandler handler;

    public Algorithms(Game game, HistoryCommandHandler handler){
        this.game = game;
        this.handler = handler;
    }

    //------------------------------------MIN-MAX------------------------------------
    public MyVector minMaxDecision(GameState state){
        player = state.getPlayer();
        return maxBetweenMin(game.actions(state),state);
    }//minMaxDecision

    private float maxValue(GameState state){
        if( game.terminalTest(state) )
            return game.utility(state,player);

        float v = Integer.MIN_VALUE;
        for( MyVector action : game.actions(state) ){
            v = Math.max(v, minValue(game.result(state,action)) );
        }
        return v;
    }//maxValue

    private float minValue(GameState state){
        if(game.terminalTest(state) )
            return game.utility(state, player);

        float v = Integer.MAX_VALUE;
        for( MyVector action : game.actions(state) ){
            v = Math.min(v, maxValue(game.result(state,action)) );
            handler.undo();
        }
        return v;
    }//minValue

    private MyVector maxBetweenMin(LinkedList<MyVector> actions, GameState state){
        float max = Integer.MIN_VALUE;
        MyVector move = null;
        for( MyVector action : actions ){
            float v = minValue(game.result(state, action));
            handler.undo();
            if( v > max ){
                max = v;
                move = action;
            }
        }
        return move;
    }//map


    //------------------------------------ALPHA-BETA-SEARCH------------------------------------
    public MyVector alphaBetaSearch( GameState state ){
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

    private float maxValue(GameState state, float alpha, float beta ){
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


    //------------------------------------ALPHA-BETA-CUTOFF------------------------------------
}//Algorithms

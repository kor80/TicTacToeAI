package is.algorithms;

import is.game.BoardManager;
import is.game.Game;
import is.game.GameState;
import is.utils.MyVector;

import java.util.LinkedList;

public class Algorithms
{
    private Game game;
    private BoardManager.Player player = null;

    public Algorithms(Game game){
        this.game = game;
    }

    public MyVector minMaxDecision(GameState state){
        player = state.getPlayer();
        return maxBetweenMin(game.actions(state),state);
    }//minMaxDecision

    private float maxValue(GameState state){
        if(game.terminalTest() )
            return game.utility(state,player);

        float v = Integer.MIN_VALUE;
        for( MyVector action : game.actions(state) )
            v = Math.max(v, minValue(game.result(state,action)) );
        return v;
    }//maxValue

    private float minValue(GameState state){
        if(game.terminalTest() )
            return game.utility(state,player);

        float v = Integer.MAX_VALUE;
        for( MyVector action : game.actions(state) )
            v = Math.min(v, maxValue(game.result(state,action)) );
        return v;
    }//minValue

    private MyVector maxBetweenMin(LinkedList<MyVector> actions, GameState state){
        float min = Integer.MAX_VALUE;
        MyVector move = null;
        for( MyVector action : actions ){
            float v = minValue(game.result(state, action));
            if( v < min ){
                min = v;
                move = action;
            }
        }
        return move;
    }//map
}//Algorithms

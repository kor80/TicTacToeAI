package is.game;

import is.player.Player;
import is.utils.MyVector;

import java.util.LinkedList;

public abstract class Game
{
    private GameState initial;

    protected Game( GameState initial ){
        this.initial = initial;
    }

    public abstract LinkedList<MyVector> actions(GameState state);

    public abstract GameState result(GameState state, MyVector move);

    public abstract float utility(GameState state, BoardManager.Player to_move);

    public abstract boolean terminalTest();

    public abstract BoardManager.Player toMove(GameState state);

    public void display(){
        BoardManager.getInstance().display();
    }//display

    public float play( Player... players){
        GameState state = initial;
        while( true ){
            for( Player p : players){
                MyVector move = p.getNextAction(state);
                state = result(state, move);
                if( terminalTest() ){
                    display();
                    return utility(state, toMove(initial));
                }
            }
        }
    }//play
}//is.game.Game

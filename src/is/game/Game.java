package is.game;

import is.player.Player;
import is.utils.MyVector;

import java.util.LinkedList;

public abstract class Game
{
    protected GameState initial;

    public abstract LinkedList<MyVector> actions(GameState state);

    public abstract GameState result(GameState state, MyVector move);

    public abstract float utility(GameState state, BoardManager.Player to_move);

    public abstract boolean terminalTest(GameState state);

    public abstract BoardManager.Player toMove(GameState state);

    public void display(){
        BoardManager.getInstance().display();
    }//display

    public float play( Player... players){
        System.out.println("Match started...");
        GameState state = initial;
        int t=1;
        while( true ){
            System.out.printf("----------Turn %d----------%n",t);
            for( Player p : players ){
                System.out.printf("---> Player %s%n",state.getPlayer().getChar());
                display();
                MyVector move = p.getNextAction(state);
                state = result(state, move);
                if( terminalTest(state) ){
                    System.out.println("Final board");
                    display();
                    return utility(state, toMove(initial));
                }
            }
            t ++;
        }
    }//play
}//is.game.Game

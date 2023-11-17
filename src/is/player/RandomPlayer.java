package is.player;

import is.game.Game;
import is.game.GameState;
import is.utils.MyVector;

import java.util.LinkedList;
import java.util.Random;

public class RandomPlayer implements Player{
    private Game game;
    private Random rand;

    public RandomPlayer(Game game){
        this.game = game;
        rand = new Random();
    }

    @Override
    public MyVector getNextAction(GameState state) {
        LinkedList<MyVector> actions = game.actions(state);
        if( !actions.isEmpty() ){
            return actions.get(rand.nextInt(actions.size()));
        }
        return null;
    }//getNextAction
}//RandomPlayer

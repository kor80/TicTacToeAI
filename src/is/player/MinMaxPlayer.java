package is.player;

import is.algorithms.Algorithms;
import is.game.Game;
import is.game.GameState;
import is.utils.MyVector;

public class MinMaxPlayer implements Player{
    private Game game;
    private Algorithms algorithms;

    public MinMaxPlayer(Game game){
        this.game = game;
        algorithms = new Algorithms(game);
    }
    @Override
    public MyVector getNextAction(GameState state) {
        return algorithms.minMaxDecision(state);
    }//getNextAction
}//MinMaxPlayer

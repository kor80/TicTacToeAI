package is.player;

import is.algorithms.Algorithms;
import is.command.HistoryCommandHandler;
import is.game.Game;
import is.game.GameState;
import is.utils.MyVector;

public class MinMaxPlayer implements Player{
    private final Algorithms algorithms;

    public MinMaxPlayer(Game game, HistoryCommandHandler handler){
        algorithms = new Algorithms(game, handler);
    }
    @Override
    public MyVector getNextAction(GameState state) {
        return algorithms.minMaxDecision(state);
    }//getNextAction
}//MinMaxPlayer

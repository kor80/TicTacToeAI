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
        long startingTime = System.currentTimeMillis();
        MyVector move = algorithms.minMaxDecision(state);
        System.out.println("Time spent for the move: "+(System.currentTimeMillis()-startingTime)+"ms");
        return move;
    }//getNextAction
}//MinMaxPlayer

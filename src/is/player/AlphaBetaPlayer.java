package is.player;

import is.algorithms.Algorithms;
import is.command.HistoryCommandHandler;
import is.game.Game;
import is.game.GameState;
import is.utils.MyVector;

public class AlphaBetaPlayer implements Player{
    private final Algorithms algorithms;

    public AlphaBetaPlayer(Game game, HistoryCommandHandler handler){
        algorithms = new Algorithms(game,handler);
    }

    @Override
    public MyVector getNextAction(GameState state) {
        long startingTime = System.currentTimeMillis();
        MyVector move = algorithms.alphaBetaSearch(state);
        System.out.println("Time spent for the move: "+(System.currentTimeMillis()-startingTime)+"ms");
        return move;
    }//getNextAction
}//AlphaBetaPlayer

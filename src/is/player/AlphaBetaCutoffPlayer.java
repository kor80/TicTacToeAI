package is.player;

import is.algorithms.AlphaBetaCutoffAlgorithm;
import is.command.HistoryCommandHandler;
import is.game.Game;

public class AlphaBetaCutoffPlayer extends AIPlayer{
    public AlphaBetaCutoffPlayer(Game game, HistoryCommandHandler handler, int maxDepth) {
        super(new AlphaBetaCutoffAlgorithm(game,handler,maxDepth));
    }
}//AIPlayer

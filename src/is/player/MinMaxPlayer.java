package is.player;

import is.algorithms.MinMaxAlgorithm;
import is.command.HistoryCommandHandler;
import is.game.Game;

public class MinMaxPlayer extends AIPlayer {
    public MinMaxPlayer(Game game, HistoryCommandHandler handler){
        super(new MinMaxAlgorithm(game, handler));
    }
}//MinMaxPlayer

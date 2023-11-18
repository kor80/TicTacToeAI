package is.algorithms;

import is.command.HistoryCommandHandler;
import is.game.BoardManager;
import is.game.Game;
import is.game.GameState;
import is.utils.MyVector;

public abstract class AbstractAlgorithms {
    protected final Game game;
    protected BoardManager.Player player = null;
    protected final HistoryCommandHandler handler;

    public AbstractAlgorithms(Game game, HistoryCommandHandler handler){
        this.game = game;
        this.handler = handler;
    }

    public abstract MyVector searchForBestAction(GameState state);
}//AbstractAlgorithms

package is.player;

import is.game.GameState;
import is.utils.MyVector;

public interface Player {
    MyVector getNextAction(GameState state);
}//Player

package is.game;

import is.utils.MyVector;

public interface Player {
    MyVector getNextAction(GameState state);
}//Player

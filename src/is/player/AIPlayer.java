package is.player;

import is.algorithms.AbstractAlgorithms;
import is.game.GameState;
import is.utils.MyVector;

public abstract class AIPlayer implements Player{
    private final AbstractAlgorithms algorithm;

    protected AIPlayer(AbstractAlgorithms algorithm){
        this.algorithm = algorithm;
    }

    @Override
    public MyVector getNextAction(GameState state){
        long startingTime = System.currentTimeMillis();
        MyVector move = algorithm.searchForBestAction(state);
        System.out.println("Time spent for the move: "+(System.currentTimeMillis()-startingTime)+"ms");
        return move;
    }//getNextAction
}//Player

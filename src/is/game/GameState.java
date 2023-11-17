package is.game;

import is.utils.MyVector;
import java.util.LinkedList;

public class GameState
{
    private final BoardManager.Player toMove;
    private final float utility;
    private LinkedList<MyVector> moves;

    public GameState(BoardManager.Player toMove, float utility, LinkedList<MyVector> moves){
        this.toMove = toMove;
        this.utility = utility;
        this.moves = moves;
    }

    public LinkedList<MyVector> getMoves(){
        return new LinkedList<>(moves);
    }//getMoves

    public BoardManager.Player getPlayer(){ return toMove; }

    public float getUtility(){ return utility; }
}//is.game.GameState

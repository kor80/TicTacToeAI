package is.game;

import is.utils.MyVector;
import java.util.LinkedList;

public class GameState
{
    private BoardManager.Player toMove;
    private float utility;
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
}//is.game.GameState

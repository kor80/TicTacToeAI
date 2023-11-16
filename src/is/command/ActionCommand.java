package is.command;
import is.game.*;
import is.utils.MyVector;

public class ActionCommand implements Command
{
    private MyVector move;
    private BoardManager.Player to_move;

    public ActionCommand(MyVector move, BoardManager.Player to_move){
        this.move = move;
        this.to_move = to_move;
    }

    public void doIt(){
        BoardManager.getInstance().setMove(move,to_move);
    }//doIt

    public void undoIt(){
        BoardManager.getInstance().clearCell(move);
    }//undoIt
}//ActionCommand

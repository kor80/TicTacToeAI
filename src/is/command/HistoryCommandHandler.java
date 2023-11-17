package is.command;

import java.util.LinkedList;

public class HistoryCommandHandler implements Handler
{
    private LinkedList<Command> history;

    public HistoryCommandHandler(){
        history = new LinkedList<>();
    }

    public void handle(Command cmd){
        cmd.doIt();
        history.addFirst(cmd);
    }//handle

    public void undo(){
        Command cmd = history.removeFirst();
        cmd.undoIt();
    }//cmd

    public void clearHistory(){
        history.clear();
    }//clearStory
}//HistoryCommandHandler

package is.player;

import is.game.BoardManager;
import is.game.Game;
import is.game.GameState;
import is.utils.MyVector;

import java.util.LinkedList;
import java.util.Scanner;

public class QueryPlayer implements Player
{
    private Game game;
    private Scanner sc;

    public QueryPlayer(Game game){
        this.game = game;
        sc = new Scanner(System.in);
    }

    @Override
    public MyVector getNextAction(GameState state) {
        LinkedList<MyVector> actions = game.actions(state);

        System.out.println("Current state:");
        game.display();
        System.out.println("Available moves: "+actions+"\n");

        MyVector move = null;
        if( !actions.isEmpty() ){
            System.out.println("Make your move:");
            move = new MyVector(takeInput("row"), takeInput("column"));
        }
        else System.out.println("The match is ended");
        return move;
    }//getNextAction

    private int takeInput( String what ){
        System.out.printf("%s> ",what);
        int input = sc.nextInt();
        while( input < 1 || input > BoardManager.N ){
            System.out.printf("Invalid %s. Please choose a number between 1 and %d %n",what,BoardManager.N);
            System.out.printf("%s> ",what);
            input = sc.nextInt();
        }
        return input;
    }//takeInput
}//QueryPlayer

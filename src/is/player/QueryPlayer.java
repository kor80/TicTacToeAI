package is.player;

import is.game.BoardManager;
import is.game.Game;
import is.game.GameState;
import is.utils.MyVector;

import java.util.LinkedList;
import java.util.Scanner;

public class QueryPlayer implements Player
{
    private final Game game;
    private final Scanner sc;
    private final int N = BoardManager.N;

    public QueryPlayer(Game game){
        this.game = game;
        sc = new Scanner(System.in);
    }

    @Override
    public MyVector getNextAction(GameState state) {
        LinkedList<MyVector> actions = game.actions(state);

        MyVector move = null;
        if( !actions.isEmpty() ){
            System.out.printf("Make your move (-1<row<%d , -1<column<%d):%n",N,N);
            int x = takeInput("row");
            int y = takeInput("column");
            while( !BoardManager.getInstance().feasibleMove(x,y) ){
                x = takeInput("row");
                y = takeInput("column");
            }
            move = new MyVector(x, y);
        }
        else System.out.println("The match is ended");
        return move;
    }//getNextAction

    private int takeInput( String axis ){
        System.out.printf("%s> ",axis);
        int input;
        try{
            input = sc.nextInt();
        }catch( Exception e ){
            return -1;
        }
        while( input < 0 || input >= BoardManager.N ){
            System.out.printf("Invalid %s. Please choose a number between 0 and %d %n",axis,N-1);
            System.out.printf("%s> ",axis);
            input = sc.nextInt();
        }
        return input;
    }//takeInput
}//QueryPlayer

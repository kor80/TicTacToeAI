package is.game;

import is.utils.MyVector;

import java.util.LinkedList;

public class TicTacToe extends Game
{
    public TicTacToe( GameState initial ){
        super(initial);
    }

    @Override
    public LinkedList<MyVector> actions(GameState state) {
        return null;
    }

    @Override
    public GameState result(GameState state, MyVector move) {
        return null;
    }

    @Override
    public float utility(GameState state, BoardManager.Player to_move) {
        return 0;
    }

    @Override
    public boolean terminalTest(GameState state) {
        return false;
    }

    @Override
    public BoardManager.Player toMove(GameState state) {
        return null;
    }
}//Game

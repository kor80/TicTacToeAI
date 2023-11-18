package is.game;

public class BoardManager
{
    public enum Player{
        X,
        O;

        public char getChar() {
            return this == Player.X ? 'X' : 'O';
        }
    }//Player
}//BoardManager

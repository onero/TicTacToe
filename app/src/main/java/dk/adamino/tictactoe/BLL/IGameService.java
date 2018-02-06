package dk.adamino.tictactoe.BLL;

public interface IGameService {
    boolean gameHasWinner(int[][] gameBoard);
    boolean gameIsDraw(int maxMoves, int movesLeft);
    int getWinnerId();
}

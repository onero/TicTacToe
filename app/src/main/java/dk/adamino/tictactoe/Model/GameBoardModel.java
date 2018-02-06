package dk.adamino.tictactoe.Model;

/**
 * Created by Adamino.
 */

public class GameBoardModel {
    public static final int DIMENSION_X = 3;
    public static final int DIMENSION_Y = 3;
    public static final int PLAYER_ONE_ID = 1;
    public static final int PLAYER_TWO_ID = 2;
    private int[][] mGameBoard;
    private int mMovesTaken;
    private int mMaxMoves;
    private int mCurrentPlayerId;


    public GameBoardModel() {
        mGameBoard = new int[DIMENSION_X][DIMENSION_Y];
        mMaxMoves = DIMENSION_X * DIMENSION_Y;
        mMovesTaken = 0;
        mCurrentPlayerId = 1;
    }

    /**
     * Put defined value on gameboard
     * @param row
     * @param col
     */
    public void updateGameBoard(int row, int col) {
        // Update value
        mGameBoard[row][col] = mCurrentPlayerId;
        // Update moves taken
        mMovesTaken++;
        // Switch to next player
        switchPlayerId();
    }

    public int[][] getGameBoard() {
        return mGameBoard;
    }

    public int getMovesTaken() {
        return mMovesTaken;
    }

    public int getMaxMoves() {
        return mMaxMoves;
    }

    public int getCurrentPlayerId() {
        return mCurrentPlayerId;
    }

    /**
     * Switch current player id to 1, if current 2, otherwise to 1
     */
    private void switchPlayerId() {
        mCurrentPlayerId = (mCurrentPlayerId == PLAYER_ONE_ID) ? PLAYER_TWO_ID : PLAYER_ONE_ID;
    }
}

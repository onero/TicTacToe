package dk.adamino.tictactoe.BLL;

/**
 * Created by Adamino.
 */

public class GameService implements IGameService {

    public static final int NUMBER_OF_BUTTONS = 3;
    public static final int PLAYER_ONE_ID = 1;
    public static final int PLAYER_TWO_ID = 2;
    private boolean hasEmptySpace;
    private int mWinnerId = 0;

    @Override
    public boolean gameHasWinner(int[][] gameBoard) {
        //Horizontal --- rows
        for(int i = 0; i< NUMBER_OF_BUTTONS; i++){
            if(gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][0] == gameBoard[i][2]){
                if (gameBoard[i][0]== PLAYER_ONE_ID){
                    mWinnerId = 1;
                    return true;
                }
                else if (gameBoard[i][0]== PLAYER_TWO_ID) {
                    mWinnerId = 2;
                    return true;
                }
            }
        }

        //Vertical --- columns
        for(int i=0; i<3; i++){
            if(gameBoard[0][i] == gameBoard[1][i] && gameBoard[0][i] == gameBoard[2][i]){
                if (gameBoard[0][i]==PLAYER_ONE_ID){
                    mWinnerId = 1;
                    return true;
                }
                else if (gameBoard[0][i]==PLAYER_TWO_ID) {
                    mWinnerId = 2;
                    return true;
                }
            }
        }

        //First diagonal
        if(gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2]){
            if (gameBoard[0][0]==PLAYER_ONE_ID){
                mWinnerId = 1;
                return true;
            }
            else if (gameBoard[0][0]==PLAYER_TWO_ID) {
                mWinnerId = 2;
                return true;
            }
        }

        //Second diagonal
        if(gameBoard[0][2] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0]){
            if (gameBoard[0][2]==PLAYER_ONE_ID){
                mWinnerId = 1;
                return true;
            }
            else if (gameBoard[0][2]==PLAYER_TWO_ID) {
                mWinnerId = 2;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean gameIsDraw(int maxMoves, int movesTaken) {
        return maxMoves == movesTaken;
    }

    @Override
    public int getWinnerId() {
        return mWinnerId;
    }
}

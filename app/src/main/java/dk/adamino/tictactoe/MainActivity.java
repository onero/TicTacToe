package dk.adamino.tictactoe;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import dk.adamino.tictactoe.BLL.GameService;
import dk.adamino.tictactoe.BLL.IGameService;
import dk.adamino.tictactoe.Model.GameBoardModel;

public class MainActivity extends AppCompatActivity {

    private ImageView mWinnerView;

    public static final String TAG = "Adamino";
    private IGameService mGameService;
    private GameBoardModel mGameBoardModel;

    private Drawable mImageX;
    private Drawable mImageO;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mImageX = getDrawable(R.drawable.x);
        mImageO = getDrawable(R.drawable.o);
        resetApplication();
    }

    /**
     * Set the view with toolbar
     * @param viewId
     */
    private void setView(int viewId) {
        setContentView(viewId);

        Toolbar myChildToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myChildToolbar);
    }

    private void resetApplication() {
        setView(R.layout.activity_main);

        mGameService = new GameService();
        mGameBoardModel = new GameBoardModel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case R.id.btnNewGame:
                // Handle Game Reset
                resetApplication();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    public void onTopOneClicked(View view) {
        Button buttonClicked = (Button) view;
        onButtonClicked(buttonClicked, 1);
    }
    public void onTopTwoClicked(View view) {
        Button buttonClicked = (Button) view;
        onButtonClicked(buttonClicked, 2);
    }
    public void onTopThreeClicked(View view) {
        Button buttonClicked = (Button) view;
        onButtonClicked(buttonClicked, 3);
    }
    public void onMiddleOneClicked(View view) {
        Button buttonClicked = (Button) view;
        onButtonClicked(buttonClicked, 4);
    }
    public void onMiddleTwoClicked(View view) {
        Button buttonClicked = (Button) view;
        onButtonClicked(buttonClicked, 5);
    }
    public void onMiddleThreeClicked(View view) {
        Button buttonClicked = (Button) view;
        onButtonClicked(buttonClicked, 6);
    }
    public void onBottomOneClicked(View view) {
        Button buttonClicked = (Button) view;
        onButtonClicked(buttonClicked, 7);
    }
    public void onBottomTwoClicked(View view) {
        Button buttonClicked = (Button) view;
        onButtonClicked(buttonClicked, 8);
    }
    public void onBottomThreeClicked(View view) {
        Button buttonClicked = (Button) view;
        onButtonClicked(buttonClicked, 9);
    }

    /**
     * React to user clicking a button
     * @param buttonClicked
     * @param buttonPosition
     */
    private void onButtonClicked(Button buttonClicked, int buttonPosition) {
        Drawable currentImage = buttonClicked.getBackground();
        if (currentImage != mImageX && currentImage != mImageO) {
            if (mGameBoardModel.getCurrentPlayerId() == GameBoardModel.PLAYER_ONE_ID) {
                buttonClicked.setBackground(mImageX);
                updateGameBoard(buttonPosition);
            } else {
                buttonClicked.setBackground(mImageO);
                updateGameBoard(buttonPosition);
            }
        }
        checkForWinner();
    }

    private void checkForDraw() {
        boolean gameIsDraw = mGameService.gameIsDraw(mGameBoardModel.getMaxMoves(), mGameBoardModel.getMovesTaken());
        if (gameIsDraw) {
            setWinScreen();

            mWinnerView.setImageResource(R.drawable.draw);
            TextView winnerText = findViewById(R.id.txtWinner);
            winnerText.setText(R.string.draw_text);
        }
    }

    private void setWinScreen() {
        setView(R.layout.win_screen);
        mWinnerView = findViewById(R.id.imgWinner);
    }

    /**
     * Update gameboard with player moves
     * @param buttonPosition
     */
    private void updateGameBoard(int buttonPosition) {
        switch (buttonPosition) {
            case 1:
                mGameBoardModel.updateGameBoard(0, 0);
                break;
            case 2:
                mGameBoardModel.updateGameBoard(0, 1);
                break;
            case 3:
                mGameBoardModel.updateGameBoard(0, 2);
                break;
            case 4:
                mGameBoardModel.updateGameBoard(1, 0);
                break;
            case 5:
                mGameBoardModel.updateGameBoard(1, 1);
                break;
            case 6:
                mGameBoardModel.updateGameBoard(1, 2);
                break;
            case 7:
                mGameBoardModel.updateGameBoard(2, 0);
                break;
            case 8:
                mGameBoardModel.updateGameBoard(2, 1);
                break;
            case 9:
                mGameBoardModel.updateGameBoard(2, 2);
                break;
        }
    }

    /**
     * Check for finished game
     */
    private void checkForWinner() {
        boolean hasWinner = mGameService.gameHasWinner(mGameBoardModel.getGameBoard());
        if (hasWinner) {
            int winner = mGameService.getWinnerId();

            setWinScreen();

            switch (winner) {
                case 1:
                    mWinnerView.setImageResource(R.drawable.x);
                    break;
                case 2:
                    mWinnerView.setImageResource(R.drawable.o);
                    break;
                default:
                    Log.d(TAG, "Game over error!");
                    break;
            }
        } else {
            checkForDraw();
        }
    }
}

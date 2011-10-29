package de.passsy.friendbattle.games;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import de.passsy.friendbattle.Player;
import de.passsy.friendbattle.utility.Tools;

/**
 * The MiniGames for FriendBattle
 * @author Pascal.Welsch
 *
 */
/**
 * @author Pascal.Welsch
 * 
 */
public abstract class MiniGame extends RelativeLayout {

    public interface OnNextGameListener {
	/**
	 * fired if the current MiniGame is solved and a new game should launch
	 * 
	 * @param game
	 */
	public abstract void onNextGame(MiniGame game);
    }

    /**
     * Enum for the orrectness state
     * @author Pascal.Welsch
     *
     */
    public enum Correctness {
	correct, incorrect, toolate;
    }

    /**
     * How long the Introductions are shown
     */
    private static final int HOWTO_TIME = 5;

    /**
     * Listener for the Event OnNextGame
     */
    private OnNextGameListener NextGameListener;

    /**
     * true if the game has the state Correct false if the game isn't correct at
     * the moment
     */
    private boolean mCorrectness = false;

    /**
     * true if the game is solved else false
     */
    private boolean mSolved = false;

    /**
     * timer that runs if the Game is solved by a User to start the next Game
     */
    private Timer mNewGameTimer = new Timer();

    /**
     * Handler to run the mFireTimer from the Timer thread
     */
    private final Handler mHandler = new Handler();

    /**
     * This Runnable is Called from the mNewGameTimer
     */
    final Runnable mFireTimer = new Runnable() {
	public void run() {
	    if (NextGameListener != null) {
		NextGameListener.onNextGame(MiniGame.this);
	    }
	}
    };

    /**
     * Rounds per Game
     */
    private int mRounds;

    /**
     * @return true if the Game is solved
     */
    private boolean isSolved() {
	return mSolved;
    }

    /**
     * true if the game is solved. Users are unable to get points
     * 
     * @param mSolved
     *            solvable?
     */
    private void setSolved(boolean mSolved) {
	this.mSolved = mSolved;
    }

    /**
     * @return Rounds per Game
     */
    public int getRounds() {
	return mRounds;
    }

    /**
     * sets the rounds per game
     * 
     * @param mRounds
     *            rounds per game
     */
    public void setRounds(int mRounds) {
	this.mRounds = mRounds;
    }

    /**
     * @return the correctness of the game
     */
    public boolean getCorrectness() {
	return mCorrectness;
    }

    /**
     * should only set true, if the players are able to solve the game
     * 
     * @param isCorrect
     *            game is correct and can solved?
     */
    public void setCorrectness(boolean isCorrect) {
	this.mCorrectness = isCorrect;
    }

    /**
     * Constructor
     */
    public MiniGame() {
	this(Tools.getContext());
    }

    /**
     * Constructor
     * 
     * @param context
     */
    public MiniGame(Context context) {
	super(context);
    }

    /**
     * Constructor
     * 
     * @param context
     * @param attrs
     */
    public MiniGame(Context context, AttributeSet attrs) {
	super(context, attrs);
    }

    /**
     * standard GuessManagement. gives and reduce points of the Players if they
     * buzz starts the next Game if the answer was correct
     * 
     * @param player
     */
    public Correctness onGuess(Player player) {

	Correctness result = Correctness.incorrect;
	if (getCorrectness()) {
	    // Game is correct
	    if (!isSolved()) {
		// First who guessed correct
		// Points++
		result = Correctness.correct;
	    } else {
		// Player was to late, someone else was faster
		// Points don't change
		result = Correctness.toolate;
	    }
	    // now no one can get points
	    setSolved(true);
	    // start next game in 1 second
	    mNewGameTimer.schedule(new TimerTask() {

		@Override
		public void run() {
		    mHandler.post(mFireTimer);
		}
	    }, 1000);

	} else {
	    // Game isn't correct
	    if (!mSolved) {
		// Player gets Points--
		result = Correctness.incorrect;
	    }
	}
	return result;
    };

    /**
     * gets fired after everything was loaded from the GameCycle run
     * super().start in method @Override
     * 
     */
    public void start() {
	showIntroductions(HOWTO_TIME);
    };

    /**
     * shows the introduction at the beginning of every Game
     * 
     * @param seconds
     */
    abstract protected void showIntroductions(int seconds);

    public void onNextGame(final View v) {
	if (NextGameListener != null) {
	    NextGameListener.onNextGame(this);
	}
    }

    public void setOnNextGameListener(final OnNextGameListener l) {
	NextGameListener = l;
    }

}

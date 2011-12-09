package de.passsy.friendbattle.controllers;

import de.passsy.friendbattle.data.Player;
import de.passsy.friendbattle.games.MiniGame;
import de.passsy.friendbattle.games.MiniGame.Correctness;
import de.passsy.friendbattle.utility.GoodTimer;
import de.passsy.friendbattle.utility.GoodTimer.OnTimerListener;

public class FirstGets extends PointProvider {

    private Boolean mSolved = false;

    @Override
    public Correctness evalCorrectness(final Boolean correctness,
	    final Player player, final MiniGame miniGame) {
	Correctness result = Correctness.incorrect;
	if (correctness) {
	    // Game is correct
	    if (!mSolved) {
		// First who guessed correct
		// Points++
		result = Correctness.correct;
	    } else {
		// Player was to late, someone else was faster
		// Points don't change
		result = Correctness.toolate;
	    }
	    // now no one can get points
	    mSolved = true;
	    // start next game in 1 second

	    final GoodTimer timer = new GoodTimer(1000, false);
	    timer.setOnTimerListener(new OnTimerListener() {

		@Override
		public void onTimer() {
		    miniGame.getOnNextGameListener().onNextGame(miniGame);
		}
	    });
	    timer.start();

	} else {
	    // Game isn't correct
	    if (!mSolved) {
		// Player gets Points--
		result = Correctness.incorrect;
	    }
	}
	return result;

    }

}

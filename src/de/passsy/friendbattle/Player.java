package de.passsy.friendbattle;

public class Player {
    private int mId;
    private int mPoints = 0;
    private Buzzer mBuzzer;
    
    /**
     * 
     * @param id ID for this player
     */
    public Player(int id) {
	this.mId = id;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public int getPoints() {
        return mPoints;
    }

    public void setPoints(int points) {
        this.mPoints = points;
        mBuzzer.setText(String.valueOf(points));
    }

    public Buzzer getBuzzer() {
        return mBuzzer;
    }

    public void setBuzzer(Buzzer buzzer) {
        this.mBuzzer = buzzer;
    }
    
    
}

/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package model;

import java.awt.Color;
import java.util.Random;

/**
 * Stores a participant's information.
 * @author stelrq
 * @version Winter 2019
 */
public class Participant {
    /** The max value for each of the values of the random color selector. */
    private static final int RGB = 255;
    /** The participant's racer id. */
    private final int myRacerID;
    /** The participant's name. */
    private final String myName;
    /** The distance the racer has traveled in the current lap. */
    private double myDistance;
    /** Stores if the racer is currently enabled. */
    private boolean myEnabled;
    /** Random object to generate random racer colors. */
    private Random myRandom = new Random();
    /** Stores the racer's color for display by the view. */
    private final Color myColor;
    /**
     * @param theRacerID id of the racer 
     * @param theName name of the racer
     * @param theDistance starting distance of the racer
     */
    public Participant(final int theRacerID, final String theName, final double theDistance) {
        myRacerID = theRacerID;
        myName = theName;
        myDistance = theDistance;
        myEnabled = true;
        myColor = randomColor();
    }

    /**
     * Create a random color every time the method is called.
     * @return a new random color
     */
    private Color randomColor() {
        final int r = myRandom.nextInt(RGB);
        final int g = myRandom.nextInt(RGB);
        final int b = myRandom.nextInt(RGB);
        return new Color(r, g, b);
    }
    /**
     * Gets the participant's id.
     * @return the myRacerID the participant's racer id
     */
    public int getRacerID() {
        return myRacerID;
    }
    
    /**
     * Gets the name of the participant.
     * @return the myName the participant's name
     */
    public String getName() {
        return myName;
    }
    /**
     * Get's the racer's distance on the current lap.
     * @return the myDistance the participant's distance on the current lap
     */
    public double getDistance() {
        return myDistance;
    }
    /**
     * Sets the racer's participation state.
     * @param theEnabled sets the racer's participation state
     */
    public void setEnabled(final boolean theEnabled) {
        myEnabled = theEnabled;
    }
    /**
     * Retrieves the racer's participation state.
     * @return the racer's current participation state
     */
    public boolean getEnabled() {
        return myEnabled;
    }
    
    @Override
    public String toString() {
        return myRacerID + ": " + myName;
    }

    /**
     * @return the myColor
     */
    public Color getColor() {
        return myColor;
    }

}

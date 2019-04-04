/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package model;

/**
 * Stores the information for a telemetry message which is responsible for updating
 * the racer's position.
 * @author sterling
 * @version Winter 2019
 */
public class TelemetryMessage extends AbstractMessage implements Message {
    /** The id for the racer this message refers to. */
    private final int myRacerID;
    /** The current distance in the lap for this racer. */
    private final double myDistance;
    /** The lap the racer is on. */
    private final int myLap;
    /**
     * Constructs a new telemetry message.
     * @param theTimeStamp the time of this message
     * @param theRacerID the racer the message refers to
     * @param theDistance the current distance on the current lap
     * @param theLap the lap the racer is on
     */
    public TelemetryMessage(final int theTimeStamp, final int theRacerID,
                            final double theDistance, final int theLap) {
        super(theTimeStamp);
        myRacerID = theRacerID;
        myDistance = theDistance;
        myLap = theLap;
    }
    /**
     * Gets the id of the racer that this message refers to.
     * @return the racer's id
     */
    public int getRacerID() {
        return myRacerID;
    }
    /** 
     * Returns a string in the format desired by the status bar.
     * @return the racer's id and current distance formatted to be displayed by the status bar
     */
    public String statusBarDisplay() {
        return " ID:" + myRacerID + " Current Distance:" + myDistance;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(STRING_BUILDER_SIZE);
        sb.append("Telemetry Message at Time:");
        sb.append(getTimeStamp());
        sb.append(":Racer ID:");
        sb.append(myRacerID);
        sb.append(":Distance:");
        sb.append(myDistance);
        sb.append(":Lap:");
        sb.append(myLap);
        
        return sb.toString();
    }
    /**
     * Returns the distance of this message.
     * @return the myDistance
     */
    public double getDistance() {
        return myDistance;
    }
    /**
     * Returns the lap that this message refers to.
     * @return the myLap
     */
    public int getLap() {
        return myLap;
    }
}
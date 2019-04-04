/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package model;
/**
 * A message that contains the information for a racer crossing the finishline.
 * @author stelrq
 * @version Winter 2019
 */
public class FinishMessage extends AbstractMessage implements Message {
    /** The Racer id the crossed the finish. */
    private final int myRacerID;
    /** The lap the racer just finished. */
    private final int myLap;
    /** If the race is finished. */
    private final boolean myFinish;
    /**
     * Constructs a new FinishMessage.
     * @param theTimeStamp the current time in the simulation
     * @param theRacerID the racer's id
     * @param theLap the lap just finished
     * @param theFinish if the race is over
     */
    public FinishMessage(final int theTimeStamp, final int theRacerID, 
                         final int theLap, final boolean theFinish) {
        super(theTimeStamp);
        myRacerID = theRacerID;
        myLap = theLap;
        myFinish = theFinish;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(STRING_BUILDER_SIZE);
        sb.append("Finish Crossing Message at Time:");
        sb.append(getTimeStamp());
        sb.append(":Racer ID:");
        sb.append(myRacerID);
        sb.append(":Lap:");
        sb.append(myLap);
        sb.append(":Race Finished:");
        sb.append(myFinish);
        
        return sb.toString();
    }

}

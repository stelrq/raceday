/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package model;
/**
 * An object containing the race standings at a certain time.
 * @author stelrq
 * @version Winter 2019
 *
 */
public class LeaderboardMessage extends AbstractMessage implements Message {
    /** The list of racers in placing order. */
    private int [] myRacerIDs;
    /**
     * Builds a new LeaderboardMessage.
     * @param theTimeStamp the time that this message is sent.
     * @param theRacerIDs the racers to be listed in order
     */
    public LeaderboardMessage(final int theTimeStamp, final int [] theRacerIDs) {
        super(theTimeStamp);
        myRacerIDs = theRacerIDs;
    }
    /**
     * Gets the racer ids.
     * @return an array of the racer ids in this LeaderBoard.
     */
    public int []  getRacerIDs() {
        return myRacerIDs.clone();
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(STRING_BUILDER_SIZE);
        sb.append("Leader Board Message:");
        sb.append(getTimeStamp());
        for (int i : myRacerIDs) {
            sb.append(":Racer ID:");
            sb.append(i);
        }
        return sb.toString();
    }

}

/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package model;
/**
 * Defines the shared attributes of a message.
 * @author sterling
 * @version Winter 2019
 */
public abstract class AbstractMessage implements Message {
    /** Defines a StringBuilder size that will be large enough for all expected messages. */
    protected static final int STRING_BUILDER_SIZE = 128;
    /** The time in the simulation that this message is about. */
    private final int myTimeStamp;
    /**
     * Abstract constructor that assigns the timestamp.
     * @param theTimeStamp the time in the simulation to which this message refers
     */
    public AbstractMessage(final int theTimeStamp) {
        myTimeStamp = theTimeStamp;
    }
    /**
     * Returns the time of the message.
     * @return the time of this message
     */
    public int getTimeStamp() {
        return myTimeStamp;
    }

}

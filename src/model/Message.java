/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package model;
/**
 * An interface to group together the three types of messages.
 * @author stelrq
 * @version Winter 2019
 */
public interface Message {
    /** 
     * Method to get the time associated with the message. 
     * @return integer representing the time of this message
     */
    int getTimeStamp();
    
}

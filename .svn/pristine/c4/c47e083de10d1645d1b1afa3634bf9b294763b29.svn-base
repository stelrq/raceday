/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package model;

import controller.TimePanel;

/**
 * Stores the information from a race file header.
 * @author stelrq
 * @version Winter 2019
 */
public class FileHeader {
    /** The number of lines in the header. */
    public static final int HEADER_LENGTH = 7;
    /** The size of the StringBuilder. */
    public static final int SB_SIZE = 128;
    /** New line character to use when displaying the header. */
    private static final String NEW_LINE = "\n";
    /** The name of the race. */
    private String myName;
    /** The shape of the track. */
    private String myGeometry;
    /** The width of the track. */
    private int myWidth;
    /** The height of the track. */
    private int myHeight;
    /** The distance of a race lap. */
    private int myDistance;
    /** The total race time in milliseconds. */
    private int myTime;
    /** Number of racers in this race. */
    private int myRacerCount;
    /**
     * Constructs a header based on the passed in array. 
     * Assumes that the array is in the order specified by the DDS
     * @param theHeader an array that contains the header from the race file
     */
    public FileHeader(final String [] theHeader) {
        constructHeader(theHeader);
        
    }
    /**
     * Builds a header from an array of strings representing a header.
     * @param theHeader the lines representing a header following the DDS.
     */
    private void constructHeader(final String[] theHeader) {
        int i = 0;
        myName = removeTitle(theHeader[i]);
        i++;
        myGeometry = removeTitle(theHeader[i]);
        i++;
        myWidth = removeAndParse(theHeader[i]);
        i++;
        myHeight = removeAndParse(theHeader[i]);
        i++;
        myDistance = removeAndParse(theHeader[i]);
        i++;
        myTime = removeAndParse(theHeader[i]);
        i++;
        myRacerCount = removeAndParse(theHeader[i]);
    }
    /**
     * Removes the title from a line.
     * @param theLine the line to be shortened
     * @return A line without the first element
     */
    private String removeTitle(final String theLine) { 
        return theLine.substring(theLine.indexOf(RaceModel.DELIMITER_C) + 1);
    }
    /**
     * Removes the title from a line and returns the integer information contained.
     * @param theLine a line with a title and integer separated by the delimiter.
     * @return the integer information from this line
     */
    private int removeAndParse(final String theLine) {
        return Integer.parseInt(removeTitle(theLine));
    }

    /**
     * @return the myName
     */
    public String getRaceName() {
        return myName;
    }
    /**
     * @return the myGeometry
     */
    public String getRaceGeometry() {
        return myGeometry;
    }
    /**
     * @return the myWidth
     */
    public int getTrackWidth() {
        return myWidth;
    }
    /**
     * @return the myHeight
     */
    public int getTrackHeight() {
        return myHeight;
    }
    /**
     * @return the myDistance
     */
    public int getTrackDistance() {
        return myDistance;
    }
    /**
     * @return the myTime
     */
    public int getRaceTime() {
        return myTime;
    }
    /**
     * @return the myRacerCount
     */
    public int getRacerCount() {
        return myRacerCount;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(SB_SIZE);
        sb.append(myName);
        sb.append(NEW_LINE);
        sb.append("Track Type: ");
        sb.append(myGeometry);
        sb.append(NEW_LINE);
        sb.append("Total Time: ");
        sb.append(TimePanel.formatTime(myTime));
        sb.append(NEW_LINE);
        sb.append("Lap Distance: ");
        sb.append(myDistance);
        return sb.toString();
    }
}

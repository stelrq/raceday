/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Model that informs the view elements for this simulation.
 * @author stelrq
 * @version Winter 2019
 */
public class RaceModel implements ChangeListener, PropertyChangeEnabledRaceControls {
    /** Patterns for the header of the file. */
    public static final String [] HEADER_PATTERN =
        {"#RACE:.+", "#TRACK:.+", "#WIDTH:\\d+", 
            "#HEIGHT:\\d+", "#DISTANCE:\\d+", "#TIME:\\d+", "#PARTICIPANTS:\\d+"};
    /** Pattern for a race entry. */
    public static final String ENTRY_PATTERN = 
        "#\\d+:[A-Z][a-z]*:[-]?[0-9]+\\.[0-9]";
    /** Pattern for a telemetry line. */
    public static final String TELEMETRY_PATTERN =
        "\\$T:\\d+:[0-9]?[0-9]:[-]?[0-9]+\\.[0-9][0-9]:[0-9]+";
    //based on this https://www.regular-expressions.info/floatingpoint.html
    /** Pattern for a leaderboard line. */
    public static final String LEADERBOARD_PATTERN = "\\$L:\\d+(:[0-9]?[0-9])*";
    /** Pattern for a finish line crossing line. */
    public static final String FINISH_PATTERN = "\\$C:\\d+:\\d+:\\d+:(true|false)";
    /** Character that separates information in file lines. */
    public static final int DELIMITER_C = 58;
    /** The delimiter in String form for use as a regex. */
    public static final String DELIMITER_R = ":";
    /** The default starting time. */
    public static final int DEFAULT_START_TIME = 0;
    /** An error message for illegal arguments. */
    private static final String ERROR_MESSAGE = "Time may not be less than 0.";
    /** Contains information describing a race. */
    private FileHeader myFileHeader;
    /** Data describing the race(s) loaded in from a file. */
    private List<List<Message>> myData;
    /** Stores this objects time. */
    private int myTime;
    /** Manager for Property Change Listeners. */
    private final PropertyChangeSupport myPcs;
    /** List of participants in the race. */
    private Map<Integer, Participant> myRacers;
    /**
     * Constructs a new race model.
     */
    public RaceModel() {
        myTime = DEFAULT_START_TIME;
        myPcs = new PropertyChangeSupport(this);
    }
    
    /**
     * Advances the model by one millisecond.
     * @see model.RaceControls#advance()
     */
    @Override
    public void advance() {
        advance(1);
    }

    /**
     * Advances the race by the given amount of milliseconds.
     * @param theMillisecond amount of time to advance the race by
     */
    @Override
    public void advance(final int theMillisecond) {
        changeTime(myTime + theMillisecond);

    }
    /**
     * Moves the race to the given time.
     * @param theMillisecond time stamp to advance the race to
     * @throws IllegalArgumentException if the given time is less than 0
     */
    @Override
    public void moveTo(final int theMillisecond) {
        if (theMillisecond < 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        changeTime(theMillisecond);
    }
    
    /**
     * Helper method to change the value of time and notify observers. 
     * Functional decomposition. 
     * @param theMillisecond the time to change to
     */
    private void changeTime(final int theMillisecond) {
        int direction = 1;
        if (theMillisecond < myTime) {
            direction = -1;
        }
        final int old = myTime;
        myTime = theMillisecond;
        if (myTime > myFileHeader.getRaceTime() - 1) {
            myPcs.firePropertyChange(PROPERTY_RACE_FINISHED, null, null);
            return;
        }
        for (int i = old * direction + 1; i <= myTime * direction; i++) {
            final int index = i * direction;
            if (index < myData.size()) {
                for (Message m : myData.get(index)) {
                    if (m instanceof TelemetryMessage) {
                        if (myRacers.get(((TelemetryMessage) m).getRacerID()).getEnabled()) {
                            myPcs.firePropertyChange(PROPERTY_T_MESSAGE, null, m);
                        }
                    } else if (m instanceof LeaderboardMessage) {
                        myPcs.firePropertyChange(PROPERTY_L_MESSAGE, null, m);
                    } else {
                        myPcs.firePropertyChange(PROPERTY_F_MESSAGE, null, m);
                    }
                }
                myPcs.firePropertyChange(PROPERTY_TIME, old, myTime);
            }
        }
    }


    /**
     * Enables or disables the racer with the given id.
     * @param theParticipantID the racer to be enabled/disabled
     * @param theToggle whether to enable or disable the participant
     */
    @Override
    public void toggleParticipant(final int theParticipantID, final boolean theToggle) {
        myRacers.get(theParticipantID).setEnabled(theToggle);
        if (theToggle) {
            myPcs.firePropertyChange(PROPERTY_RACER_TOGGLED, !theToggle, theParticipantID);
        } else {
            myPcs.firePropertyChange(PROPERTY_RACER_TOGGLED, !theToggle, theParticipantID);
        }
    }

    /**
     * Loads a race file into the model. The file must follow the DDS.
     * @param theRaceFile file sent from the controller
     * @throws IOException if the file is not in the correct format
     */
    @Override
    public void loadRace(final File theRaceFile) throws IOException {
        final FileReader f = new FileReader(Objects.requireNonNull(theRaceFile));
        final BufferedReader r = new BufferedReader(f);
        myFileHeader = buildFileHeader(r);
        myData = new ArrayList<List<Message>>(myFileHeader.getRaceTime() + 1);
        for (int j = 0; j <= myFileHeader.getRaceTime(); j++) {
            myData.add(j, new ArrayList<Message>());
        }
        myRacers = (HashMap<Integer, Participant>) buildRacerList(r);
        final List<String> temp = new ArrayList<String>();
        String line = r.readLine();
        while (line != null) {
            temp.add(line);
            line = r.readLine();
        }
        r.close();
        buildData(temp);
        myPcs.firePropertyChange(PROPERTY_HEADER_LOADED, 0, myFileHeader);
        myPcs.firePropertyChange(PROPERTY_PARTICIPANTS_LOADED, null, myRacers);
        myPcs.firePropertyChange(PROPERTY_RACE_LOADED, null, null);
    }
    /**
     * Processes a string that must be in the format
     * of one of the three message types. Builds a new message depending
     * on the format of the String. If the String does not match a message format
     * nothing happens.
     * @param theLine a String that must match the format for a message line
     * @return a new message
     */
    private Message buildMessage(final String theLine) {
        if (theLine.matches(TELEMETRY_PATTERN)) {
            return parseTelemetry(removeTitle(theLine));
        } else if (theLine.matches(LEADERBOARD_PATTERN)) {
            return parseLeaderBoard(removeTitle(theLine));
        } else {
            return parseFinish(removeTitle(theLine));
        }
    }
    /** Builds a FileHeader from the first line of the file.
     *  The format for these lines is defined in the HEADER_PATTERN constant.
     * @param theReader a Reader reading from a race file
     * @return a new header
     * @throws IOException if the information from the reader is not in the right order
     */
    private FileHeader buildFileHeader(final BufferedReader theReader) throws IOException {
        String line;
        final String [] headerContents = new String [FileHeader.HEADER_LENGTH];
        for (int i = 0; i < HEADER_PATTERN.length; i++) {
            line = theReader.readLine();
            if (line == null || !line.matches(HEADER_PATTERN[i])) {
                theReader.close();
                throw new IOException();
            }
            headerContents [i] = line;
        }
        return new FileHeader(headerContents);
    }
    /**
     * Builds an array of the current racer IDs.
     * @param theReader Reader that refers to the file at the start of the entries
     * @return array of the racers
     * @throws IOException if any of the lines within 
     * the count are not in the format for a race entry
     */
    private Map<Integer, Participant> buildRacerList(final BufferedReader theReader)
                    throws IOException {
        final Map<Integer, Participant> raceEntries = new HashMap<Integer, Participant>();
        String line;
        for (int i = 0; i < myFileHeader.getRacerCount(); i++) {
            line = theReader.readLine();
            if (line == null || !line.matches(ENTRY_PATTERN)) {
                theReader.close();
                throw new IOException();
            }
            final Participant p = parseParticipant(line.substring(1).split(DELIMITER_R));
            raceEntries.put((Integer) p.getRacerID(), p);
        }
        return raceEntries;
    }
    /**
     * Method accepts a list of Strings and fills the model's
     * data structure describing a race. All strings must be in the format
     * of one of the three message types.
     * @param theData List of strings that must follow the format of a message
     * @throws IOException if a string doesn't match the format of a message
     */
    private void buildData(final List<String> theData) throws IOException {
        final Predicate<String> message = s -> 
            s.matches(TELEMETRY_PATTERN) || s.matches(LEADERBOARD_PATTERN)
            || s.matches(FINISH_PATTERN);
        Stream<String> lines = theData.stream();
        if (!lines.allMatch(message)) {
            throw new IOException();
        }
        lines = theData.stream();
        final Function<String, Message> messageBuilder = s -> buildMessage(s);
        final Consumer<Message> fillList = m -> myData.get(m.getTimeStamp()).add(m);
        lines.map(messageBuilder).forEach(fillList);    
    }
    
   /**
    * Extracts the relevant information from a TelemetryMessage line.
    * @param theLine string to be processed, must in the format of a telemetry message
    * @return a new telemetry message.
    */
    private String [] removeTitle(final String theLine) { 
        return theLine.substring
            (theLine.indexOf(RaceModel.DELIMITER_C) + 1).split(DELIMITER_R);
    }
   /**
    * Takes an array of Strings representing information from
    * a telemetry message. The information must be split into 
    * numbers and be ordered by time stamp, race id, distance,
    * and lap.
    * @param theData an array of numbers in the specified order
    * @return a new TelemetryMessage
    */
    private Message parseTelemetry(final String [] theData) {
        int i = 0;
        final int timeStamp = Integer.parseInt(theData [i]);
        i++;
        final int racerID = Integer.parseInt(theData [i]);
        i++;
        final double distance = Double.parseDouble(theData [i]);
        i++;
        final int lap = Integer.parseInt(theData [i]);
        return new TelemetryMessage(timeStamp, racerID, distance, lap);
       
    }
   /**
    * Processes an array of String representing a 
    * leader board message and creates a new message object. 
    * The strings in the array must contain only ints.
    * The first int will be a timestamp followed by 
    * racer IDs.
    * @param theData strings containing a leader board message
    * @return a new LeaderBoardMessage
    */
    private Message parseLeaderBoard(final String [] theData) {
        final int timeStamp = Integer.parseInt(theData [0]);
        final int [] racers = new int [theData.length - 1];
        for (int i = 0; i < racers.length; i++) {
            racers [i] = Integer.parseInt(theData [i + 1]);
        }
        return new LeaderboardMessage(timeStamp, racers);
    }
   /**
    * Processes the data for a finish line crossing and creates a new
    * message with that information.
    * @param theData an array of finish line data
    * @return a new message describing a finish line crossing event
    */
    private Message parseFinish(final String [] theData) {
        int i = 0;
        final int timeStamp = Integer.parseInt(theData [i]);
        i++;
        final int racerID = Integer.parseInt(theData [i]);
        i++;
        final int lap = Integer.parseInt(theData [i]);
        i++;
        final boolean finished = Boolean.parseBoolean(theData [i]);
        return new FinishMessage(timeStamp, racerID, lap, finished);
    }
    /**
     * Creates a participant from an Array of strings.
     * The Array must be in the order racer id, name, starting distance.
     * @param theData an array containing participant information
     * @return a new participant
     */
    private Participant parseParticipant(final String [] theData) {
        int i = 0;
        final int racerID = Integer.parseInt(theData [i]);
        i++;
        final String name = theData [i];
        i++;
        final double distance = Double.parseDouble(theData [i]);
        return new Participant(racerID, name, distance);
    }

    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);

    }


    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);
    }


    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);

    }


    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(thePropertyName, theListener);

    } 
    /**
     * Advances if the slider is moved by the user
     * by the amount the slider is moved.
     * @param theEvent slider being moved by the user
     */
    @Override
    public void stateChanged(final ChangeEvent theEvent) {
        if (theEvent.getSource() instanceof JSlider) {
            moveTo(((JSlider) theEvent.getSource()).getValue());
        }
    }
}

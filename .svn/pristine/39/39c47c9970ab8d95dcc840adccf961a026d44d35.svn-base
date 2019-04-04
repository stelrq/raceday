/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package controller;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_TIME;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Provided code for a timer
 * with modifications to allow custom sizing. 
 * 
 * @author Charles Bryan
 * @author Sterling Quinn
 * @version Autumn 2015
 */
public class TimePanel extends JPanel implements PropertyChangeListener {
    
    /** The separator for formatted. */
    public static final String SEPARATOR = ":";
    
    /** The number of milliseconds in a second. */
    public static final int MILLIS_PER_SEC = 1000;
    
    /** The number of seconds in a minute. */
    public static final int SEC_PER_MIN = 60;
    
    /** The number of minute in a hour. */
    public static final int MIN_PER_HOUR = 60;
        
    /** A formatter to require at least 1 digit, leading 0. */
    public static final DecimalFormat ONE_DIGIT_FORMAT = new DecimalFormat("0");
    
    /** A formatter to require at least 2 digits, leading 0s. */
    public static final DecimalFormat TWO_DIGIT_FORMAT = new DecimalFormat("00");
    
    /** A formatter to require at least 3 digits, leading 0s. */
    public static final DecimalFormat THREE_DIGIT_FORMAT = new DecimalFormat("000");
    
    /**  
     * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     */
    private static final long serialVersionUID = 8385732728740430466L;
    /** Padding for the border around the label. */
    private static final int PADDING = 5;
    
//    /** Padding for the border around the label. */
//    private static final Dimension MARGIN = new Dimension(20, 20);
    
    /** A label to display time. */
    private final JLabel myLabel;
    
    /** The spacing around the panel. */
    private Dimension myMargin;
    
    /**
     * Construct a Time Panel. 
     * @param theMargin margin that sets the spacing 
     * surrounding the label that displays the time.
     */
    public TimePanel(final Dimension theMargin) {
        super();
        myLabel = new JLabel(formatTime(0));
        setupComponents();
        myMargin = theMargin;
    }
    
    /**
     * Setup and layout components. 
     */
    private void setupComponents() {
//        setLayout(new BorderLayout());
        JPanel margin = new JPanel();
        margin.setPreferredSize(myMargin);
        add(margin, BorderLayout.NORTH);
        margin = new JPanel();
        margin.setPreferredSize(myMargin);
        add(margin, BorderLayout.SOUTH);
        
        final JPanel content = new JPanel();
        myLabel.setBorder(BorderFactory.createCompoundBorder(
                                 BorderFactory.createEtchedBorder(), 
                                 BorderFactory.createEmptyBorder(PADDING, 
                                                                 PADDING, 
                                                                 PADDING, 
                                                                 PADDING)));
        content.add(myLabel);
        add(content, BorderLayout.CENTER);
    }
    
    /**
     * This formats a positive integer into minutes, seconds, and milliseconds. 
     * 00:00:000
     * @param theTime the time to be formatted
     * @return the formated string. 
     */
    public static String formatTime(final long theTime) {
        long time = theTime;
        final long milliseconds = time % MILLIS_PER_SEC;
        time /= MILLIS_PER_SEC;
        final long seconds = time % SEC_PER_MIN;
        time /= SEC_PER_MIN;
        final long min = time % MIN_PER_HOUR;
        time /= MIN_PER_HOUR;
        return TWO_DIGIT_FORMAT.format(min) + SEPARATOR
                        + TWO_DIGIT_FORMAT.format(seconds) 
                        + SEPARATOR + THREE_DIGIT_FORMAT.format(milliseconds);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_TIME.equals(theEvent.getPropertyName())) {
            myLabel.setText(formatTime((Integer) theEvent.getNewValue()));
        }
    }
}

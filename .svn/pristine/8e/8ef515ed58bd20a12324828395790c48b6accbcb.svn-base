/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package view;

import static model.PropertyChangeEnabledRaceControls.PROPERTY_RACER_SELECTED;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_TIME;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_T_MESSAGE;

import controller.TimePanel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import model.RaceModel;
import model.TelemetryMessage;



/**
 * A status bar to display time and the information for the selected racer.
 * @author sterling
 * @version Winter 2019
 */
public class StatusBar extends JPanel implements PropertyChangeListener {
    /**  
     * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     */
    private static final long serialVersionUID = -3362708020012797540L;
    /** Displays the race time. */
    private JLabel myTimeDisplay;
    /** Label to display information about a racer. */
    private JLabel myRacerDisplay;
    /** The racer selected by the user. -1 if no racer is selected. */
    private int myRacerSelected;
    /** Name of the selected racer. */
    private String myNameSelected;
    /**
     * Builds a JPanel to display time and racer information.
     * @param theWidth the width of the window that this status bar will be placed in.
     */
    public StatusBar(final int theWidth) {
        super();
        setBorder(new EtchedBorder(EtchedBorder.RAISED));
        myTimeDisplay = new JLabel(TimePanel.formatTime(0));
        myRacerDisplay = new JLabel("Select a participant.");
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(myRacerDisplay);
        add(Box.createHorizontalGlue());
        add(myTimeDisplay);
        myRacerSelected = -1;
    }
    /**
     * Sets the racer whose information will be displayed in the status bar.
     * @param theRacerID the racer's id
     * @param theName the racer's name
     */
    protected void selectRacer(final int theRacerID, final String theName) {
        myRacerSelected = theRacerID;
        myNameSelected = theName;
    }
    
    /**
     * Receives messages from the model.
     * Receives an update from the trackpanel or leaderboard if the
     * user selects a racer and displays the racer's data if a racer is selected.
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (theEvent.getPropertyName().equals(PROPERTY_TIME)) {
            myTimeDisplay.setText(TimePanel.formatTime((int) theEvent.getNewValue()));
        } else if (theEvent.getPropertyName().equals(PROPERTY_T_MESSAGE)) {
            final TelemetryMessage tm = (TelemetryMessage) theEvent.getNewValue();
            if (tm.getRacerID() == myRacerSelected) {
                myRacerDisplay.setText(myNameSelected + tm.statusBarDisplay());
            }
        } else if (theEvent.getPropertyName().equals(PROPERTY_RACER_SELECTED)) {
            myRacerSelected = Integer.parseInt(((String)
                            theEvent.getNewValue()).split(RaceModel.DELIMITER_R)[0]);
            myNameSelected = ((String) theEvent.getNewValue()).split(RaceModel.DELIMITER_R)[1];
        }
    }

}

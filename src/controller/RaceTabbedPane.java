/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package controller;
import static controller.RaceController.PADDING;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_PARTICIPANTS_LOADED;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_F_MESSAGE;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_L_MESSAGE;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_T_MESSAGE;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import model.Message;
import model.Participant;
import model.PropertyChangeEnabledRaceControls;
import view.RaceView;


/**
 * Class that groups the data output area and the racer checkbox into a tabbed pane.
 * @author sterling
 * @version Winter 2019
 */
public class RaceTabbedPane extends JTabbedPane implements PropertyChangeListener {
    /** Number of rows of text in the display area. */
    public static final int TEXT_ROWS = 10;
    /** Number of columns of text in the display area. */
    public static final int TEXT_COLS = 50;
    /** For checkstyle. */
    private static final long serialVersionUID = -6020757167054120535L;
    /** Data output text area. */
    private JTextArea myDataArea;
    /** Racer list text area. */
    private JPanel myRacerArea;
    /** Scroll pane for when data exceeds display area. */
    private JScrollPane myDataScrollPane;
    /** Scroll pane for if the racer list is bigger than the display area. */
    private JScrollPane myRacerScrollPane;
    /** Reference to the model to link the checkboxes. */
    private PropertyChangeEnabledRaceControls myModel;
    
    /** 
     * Builds tabbedpane for text output. Displays race data in the default window.
     * Displays the race participants in the second window.
     * @param theModel the model that will listen to this pane.
     * 
     */
    public RaceTabbedPane(final PropertyChangeEnabledRaceControls theModel) {
        
        initComponents(theModel);
    }
    
    /**
     * Initializes the components in the pane.
     * @param theModel model that will be informed by the checkbox pane
     */
    private void initComponents(final PropertyChangeEnabledRaceControls theModel) {
        myDataArea = new JTextArea(TEXT_ROWS, TEXT_COLS);
        myDataArea.setEditable(false);
        myDataScrollPane = new JScrollPane(myDataArea);
        myRacerArea = new JPanel();
        myRacerArea.setPreferredSize(new Dimension(TEXT_ROWS, TEXT_COLS));
        myRacerScrollPane = new JScrollPane(myRacerArea);
        myRacerScrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        addTab("Data Output Stream", myDataScrollPane);
        add("Race Participants", myRacerScrollPane);
        setEnabledAt(1, false);
        setBorder(BorderFactory.createEmptyBorder
                             (PADDING, PADDING, PADDING, PADDING));
        myModel = theModel;
    }
    /**
     * Accepts property changes from the model. After receiving
     * a PROPERTY_MESSAGE event, the data area prints a message received from the model.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (theEvent.getPropertyName().equals(PROPERTY_T_MESSAGE)
            || theEvent.getPropertyName().equals(PROPERTY_L_MESSAGE)
                || theEvent.getPropertyName().equals(PROPERTY_F_MESSAGE)) {
            myDataArea.append(((Message) theEvent.getNewValue()).toString() + "\n");
        } else if (theEvent.getPropertyName().equals(PROPERTY_PARTICIPANTS_LOADED)) {
            setEnabledAt(1, true);
            populateRacerList(RaceView.verifyHashMap(theEvent.getNewValue(),
                                                     Participant.class), myModel);
        }

    }
    /**
    * Clears the data output area.
    */
    public void clearData() {
        myDataArea.setText(null);
    }
    /**
     * Displays the racers in the racer area with check boxes.
     * All check boxes are initially selected.
     * @param theRacers an array of race Participants
     * @param theModel model that will be informed if racers are selected or deselected
     */
    private void populateRacerList(final Map<Integer, Participant> theRacers,
        final PropertyChangeEnabledRaceControls theModel) {
        for (final Integer key : theRacers.keySet()) { 
            final JCheckBox b = new JCheckBox(theRacers.get(key).getName());
            b.setSelected(true);
            b.addItemListener(new ItemListener() {
                public void itemStateChanged(final ItemEvent theEvent) {
                    if (theEvent.getStateChange() == 1) {
                        theModel.toggleParticipant(key, true);
                    } else {
                        theModel.toggleParticipant(key, false);
                    }
                }
            });
            myRacerArea.add(b);
        }
    }


}

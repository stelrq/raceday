/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package view;

import static model.PropertyChangeEnabledRaceControls.PROPERTY_L_MESSAGE;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_PARTICIPANTS_LOADED;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_RACER_SELECTED;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.LeaderboardMessage;
import model.Participant;


/**
 * Displays the current racers in order from top to bottom.
 * @author sterling
 * @version Winter 2019
 */
public class Leaderboard extends JPanel implements PropertyChangeListener {

    /**  
     * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     */
    private static final long serialVersionUID = 5184740209901199236L;
    /** Horizontal size of the buttons. */
    private static final Dimension BUTTON_DIMENSION = new Dimension(150, 30);
    /** Diameter of the colored racer icon. */
    private static final double ICON_SIZE = 20;
    /** The maximum number of racers. */
    private static final int MAX_RACERS = 10;
    /** Spacing between buttons. */
    private static final int SPACING = 5;
    /** Modifies racer icon to fit in button. */
    private static final int MOD = 4;
    /** JButton to make the leaderboard show up before a file loads. */
    private JButton myFiller;
    /** Stores the buttons that represent racers in the leaderboard. */
    private Map<Integer, JButton> myButtons;
    /**
     * Constructs a new empty leaderboard.
     */
    public Leaderboard() {
        super();
        setLayout(new GridLayout(MAX_RACERS, 1, SPACING, SPACING));
        myFiller = new JButton();
        myFiller.setPreferredSize(BUTTON_DIMENSION);
        add(myFiller);
        myFiller.setVisible(false);
        myButtons = new HashMap<Integer, JButton>();
        setBorder(BorderFactory.createEmptyBorder(SPACING, SPACING, SPACING, SPACING));
    }
    
    /**
     * Informs listeners that the user selected a racer.
     * @param theEvent the user selecting a racer
     */
    private void selectRacer(final ActionEvent theEvent) {
        firePropertyChange(PROPERTY_RACER_SELECTED,
                           null, ((JButton) theEvent.getSource()).getText());
    }
    /**
     * Fills the leaderboard with buttons representing racers with their color and 
     * name. Clicking on a racer will inform the registered listeners to display the 
     * racer's information.
     * @param theRacers the racers to be listed
     */
    private void fillLeaderboard(final Map<Integer, Participant> theRacers) {
        remove(myFiller);
        for (Integer i : theRacers.keySet()) {
            final Participant p = theRacers.get(i);
            final JButton j = new JButton(p.toString());
            j.addActionListener(this::selectRacer);
            j.setHorizontalAlignment(SwingConstants.LEFT);
            final BufferedImage m =
                 new BufferedImage((int) BUTTON_DIMENSION.getHeight(),
                     (int) BUTTON_DIMENSION.getHeight(), BufferedImage.TYPE_INT_ARGB);
            final Graphics2D g2d = m.createGraphics();
            g2d.setColor(p.getColor());
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                 RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.fill(new Ellipse2D.Double(ICON_SIZE / MOD,
                ICON_SIZE / MOD, ICON_SIZE, ICON_SIZE));
            j.setIcon(new ImageIcon(m));
            j.setPreferredSize(BUTTON_DIMENSION);
            add(j);
            myButtons.put(p.getRacerID(), j);
        }   
        revalidate();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (theEvent.getPropertyName().equals(PROPERTY_PARTICIPANTS_LOADED)) {
            final Map<Integer, Participant> h =
                            RaceView.verifyHashMap(theEvent.getNewValue(), Participant.class);
            fillLeaderboard(h);
        } else if (theEvent.getPropertyName().equals(PROPERTY_L_MESSAGE)) {
            final int [] ids = ((LeaderboardMessage) theEvent.getNewValue()).getRacerIDs();
            for (int i : ids) {
                remove(myButtons.get(i));
                add(myButtons.get(i));
                
            }
            revalidate();
        }
    }


}

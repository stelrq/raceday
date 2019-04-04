/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package view;


import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import model.PropertyChangeEnabledRaceControls;
/**
 * Class that contains the different components of the view.
 * @author stelrq
 * @version Winter 2019
 */
public class RaceView extends JFrame {

    /**  
     * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     */
    private static final long serialVersionUID = -509937030149100106L;
    /** Title for the window. */
    private static String VIEW_TITLE = "Race Track";
    /** Race track for this panel. */
    private TrackPanel myPanel;
    /** Leaderboard to display participants. */
    private Leaderboard myLeaderboard;
    /** Displays Race time and participant info if a participant is selected. */
    private StatusBar myStatusBar;
   
    /** 
     * Constructs a new view that listens to the passed in race.
     * @param theOffset the horizontal position of the view 
     * so that it doesn't cover the controller
     * @param theVertical the vertical position of the view
     * @param theRace the source of data for this display
     *
     */
    public RaceView(final int theOffset, final int theVertical,
                    final PropertyChangeEnabledRaceControls theRace) {
        super(VIEW_TITLE);
        this.setLocation(theOffset, theVertical);
        initView();
        theRace.addPropertyChangeListener(myLeaderboard);
        theRace.addPropertyChangeListener(myPanel);
        theRace.addPropertyChangeListener(myStatusBar);
    }
    /**
     * Initializes a the view for a race, until a file is loaded it will be mostly blank.
     */
    private void initView() {
        myStatusBar = new StatusBar(getWidth());
        myPanel = new TrackPanel();
        myPanel.addPropertyChangeListener(myStatusBar);
        myLeaderboard = new Leaderboard();
        myLeaderboard.addPropertyChangeListener(myStatusBar);
        add(myLeaderboard, BorderLayout.EAST);
        add(myPanel, BorderLayout.CENTER);
        add(myStatusBar, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setResizable(false);
        pack();
    }
    /**
     * Verifies content of HashMap matches the given type.
     * @param theMap map to be verified.
     * @param theValue class of the values in the map
     * @param <V> the type of values in the map
     * @return the original HashMap with all the keys 
     * cast to Integer and the values to the passed in class
     */
    public static <V> Map<Integer, V> verifyHashMap(final Object theMap,
                                                    final Class<V> theValue) {
        final Map<Integer, V> h = new HashMap<Integer, V>();
        final Map<?, ?> temp = (Map<?, ?>) theMap;
        for (Object o : temp.keySet()) {
            h.put((Integer) o, theValue.cast(temp.get(o)));
        }
        return h;
    }  
    
}

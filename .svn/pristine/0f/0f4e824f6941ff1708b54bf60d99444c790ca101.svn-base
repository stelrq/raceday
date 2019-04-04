/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package view;

import static model.PropertyChangeEnabledRaceControls.PROPERTY_HEADER_LOADED;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_PARTICIPANTS_LOADED;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_T_MESSAGE;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_RACER_SELECTED;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_RACER_TOGGLED;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import model.FileHeader;
import model.Participant;
import model.TelemetryMessage;
import track.VisibleRaceTrack;


/**
 * A demo of PropertyChangeListener and track.jar. 
 * 
 * @author Charles Bryan
 * @version Autumn 2015
 */
public class TrackPanel extends JPanel implements PropertyChangeListener {
    
    /**  
     * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     */
    private static final long serialVersionUID = 8385732728740430466L;
    
    /** The size of the Race Track Panel. */
    private static final Dimension TRACK_SIZE = new Dimension(500, 400);
    
    /** The x and y location of the Track. */
    private static final int OFF_SET = 40;

    /** The stroke width in pixels. */
    private static final int STROKE_WIDTH = 25;

    /** The size of participants moving around the track. */
    private static final int OVAL_SIZE = 20;
    
    /** The visible track. */
    private VisibleRaceTrack myTrack;
    
    /** The circle moving around the track. */
    private Map<Integer, ParticipantView> myParticipants;
    /** The height of the track in relation to the width. */
    private int myHeightRatio;
    /** The width of the track in relation to the height. */
    private int myWidthRatio;
    /** The distance around the track. */
    private int myDistance;
    /** List of enabled racer's ids. */
    private List<Integer> myEnabledRacers;
    /** The physical x coordinate of the track. */
    private int myX;
    /** The physical y coordinate of the track. */
    private int myY;
    /** The actual track width. */
    private int myWidth;
    /** The actual track height. */
    private int myHeight;
    /**
     * Construct a Track Panel. 
     */
    public TrackPanel() {
        super();
        setPreferredSize(TRACK_SIZE);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                                                   "Race Track"));
        myParticipants = new HashMap<Integer, ParticipantView>(); 
        myEnabledRacers = new LinkedList<Integer>();
    }
    
    /**
     * Setup and layout components. 
     */
    private void setupComponents() {
        myWidth = (int) TRACK_SIZE.getWidth() - (OFF_SET * 2);
        myHeight =
                        ((int) TRACK_SIZE.getWidth()  - 2 * OFF_SET)
                        / myWidthRatio * myHeightRatio;
        myX = OFF_SET;
        myY = (int) TRACK_SIZE.getHeight()  / 2 - myHeight / 2;

        myTrack = new VisibleRaceTrack(myX, myY, myWidth, myHeight, myDistance);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent theEvent) {
                if (theEvent.getButton() == 1) {
                    final Point p = theEvent.getPoint();
                    for (Integer i : myParticipants.keySet()) {
                        final ParticipantView pv = myParticipants.get(i);
                        if (pv.getBounds2D().contains(p)) {
                            firePropertyChange(PROPERTY_RACER_SELECTED,
                                               null, i + ":" + pv.getName());
                        }
                    }
                }
                
            }
        });
        
    }
    /**
     * Creates the avatars for the racers.
     * @param theRacers the racers to be displayed
     */
    private void populateParticipants(final Map<Integer, Participant> theRacers) {
        for (Integer i : theRacers.keySet()) {
            final Participant p = theRacers.get(i);
            myEnabledRacers.add(i);
            final ParticipantView v = new ParticipantView(p.getColor(), p.getName());
            myParticipants.put(p.getRacerID(), v);
            final Point2D start = myTrack.getPointAtDistance(p.getDistance());
            v.setFrame(start.getX() - OVAL_SIZE / 2,
                       start.getY() - OVAL_SIZE / 2, OVAL_SIZE, OVAL_SIZE);
        }
    }
    
    /**
     * Paints the VisibleTrack with all enabled racers moving around it.
     * 
     * @param theGraphics The graphics context to use for painting.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.clearRect(myX, myY, myWidth, myHeight);
        if (myTrack != null) {
            g2d.setPaint(Color.BLACK);
            g2d.setStroke(new BasicStroke(STROKE_WIDTH));
            g2d.draw(myTrack);
        }
        for (Integer i: myParticipants.keySet()) {
            if (myEnabledRacers.contains(i)) {
                final ParticipantView v = myParticipants.get(i);
                g2d.setPaint(v.getColor());
                g2d.setStroke(new BasicStroke(1));
                g2d.fill(v);
            }
        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_T_MESSAGE.equals(theEvent.getPropertyName())) {
            final Point2D p = 
                myTrack.getPointAtDistance(((TelemetryMessage)
                    theEvent.getNewValue()).getDistance());
            myParticipants.get(((TelemetryMessage)
                            theEvent.getNewValue()).
                               getRacerID()).setFrame(p.getX() - OVAL_SIZE / 2, 
                        p.getY() - OVAL_SIZE / 2, 
                            OVAL_SIZE, OVAL_SIZE);
            repaint();
        } else if (PROPERTY_HEADER_LOADED.equals(theEvent.getPropertyName())) {
            myWidthRatio = ((FileHeader) theEvent.getNewValue()).getTrackWidth();
            myHeightRatio = ((FileHeader) theEvent.getNewValue()).getTrackHeight();
            myDistance = ((FileHeader) theEvent.getNewValue()).getTrackDistance();
        } else if (PROPERTY_PARTICIPANTS_LOADED.equals(theEvent.getPropertyName())) {
            setupComponents();
            populateParticipants((HashMap<Integer, Participant>) 
                                 RaceView.verifyHashMap(theEvent.getNewValue(),
                                                        Participant.class));
            repaint();
        } else if (PROPERTY_RACER_TOGGLED.equals(theEvent.getPropertyName())) {
            if ((boolean) theEvent.getOldValue()) {
                myEnabledRacers.remove(Integer.valueOf((int) theEvent.getNewValue()));
            } else {
                myEnabledRacers.add(Integer.valueOf((int) theEvent.getNewValue()));
            }
        }
    }
}


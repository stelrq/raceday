/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package view;

import java.awt.Color;
import java.awt.geom.Ellipse2D.Double;

/**
 * An ellipse that is paired to a color and name.
 * @author stelrq
 * @version 2019
 */
public class ParticipantView extends Double {

    /**  
     * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     */
    private static final long serialVersionUID = -5532620637239319971L;
    /**
     * Groups together the elements that display information for a particular participant. 
     * This object is an Ellipse2D that also
     * stores a name and color.
     */

    /** Stores the racer's color for display by the view. */
    private final Color myColor;
    /** Stores the name of the participant associated with this ParticipantView. */
    private String myName;
    /**
     * Constructs a new ParticipantView.
     * @param theColor the Color of the racer
     * @param theName the name of the racer
     */
    
    public ParticipantView(final Color theColor, final String theName) {
        super();
        myColor = theColor;
        myName = theName;
    }
    

    /**
     * Returns the color associated with this participant.
     * @return the color of this racer
     */
    public Color getColor() {
        return myColor;
    }
    /**
     * Returns the name of associated with this avatar.
     * @return the name of this racer
     */
    public String getName() {
        return myName;
    }

}

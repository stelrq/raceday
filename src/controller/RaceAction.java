/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
/**
 * An action that is shared between multiple components
 * with an associated icon and text. This class also gives the option
 * to define an action with only text and an action to group together
 * actions that should respond to a single property change event.
 * @author stelrq
 * @version Winter 2019
 */
public class RaceAction extends AbstractAction {
    /** To handle checkstyle. */
    private static final long serialVersionUID = 3357152817171318950L;
    /** The name of the default image to be displayed for this action. */
    private String myDefault;
    /** The text to be displayed for this action. */
    private String myText;
    /** The Action to be executed. */
    private Runnable myDefaultAction;
    /**
     * Constructor for race actions that never change functionality.
     * @param theDefault the image to be displayed on the button
     * @param theDefaultText the text to be displayed on the button
     * @param theDefaultAction the action to be run when the button is used
     */
    public RaceAction(final String theDefault,
        final String theDefaultText,
            final Runnable theDefaultAction) {
        super(theDefaultText);
        setIcon(theDefault);
        myDefault = theDefault;
        myText = theDefaultText;
        myDefaultAction = theDefaultAction;
        setEnabled(false);
    }
    /**
     * Constructor for race actions that only need action and text.
     * @param theDefaultText text to be displayed with this action
     * @param theDefaultAction 
     */
    public RaceAction(final String theDefaultText, final Runnable theDefaultAction) {
        super(theDefaultText);
        myText = theDefaultText;
        myDefaultAction = theDefaultAction;
        setEnabled(false);
    }
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myDefaultAction.run();

    }
    /**
     * Method to set the small and large icons for this action.    
     * @param theImage the name of the image associated with this action
     * which must be in the form that RaceController.buildImage(String, int)
     * expects.
     */
    protected final void setIcon(final String theImage) {
        putValue(LARGE_ICON_KEY, new ImageIcon(RaceController.buildImage
            (theImage, RaceController.CONTROL_BUTTON_SIZE)));
        putValue(SMALL_ICON, new ImageIcon(RaceController.buildImage
            (theImage, RaceController.MENU_ICON_SIZE)));
    }
    /**
     * Gets the name of the default image for this action.
     * @return the myDefault the name of the default image
     */
    public String getDefault() {
        return myDefault;
    }
    /**
     * Gets the default text for this action.
     * @return the myText the default text for this action
     */
    public String getText() {
        return myText;
    }
    

}

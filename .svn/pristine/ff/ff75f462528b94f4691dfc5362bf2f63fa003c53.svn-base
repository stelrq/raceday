/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package controller;

import java.awt.event.ActionEvent;

/**
 * A class that defines an Action.
 * The Action can have two different states
 *  with different functionality, text, and icons for each state.
 * 
 * @author sterling
 * @version Winter 2019
 */
public class ToggleRaceAction extends RaceAction {
    /** Handles checkstyle warnings. */
    private static final long serialVersionUID = 1895705945647052298L;
    /** The text to be displayed if this action is toggled. */
    private String myAlternateText;
    /** The name of the alternate image to be displayed for this action. */   
    private String myAlternate;
    /** A boolean to track if this Action is it's default or alternate state. */
    private boolean myDefaultState;
    /** The alternate Action to be executed. */
    private Runnable myAlternateAction;

    /**
     * Constructor for use by child classes.
     * @param theDefault the name of the file of the main image
     * @param theAlternate the name of the file of the alternate image
     * @param theText text for the button in the default state
     * @param theAlternateText text for the button in the alternate state
     * @param theDefaultAction the action for the button in the default state
     * @param theAlternateAction the text for the action in the alternate state
     */
    public ToggleRaceAction(final String theDefault,
                            final String theAlternate,
                            final String theText, final String theAlternateText,
                            final Runnable theDefaultAction,
                            final Runnable theAlternateAction) {
        super(theDefault, theText, theDefaultAction);
        myAlternate = theAlternate;
        myDefaultState = true;
        myAlternateAction = theAlternateAction;
        myAlternateText = theAlternateText;
    }
    /**
     * Executes a different behavior depending on whether the action
     * is in its default or alternate state. Also toggles the button icon
     * and text depending on the state. When a behavior is executed,
     * the state of the action also toggled.
     * @param theEvent the event that triggers this action
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        
        if (myDefaultState) {
            super.actionPerformed(theEvent);
            myDefaultState = false;
            setIcon(myAlternate);
            putValue(NAME, myAlternateText);
        } else {
            myAlternateAction.run();
            myDefaultState = true;
            setIcon(this.getDefault());
            putValue(NAME, this.getText());
        }
    }

}

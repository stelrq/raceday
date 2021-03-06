/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package model;

import java.beans.PropertyChangeListener;

/**
 * Defines behaviors allowing PropertyChangeListeners to be added or removed from a 
 * RaceControls object. Implementing classes should inform PropertyChangeListeners
 * when methods defined in RaceControls mutate the state of the Race. 
 * 
 * Defines a set of Properties that may be listened too. Implementing class may further define
 * more Properties. 
 * 
 * @author Charles Bryan
 * @version Fall 2018
 *
 */
public interface PropertyChangeEnabledRaceControls extends RaceControls {
   
    
    /*
     * Add your own constant Property values here. 
     */
    
    /**
     * A property name for an example. Use this as a template for your own Property values. 
     */
    /** The property name for a telemetry message. */
    String PROPERTY_T_MESSAGE = "the telemetry message";
    /** The property name for a leaderboard message. */
    String PROPERTY_L_MESSAGE = "the leaderboard message";
    /** The property name for a finish message. */
    String PROPERTY_F_MESSAGE = "the finish message";
    /** A property name for moving the current time step in the race. */
    String PROPERTY_TIME = "the time step";
    /** The property name for the header for a race file being successfully loaded. */
    String PROPERTY_HEADER_LOADED = "the header loaded";
    /** The property name for the participants in a race being successfully loaded. */
    String PROPERTY_PARTICIPANTS_LOADED = "the participants loaded";
    /** The property name for a race being fully loaded. */
    String PROPERTY_RACE_LOADED = "the race loaded";
    /** The property name for the race being in play mode. */
    String PROPERTY_PLAYING = "the race playing";
    /** The property name for the race finishing. */
    String PROPERTY_RACE_FINISHED = "the race finished";
    /** The property name for a racer passing another racer. */
    String PROPERTY_RACER_PASSED = "a racer passed another";
    /** The property name for a racer being selected in the view. */
    String PROPERTY_RACER_SELECTED = "a racer being selected in the view";
    /** The property name for a racer being toggled. */
    String PROPERTY_RACER_TOGGLED = "a racer being toggled";
    
    /**
     * Add a PropertyChangeListener to the listener list. The listener is registered for 
     * all properties. The same listener object may be added more than once, and will be 
     * called as many times as it is added. If listener is null, no exception is thrown and 
     * no action is taken.
     * 
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(PropertyChangeListener theListener);
    
    
    /**
     * Add a PropertyChangeListener for a specific property. The listener will be invoked only 
     * when a call on firePropertyChange names that specific property. The same listener object
     * may be added more than once. For each property, the listener will be invoked the number 
     * of times it was added for that property. If propertyName or listener is null, no 
     * exception is thrown and no action is taken.
     * 
     * @param thePropertyName The name of the property to listen on.
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener);

    /**
     * Remove a PropertyChangeListener from the listener list. This removes a 
     * PropertyChangeListener that was registered for all properties. If listener was added 
     * more than once to the same event source, it will be notified one less time after being 
     * removed. If listener is null, or was never added, no exception is thrown and no action 
     * is taken.
     * 
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(PropertyChangeListener theListener);
    
    /**
     * Remove a PropertyChangeListener for a specific property. If listener was added more than
     * once to the same event source for the specified property, it will be notified one less 
     * time after being removed. If propertyName is null, no exception is thrown and no action 
     * is taken. If listener is null, or was never added for the specified property, no 
     * exception is thrown and no action is taken.
     * 
     * @param thePropertyName The name of the property that was listened on.
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(String thePropertyName, 
                                      PropertyChangeListener theListener);
}

/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package controller;
import static controller.RaceController.PADDING;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_HEADER_LOADED;
import static model.PropertyChangeEnabledRaceControls.PROPERTY_TIME;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.JSlider;
import model.FileHeader;
import model.RaceModel;

/**
 * Slider object that moves as the race progresses
 * and allows the user to fast forward through the race.
 * @author stelrq
 * @version Winter 2019
 */
public class RaceSlider extends JSlider implements PropertyChangeListener {
    /** To handle check style. */
    private static final long serialVersionUID = 8216905914123243248L;
    /** Controls minor tick spacing(in milliseconds). */
    private static final int MINOR_TICK_SPACING = 10000;
    /** Controls major tick spacing(in minutes). */
    private static final int MAJOR_TICK_SPACING = 60000;
    /**
     * Builds the slider that controls race time.
     */
    public RaceSlider() {
        super();
        setEnabled(false);
        setBorder(BorderFactory.createEmptyBorder
                         (PADDING, PADDING, PADDING, PADDING));
        setValue(0);
    }
    /**
     */
    /**
     * Accepts a property change from the model. 
     * Enables slider and paints ticks if it is a PROPERTY_FILE_LOADED
     * event. Moves slider to current race time if it is a PROPERTY_TIME event.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (theEvent.getSource() instanceof RaceModel) {
            if (theEvent.getPropertyName().equals(PROPERTY_HEADER_LOADED)) {
                setEnabled(true);
                setMaximum(((FileHeader) theEvent.getNewValue()).getRaceTime());
                setMajorTickSpacing(MAJOR_TICK_SPACING);
                setMinorTickSpacing(MINOR_TICK_SPACING);
                setPaintTicks(true);
            } else if (theEvent.getPropertyName().equals(PROPERTY_TIME)) {
                setValue((Integer) theEvent.getNewValue());
            }
        }

    }

}

/*
* TCSS 305 – Winter 2019
* Assignment 5C – Race Day
*/
package application;

import controller.RaceController;
import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Runs the controller for the race simulation.
 * @author stelrq
 * @version Winter 2019
 *
 */
public final class Main {
    /**
     * To avoid accidental construction.
     */
    private Main() {
        
    }
    /**
     * Initiates the controller with the metal look and feel.
     * @param theArgs command line arguments
     */
    public static void main(final String[] theArgs) {
        
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RaceController();
            }
        });
    }
}

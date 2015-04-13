/*
 * TCSS 305 Winter 2014 Assignment 2 - Shopping Cart
 */

package view;

import java.awt.EventQueue;
import java.math.BigDecimal;
import java.util.Arrays;

import model.Item;

/**
 * ShoppingMain provides the main method for a simple shopping cart GUI
 * displayer and calculator.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman (Formatting and Comments)
 * @author Alan Fowler (Numerous changes including use of BigDecimal)
 * @version Winter 2014
 */

public final class ShoppingMain {

    /**
     * An array of items to be displayed in the shopping cart.
     */
    private static final Item[] ITEMS = new Item[] {
        new Item("Silly Putty", new BigDecimal("4.14"), 8, new BigDecimal("12.48")),
        new Item("Silly String", new BigDecimal("3.80"), 3, new BigDecimal("10.89")),
        new Item("Xbox One", new BigDecimal("429.00")),
        new Item("PlayStation 4", new BigDecimal("496.89")),
        new Item("Arduino Uno Ultimate Starter Kit", new BigDecimal("54.99")),
        new Item("Computer science pen", new BigDecimal("1.20")),
        new Item("Rubik's Cube", new BigDecimal("9.99")),
        new Item("Computer Science Barbie", new BigDecimal("19.01")),
        new Item("'Java Rules!' button", new BigDecimal("0.95"), 10, new BigDecimal("5.00")),
        new Item("'Java Rules!' bumper sticker", new BigDecimal("0.99"), 20,
                                                 new BigDecimal("8.95"))};
    
    // Note: some are actual prices from Amazon.com in Jan 2014

    /**
     * A private constructor, to prevent instances from being constructed.
     */
    private ShoppingMain() {
    }

    /**
     * The main() method - displays and runs the shopping cart GUI.
     * 
     * @param theArgs Command line arguments, ignored by this program.
     */
    public static void main(final String... theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShoppingFrame(Arrays.asList(ITEMS));     
            }
        });
    } // end main()

} // end class ShoppingMain

/*
 * TCSS 305 Winter 2014 Assignment 2 - Shopping Cart
 */

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Item;
import model.ItemOrder;
import model.ShoppingCart;

/**
 * ShoppingFrame provides the user interface for a shopping cart program.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman (Formatting and Comments)
 * @author Alan Fowler (Numerous small changes to code, formatting, and comments)
 * @version Winter 2014
 */
public final class ShoppingFrame extends JFrame {

    /**
     * The Serialization ID.
     */
    private static final long serialVersionUID = 0;
    
    // constants to capture screen dimensions
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /**
     * The width of the text field in the GUI.
     */
    private static final int TEXT_FIELD_WIDTH = 12;

    /**
     * The background color for items in the GUI.
     */
    private static final Color BG_COLOR = new Color(0, 180, 0);

    /**
     * The shopping cart used by this GUI.
     */
    private final ShoppingCart myItems;

    /**
     * The text field used to display the total amount owed by the customer.
     */
    private final JTextField myTotal;

    /**
     * Creates a new ShoppingGUI to sell the specified list of items.
     * 
     * @param theItems The list of items.
     */
    public ShoppingFrame(final List<Item> theItems) {
        // create frame and order list
        super("Holiday Gifts");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myItems = new ShoppingCart();

        // set up text field with order total
        myTotal = new JTextField("$0.00", TEXT_FIELD_WIDTH);
        add(makeTotalPanel(), BorderLayout.NORTH);
        add(makeItemsPanel(theItems), BorderLayout.CENTER);
        add(makeCheckBoxPanel(), BorderLayout.SOUTH);

        // adjust size to just fit
        pack();
        // position the frame in the center of the screen
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                    SCREEN_SIZE.height / 2 - getHeight() / 2);
        setVisible(true);
    }

    /**
     * Creates a panel to hold the total.
     * 
     * @return The created panel.
     */
    private JPanel makeTotalPanel() {
        // tweak the text field so that users can't edit it, and set
        // its color appropriately

        myTotal.setEditable(false);
        myTotal.setEnabled(false);
        myTotal.setDisabledTextColor(Color.BLACK);

        // create the panel, and its label

        final JPanel p = new JPanel();
        p.setBackground(Color.BLUE);
        final JLabel l = new JLabel("order total");
        l.setForeground(Color.WHITE);
        p.add(l);
        p.add(myTotal);
        return p;
    }

    /**
     * Creates a panel to hold the specified list of items.
     * 
     * @param theItems The items.
     * @return The created panel.
     */
    private JPanel makeItemsPanel(final List<Item> theItems) {
        final JPanel p = new JPanel(new GridLayout(theItems.size(), 1));

        for (final Item item : theItems) {
            addItem(item, p);
        }

        return p;
    }

    /**
     * Creates and returns the checkbox panel for our window.
     * 
     * @return the checkbox panel for our window.
     */
    private JPanel makeCheckBoxPanel() {
        final JPanel p = new JPanel();
        p.setBackground(Color.BLUE);
        final JCheckBox cb = new JCheckBox("customer has store membership");
        p.add(cb);
        cb.setForeground(Color.BLACK);
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myItems.setMembership(cb.isSelected());
                updateTotal();
            }
        });
        return p;
    }

    /**
     * Adds the specified product to the specified panel.
     * 
     * @param theItem The product to add.
     * @param thePanel The panel to add the product to.
     */
    private void addItem(final Item theItem, final JPanel thePanel) {
        final JPanel sub = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sub.setBackground(BG_COLOR);
        final JTextField quantity = new JTextField(3);
        quantity.setHorizontalAlignment(SwingConstants.CENTER);
        quantity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                updateItem(theItem, quantity);
                quantity.transferFocus();
            }
        });
        quantity.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(final FocusEvent theEvent) {
                updateItem(theItem, quantity);
            }
        });
        sub.add(quantity);
        final JLabel l = new JLabel(theItem.toString());
        l.setForeground(Color.WHITE);
        sub.add(l);
        thePanel.add(sub);
    }

    /**
     * Updates the set of items by changing the quantity of the specified
     * product to the specified quantity.
     * 
     * @param theItem The product to update.
     * @param theQuantity The new quantity.
     */
    private void updateItem(final Item theItem, final JTextField theQuantity) {
        final String text = theQuantity.getText().trim();
        int number;
        try {
            number = Integer.parseInt(text);
            if (number < 0) {
                // disallow negative numbers
                throw new NumberFormatException();
            }
        } catch (final NumberFormatException e) {
            number = 0;
            theQuantity.setText("");
        }
        myItems.add(new ItemOrder(theItem, number));
        updateTotal();
    }

    /**
     * Updates the total displayed in the window.
     */
    private void updateTotal() {
        final double total = myItems.calculateTotal().doubleValue();
        myTotal.setText(NumberFormat.getCurrencyInstance().format(total));
    }
}

// end of class ShoppingFrame

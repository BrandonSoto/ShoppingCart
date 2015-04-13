/*
 * TCSS 305 - Winter 2014
 * Assignment 2 - Shopping
 */
package model;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Stores information about an order for a particular item. 
 * 
 * @author Brandon Soto
 * @version Jan 16, 2014
 */
public final class ItemOrder {
    
    /**
     * Format string representations of BigDecimals as a currency. 
     */
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance();
    
    /**
     * Item to be ordered. 
     */
    private final Item myItem;
    
    /**
     * Total number of items to be ordered. 
     */
    private final int myQuantity;

    /**
     * Constructs an ItemOrder with the item to be ordered and the number of items to 
     * be ordered. 
     * 
     * @param theItem Item to be ordered. 
     * @param theQuantity Total number of items to be ordered. 
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        myItem = theItem;
        myQuantity = theQuantity;
    }

    /**
     * Return the cost for this item order.  
     * 
     * @return order total. 
     */
    public BigDecimal calculateOrderTotal() {
        return myItem.calculateItemTotal(myQuantity);
    }

    /**
     * Query method for myItem field.
     * 
     * @return myItem field.
     */
    public Item getItem() {
        return myItem;
    }

    /**
     * Represents an ItemOrder as a string. 
     * 
     * @return string representation of an ItemOrder. 
     */
    @Override
    public String toString() {
        return String.format("   Item: %s\n   Order Total: %s", 
                              myItem, CURRENCY_FORMAT.format(calculateOrderTotal()));
    }
}

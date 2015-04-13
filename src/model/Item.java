/*
 * TCSS 305 - Winter 2014
 * Assignment 2 - Shopping
 */

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Objects;

/**
 * Represents an individual item to be bought. 
 * 
 * @author Brandon Soto
 * @version Jan 15, 2014
 */
public final class Item {
    
    /**
     * Format string representations of BigDecimals as a currency. 
     */
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance();
    
    /**
     * Name of the item.
     */
    private final String myName;

    /**
     * Price of an individual item.
     */
    private final BigDecimal myPrice;

    /**
     * Price of the item in bulk. (equals 0 if the item doesn't have a bulk price.)
     */
    private final BigDecimal myBulkPrice;

    /**
     * Number of items in a bulk. (equals 0 if the item doesn't offer bulk quantities.)
     */
    private final int myBulkQuantity;

    /**
     * Constructs an Item with a name and a price.
     * 
     * @param theName name of item.
     * @param thePrice price of item.
     */
    public Item(final String theName, final BigDecimal thePrice) { 
        this(theName, thePrice, 0, BigDecimal.ZERO); 
    }

    /**
     * Constructs an item with a name, a price, a bulk quantity, and a bulk price.
     * 
     * @param theName name of item.
     * @param thePrice price of item.
     * @param theBulkQuantity number of items in bulk.
     * @param theBulkPrice price of item in bulk.
     */
    public Item(final String theName, final BigDecimal thePrice,
        final int theBulkQuantity, final BigDecimal theBulkPrice) {
        
        myName = theName;
        myPrice = thePrice;
        myBulkQuantity = theBulkQuantity;
        myBulkPrice = theBulkPrice;
    }
    
    /**
     * Calculates the total price for a given quantity of an item. 
     * (taking into account bulk price, if applicable.)
     * 
     * @param theQuantity number of items to be bought.
     * @return the total price for a given quantity of the item. 
     */
    public BigDecimal calculateItemTotal(final int theQuantity) {
        BigDecimal total = BigDecimal.ZERO;
        int numOfBulkItems;
        int numOfSingleItems; 

        if (myBulkQuantity > 0) {
            numOfBulkItems = theQuantity / myBulkQuantity;
            numOfSingleItems = theQuantity % myBulkQuantity;
            
            // total = bulk price * bulk number
            total = myBulkPrice.multiply(BigDecimal.valueOf(numOfBulkItems)); 
        } else {
            numOfSingleItems = theQuantity;
        }
        
        // total = total + (individual price * number of individual items)
        total = total.add(myPrice.multiply(BigDecimal.valueOf(numOfSingleItems))); 
        
        return total;
    }

    /**
     * Represents a item object as a string.
     * 
     * @return String representation of an item. 
     */
    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder().append(
                            String.format("%s, %s", myName, CURRENCY_FORMAT.format(myPrice)));
        
        if (myBulkQuantity > 0) {
            result.append(String.format(
                        " (%d for %s)", myBulkQuantity, CURRENCY_FORMAT.format(myBulkPrice)));
        }
        
        return result.toString();
    }

    /**
     * Checks whether two objects represent a similar object. If so, it returns true.
     * Otherwise it returns false. 
     * 
     * @param theOther object to be compared to. 
     * @return true if the objects are the same. Otherwise false. 
     */
    @Override
    public boolean equals(final Object theOther) {
        boolean flag = false; 
        
        if (this == theOther) {
            flag = true;
        } else if (theOther != null && getClass() == theOther.getClass()) {
            final Item other = (Item) theOther; 
            
            flag = Objects.equals(myName, other.myName)
                    && Objects.equals(myPrice, other.myPrice)
                    && Objects.equals(myBulkPrice, other.myBulkPrice)
                    && myBulkQuantity == other.myBulkQuantity;
        }
        
        return flag; 
    }
    
    /**
     * Generates a hash code value for a given item.
     * 
     * @return hash code for this item.  
     */
    @Override
    public int hashCode() {
        return Objects.hash(myName, myPrice, myBulkPrice, myBulkQuantity);
    }
}

/*
 * TCSS 305 - Winter 2014
 * Assignment 2 - Shopping
 */
package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores information about the customer's overall purchase. 
 * 
 * @author Brandon Soto
 * @version Jan 16, 2014
 */
public class ShoppingCart {
    
    /**
     * Format string representations of BigDecimals as a currency. 
     */
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance();
    
    /**
     * Helps to make a ShoppingCart's string representation easier to understand. 
     */
    private static final String DIVIDER_LINE = "---------------------------";

    /**
     * Represents a shopping cart. Has an Item as a key and an ItemOrder as a value.
     */
    private final Map<Item, ItemOrder> myShoppingCart;
    
    /**
     * Store membership status. (true - customer has membership, false otherwise.)
     */
    private boolean myMembership; 
    
    /**
     * Constructor that initializes the myItemOrders list and sets membership to 
     * false. 
     */
    public ShoppingCart() {
        myShoppingCart = new HashMap<Item, ItemOrder>();
        myMembership = false;
    }

    /**
     * Adds an item order to the shopping cart. If the item already exists in the 
     * shopping cart, it is overwritten with the new item order. 
     * 
     * @param theOrder Order to be added to shopping cart. 
     */
    public void add(final ItemOrder theOrder) {
        myShoppingCart.put(theOrder.getItem(), theOrder);
    }

    /**
     * Sets the membership status of the customer.
     * 
     * @param theMembership Membership status of the customer. 
     */
    public void setMembership(final boolean theMembership) {
        myMembership = theMembership; 
    }

    /**
     * Calculates the total cost of this shopping cart.  
     * 
     * @return Total cost of shopping cart. 
     */
    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (final Item key : myShoppingCart.keySet()) {
            total = total.add(myShoppingCart.get(key).calculateOrderTotal());
        }
        
        if (myMembership) {
            total = applyDiscount(total);
        }
        
        return total;
    }
    
    /**
     * Returns new total with membership discount applied. 
     * 
     * @param theOldTotal the original shopping cart total. 
     * @return the new total with membership discount applied. 
     */
    private BigDecimal applyDiscount(final BigDecimal theOldTotal) {
        BigDecimal discount = BigDecimal.ZERO; 
        
        if (theOldTotal.compareTo(new BigDecimal("20.00")) > 0) { // if theTotal > 20
            discount = theOldTotal.multiply(new BigDecimal("0.10"));
        }
        
        return theOldTotal.subtract(discount); 
    }

    /**
     * Represents a ShoppingCart object as a string. 
     * 
     * @return string representation of ShopingCart object. 
     */
    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder("Your Shopping Cart:     ");
        
        for (final Item key : myShoppingCart.keySet()) {
            result.append(String.format("\n%s\n%s", 
                                             DIVIDER_LINE, myShoppingCart.get(key)));
        }
        result.append(String.format("\n%s\nItems Purchased: %d\nYour Grand Total: ", 
                                               DIVIDER_LINE, myShoppingCart.size()));
        result.append(CURRENCY_FORMAT.format(calculateTotal()));
        
        return result.toString();
    }
}
